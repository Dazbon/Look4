package eu.heliopora.look4.application.utils.impl;

import java.util.Properties;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.heliopora.look4.application.utils.IPropertyReader;

public class PropertyReader implements IPropertyReader {

    // Logger

    static final Logger log = LogManager.getLogger(PropertyReader.class);

    // Methods that read different types of properties

    public String readStringProperty(final Properties props, final String propertyName, final String defaultValue) {
        return readProperty(String.class, props, propertyName, defaultValue);
    }

    public Boolean readBooleanProperty(final Properties props, final String propertyName, final Boolean defaultValue) {
        return readProperty(Boolean.class, props, propertyName, defaultValue);
    }

    public Integer readIntegerProperty(final Properties props, final String propertyName, final Integer defaultValue) {
        return readProperty(Integer.class, props, propertyName, defaultValue);
    }

    // Generic method is used to implement the aforementioned methods

    @SuppressWarnings("unchecked")
    private <T> T readProperty(final Class<T> typeClass, final Properties props, final String propertyName, final T defaultValue) {
        Validate.notNull(props);
        Validate.notNull(propertyName);
        Validate.notNull(defaultValue);

        String stringPropertyValue = props.getProperty(propertyName);
        if (stringPropertyValue == null) {
            log.info("Property {} not found. Setting default value {}.", propertyName, defaultValue.toString());
            return defaultValue;
        }

        try {
            T property = defaultValue;
            if (typeClass.equals(String.class)) {
                property = (T) stringPropertyValue;
            } else if (typeClass.equals(Boolean.class)) {
                property = (T) Boolean.valueOf(stringPropertyValue);
            } else if (typeClass.equals(Integer.class)) {
                property = (T) Integer.valueOf(stringPropertyValue);
            } else {
                assert false : "Implementation error: Unknown property type.";
            }
            log.info("Property {} read sucessfully. Value is {}.", propertyName, stringPropertyValue);
            return property;
        } catch (Exception e) {
            log.error("Error reading property {}. Setting default value {}.", propertyName, defaultValue.toString());
            return defaultValue;
        }
    }

}
