package eu.heliopora.look4.tracking.resources.entities;

import java.io.Serializable;

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

public class Resource extends BasePersistentObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    // Logger

    static final Logger log = LogManager.getLogger(Resource.class);

    // Servlet context

    @Transient
    private static ServletContext servletContext;

    // If you modify these fields in any way, you have to modify Builder's
    // fields and its build() method as well

    ResourceTrackingDetail trackingDetail;

    ResourceBusinessDetail businessDetail;

    // No-arguments constructor required by JPA

    public Resource() {

    }

    // Instances can only be created via Builder

    private Resource(Builder builder) {
        this.trackingDetail = builder.trackingDetail;
        this.businessDetail = builder.businessDetail;
    }

    // Builder
    public static class Builder implements IBuilder<Resource> {
        private ResourceTrackingDetail trackingDetail;
        private ResourceBusinessDetail businessDetail;

        public Builder trackingDetail(ResourceTrackingDetail trackingDetail) {
            this.trackingDetail = trackingDetail;
            return this;
        }

        public Builder businessDetail(ResourceBusinessDetail businessDetail) {
            this.businessDetail = businessDetail;
            return this;
        }

        // Validation has to be performed on a newly created instance, not on
        // Builder's fields (Builder is not thread-safe).

        public Resource build() {
            Resource resource = new Resource(this);
            Validate.notNull(resource.getBusinessDetail());
            Validate.notNull(resource.getTrackingDetail());
            return resource;
        }
    }

    // Getters and Setters

    public ResourceTrackingDetail getTrackingDetail() {
        return trackingDetail;
    }

    public void setTrackingDetail(ResourceTrackingDetail trackingDetail) {
        this.trackingDetail = trackingDetail;
    }

    public ResourceBusinessDetail getBusinessDetail() {
        return businessDetail;
    }

    public void setBusinessDetail(ResourceBusinessDetail businessDetail) {
        this.businessDetail = businessDetail;
    }

    // Setting and initializing Servlet context, so we can inject Spring Beans
    // into Hibernate Entities

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        Resource.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing ServletContext as [{}]", Resource.servletContext.toString());
    }

    // For code brevity, toString() method is created via reflection. You can
    // provide custom implementation, if needed.

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
