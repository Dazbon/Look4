package eu.heliopora.look4.tracking.entities;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.business.objects.entities.BusinessObject;
import eu.heliopora.look4.business.objects.entities.BusinessObject.Builder;
import eu.heliopora.look4.business.organization.entities.OrganizationUnit;
import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class Resource extends BasePersistentObjectImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	// If you modify these fields in any way, you have to modify Builder's
	// fields and its build() method as well

	TrackingDetail trackingDetail;

	BusinessDetail businessDetail;

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
		private TrackingDetail trackingDetail;
		private BusinessDetail businessDetail;

		public Builder trackingDetail(TrackingDetail trackingDetail) {
			this.trackingDetail = trackingDetail;
			return this;
		}

		public Builder businessDetail(BusinessDetail businessDetail) {
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

	public TrackingDetail getTrackingDetail() {
		return trackingDetail;
	}

	public void setTrackingDetail(TrackingDetail trackingDetail) {
		this.trackingDetail = trackingDetail;
	}

	public BusinessDetail getBusinessDetail() {
		return businessDetail;
	}

	public void setBusinessDetail(BusinessDetail businessDetail) {
		this.businessDetail = businessDetail;
	}

	// For code brevity, toString() method is created via reflection. You can
	// provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
