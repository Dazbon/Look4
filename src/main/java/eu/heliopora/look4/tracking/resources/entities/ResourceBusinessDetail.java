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

import eu.heliopora.look4.business.objects.entities.BusinessObject;
import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class ResourceBusinessDetail extends BasePersistentObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    // Logger

    static final Logger log = LogManager.getLogger(ResourceBusinessDetail.class);

    // Servlet context

    @Transient
    private static ServletContext servletContext;

    // If you modify these fields in any way, you have to modify Builder's
    // fields and its build() method as well

    private BusinessObject businessObject;

    private Set<Resource> resources;

    // No-arguments constructor required by JPA

    public ResourceBusinessDetail() {

    }

    // Instances can only be created via Builder

    private ResourceBusinessDetail(Builder builder) {
        this.businessObject = builder.businessObject;
        this.resources = builder.resources;
    }

    // Builder

    public static class Builder implements IBuilder<ResourceBusinessDetail> {
        private BusinessObject businessObject;
        private Set<Resource> resources;

        public Builder businessObject(BusinessObject businessObject) {
            this.businessObject = businessObject;
            return this;
        }

        public Builder resources(Set<Resource> resources) {
            this.resources = resources;
            return this;
        }

        // Validation has to be performed on a newly created instance, not on
        // Builder's fields (Builder is not thread-safe).

        public ResourceBusinessDetail build() {
            ResourceBusinessDetail businessDetail = new ResourceBusinessDetail(this);
            Validate.notNull(businessDetail.getBusinessObject());
            Validate.notNull(businessDetail.getResources());
            return businessDetail;
        }
    }

    // Getters and Setters

    public BusinessObject getBusinessObject() {
        return businessObject;
    }

    public void setBusinessObject(BusinessObject businessObject) {
        this.businessObject = businessObject;
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
        ResourceBusinessDetail.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing ServletContext as [{}]", ResourceBusinessDetail.servletContext.toString());
    }


    // For code brevity, toString() method is created via reflection. You can
    // provide custom implementation, if needed.

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
