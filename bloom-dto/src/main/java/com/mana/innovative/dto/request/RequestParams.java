package com.mana.innovative.dto.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Request params.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class RequestParams {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( RequestParams.class );

    /**
     * The Is error.
     */
    private boolean isError;

    /**
     * The Is delete all.
     */
    private boolean isDeleteAll;

    /**
     * The Page size.
     */
    private Integer pageSize;
    /**
     * The Start limit.
     */
    private Long startLimit;
    /**
     * The End limit.
     */
    private Long endLimit;

    /**
     * The Id.
     */
    private Long Id;

    /**
     * The Start date.
     */
    private String startDate;
    /**
     * The End date.
     */
    private String endDate;

    /**
     * Instantiates a new Request params.
     */
    public RequestParams( ) {
        logger.debug( "Class initiated" + this.getClass( ).getCanonicalName( ) );
    }

    /**
     * Is error.
     *
     * @return the boolean
     */
    public boolean isError( ) {
        return isError;
    }

    /**
     * Sets is error.
     *
     * @param isError the is error
     */
    public void setIsError( final boolean isError ) {
        this.isError = isError;
    }

    /**
     * Is delete all.
     *
     * @return the boolean
     */
    public boolean isDeleteAll( ) {
        return isDeleteAll;
    }

    /**
     * Sets is delete all.
     *
     * @param isDeleteAll the is delete all
     */
    public void setIsDeleteAll( final boolean isDeleteAll ) {
        this.isDeleteAll = isDeleteAll;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public Integer getPageSize( ) {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize( final Integer pageSize ) {
        this.pageSize = pageSize;
    }

    /**
     * Gets start limit.
     *
     * @return the start limit
     */
    public Long getStartLimit( ) {
        return startLimit;
    }

    /**
     * Sets start limit.
     *
     * @param startLimit the start limit
     */
    public void setStartLimit( final Long startLimit ) {
        this.startLimit = startLimit;
    }

    /**
     * Gets end limit.
     *
     * @return the end limit
     */
    public Long getEndLimit( ) {
        return endLimit;
    }

    /**
     * Sets end limit.
     *
     * @param endLimit the end limit
     */
    public void setEndLimit( final Long endLimit ) {
        this.endLimit = endLimit;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId( ) {
        return Id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId( final Long id ) {
        Id = id;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public String getStartDate( ) {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate( final String startDate ) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEndDate( ) {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate( final String endDate ) {
        this.endDate = endDate;
    }
}
