package eu.heliopora.look4.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import eu.heliopora.look4.commons.utils.impl.PropertyReaderImpl;

@Configurable
public class BuildInformation {
	
	static final Logger log = Logger.getLogger(PropertyReaderImpl.class);
	
	PropertyReader pr = new PropertyReaderImpl();
	
	//Properties location
	private static final String BUILD_INFO_PROPERTIES = "build-info.properties";
    
	//Properties
	
	//Footer
    private String buildVersion;
	private static final String BUILD_VERSION_PARAM = "build.version";
    private String buildTimestamp;
    private static final String BUILD_TIMESTAMP_PARAM = "build.timestamp";
		
    private static final BuildInformation singleton = new BuildInformation();
    
    private BuildInformation() {
    	InputStream in = BuildInformation.class.getClassLoader().getResourceAsStream(BUILD_INFO_PROPERTIES);
        if (in == null)
            return;

        Properties props = new Properties();
        try {
        	props.load(in);
        } catch (IOException e) {
        	log.error("Error reading property file " + BUILD_INFO_PROPERTIES);
        	return;
        }

        pr.readStringProperty(this.buildVersion, props, BUILD_VERSION_PARAM, "N/A");
        pr.readStringProperty(this.buildVersion, props, BUILD_TIMESTAMP_PARAM, "N/A");
    }
    
    // I think we do not need this
    public static BuildInformation getInstance() {
        return singleton;
    }
    
    public static String getBuildVersion() {
        return singleton.getBuildVersionInternal();
    }
    
    private String getBuildVersionInternal() {
        return this.buildVersion;
    }
    
    public static String getBuildTimestamp() {
        return singleton.getBuildTimestampInternal();
    }
    
    private String getBuildTimestampInternal() {
        return this.buildTimestamp;
    }

}
