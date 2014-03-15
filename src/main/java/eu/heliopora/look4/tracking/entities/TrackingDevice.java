package eu.heliopora.look4.tracking.entities;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jboss.logging.annotations.ValidIdRanges;

import eu.heliopora.look4.business.objects.entities.Worker;
import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;
import eu.heliopora.look4.tracking.entities.Resource.Builder;

public class TrackingDevice extends BasePersistentObjectImpl implements
		Serializable {

	private static final long serialVersionUID = 1L;

	// If you modify these fields in any way, you have to modify Builder's
	// fields and its build() method as well

	private String imei;
	private String telephoneNumber;
	private Worker worker;
	// private TrackingDeviceState trackingDeviceState;

	// No-arguments constructor required by JPA

	public TrackingDevice() {

	}

	// Instances can only be created via Builder

	private TrackingDevice(Builder builder) {
		this.imei = builder.imei;
		this.telephoneNumber = builder.telephoneNumber;
		this.worker = builder.worker;
	}

	// Builder
	public static class Builder {
		private String imei;
		private String telephoneNumber;
		private Worker worker;

		public Builder imei(String imei) {
			this.imei = imei;
			return this;
		}

		public Builder telephoneNumber(String telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
			return this;
		}

		public Builder worker(Worker worker) {
			this.worker = worker;
			return this;
		}

		// Validation has to be performed on a newly created instance, not on
		// Builder's fields (Builder is not thread-safe).

		public TrackingDevice build() {
			TrackingDevice trackingDevice = new TrackingDevice(this);
			Validate.notNull(trackingDevice.getImei());
			Validate.notNull(trackingDevice.getTelephoneNumber());
			Validate.notNull(trackingDevice.getWorker());
			return trackingDevice;
		}
	}

	// Getters and Setters
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	// For code brevity, toString() method is created via reflection. You can
	// provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
