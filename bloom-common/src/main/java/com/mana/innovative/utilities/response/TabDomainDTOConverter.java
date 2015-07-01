package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is TabDomainDTOConverter
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class TabDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( TabDomainDTOConverter.class );
    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;
    /**
     * The constant EMPTY_STRING.
     */
    private static final String EMPTY_STRING = DAOConstants.EMPTY_STRING;

    /**
     * Gets converted tab dTO from tab domain.
     *
     * @param tabDTO the tab dTO
     * @param tabDomain the tab domain
     * @return the converted tab dTO from tab domain
     */
    public static Tab getConvertedDTOFromDomain( Tab tabDTO, com.mana.innovative.domain.common.Tab tabDomain ) {

        if ( tabDomain == null ) {
            String message = "Parameter tabDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( tabDTO == null ) {
            tabDTO = new Tab( );
            logger.warn( " Creating tabDTO, received null object" );
        }
        if ( tabDomain.getTabId( ) >= ZERO ) {
            tabDTO.setTabId( tabDomain.getTabId( ) );
        }
        tabDTO.setTabName( tabDomain.getTabName( ) );
        tabDTO.setTabContent( tabDomain.getTabContent( ) );
//            tab.setShopTab();
        return tabDTO;
    }

    /**
     * Gets converted tab dTO list.
     *
     * @param tabDomainList the tab domain list
     * @return the converted tab dTO list
     */
    public static List< Tab > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.common.Tab > tabDomainList ) {

        List< Tab > tabDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.common.Tab tab : tabDomainList ) {

            Tab tabDTO = new Tab( );
            tabDTO = getConvertedDTOFromDomain( tabDTO, tab );
            tabDTOList.add( tabDTO );
        }
        return tabDTOList;
    }

    /**
     * Gets converted tab domain from tab dTO.
     *
     * @param tabDomain the tab domain
     * @param tabDTO the tab dTO
     * @return the converted tab domain from tab dTO
     */
    public static com.mana.innovative.domain.common.Tab getConvertedDomainFromDTO( com.mana.innovative.domain.common.Tab tabDomain, Tab tabDTO ) {

        if ( tabDTO == null ) {
            String message = "Parameter tabDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( tabDomain == null ) {
            tabDomain = new com.mana.innovative.domain.common.Tab( );
            logger.warn( "Creating tabDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( tabDTO.getTabName( ) ) ) {
            tabDomain.setTabName( tabDTO.getTabName( ) );
        } else {
            flag = true;
            stringBuilder.append( " TabName," );
        }
        if ( !StringUtils.isEmpty( tabDTO.getTabContent( ) ) ) {
            tabDomain.setTabContent( tabDTO.getTabContent( ) );
        } else tabDomain.setTabContent( EMPTY_STRING );
//            tab.setShopTab();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return tabDomain;
    }

    /**
     * Gets converted tab domain list from tab dTO list.
     *
     * @param tabDTOList the tab dTO list
     * @return the converted tab domain list from tab dTO list
     */
    public static List< com.mana.innovative.domain.common.Tab > getConvertedListDomainFromDTO( List< Tab > tabDTOList ) {

        List< com.mana.innovative.domain.common.Tab > tabDomainList = new ArrayList<>( );
        for ( Tab tabDTO : tabDTOList ) {
            com.mana.innovative.domain.common.Tab tabDomain = new com.mana.innovative.domain.common.Tab( );
            tabDomain = getConvertedDomainFromDTO( tabDomain, tabDTO );
            tabDomainList.add( tabDomain );
        }
        return tabDomainList;
    }

}
