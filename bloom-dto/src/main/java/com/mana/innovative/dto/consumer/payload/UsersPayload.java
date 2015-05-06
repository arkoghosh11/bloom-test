/**
 * @author Bloom This Class UserPayload.java is for Created at Aug 28, 2012 4:19:39 PM
 */
package com.mana.innovative.dto.consumer.payload;


import com.mana.innovative.dto.consumer.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;


/**
 * The type Users payload.

 * Created by Bloom/Rono on $date $time.
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class UsersPayload {

    /**
     * The Users.
     */
    private List< User > users;
    /**
     * The Total count.
     */
    private int totalCount;

    /**
     * Gets users.
     *
     * @return the user
     */
    public List< User > getUsers( ) {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users The user to set
     */
    @XmlElement( name = "user" )
    @XmlElementWrapper( name = "users", nillable = false, required = true )
    public void setUsers( List< User > users ) {
        this.users = users;
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
