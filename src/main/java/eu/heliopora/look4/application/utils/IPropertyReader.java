package eu.heliopora.look4.application.utils;

import java.util.Properties;

public interface IPropertyReader {
	
	String readStringProperty(Properties props, String propertyName, String defaultValue);
	
	Boolean readBooleanProperty(Properties props, String propertyName, Boolean defaultValue);
	
	Integer readIntegerProperty(Properties props, String propertyName, Integer defaultValue);

}
