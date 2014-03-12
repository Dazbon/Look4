package eu.heliopora.look4.business.organization.entities;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.application.users.entities.UserBusinessDetail;
import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class OrganizationUnit extends BasePersistentObjectImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	// If you modify these fields in any way, you have to modify Builder's fields and its build() method as well

	private String name;
	
	private OrganizationUnit parentOrganizationUnit;

	private Set<OrganizationUnit> childOrganizationUnits;
	
	private Set<UserBusinessDetail> userBusinessDetails;
	
	// Methods
	
	public Boolean isRootOrganizationUnit() {
		return this.getParentOrganizationUnit() == null;
	}

	// No-arguments constructor required by JPA

	protected OrganizationUnit() {

	}
	
	// Instances can only be created via Builder
	
	private OrganizationUnit(Builder builder) {
		this.name = builder.name;
		this.parentOrganizationUnit = builder.parentOrganizationUnit;
		this.childOrganizationUnits = builder.childOrganizationUnits;
		this.userBusinessDetails = builder.userBusinessDetails;
	}
	
	// Builder
	
	public static class Builder implements IBuilder<OrganizationUnit> {
		private String name;
		private OrganizationUnit parentOrganizationUnit;
		private Set<OrganizationUnit> childOrganizationUnits;
		private Set<UserBusinessDetail> userBusinessDetails;

		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder parentOrganizationUnit(OrganizationUnit parentOrganizationUnit) {
			this.parentOrganizationUnit = parentOrganizationUnit;
			return this;
		}

		public Builder childOrganizationUnits(Set<OrganizationUnit> childOrganizationUnits) {
			this.childOrganizationUnits = childOrganizationUnits;
			return this;
		}
		
		public Builder userBusinessDetails(Set<UserBusinessDetail> userBusinessDetails) {
			this.userBusinessDetails = userBusinessDetails;
			return this;
		}
		
		// Validation has to be performed on a newly created instance, not on Builder's fields (Builder is not thread-safe).

		public OrganizationUnit build() {
			OrganizationUnit organizationUnit = new OrganizationUnit(this);
			Validate.notNull(organizationUnit.getName());
			return organizationUnit;
		}
	}

	// Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public OrganizationUnit getParentOrganizationUnit() {
		return parentOrganizationUnit;
	}

	public void setParentOrganizationUnit(OrganizationUnit parentOrganizationUnit) {
		this.parentOrganizationUnit = parentOrganizationUnit;
	}

	public Set<OrganizationUnit> getChildOrganizationUnits() {
		return childOrganizationUnits;
	}

	public void setChildOrganizationUnits(Set<OrganizationUnit> childOrganizationUnits) {
		this.childOrganizationUnits = childOrganizationUnits;
	}

	public Set<UserBusinessDetail> getUserBusinessDetails() {
		return userBusinessDetails;
	}

	public void setUserBusinessDetails(Set<UserBusinessDetail> userBusinessDetails) {
		this.userBusinessDetails = userBusinessDetails;
	}
	
	// For code brevity, toString() method is created via reflection. You can provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
