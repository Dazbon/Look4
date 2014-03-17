package eu.heliopora.look4.tracking.resources.entities;

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
import eu.heliopora.look4.commons.interfaces.IBuilder;
import eu.heliopora.look4.tracking.trackingDevices.entities.TrackingDevice;

public class ResourceTrackingDetail extends BasePersistentObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    // Logger

    static final Logger log = LogManager.getLogger(ResourceTrackingDetail.class);

    // Servlet context

    @Transient
    private static ServletContext servletContext;

    // If you modify these fields in any way, you have to modify Builder's
    // fields and its build() method as well

    private TrackingDevice trackingDevice;
    private Set<Resource> resources;

    // No-arguments constructor required by JPA

    public ResourceTrackingDetail() {

    }

    // Instances can only be created via Builder

    private ResourceTrackingDetail(Builder builder) {
        this.trackingDevice = builder.trackingDevice;
        this.resources = builder.resources;
    }

    // Builder

    public static class Builder implements IBuilder<ResourceTrackingDetail> {
        private TrackingDevice trackingDevice;
        private Set<Resource> resources;

        public Builder trackingDevice(TrackingDevice trackingDevice) {
            this.trackingDevice = trackingDevice;
            return this;
        }

        public Builder resources(Set<Resource> resources) {
            this.resources = resources;
            return this;
        }

        public ResourceTrackingDetail build() {
            ResourceTrackingDetail trackingDetail = new ResourceTrackingDetail(this);
            Validate.notNull(trackingDetail.getTrackingDevice());
            Validate.notNull(trackingDetail.getResources());
            return trackingDetail;
        }
    }

    // Validation has to be performed on a newly created instance, not on
    // Builder's fields (Builder is not thread-safe).

    // Getters and Setters

    public TrackingDevice getTrackingDevice() {
        return trackingDevice;
    }

    public void setTrackingDevice(TrackingDevice trackingDevice) {
        this.trackingDevice = trackingDevice;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    // Setting and initializing Servlet context, so we can inject Spring Beans
    // into Hibernate Entities

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        ResourceTrackingDetail.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing ServletContext as [{}]", ResourceTrackingDetail.servletContext.toString());
    }

    // For code brevity, toString() method is created via reflection. You can
    // provide custom implementation, if needed.

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
