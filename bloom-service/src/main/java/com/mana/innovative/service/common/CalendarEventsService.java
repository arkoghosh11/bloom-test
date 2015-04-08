package com.mana.innovative.service.common;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:40 PM
 * @since: jdk 1.7
 */
@Service
public interface CalendarEventsService {

    Response getAllEvents( RequestParams requestParams );

    Response deleteEvents( List< Long > calendarEventIds, RequestParams requestParams );

}
