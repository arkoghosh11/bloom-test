package com.mana.innovative.scheduler.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.common.email.CustomEvent;
import com.mana.innovative.dto.adapter.DateFormatAdapter;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.dto.common.email.EmailContents;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.email.BloomEmailService;
import com.mana.innovative.reader.CustomSpecificFileReader;
import com.mana.innovative.scheduler.CustomScheduler;
import com.mana.innovative.scheduler.service.CustomDatabaseService;
import com.mana.innovative.utilities.response.ItemDomainDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The type Custom scheduler.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class CustomSchedulerImpl implements CustomScheduler {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomSchedulerImpl.class );
    private static final int SEVEN = 7;
    private static final int SPECIFIC_MINUTE = 10;


//    @Resource
//    private JobLauncher jobScheduler;
    /**
     * The Item dAO.
     */

    @Resource
    private CustomSpecificFileReader< ItemsPayload > customSpecificFileReader;

    @Resource
    private CustomDatabaseService< Object, Date > customDatabaseService;

    @Resource
    private BloomEmailService bloomEmailService;

    /**
     * The Context path. //todo something for the future to main a xml package structure
     */
    @Value( value = "${bloom-non-client-service.xml_java_context_path}" )
    private String contextPath;
    /**
     * The Is load item.
     */
    @Value( value = "${bloom-non-client-service.is_load_item}" )
    private boolean isLoadItem;
    /**
     * The Csv file to read.
     */
    @Value( value = "${bloom-non-client-service.csv_file_to_read}" )
    private String csvFileToRead;
    /**
     * The Xml file to read.
     */
    @Value( value = "${bloom-non-client-service.xml_file_to_read}" )
    private String xmlFileToRead;

    /**
     * Check for file.
     */
    public void checkForFile( ) {
        logger.info( "Checking for file with filename " );
        System.out.println( "Checking for file with filename" );
        //todo need to complete this method
    }

    /**
     * This method is a scheduler with a cron job Below comments taken from From http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
     * <p/>
     * The pattern is a list of six single space-separated fields: representing second, minute, hour, day, month,
     * weekday. Month and weekday names can be given as the first three letters of the English names.
     * <p/>
     * Example patterns:
     * <p/>
     * "0 0 * * * *" = the top of every hour of every day. "*\/10 * * * * *" = every ten seconds. "0 0 8-10 * * *" = 8,
     * 9 and 10 o'clock of every day. "0 0\/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day. "0 0 9-17
     * * * MON-FRI" = on the hour nine-to-five weekdays "0 0 0 25 12 ?" = every Christmas Day at midnight. Load items
     * file.
     */
    @Override
    @Scheduled( cron = "${bloom-non-client-service.cron_job_item_value}" )
    public void loadItemsFile( ) {

        File file = new File( csvFileToRead );
        if ( !isLoadItem ) {
            logger.warn( "leaving " + this.getClass( ).getCanonicalName( ) + "#loadItemsFile() due to -ve flag" );
            return;
        }
        boolean flagCsv = true;
        try {
            if ( !file.canRead( ) ) {
                file = new File( xmlFileToRead );
                flagCsv = false;
            }
            if ( !file.canRead( ) ) {
                throw new FileNotFoundException( );
            }
        } catch ( FileNotFoundException exception ) {
            logger.error( "Failed to find file with name " + csvFileToRead + " or with name " + xmlFileToRead );
        }

        if ( flagCsv ) {
            this.readFromCSVNSave( file );
        } else {
            this.readFromXMLNSave( file );
        }
    }

    @Override
    @Scheduled( cron = "${bloom-non-client-service.cron_job__email_event_value}" )
    public void getEventsNEmail( ) {

        List< CustomEvent > customEventList = this.readEventsFromDB( );
        for ( CustomEvent customEvent : customEventList ) {
            boolean runNow = this.isEventRunnable( customEvent );
            EmailContents emailContents = new EmailContents( );
            emailContents.setReceiver( customEvent.getReceivers( ) );
            emailContents.setCcReceiver( customEvent.getCcReceivers( ) );
            emailContents.setBccReceiver( customEvent.getBccReceivers( ) );
            emailContents.setSubject( customEvent.getSubject( ) );
            emailContents.setBody( customEvent.getBody( ) );

            if ( runNow && customEvent.isAttachment( ) ) {
                bloomEmailService.sendMail( emailContents );
            }
            if ( runNow && !customEvent.isAttachment( ) ) {

                emailContents.setHasAttachment( customEvent.isAttachment( ) );
                emailContents.setAttachmentLocation( customEvent.getAttachmentLocation( ) );
                bloomEmailService.sendMail( emailContents );
            }
        }
    }

    /**
     * Is event runnable.
     *
     * @param customEvent the custom event
     *
     * @return the boolean
     */
    private boolean isEventRunnable( final CustomEvent customEvent ) {

        String location = this.getClass( ).getCanonicalName( ) + "#isEventRunnable()";
        logger.debug( "Starting " + location );

        Calendar eventDate = Calendar.getInstance( );
        eventDate.setTime( customEvent.getEventDate( ) );
        Calendar todayDate = Calendar.getInstance( );

        String eventTime = customEvent.getTimeOfEvent( );
        if ( eventDate.get( Calendar.MONTH ) == todayDate.get( Calendar.MONTH ) &&
                ( eventDate.get( Calendar.DAY_OF_MONTH ) == todayDate.get( Calendar.DAY_OF_MONTH ) ||
                        eventDate.get( Calendar.DAY_OF_MONTH ) == ( todayDate.get( Calendar.MONTH ) + SEVEN ) ) ) {

            String times[] = eventTime.split( ":" );
            int eventTimeMinute = Integer.parseInt( times[ 1 ] );
            int currentMinute = todayDate.get( Calendar.MINUTE );
            if ( times[ 0 ].equalsIgnoreCase( "" + todayDate.get( Calendar.HOUR_OF_DAY ) ) &&
                    currentMinute < SPECIFIC_MINUTE ) {
                logger.info( "Found an event ", customEvent );
                return true;
            }
        }
        logger.debug( "Finishing " + location );
        return false;
    }

    /**
     * Read from cSVN save.
     *
     * @param file the file
     *
     * @return the boolean
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public boolean readFromCSVNSave( File file ) {

        String location = this.getClass( ).getCanonicalName( ) + "#readFromCSVNSave()";
        logger.debug( "Starting " + location );
        List< Item > domainItems;
        try {
            ItemsPayload itemsPayload = customSpecificFileReader.readExcelFileReader( file );

            domainItems = ItemDomainDTOConverter
                    .getConvertedListDomainFromDTO( itemsPayload.getItems( ) );

            for ( Item item : domainItems ) {
                customDatabaseService.createData( item );
            }
            return false;
        } catch ( Exception exception ) {
            logger.error( "An exception occurred while reading file and storing into database", exception );
            return false;
        } finally {
            logger.debug( "Finishing " + location );
        }
    }

    /**
     * Read from xMLN save.
     *
     * @param file the file
     *
     * @return the boolean
     */
    public boolean readFromXMLNSave( File file ) {

        String location = this.getClass( ).getCanonicalName( ) + "#readFromXMLNSave()";
        logger.debug( "Starting " + location );
        List< Item > domainItems;
        try {

            ItemsPayload itemsPayload = customSpecificFileReader.readXMLFile( file );
            domainItems = ItemDomainDTOConverter
                    .getConvertedListDomainFromDTO( itemsPayload.getItems( ) );
            // Note create and save each item in database
            if ( isLoadItem ) {
                for ( Item domainItem : domainItems ) {
                    customDatabaseService.createData( domainItem );
                }
            }
            return true;
        } catch ( Exception exception ) {
            logger.error( "An exception occurred while reading file and storing into database", exception );
            return false;
        } finally {
            logger.debug( "Finishing " + location );
        }
    }

    @SuppressWarnings( "unchecked" )
    public List< CustomEvent > readEventsFromDB( ) {

        String location = this.getClass( ).getCanonicalName( ) + "#readEventsFromDB()";
        logger.debug( "Starting " + location );
        Date date = Calendar.getInstance( ).getTime( );
        RequestParams requestParams = new RequestParams( );

        requestParams.setIsError( Boolean.TRUE );
        DAOResponse< CustomEvent > customEventDAOResponse = ( DAOResponse< CustomEvent > ) customDatabaseService.readData( date );
        if ( customEventDAOResponse.getCount( ) < DAOConstants.ONE ) {
            logger.warn( "No events for date " + date.toString( ) );
        }

        logger.debug( "Finishing " + location );
        return customEventDAOResponse.getResults( );
    }

    /**
     * Write from xMLN save.
     *
     * @param file the file
     *
     * @return the boolean
     */
    public boolean writeFromXMLNSave( ) {

        String location = this.getClass( ).getCanonicalName( ) + "#writeFromXMLNSave()";
        logger.debug( "Starting " + location );
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( ItemsPayload.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller( );
            jaxbMarshaller.setAdapter( new DateFormatAdapter( ) );

            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//            items = ItemDomainDTOConverter
//                    .getConvertedListDomainFromDTO( );
            ItemsPayload itemsPayload = new ItemsPayload( );
            itemsPayload.setTotalCount( 1 );
            List< com.mana.innovative.dto.client.Item > dtoItems = new ArrayList<>( );
            itemsPayload.setItems( dtoItems );
            com.mana.innovative.dto.client.Item dtoItem = new com.mana.innovative.dto.client.Item( );
            dtoItems.add( dtoItem );

            dtoItem.setItemId( ( long ) TestConstants.MINUS_ONE );
            dtoItem.setItemName( TestConstants.TEST_VALUE );
            dtoItem.setItemPriceCurrency( TestConstants.TEST_PRICE_CURRENCY );
            dtoItem.setItemType( TestConstants.TEST_VALUE );
            dtoItem.setItemSubType( TestConstants.TEST_ITEM_TYPE );
            dtoItem.setBoughtFrom( TestConstants.TEST_BROUGHT_FROM );

            dtoItem.setItemPrice( ( double ) TestConstants.THREE );
            dtoItem.setWeight( TestConstants.TEST_WEIGHT );
            dtoItem.setQuantity( TestConstants.TEST_QUANTITY );

            dtoItem.setQuantityType( QuantityType.UNIT.toString( ) );
            dtoItem.setWeightedUnit( WeightedUnit.POUND.toString( ) );

            dtoItem.setBoughtDate( new Date( ) );

            jaxbMarshaller.marshal( itemsPayload, System.out );
            // Note create and save each item in database
//            if ( itemDAO != null ) {
//                for ( Item item : items ) {
//                    itemDAO.createItem( item, false );
//                }
//            }
            return true;
        } catch ( JAXBException exception ) {
            exception.printStackTrace( );
            logger.error( "An exception occurred while unmarshalling", exception );
            return false;
        } finally {
            logger.debug( "Finishing " + location );
        }
    }
}
