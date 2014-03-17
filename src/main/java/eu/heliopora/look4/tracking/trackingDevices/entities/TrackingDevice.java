package eu.heliopora.look4.tracking.trackingDevices.entities;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.Transient;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.tracking.resources.entities.ResourceTrackingDetail;

public class TrackingDevice extends BasePersistentObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    // Logger

    static final Logger log = LogManager.getLogger(TrackingDevice.class);

    // Servlet context

    @Transient
    private static ServletContext servletContext;


    // If you modify these fields in any way, you have to modify Builder's
    // fields and its build() method as well

    private String imei;
    private String telephoneNumber;
    private Set<ResourceTrackingDetail> resourceTrackingDetails;

    // private TrackingDeviceState trackingDeviceState;

    // No-arguments constructor required by JPA

    public TrackingDevice() {

    }

    // Instances can only be created via Builder

    private TrackingDevice(Builder builder) {
        this.imei = builder.imei;
        this.telephoneNumber = builder.telephoneNumber;
    }

    // Builder
    public static class Builder {
        private String imei;
        private String telephoneNumber;
        private Set<ResourceTrackingDetail> resourceTrackingDetails;

        public Builder imei(String imei) {
            this.imei = imei;
            return this;
        }

        public Builder telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public Builder resourceTrackingDetails(Set<ResourceTrackingDetail> resourceTrackingDetails) {
            this.resourceTrackingDetails = resourceTrackingDetails;
            return this;
        }

        // Validation has to be performed on a newly created instance, not on
        // Builder's fields (Builder is not thread-safe).

        public TrackingDevice build() {
            TrackingDevice trackingDevice = new TrackingDevice(this);
            Validate.notNull(trackingDevice.getImei());
            Validate.notNull(trackingDevice.getTelephoneNumber());
            Validate.notNull(trackingDevice.getResourceTrackingDetails());
            return trackingDevice;
        }
    }

    // Getters and Setters
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Set<ResourceTrackingDetail> getResourceTrackingDetails() {
        return resourceTrackingDetails;
    }

    public void setResourceTrackingDetails(Set<ResourceTrackingDetail> resourceTrackingDetails) {
        this.resourceTrackingDetails = resourceTrackingDetails;
    }

    // Setting and initializing Servlet context, so we can inject Spring Beans
    // into Hibernate Entities

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        TrackingDevice.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing ServletContext as [{}]", TrackingDevice.servletContext.toString());
    }

    // For code brevity, toString() method is created via reflection. You can
    // provide custom implementation, if needed.

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
