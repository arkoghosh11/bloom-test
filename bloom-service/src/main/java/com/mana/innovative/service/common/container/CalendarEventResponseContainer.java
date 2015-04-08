package com.mana.innovative.service.common.container;

import com.mana.innovative.dto.common.payload.CalendarEventPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:23 PM
 * @since: jdk 1.7
 */
@XmlRootElement( name = "response" )
@XmlSeeAlso( { CalendarEventPayload.class, ResponseContainer.class } )
public class CalendarEventResponseContainer < T > extends ResponseContainer< T > {

}
