package eu.heliopora.look4.business.objects.entities;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.persistence.Transient;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import eu.heliopora.look4.business.organization.entities.OrganizationUnit;
import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class BusinessObject extends BasePersistentObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    // Logger

    static final Logger log = LogManager.getLogger(BusinessObject.class);

    // Servlet context

    @Transient
    private static ServletContext servletContext;

    // If you modify these fields in any way, you have to modify Builder's
    // fields and its build() method as well
    private String internalNumber;

    private OrganizationUnit organizationUnit;

    // No-arguments constructor required by JPA

    public BusinessObject() {

    }

    // Instances can only be created via Builder

    private BusinessObject(Builder builder) {
        this.internalNumber = builder.internalNumber;
        this.organizationUnit = builder.organizationUnit;
    }

    // Builder
    public static class Builder implements IBuilder<BusinessObject> {
        private String internalNumber;
        private OrganizationUnit organizationUnit;

        public Builder internalNumber(String internalNumber) {
            this.internalNumber = internalNumber;
            return this;
        }

        public Builder organizationUnit(OrganizationUnit organizationUnit) {
            this.organizationUnit = organizationUnit;
            return this;
        }

        // Validation has to be performed on a newly created instance, not on
        // Builder's fields (Builder is not thread-safe).

        public BusinessObject build() {
            BusinessObject businessObj = new BusinessObject(this);
            Validate.notNull(businessObj.getInternalNumber());
            Validate.notNull(businessObj.getOrganizationUnit());
            return businessObj;
        }

    }

    // Getters and Setters

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    // Setting and initializing Servlet context, so we can inject Spring Beans
    // into Hibernate Entities

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        BusinessObject.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing ServletContext as [{}]", BusinessObject.servletContext.toString());
    }

    // For code brevity, toString() method is created via reflection. You can
    // provide custom implementation, if needed.

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
