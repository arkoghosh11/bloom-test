package com.mana.innovative.scheduler;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dao.ItemDAO;
import com.mana.innovative.domain.Item;
import com.mana.innovative.dto.adapter.DateFormatAdapter;
import com.mana.innovative.dto.payload.ItemsPayload;
import com.mana.innovative.utilities.response.ItemDomainDTOConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Custom scheduler.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
public class CustomScheduler {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( CustomScheduler.class );

    /**
     * The Item dAO.
     */
//    @Resource
//    private JobLauncher jobScheduler;
//    @Resource
    private ItemDAO itemDAO;

    /**
     * The Context path.
     */
    @Value( value = "${xml_java_context_path}" )
    private String contextPath;
    /**
     * The Is load item.
     */
    @Value( value = "${is_load_item}" )
    private boolean isLoadItem;

    /**
     * The Csv file to read.
     */
    @Value( value = "${csv_file_to_read}" )
    private String csvFileToRead;
    /**
     * The Xml file to read.
     */
    @Value( value = "${xml_file_to_read}" )
    private String xmlFileToRead;

    /**
     * The Bloom email service.
     */
//    @Resource
//    private BloomEmailService bloomEmailService;

    /**
     * Check for file.
     */
    @Scheduled( fixedDelay = 5000 )
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
    @Scheduled( cron = "${cron_job_item_value}" )
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

    /**
     * Read from cSVN save.
     *
     * @param file the file
     *
     * @return the boolean
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public boolean readFromCSVNSave( File file ) {

        List< Item > items;
        try {
            BufferedReader bufferedFileReader = new BufferedReader( new FileReader( file ) );
            String temp = "";
            items = new ArrayList<>( );
            while ( ( temp = bufferedFileReader.readLine( ) ) != null ) {
                String stringItems[] = temp.split( "," );
                Item item = this.getItemFromItemStringArray( stringItems );
                items.add( item );
            }
            if ( itemDAO != null ) {
                for ( Item item : items ) {
                    itemDAO.createItem( item, false );
                }
            }
            return true;
        } catch ( FileNotFoundException e ) {
            logger.error( "Failed to open file " + file.getName( ), e );
            return false;
        } catch ( IOException e ) {
            logger.error( "Failed to read file " + file.getName( ), e );
            return false;
        }
    }

    /**
     * Read from xMLN save.
     *
     * @param file the file
     *
     * @return the boolean
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public boolean readFromXMLNSave( File file ) {
        List< Item > domainItems;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( ItemsPayload.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller( );
//            jaxbUnmarshaller.setAdapter( new DateFormatAdapter( ) );
//            jaxbUnmarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            ItemsPayload itemsPayload = ( ItemsPayload ) jaxbUnmarshaller.unmarshal( file );
            System.out.println( itemsPayload.getItems( ).size( ) );
            domainItems = ItemDomainDTOConverter
                    .getConvertedListDomainFromDTO( itemsPayload.getItems( ) );
            System.out.println( domainItems );
            // Note create and save each item in database
            if ( itemDAO != null ) {
                for ( Item domainItem : domainItems ) {
                    itemDAO.createItem( domainItem, false );
                }
            }
            return true;
        } catch ( JAXBException exception ) {
            exception.printStackTrace( );
            logger.error( "An exception occurred while unmarshalling", exception );
            return false;
        }
    }

    /**
     * Write from xMLN save.
     *
     * @param file the file
     *
     * @return the boolean
     */
    public boolean writeFromXMLNSave( File file ) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( ItemsPayload.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller( );
            jaxbMarshaller.setAdapter( new DateFormatAdapter( ) );
//            jaxbUnmarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//            items = ItemDomainDTOConverter
//                    .getConvertedListDomainFromDTO( );
            ItemsPayload itemsPayload = new ItemsPayload( );
            itemsPayload.setTotalCount( 1 );
            List< com.mana.innovative.dto.Item > dtoItems = new ArrayList<>( );
            itemsPayload.setItems( dtoItems );
            com.mana.innovative.dto.Item dtoItem = new com.mana.innovative.dto.Item( );
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

            jaxbMarshaller.marshal( itemsPayload, file );
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
        }
    }

    /**
     * Gets item from item string array.
     *
     * @param stringItems the string items
     *
     * @return the item from item string array
     */
    private Item getItemFromItemStringArray( final String[] stringItems ) {
        logger.debug( "Starting " + this.getClass( ).getCanonicalName( ) + "#getItemFromItemStringArray" );
        // todo implement this method
        logger.debug( "Finishing " + this.getClass( ).getCanonicalName( ) + "#getItemFromItemStringArray" );
        return null;
    }
}
