package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by Bloom/Rono on 5/14/2015 6:37 PM. This class is Privilege
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "privilege", namespace = "http://localhost:8080/Bloom/rest/user" )
public class Privilege {

    /**
     * The Privilege id.
     */
    private Integer privilegeId;
    /**
     * The Privilege name.
     */
    private String privilegeName;
    /**
     * The Accessible.
     */
    private boolean accessible;


    /**
     * Gets privilege id.
     *
     * @return the privilege id
     */
    @XmlElement( name = "privilege_id" )
    public Integer getPrivilegeId( ) {
        return privilegeId;
    }

    /**
     * Sets privilege id.
     *
     * @param privilegeId the privilege id
     */
    public void setPrivilegeId( final Integer privilegeId ) {
        this.privilegeId = privilegeId;
    }

    /**
     * Gets privilege name.
     *
     * @return the privilege name
     */
    @XmlElement( name = "privilege_name" )
    public String getPrivilegeName( ) {
        return privilegeName;
    }

    /**
     * Sets privilege name.
     *
     * @param privilegeName the privilege name
     */
    public void setPrivilegeName( final String privilegeName ) {
        this.privilegeName = privilegeName;
    }

    /**
     * Is accessible.
     *
     * @return the boolean
     */
    @XmlElement( name = "is_accessible" )
    public boolean isAccessible( ) {
        return accessible;
    }

    /**
     * Sets accessible.
     *
     * @param accessible the accessible
     */
    public void setAccessible( final boolean accessible ) {
        this.accessible = accessible;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Privilege ) ) return false;
        Privilege privilege = ( Privilege ) o;
        return Objects.equals( getPrivilegeId( ), privilege.getPrivilegeId( ) ) &&
                Objects.equals( getPrivilegeName( ), privilege.getPrivilegeName( ) ) &&
                Objects.equals( isAccessible( ), privilege.isAccessible( ) );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Privilege {" +
                " privilegeId=" + privilegeId +
                ", privilegeName= " + privilegeName +
                ", accessible=" + accessible +
                '}';
    }
}
