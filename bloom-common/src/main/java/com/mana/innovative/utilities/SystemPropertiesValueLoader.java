package com.mana.innovative.utilities;/**
 * Created by Rono on 2/18/2015.
 * This is a class for .. todo 
 */

import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.Map;

/**
 * Created by Bloom on 2/18/2015 : 10:16 PM
 * todo This class is for ...
 */
public class SystemPropertiesValueLoader extends SystemEnvironmentPropertySource {

    /**
     * Create a new {@code SystemEnvironmentPropertySource} with the given name and
     * delegating to the given {@code MapPropertySource}.
     *
     * @param name {@link String}
     * @param source {@link Map<String,Object></>}
     */
    public SystemPropertiesValueLoader(String name, Map<String, Object> source) {
        super(name, source);
    }

}
