package com.mana.innovative.constants;

/**
 * Created by Bloom/Rono on 4/27/2015 5:50 PM. This class is CardType
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public enum CardType {

    /**
     * The Visa.
     */
    Visa( "4" ),
    /**
     * The AmericanExpress.
     */
    AmericanExpress( "34,37" ),
    /**
     * The Discover.
     */
    Discover( "6011,65" ),
    /**
     * The MasterCard.
     */
    MasterCard( "51-55" ),
    /**
     * The Diners.
     */
    Diners( "300-305,36,38" ),
    /**
     * The JCB.
     */
    JCB( "1800,2131,35" );

    /**
     * The Value.
     */
    private final String value;

    /**
     * Instantiates a new Card type.
     *
     * @param value the value
     */
    CardType( String value ) {
        this.value = value;
    }

    /**
     * Get value.
     *
     * @return the string [ ]
     */
    public String[] getValue( ) {
        return value.split( "," );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return super.toString( ).toLowerCase( );
    }
}
