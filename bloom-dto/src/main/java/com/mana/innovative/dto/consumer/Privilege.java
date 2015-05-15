package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by Bloom/Rono on 5/14/2015 6:37 PM. This class is Privilege
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "privilege", namespace = "http://localhost:8080/Bloom/rest/user" )
public class Privilege {

    private Integer privilegeId;
    private String privilegeName;
    private boolean accessible;


    @XmlElement( name = "privilege_id" )
    public Integer getPrivilegeId( ) {
        return privilegeId;
    }

    public void setPrivilegeId( final Integer privilegeId ) {
        this.privilegeId = privilegeId;
    }

    @XmlElement( name = "privilege_name" )
    public String getPrivilegeName( ) {
        return privilegeName;
    }

    public void setPrivilegeName( final String privilegeName ) {
        this.privilegeName = privilegeName;
    }

    @XmlElement( name = "is_accessible" )
    public boolean isAccessible( ) {
        return accessible;
    }

    public void setAccessible( final boolean accessible ) {
        this.accessible = accessible;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Privilege ) ) return false;
        Privilege privilege = ( Privilege ) o;
        return Objects.equals( getPrivilegeId( ), privilege.getPrivilegeId( ) ) &&
                Objects.equals( getPrivilegeName( ), privilege.getPrivilegeName( ) ) &&
                Objects.equals( isAccessible( ), privilege.isAccessible( ) );
    }


    @Override
    public String toString( ) {
        return "Privilege {" +
                " privilegeId=" + privilegeId +
                ", privilegeName= " + privilegeName +
                ", accessible=" + accessible +
                '}';
    }
}
