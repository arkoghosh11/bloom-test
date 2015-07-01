package com.mana.innovative.service.client.container;

import com.mana.innovative.dto.client.payload.ShopsPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type Shop response container.
 *
 * @param <T>   the type parameter
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8080/bloom-test/rest/" )
@XmlSeeAlso( value = { ShopsPayload.class, ResponseContainer.class } )
public class ShopResponseContainer < T > extends ResponseContainer< T > {
}
