package com.mana.innovative.email;

import com.mana.innovative.dto.common.email.EmailContents;
import org.springframework.stereotype.Service;

/**
 * The type Bloom email service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface BloomEmailService {

    /**
     * This method will send compose and send the message
     *
     * @param emailContents the email contents
     * @return the boolean
     */
    boolean sendMail( EmailContents emailContents );
}
