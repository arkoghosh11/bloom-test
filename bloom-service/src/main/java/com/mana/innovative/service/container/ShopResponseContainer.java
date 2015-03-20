package com.mana.innovative.service.container;

import com.mana.innovative.dto.payload.ShopsPayload;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type Shop response container.
 *
 * @param <T> the type parameter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8080/bloom-test/rest/" )
@XmlSeeAlso( value = { ShopsPayload.class, ResponseContainer.class } )
public class ShopResponseContainer < T > extends ResponseContainer< T > {
}
