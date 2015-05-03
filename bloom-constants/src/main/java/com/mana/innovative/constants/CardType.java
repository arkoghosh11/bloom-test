package com.mana.innovative.constants;

/**
 * Created by Bloom/Rono on 4/27/2015 5:50 PM. This class is CardType
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public enum CardType {

    Visa( "4" ),
    AmericanExpress( "34,37" ),
    Discover( "6011,65" ),
    MasterCard( "51-55" ),
    Diners( "300-305,36,38" ),
    JCB( "1800,2131,35" );

    private final String value;

    CardType( String value ) {
        this.value = value;
    }

    public String[] getValue( ) {
        return value.split( "," );
    }

    @Override
    public String toString( ) {
        return super.toString( ).toLowerCase( );
    }
}
