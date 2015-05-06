package com.mana.innovative.service.common.container;

import com.mana.innovative.dto.common.payload.SidebarPayload;
import com.mana.innovative.service.container.ResponseContainer;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by IntelliJ IDEA.
 *
 * @param <T>   the type parameter
 * Created by Bloom/Rono on $date $time.
 * @author Bloom Date: 4/9/13 Time: 4:24 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlSeeAlso( { ResponseContainer.class, SidebarPayload.class } )
public class SidebarResponseContainer < T > extends ResponseContainer< T > {
}
