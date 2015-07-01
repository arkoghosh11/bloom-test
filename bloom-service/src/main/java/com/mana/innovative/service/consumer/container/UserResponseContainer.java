/**
 * @author Bloom This Class UserResponseContainer.java is for Created at Aug 28, 2012 4:16:59 PM
 */
package com.mana.innovative.service.consumer.container;

import com.mana.innovative.dto.consumer.payload.UsersPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type User response container.
 *
 * @param <T>   the type parameter
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8080/rest/DoctorRest/user" )
@XmlSeeAlso( { UsersPayload.class, ResponseContainer.class } )
public class UserResponseContainer < T > extends ResponseContainer< T > {

}
