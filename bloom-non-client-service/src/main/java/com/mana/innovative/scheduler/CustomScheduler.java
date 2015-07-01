package com.mana.innovative.scheduler;

import org.springframework.stereotype.Service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomScheduler1
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface CustomScheduler {

    /**
     * Check for file.
     */
    void checkForFile( );

    /**
     * Load items file.
     */
//    @Scheduled( cron = "${cron_job_item_value}" )
    void loadItemsFile( );

    /**
     * Gets events n email.
     */
//    @Scheduled( cron = "${cron_job__email_event_value}" )
    void getEventsNEmail( );

    /**
     * Gets events n enable emailed events.
     */
    void getEventsNEnableEmailedEvents( );

}
