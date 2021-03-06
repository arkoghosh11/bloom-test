package com.mana.innovative.service.consumer.container;

import com.mana.innovative.dto.consumer.payload.CustomersPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type Customer response container.
 *
 * @param <T>   the type parameter
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8080/rest/Bloom/customer" )
@XmlSeeAlso( { CustomersPayload.class, ResponseContainer.class } )
public class CustomerResponseContainer < T > extends ResponseContainer< T > {

}
