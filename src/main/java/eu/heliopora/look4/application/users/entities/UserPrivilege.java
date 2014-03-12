package eu.heliopora.look4.application.users.entities;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class UserPrivilege extends BasePersistentObjectImpl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// If you modify these fields in any way, you have to modify Builder's fields and its build() method as well

	private String code;
	
	private String name;

	private String description;
	
	private Set<UserRole> userRoles;
	
	// No-arguments constructor required by JPA
	
	protected UserPrivilege() {
		
	}
	
	// Instances can only be created via Builder
	
	private UserPrivilege(Builder builder) {
		this.code = builder.code;
		this.name = builder.name;
		this.description = builder.description;
		this.userRoles = builder.userRoles;
	}
	
	// Builder
	
	public static class Builder implements IBuilder<UserPrivilege> {
		private String code;
		private String name;
		private String description;
		private Set<UserRole> userRoles;

		public Builder code(String code) {
			this.code = code;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}
		
		public Builder userRoles(Set<UserRole> userRoles) {
			this.userRoles = userRoles;
			return this;
		}

		// Validation has to be performed on a newly created instance, not on Builder's fields (Builder is not thread-safe).
		
		public UserPrivilege build() {
			UserPrivilege userPrivilege = new UserPrivilege(this);
			Validate.notNull(userPrivilege.getCode());
			Validate.notNull(userPrivilege.getName());
			return userPrivilege;
		}
	}

	// Getters and Setters
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
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
		
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	// For code brevity, toString() method is created via reflection. You can provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
