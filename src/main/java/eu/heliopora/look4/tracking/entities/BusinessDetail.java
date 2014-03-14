package eu.heliopora.look4.tracking.entities;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.application.users.entities.User;
import eu.heliopora.look4.application.users.entities.UserAuthenticationDetail;
import eu.heliopora.look4.application.users.entities.UserBusinessDetail;
import eu.heliopora.look4.application.users.entities.User.Builder;
import eu.heliopora.look4.business.objects.entities.BusinessObject;
import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class BusinessDetail extends BasePersistentObjectImpl implements
		Serializable {

	private static final long serialVersionUID = 1L;

	// If you modify these fields in any way, you have to modify Builder's
	// fields and its build() method as well
	
	private BusinessObject businessObject;

	// No-arguments constructor required by JPA

	public BusinessDetail() {

	}

	// Instances can only be created via Builder

	private BusinessDetail(Builder builder) {
		this.businessObject = builder.businessObject;
	}

	// Builder

	public static class Builder implements IBuilder<BusinessDetail> {
		private BusinessObject businessObject;

		public Builder businessObject(BusinessObject businessObject) {
			this.businessObject = businessObject;
			return this;
		}

		// Validation has to be performed on a newly created instance, not on
		// Builder's fields (Builder is not thread-safe).

		public BusinessDetail build() {
			BusinessDetail businessDetail = new BusinessDetail(this);
			Validate.notNull(businessDetail.getBusinessObject());
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

	// For code brevity, toString() method is created via reflection. You can
	// provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
