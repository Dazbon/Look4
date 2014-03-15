package eu.heliopora.look4.application.utils.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.heliopora.look4.application.utils.IApplicationPropertiesProvider;
import eu.heliopora.look4.application.utils.ICustomerSpecificPropertiesProvider;
import eu.heliopora.look4.application.utils.IPropertyReader;

public class CustomerSpecificPropertiesProvider implements ICustomerSpecificPropertiesProvider {

    // Logger

    static final Logger log = LogManager.getLogger(CustomerSpecificPropertiesProvider.class);

    // Properties reader

    private IPropertyReader propertyReader;

    // Application properties provider

    private IApplicationPropertiesProvider applicationProperties;

    // Properties location

    private String propertiesLocation;

    // Properties

    // Login dialog, Header
    private String customerName;
    private static final String CUSTOMER_NAME_PARAM = "customer.name";
    // UserBusinessDetail
    private Boolean userInternalNumberRequired;
    private static final String USER_INTERNAL_NUMBER_REQUIRED_PARAM = "user.internalNumber.required";

    // All properties are read in constructor.

    public CustomerSpecificPropertiesProvider(IPropertyReader propertyReader, String propertiesLocation,
                    IApplicationPropertiesProvider applicationProperties) {
        this.propertyReader = propertyReader;
        this.propertiesLocation = propertiesLocation;
        this.applicationProperties = applicationProperties;

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

        this.customerName = this.propertyReader.readStringProperty(props, CUSTOMER_NAME_PARAM, this.applicationProperties.getSymbolNA());
        this.userInternalNumberRequired = this.propertyReader.readBooleanProperty(props, USER_INTERNAL_NUMBER_REQUIRED_PARAM, true);
    }

    // Getters

    public String getCustomerName() {
        return this.customerName;
    }

    public Boolean getUserInternalNumberRequired() {
        return this.userInternalNumberRequired;
    }
}
