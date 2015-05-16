package com.mana.innovative.dao.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;

import javax.ws.rs.DefaultValue;
import java.util.List;

/**
 * The type DAO response.
 *
 * @param <T> the type parameter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class DAOResponse < T > {

    /**
     * The Is update.
     */
    private boolean isUpdate;
    /**
     * The Is create.
     */
    private boolean isCreate;
    /**
     * The Is delete.
     */
    private boolean isDelete;
    /**
     * The Request success.
     */
    private boolean requestSuccess;

    /**
     * The Count.
     */
    private int count;
    /**
     * The Total limit.
     */
    private int totalLimit;
    /**
     * The Start limit.
     */
    @DefaultValue( value = DAOConstants.STRING_ZERO )
    private int startLimit;
    /**
     * The Offset.
     */
    @DefaultValue( value = DAOConstants.DEFAULT_STRING_PAGE_LIMIT )
    private int offset;

    /**
     * The Results.
     */
    private List< T > results;

    /**
     * The Error container.
     */
    private ErrorContainer errorContainer;


    /**
     * Sets error.
     *
     * @param location the location
     * @param e        the e
     */
    public void setError( final String location, final Exception e ) {

        if ( this.errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        Error error = new Error( );
        error.setErrorData( e );
        error.setErrorLocation( location );
        error.setErrorType( e.getClass( ).getCanonicalName( ) );
        error.setErrorMessage( e.getMessage( ) );
        errorContainer.addError( new Error( ) );
    }

    /**
     * Is update.
     *
     * @return the boolean
     */
    public boolean isUpdate( ) {

        return isUpdate;
    }

    /**
     * Sets update.
     *
     * @param isUpdate the is update
     */
    public void setUpdate( final boolean isUpdate ) {

        this.isUpdate = isUpdate;
    }

    /**
     * Is create.
     *
     * @return the boolean
     */
    public boolean isCreate( ) {

        return isCreate;
    }

    /**
     * Sets create.
     *
     * @param isCreate the is create
     */
    public void setCreate( final boolean isCreate ) {

        this.isCreate = isCreate;
    }

    /**
     * Is delete.
     *
     * @return the boolean
     */
    public boolean isDelete( ) {

        return isDelete;
    }

    /**
     * Sets delete.
     *
     * @param isDelete the is delete
     */
    public void setDelete( final boolean isDelete ) {

        this.isDelete = isDelete;
    }

    /**
     * Is request success.
     *
     * @return the boolean
     */
    public boolean isRequestSuccess( ) {

        return requestSuccess;
    }

    /**
     * Sets request success.
     *
     * @param requestSuccess the request success
     */
    public void setRequestSuccess( final boolean requestSuccess ) {

        this.requestSuccess = requestSuccess;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount( ) {

        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount( final int count ) {

        this.count = count;
    }

    /**
     * Gets total limit.
     *
     * @return the total limit
     */
    public int getTotalLimit( ) {

        return totalLimit;
    }

    /**
     * Sets total limit.
     *
     * @param totalLimit the total limit
     */
    public void setTotalLimit( final int totalLimit ) {

        this.totalLimit = totalLimit;
    }

    /**
     * Gets start limit.
     *
     * @return the start limit
     */
    public int getStartLimit( ) {

        return startLimit;
    }

    /**
     * Sets start limit.
     *
     * @param startLimit the start limit
     */
    public void setStartLimit( final int startLimit ) {

        this.startLimit = startLimit;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset( ) {

        return offset;
    }

    /**
     * Sets offset.
     *
     * @param offset the offset
     */
    public void setOffset( final int offset ) {

        this.offset = offset;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public List< T > getResults( ) {

        return results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults( final List< T > results ) {

        this.results = results;
    }

    /**
     * Gets error container.
     *
     * @return the error container
     */
    public ErrorContainer getErrorContainer( ) {

        return errorContainer;
    }

    /**
     * Sets error container.
     *
     * @param errorContainer the error container
     */
    public void setErrorContainer( final ErrorContainer errorContainer ) {

        this.errorContainer = errorContainer;
    }

}
