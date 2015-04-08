/**
 * @author Bloom This Class TabResponseBuilder.java is for Created at Aug 19, 2012 6:24:31 PM
 */
package com.mana.innovative.service.common.builder;

import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.dto.common.payload.TabsPayload;
import com.mana.innovative.service.common.container.TabResponseContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bloom
 */
public class TabResponseBuilder {


    /**
     * @param tabs {@link List<Tab>} Is an arrayList passed in as super class {List} for generating tab response
     *
     * @return {TabResponseContainer} Returns a response object for tabs
     */
    public static TabResponseContainer< TabsPayload > build( final List< Tab > tabs ) {

        TabResponseContainer< TabsPayload > tabResponseContainer = new TabResponseContainer< TabsPayload >( );
        tabResponseContainer.setCount( tabs.size( ) );

        TabsPayload tabsPayload = new TabsPayload( );
        tabsPayload.setTabs( tabs );
        tabResponseContainer.setPayload( tabsPayload );

        return tabResponseContainer;

    }

    public static TabResponseContainer< TabsPayload > build( final Tab tab ) {

        TabResponseContainer< TabsPayload > tabResponseContainer = new TabResponseContainer< TabsPayload >( );
        if ( tab != null )
            tabResponseContainer.setCount( 1 );
        else
            tabResponseContainer.setCount( 0 );
        TabsPayload tabPayload = new TabsPayload( );

        final List< Tab > tabs = new ArrayList< Tab >( );
        tabs.add( tab );
        tabPayload.setTabs( tabs );
        tabResponseContainer.setPayload( tabPayload );
        return tabResponseContainer;
    }
}
