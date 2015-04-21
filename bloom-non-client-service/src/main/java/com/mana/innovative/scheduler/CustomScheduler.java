package com.mana.innovative.scheduler;

import org.springframework.stereotype.Service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomScheduler1
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface CustomScheduler {

    void checkForFile( );

    //    @Scheduled( cron = "${cron_job_item_value}" )
    void loadItemsFile( );

    //    @Scheduled( cron = "${cron_job__email_event_value}" )
    void getEventsNEmail( );

    void getEventsNEnableEmailedEvents( );
}
