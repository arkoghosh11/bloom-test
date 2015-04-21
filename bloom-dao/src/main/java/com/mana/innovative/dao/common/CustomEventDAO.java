package com.mana.innovative.dao.common;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.email.CustomEvent;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Bloom/Rono on 4/14/2015. This class is CustomEventDAO
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface CustomEventDAO {

    DAOResponse< CustomEvent > getEventById( long customEventId, RequestParams requestParams );

    DAOResponse< CustomEvent > getEventsByDate( Date eventDate, RequestParams requestParams );

    DAOResponse< CustomEvent > getEventsByEventName( String eventName, RequestParams requestParams );

    DAOResponse< CustomEvent > getAllEvents( RequestParams requestParams );

    DAOResponse< CustomEvent > updateEvent( CustomEvent customEvent, RequestParams requestParams );

    DAOResponse< CustomEvent > enableEventSchedulerForDate( Date date, RequestParams requestParams );

    DAOResponse< CustomEvent > disableEventSchedulerForDate( Date date, RequestParams requestParams );

    DAOResponse< CustomEvent > getEventsByDateRange( Date eventStartTime, Date eventEndTime, RequestParams requestParams );
}
