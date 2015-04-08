/**
 * @author Bloom This Class UserService.java is for Created at Aug 28, 2012 4:31:34 PM
 */
package com.mana.innovative.service.consumer;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The interface Users service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface UsersService {

    Response getAllUsers( RequestParams requestParams );

    Response deleteUsers( List< Long > userIds, RequestParams requestParams );

}
