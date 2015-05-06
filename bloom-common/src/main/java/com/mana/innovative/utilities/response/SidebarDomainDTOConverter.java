package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.common.SidebarType;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is SidebarDomainDTOConverter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class SidebarDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( SidebarDomainDTOConverter.class );

    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;
    /**
     * The constant EMPTY_STRING.
     */
    private static final String EMPTY_STRING = DAOConstants.EMPTY_STRING;

    /**
     * Gets converted sidebarType dTO from sidebarType domain.
     *
     * @param sidebarTypeDTO  the sidebarType dTO
     * @param sidebarTypeDomain  the sidebarType domain
     * @return the converted sidebarType dTO from sidebarType domain
     */
    public static SidebarType getConvertedDTOFromDomain( SidebarType sidebarTypeDTO, com.mana.innovative.domain.common.SidebarType sidebarTypeDomain ) {

        if ( sidebarTypeDomain == null ) {
            String message = "Parameter sidebarTypeDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( sidebarTypeDTO == null ) {
            sidebarTypeDTO = new SidebarType( );
            logger.warn( " Creating sidebarTypeDTO, received null object" );
        }
        if ( sidebarTypeDomain.getSidebarTypeId( ) >= ZERO ) {
            sidebarTypeDTO.setSidebarTypeId( sidebarTypeDomain.getSidebarTypeId( ) );
        }
        sidebarTypeDTO.setGroupType( sidebarTypeDomain.getGroupType( ) );
        sidebarTypeDTO.setGroupPriority( sidebarTypeDomain.getGroupPriority( ) );
        sidebarTypeDTO.setGroupCount( sidebarTypeDomain.getGroupCount( ) );

        sidebarTypeDTO.setTypePriority( sidebarTypeDomain.getTypePriority( ) );

        sidebarTypeDTO.setItemType( sidebarTypeDomain.getItemType( ) );
        sidebarTypeDTO.setSubject( sidebarTypeDomain.getSubject( ) );

        sidebarTypeDTO.setSuccess( sidebarTypeDomain.isSuccess( ) );

        sidebarTypeDTO.setDueDate( sidebarTypeDomain.getDueDate( ) );
//            sidebarType.setShopSidebarType();
        return sidebarTypeDTO;
    }

    /**
     * Gets converted sidebarType dTO list.
     *
     * @param sidebarTypeDomainList the sidebarType domain list
     * @return the converted sidebarType dTO list
     */
    public static List< SidebarType > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.common.SidebarType > sidebarTypeDomainList ) {

        List< SidebarType > sidebarTypeDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.common.SidebarType sidebarType : sidebarTypeDomainList ) {

            SidebarType sidebarTypeDTO = new SidebarType( );
            sidebarTypeDTO = getConvertedDTOFromDomain( sidebarTypeDTO, sidebarType );
            sidebarTypeDTOList.add( sidebarTypeDTO );
        }
        return sidebarTypeDTOList;
    }

    /**
     * Gets converted sidebarType domain from sidebarType dTO.
     *
     * @param sidebarTypeDomain the sidebarType domain
     * @param sidebarTypeDTO the sidebarType dTO
     * @return the converted sidebarType domain from sidebarType dTO
     */
    public static com.mana.innovative.domain.common.SidebarType getConvertedDomainFromDTO( com.mana.innovative.domain.common.SidebarType sidebarTypeDomain, SidebarType sidebarTypeDTO ) {

        if ( sidebarTypeDTO == null ) {
            String message = "Parameter sidebarTypeDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( sidebarTypeDomain == null ) {
            sidebarTypeDomain = new com.mana.innovative.domain.common.SidebarType( );
            logger.warn( "Creating sidebarTypeDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        sidebarTypeDomain.setGroupType( sidebarTypeDTO.getGroupType( ) );
        sidebarTypeDomain.setGroupPriority( sidebarTypeDTO.getGroupPriority( ) );
        sidebarTypeDomain.setGroupCount( sidebarTypeDTO.getGroupCount( ) );

        sidebarTypeDomain.setTypePriority( sidebarTypeDTO.getTypePriority( ) );

        if ( !StringUtils.isEmpty( sidebarTypeDTO.getItemType( ) ) ) {
            sidebarTypeDomain.setItemType( sidebarTypeDTO.getItemType( ) );
        } else sidebarTypeDomain.setItemType( EMPTY_STRING );

        if ( !StringUtils.isEmpty( sidebarTypeDTO.getSubject( ) ) ) {
            sidebarTypeDomain.setSubject( sidebarTypeDTO.getSubject( ) );
        } else {
            flag = true;
            stringBuilder.append( "Subject, " );
        }

        sidebarTypeDomain.setSuccess( sidebarTypeDTO.isSuccess( ) );

        if ( sidebarTypeDTO.getDueDate( ) != null ) {
            sidebarTypeDomain.setDueDate( sidebarTypeDTO.getDueDate( ) );
        } else {
            flag = true;
            stringBuilder.append( "DueDate." );
        }

        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return sidebarTypeDomain;
    }

    /**
     * Gets converted sidebarType domain list from sidebarType dTO list.
     *
     * @param sidebarTypeDTOList the sidebarType dTO list
     * @return the converted sidebarType domain list from sidebarType dTO list
     */
    public static List< com.mana.innovative.domain.common.SidebarType > getConvertedListDomainFromDTO( List< SidebarType > sidebarTypeDTOList ) {

        List< com.mana.innovative.domain.common.SidebarType > sidebarTypeDomainList = new ArrayList<>( );
        for ( SidebarType sidebarTypeDTO : sidebarTypeDTOList ) {
            com.mana.innovative.domain.common.SidebarType sidebarTypeDomain = new com.mana.innovative.domain.common.SidebarType( );
            sidebarTypeDomain = getConvertedDomainFromDTO( sidebarTypeDomain, sidebarTypeDTO );
            sidebarTypeDomainList.add( sidebarTypeDomain );
        }
        return sidebarTypeDomainList;
    }


}
