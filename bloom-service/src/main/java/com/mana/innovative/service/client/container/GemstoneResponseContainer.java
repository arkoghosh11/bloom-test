package com.mana.innovative.service.client.container;

import com.mana.innovative.dto.client.payload.GemstonesPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The type GemstoneResponseContainer.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 10:21 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "response", namespace = "http://localhost:8443/bloom-test/rest/" )
@XmlSeeAlso( value = { GemstonesPayload.class, ResponseContainer.class } )
public class GemstoneResponseContainer < T > extends ResponseContainer< T > {

}