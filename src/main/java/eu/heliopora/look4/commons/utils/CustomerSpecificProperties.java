package eu.heliopora.look4.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import eu.heliopora.look4.commons.utils.impl.PropertyReaderImpl;

public class CustomerSpecificProperties {
	
	static final Logger log = Logger.getLogger(PropertyReaderImpl.class);
	
	PropertyReader pr = new PropertyReaderImpl();
	
	//Properties location
	private static final String CUSTOMER_SPECIFIC_PROPERTIES = "customer-specific.properties";
	
	//Properties
	
	//Login dialog
    private String customerName;
	private static final String CUSTOMER_NAME_PARAM = "customer.name";
	//UserBusinessDetail
    private Boolean userInternalNumberRequired;
	private static final String USER_INTERNAL_NUMBER_REQUIRED_PARAM = "user.internalNumber.required";	
    
    private static final CustomerSpecificProperties singleton = new CustomerSpecificProperties();

    private CustomerSpecificProperties() {
    	InputStream in = BuildInformation.class.getClassLoader().getResourceAsStream(CUSTOMER_SPECIFIC_PROPERTIES);
        if (in == null)
            return;

        Properties props = new Properties();
        try {
        	props.load(in);
        } catch (IOException e) {
        	log.error("Error reading property file " + CUSTOMER_SPECIFIC_PROPERTIES);
        	return;
        }
        
        pr.readStringProperty(this.customerName, props, CUSTOMER_NAME_PARAM, "N/A");
        pr.readBooleanProperty(this.userInternalNumberRequired, props, USER_INTERNAL_NUMBER_REQUIRED_PARAM, true);

        this.userInternalNumberRequired = Boolean.valueOf(props.getProperty(USER_INTERNAL_NUMBER_REQUIRED_PARAM));
    }

    // I think we do not need this
    public static CustomerSpecificProperties getInstance() {
        return singleton;
    }
    
    public static String getCustomerName() {
        return singleton.getCustomerNameInternal();
    }

    private String getCustomerNameInternal() {
        return this.customerName;
    }
    
    public static Boolean getUserInternalNumberRequired() {
        return singleton.getUserInternalNumberRequiredInternal();
    }
    
    private Boolean getUserInternalNumberRequiredInternal() {
    	return this.userInternalNumberRequired;
    }
}
