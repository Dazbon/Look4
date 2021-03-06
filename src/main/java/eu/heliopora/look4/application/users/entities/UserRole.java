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

import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

@Repository
public class UserRole extends BasePersistentObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    // Logger

    static final Logger log = LogManager.getLogger(UserRole.class);

    // Servlet context

    @Transient
    private static ServletContext servletContext;

    // If you modify these fields in any way, you have to modify Builder's fields and its build()
    // method as well

    private String name;

    private String description;

    private Set<UserBusinessDetail> userBusinessDetails;

    private Set<UserPrivilege> userPrivileges;

    // No-arguments constructor required by JPA

    protected UserRole() {

    }

    // Instances can only be created via Builder

    private UserRole(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.userBusinessDetails = builder.userBusinessDetails;
        this.userPrivileges = builder.userPrivileges;
    }

    // Builder

    public static class Builder implements IBuilder<UserRole> {
        private String name;
        private String description;
        private Set<UserBusinessDetail> userBusinessDetails;
        private Set<UserPrivilege> userPrivileges;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder userBusinessDetails(Set<UserBusinessDetail> userBusinessDetails) {
            this.userBusinessDetails = userBusinessDetails;
            return this;
        }

        public Builder userPrivileges(Set<UserPrivilege> userPrivileges) {
            this.userPrivileges = userPrivileges;
            return this;
        }

        // Validation has to be performed on a newly created instance, not on Builder's fields
        // (Builder is not thread-safe).

        public UserRole build() {
            UserRole userRole = new UserRole(this);
            Validate.notNull(userRole.getName());
            return userRole;
        }
    }

    // Getters and Setters

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserBusinessDetail> getUserBusinessDetails() {
        return userBusinessDetails;
    }

    public void setUserBusinessDetails(Set<UserBusinessDetail> userBusinessDetails) {
        this.userBusinessDetails = userBusinessDetails;
    }

    public Set<UserPrivilege> getUserPrivileges() {
        return userPrivileges;
    }

    public void setUserPrivileges(Set<UserPrivilege> userPrivileges) {
        this.userPrivileges = userPrivileges;
    }

    // Setting and initializing Servlet context, so we can inject Spring Beans into Hibernate
    // Entities

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        UserRole.servletContext = servletContext;
    }

    @PostConstruct
    public void init() {
        log.info("Initializing ServletContext as [{}]", UserRole.servletContext.toString());
    }

    // For code brevity, toString() method is created via reflection. You can provide custom
    // implementation, if needed.

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
