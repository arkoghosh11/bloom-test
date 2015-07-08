package com.mana.innovative.service.consumer.container;

import com.mana.innovative.dto.consumer.payload.UserRolesPayload;
import com.mana.innovative.service.container.ResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by Bloom/Rono on 7/8/2015 2:33 AM.
 * This class is UserRoleResponseContainer
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8080/rest/DoctorRest/userRole" )
@XmlSeeAlso( { UserRolesPayload.class, ResponseContainer.class } )
public class UserRoleResponseContainer < T > extends ResponseContainer< T > {

    private static final Logger logger = LoggerFactory.getLogger( UserRoleResponseContainer.class );

}
