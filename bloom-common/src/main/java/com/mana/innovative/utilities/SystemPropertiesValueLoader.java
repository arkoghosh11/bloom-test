package com.mana.innovative.utilities;

import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.Map;

/**
 * The type System properties value loader.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class SystemPropertiesValueLoader extends SystemEnvironmentPropertySource {

    /**
     * Create a new {@code SystemEnvironmentPropertySource} with the given name and delegating to the given {@code
     * MapPropertySource}.
     *
     * @param name   the name
     * @param source the source
     */
    public SystemPropertiesValueLoader( String name, Map< String, Object > source ) {
        super( name, source );
    }

}
