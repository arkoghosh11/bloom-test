package com.mana.innovative.utilities;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by alex1 on 1/21/2015.
 * This is a domain class
 */
public class SystemPropertiesFactoryBean extends PropertiesFactoryBean {

    private static final Logger logger = Logger.getLogger(SystemPropertiesFactoryBean.class);
    private boolean propagateToSystemProperties;
    private List<String> locations;

    public void setPropagateToSystemProperties(final boolean propagateToSystemProperties) {
        logger.info("Setting boolean value");
        this.propagateToSystemProperties = propagateToSystemProperties;
    }

    @Override
    protected Properties createProperties() throws IOException {
        final Properties originalProperties = super.createProperties();
        final Properties propertiesReplaced = new Properties();

        final com.mana.innovative.utilities.PropertyVariableParser propertyParser = new PropertyVariableParser();

        for (final Map.Entry<Object, Object> entry : originalProperties.entrySet()) {
            propertiesReplaced.put(
                    entry.getKey(),
                    propertyParser.replaceVariables((String) entry.getValue())
            );
        }

        if (propagateToSystemProperties) {
            final Properties systemProperties = System.getProperties();
            logger.info(" *******\nProperties newly acquired\n "+ systemProperties.toString()+
            "\n***********");
            systemProperties.putAll(propertiesReplaced);
            System.setProperties(systemProperties);
        }
        return propertiesReplaced;
    }
}
