package eu.heliopora.look4.application.utils.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;

import eu.heliopora.look4.application.utils.IApplicationPropertiesProvider;
import eu.heliopora.look4.application.utils.IBuildInformationProvider;
import eu.heliopora.look4.application.utils.IPropertyReader;

@Configurable
public class BuildInformationProvider implements IBuildInformationProvider {

    // Logger

    static final Logger log = LogManager.getLogger(BuildInformationProvider.class);

    // Properties reader

    private IPropertyReader propertyReader;

    // Application properties provider

    private IApplicationPropertiesProvider applicationPropertiesProvider;

    // Properties location

    private String propertiesLocation;

    // Properties

    // Footer
    private String buildVersion;
    private static final String BUILD_VERSION_PARAM = "build.version";
    private String buildTimestamp;
    private static final String BUILD_TIMESTAMP_PARAM = "build.timestamp";

    // All properties are read in constructor.

    private BuildInformationProvider(IPropertyReader propertyReader, String propertiesLocation,
                    IApplicationPropertiesProvider applicationPropertiesProvider) {
        this.propertyReader = propertyReader;
        this.propertiesLocation = propertiesLocation;
        this.applicationPropertiesProvider = applicationPropertiesProvider;

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

        this.buildVersion =
                        this.propertyReader
                                        .readStringProperty(props, BUILD_VERSION_PARAM, this.applicationPropertiesProvider.getSymbolNA());
        this.buildTimestamp =
                        this.propertyReader.readStringProperty(props, BUILD_TIMESTAMP_PARAM,
                                        this.applicationPropertiesProvider.getSymbolNA());
    }

    // Getters

    public String getBuildVersion() {
        return this.buildVersion;
    }

    public String getBuildTimestamp() {
        return this.buildTimestamp;
    }

}
