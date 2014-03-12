package eu.heliopora.look4.commons.utils;

import java.util.Properties;

public interface PropertyReader {
	
	void readStringProperty(String property, Properties props, String propertyName, String defaultValue);
	
	void readBooleanProperty(Boolean property, Properties props, String propertyName, Boolean defaultValue);
	
	void readIntegerProperty(Integer property, Properties props, String propertyName, Integer defaultValue);

}
