package com.mana.innovative.domain.common;


import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.exception.IllegalSearchListSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. This class is for advanced searching of {@link Tab} from DB {@code searchParams,
 * searchConditions, searchOrder, searchMatchType } searchParams is for the columns to be search against for values
 * entered by user in DB searchConditions is for applying conditions to each pram above for the column search in DB
 * search Order is the column types on which to add a order to. Order possible is (ASC,DESC, none) searchMatchType is
 * match applied to to a specified column with whichever match the user desires like a user may enter a few letter and
 * do Anywhere or a word and to exact. possible matchTypes are Exact, Anywhere, Start, End
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright Date: 10/2/12 Time: 3:35 PM
 * @since: jdk 1.7
 */
public class TabSearchOption {

    private static final int MAX = DAOConstants.THREE;
    private static final int MIN = DAOConstants.ONE;

    private List< Map< String, Object > > searchConditionParams;
    private List< Map< String, String > > searchOrderWithParams;
    private List< Map< String, Object > > searchMatchTypeParams;
    private List< Map< String, String > > searchConditions;
    private List< Map< String, String > > searchMatchType;

    public TabSearchOption( ) {

        searchConditionParams = new ArrayList< Map< String, Object > >( );
        searchOrderWithParams = new ArrayList< Map< String, String > >( );
        searchMatchTypeParams = new ArrayList< Map< String, Object > >( );

        searchConditions = new ArrayList< Map< String, String > >( );
        searchMatchType = new ArrayList< Map< String, String > >( );
    }

    public List< Map< String, String > > getSearchConditions( ) {
        return searchConditions;
    }

    public void setSearchConditions( final List< Map< String, String > > searchConditions ) {

        if ( searchConditions.size( ) > MAX || searchConditions.size( ) < MIN ) {
            throw new IllegalSearchListSizeException( " Size invalid" );
        }
        this.searchConditions = searchConditions;
    }

    public List< Map< String, String > > getSearchMatchType( ) {
        return searchMatchType;
    }

    /**
     * This method is to set the Search Match Type
     *
     * @param searchMatchTypes {@link List<Map></> of type <String,String></String,String>}
     */
    public void setSearchMatchType( final List< Map< String, String > > searchMatchTypes ) {
        this.searchMatchType = searchMatchTypes;
    }

    public List< Map< String, Object > > getSearchConditionParams( ) {
        return searchConditionParams;
    }

    public void setSearchConditionParams( final List< Map< String, Object > > searchConditionParams ) {
        if ( searchConditionParams.size( ) > MAX || searchConditionParams.size( ) < MIN ) {
            throw new IllegalSearchListSizeException( " Size invalid" );
        }
        this.searchConditionParams = searchConditionParams;
    }

    public List< Map< String, String > > getSearchOrderWithParams( ) {
        return searchOrderWithParams;
    }

    public void setSearchOrderWithParams( final List< Map< String, String > > searchOrderWithParams ) {

        if ( searchOrderWithParams.size( ) > MAX || searchOrderWithParams.size( ) < MIN ) {
            throw new IllegalSearchListSizeException( " Size invalid" );
        }
        this.searchOrderWithParams = searchOrderWithParams;
    }

    public List< Map< String, Object > > getSearchMatchTypeParams( ) {
        return searchMatchTypeParams;
    }

    public void setSearchMatchTypeParams( final List< Map< String, Object > > searchMatchTypeParams ) {

        if ( searchMatchTypeParams.size( ) > MAX || searchMatchTypeParams.size( ) < MIN ) {
            throw new IllegalSearchListSizeException( " Size invalid" );
        }
        this.searchMatchTypeParams = searchMatchTypeParams;
    }
}
