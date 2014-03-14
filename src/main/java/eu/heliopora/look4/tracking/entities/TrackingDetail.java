package eu.heliopora.look4.tracking.entities;

import java.io.Serializable;

import javax.sound.midi.Track;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;
import eu.heliopora.look4.tracking.entities.Resource.Builder;

public class TrackingDetail extends BasePersistentObjectImpl implements
		Serializable {

	private static final long serialVersionUID = 1L;

	// If you modify these fields in any way, you have to modify Builder's
	// fields and its build() method as well

	private TrackingDevice trackingDevice;

	// No-arguments constructor required by JPA

	public TrackingDetail() {

	}

	// Instances can only be created via Builder

	private TrackingDetail(Builder builder) {
		this.trackingDevice = builder.trackingDevice;
	}

	// Builder

	public static class Builder implements IBuilder<TrackingDetail> {
		private TrackingDevice trackingDevice;

		public Builder trackingDevice(TrackingDevice trackingDevice) {
			this.trackingDevice = trackingDevice;
			return this;
		}

		public TrackingDetail build() {
			TrackingDetail trackingDetail = new TrackingDetail(this);
			Validate.notNull(trackingDetail.getTrackingDevice());
			return trackingDetail;
		}
	}

	// Validation has to be performed on a newly created instance, not on
	// Builder's fields (Builder is not thread-safe).

	// Getters and Setters

	public TrackingDevice getTrackingDevice() {
		return trackingDevice;
	}

	public void setTrackingDevice(TrackingDevice trackingDevice) {
		this.trackingDevice = trackingDevice;
	}

	// For code brevity, toString() method is created via reflection. You can
	// provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
