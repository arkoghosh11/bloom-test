package com.mana.innovative.dto.consumer.payload;

import com.mana.innovative.dto.consumer.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by Bloom/Rono on 7/8/2015 2:34 AM.
 * This class is UserRolePayload
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class UserRolesPayload {

    private static final Logger logger = LoggerFactory.getLogger( UserRolesPayload.class );

    /**
     * The Users.
     */
    private List< UserRole > userRoles;
    /**
     * The Total count.
     */
    private int totalCount;

    /**
     * Gets users.
     *
     * @return the user
     */
    public List< UserRole > getUserRoles( ) {
        return userRoles;
    }

    /**
     * Sets users.
     *
     * @param users The user to set
     */
    @XmlElement( name = "userRole" )
    @XmlElementWrapper( name = "userRoles", nillable = false, required = true )
    public void setUserRoles( List< UserRole > users ) {
        this.userRoles = users;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public int getTotalCount( ) {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    @XmlElement( name = "total_count", defaultValue = "0", nillable = false )
    public void setTotalCount( final int totalCount ) {
        this.totalCount = totalCount;
    }

}
