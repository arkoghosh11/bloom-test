/**
 * @author Bloom This Class UserPayload.java is for Created at Aug 28, 2012 4:19:39 PM
 */
package com.mana.innovative.dto.consumer.payload;


import com.mana.innovative.dto.consumer.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;


/**
 * @author Bloom
 */
public class UserPayload {

    private List< User > users;

    /**
     * @return the user
     */
    public List< User > getUsers( ) {
        return users;
    }

    /**
     * @param users The user to set
     */
    @XmlElement( name = "user" )
    @XmlElementWrapper( name = "users", nillable = false, required = true )
    public void setUsers( List< User > users ) {
        this.users = users;
    }

}
