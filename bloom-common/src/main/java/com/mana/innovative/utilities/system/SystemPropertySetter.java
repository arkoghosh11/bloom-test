package com.mana.innovative.utilities.system;/**
 * Created by Rono on 2/25/2015.
 * This is a class for .. todo 
 */

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Bloom on 2/25/2015 : 4:21 PM
 * todo This class is for ...
 */
public final class SystemPropertySetter {

    private final String systemPropertiesFile = "";

    private SystemPropertySetter() {

    }

    /**
     * For use in scenarios where you need to set specific environment values for unit tests, you might find the following hack useful.
     * It will change the environment variables throughout the JVM (so make sure you reset any changes after your test),
     * but will not alter your system environment.
     * <p/>
     * I found that a combination of the two dirty hacks by Edward Campbell and anonymous works best,
     * as one of the does not work under linux, one does not work under windows 7.
     * So to get a multi-platform evil hack I combined them:
     *
     * @param newEnv
     */
    protected static void setEnv(Map<String, String> newEnv) {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> environmentFieldMap = (Map<String, String>) theEnvironmentField.get(null);
            environmentFieldMap.putAll(newEnv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> caseInsensitiveEnvironmentFieldMap = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
            caseInsensitiveEnvironmentFieldMap.putAll(newEnv);
        } catch (NoSuchFieldException e) {
            try {
                Class[] classes = Collections.class.getDeclaredClasses();
                Map<String, String> env = System.getenv();
                for (Class cl : classes) {
                    if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                        Field field = cl.getDeclaredField("m");
                        field.setAccessible(true);
                        Object obj = field.get(env);
                        Map<String, String> map = (Map<String, String>) obj;
                        map.clear();
                        map.putAll(newEnv);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
