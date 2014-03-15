package eu.heliopora.look4.application.utils.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.heliopora.look4.application.utils.IApplicationPropertiesProvider;
import eu.heliopora.look4.application.utils.IPropertyReader;

public class ApplicationSpecificPropertiesProvider implements IApplicationPropertiesProvider {

    // Logger

    static final Logger log = LogManager.getLogger(ApplicationSpecificPropertiesProvider.class);

    // Properties reader

    private IPropertyReader propertyReader;

    // Properties location

    private String propertiesLocation;

    // Properties

    // Everywhere
    private String symbolNA;
    private static final String SYMBOL_NA_PARAM = "symbol.NA";

    // All properties are read in constructor.

    public ApplicationSpecificPropertiesProvider(IPropertyReader propertyReader, String propertiesLocation) {
        this.propertyReader = propertyReader;
        this.propertiesLocation = propertiesLocation;

        InputStream in = BuildInformationProvider.class.getClassLoader().getResourceAsStream(this.propertiesLocation);
        if (in == null) {
            log.error("Property file {} not found.", this.propertiesLocation);
            return;
        }

        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            log.error("Error reading property file {}.", this.propertiesLocation);
            return;
        }

        this.symbolNA = this.propertyReader.readStringProperty(props, SYMBOL_NA_PARAM, "N/A");
    }

    // Getters

    public String getSymbolNA() {
        return this.symbolNA;
    }

}
