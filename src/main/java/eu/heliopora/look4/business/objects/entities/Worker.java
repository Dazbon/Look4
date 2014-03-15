package eu.heliopora.look4.business.objects.entities;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class Worker extends BasePersistentObjectImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	// If you modify these fields in any way, you have to modify Builder's
	// fields and its build() method as well

	// TODO other fields
	private String personalIdentification;
	private String firstName;
	private String lastName;

	// No-arguments constructor required by JPA

	public Worker() {

	}

	// Instances can only be created via Builder
	private Worker(Builder builder) {
		this.personalIdentification = builder.personalIdentification;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
	}

	// Builder

	public static class Builder implements IBuilder<Worker> {
		private String personalIdentification;
		private String firstName;
		private String lastName;

		public Builder personalIdentification(String personalIdentification) {
			this.personalIdentification = personalIdentification;
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

		// Validation has to be performed on a newly created instance, not on
		// Builder's fields (Builder is not thread-safe).

		public Worker build() {
			Worker worker = new Worker(this);
			Validate.notNull(worker.getPersonalIdentification());
			Validate.notNull(worker.getFirstName());
			Validate.notNull(worker.getLastName());
			return worker;
		}
	}

	// Getters and Setters

	public String getPersonalIdentification() {
		return personalIdentification;
	}

	public void setPersonalIdentification(String personalIdentification) {
		this.personalIdentification = personalIdentification;
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

	// For code brevity, toString() method is created via reflection. You can
	// provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
