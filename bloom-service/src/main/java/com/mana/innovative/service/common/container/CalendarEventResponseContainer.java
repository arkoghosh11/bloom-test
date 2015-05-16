package com.mana.innovative.service.common.container;

import com.mana.innovative.dto.common.payload.CalendarEventsPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by IntelliJ IDEA.
 *
 * @param <T> the type parameter Created by Bloom/Rono on $date $time.
 *
 * @author Bloom Date: 1/2/13 Time: 4:23 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlRootElement( name = "response" )
@XmlSeeAlso( { CalendarEventsPayload.class, ResponseContainer.class } )
public class CalendarEventResponseContainer < T > extends ResponseContainer< T > {

}
