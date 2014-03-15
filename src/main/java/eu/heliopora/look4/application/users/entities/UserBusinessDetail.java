package eu.heliopora.look4.application.users.entities;

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
import org.springframework.stereotype.Repository;

import eu.heliopora.look4.application.utils.ICustomerSpecificPropertiesProvider;
import eu.heliopora.look4.business.organization.entities.OrganizationUnit;
import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

@Repository
public class UserBusinessDetail extends BasePersistentObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    static final Logger log = LogManager.getLogger(UserBusinessDetail.class);

    @Transient
    private static ServletContext servletContext;

    @Transient
    private static ICustomerSpecificPropertiesProvider customerSpecificPropertiesProvider;

    // If you modify these fields in any way, you have to modify Builder's fields and its build()
    // method as well

    private String internalNumber;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Set<UserRole> userRoles;

    private Set<OrganizationUnit> organizationUnits;

    private User user;

    // No-arguments constructor required by JPA

    protected UserBusinessDetail() {

    }

    // Instances can only be created via Builder

    private UserBusinessDetail(Builder builder) {
        this.internalNumber = builder.internalNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.userRoles = builder.userRoles;
        this.organizationUnits = builder.organizationUnits;
        this.user = builder.user;
    }

    // Builder

    public static class Builder implements IBuilder<UserBusinessDetail> {
        private String internalNumber;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private Set<UserRole> userRoles;
        private Set<OrganizationUnit> organizationUnits;
        private User user;

        public Builder internalNumber(String internalNumber) {
            this.internalNumber = internalNumber;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder userRoles(Set<UserRole> userRoles) {
            this.userRoles = userRoles;
            return this;
        }

        public Builder organizationUnits(Set<OrganizationUnit> organizationUnits) {
            this.organizationUnits = organizationUnits;
            return this;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        // Validation has to be performed on a newly created instance, not on Builder's fields
        // (Builder
        // is not thread-safe).

        public UserBusinessDetail build() {
            UserBusinessDetail userBusinessDetail = new UserBusinessDetail(this);
            if (customerSpecificPropertiesProvider.getUserInternalNumberRequired()) {
                Validate.notNull(userBusinessDetail.getUser());
            }
            // Validate.notNull(userBusinessDetail.getFirstName());
            // Validate.notNull(userBusinessDetail.getLastName());
            // Validate.notNull(userBusinessDetail.getEmail());
            // Validate.notNull(userBusinessDetail.getPhoneNumber());
            // Validate.notNull(userBusinessDetail.getUserRoles());
            // Validate.notNull(userBusinessDetail.getOrganizationUnits());
            return userBusinessDetail;
        }
    }

    // Getters and Setters

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<OrganizationUnit> getOrganizationUnits() {
        return organizationUnits;
    }

    public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
        this.organizationUnits = organizationUnits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Setting and initializing Servlet context, so we can inject Spring Beans into Hibernate
    // Entities

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        UserBusinessDetail.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing ServletContext as [{}]", UserBusinessDetail.servletContext.toString());
    }

    @Autowired
    public void setCustomerSpecificPropertiesProvider(ICustomerSpecificPropertiesProvider customerSpecificPropertiesProvider) {
        UserBusinessDetail.customerSpecificPropertiesProvider = customerSpecificPropertiesProvider;
    }

    // For code brevity, toString() method is created via reflection. You can provide custom
    // implementation, if needed.

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
