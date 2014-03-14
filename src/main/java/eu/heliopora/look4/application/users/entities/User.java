package eu.heliopora.look4.application.users.entities;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class User extends BasePersistentObjectImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	// If you modify these fields in any way, you have to modify Builder's
	// fields and its build() method as well

	UserAuthenticationDetail userAuthenticationDetail;

	UserBusinessDetail userBusinessDetail;

	// No-arguments constructor required by JPA

	protected User() {

	}

	// Instances can only be created via Builder

	private User(Builder builder) {
		this.userAuthenticationDetail = builder.userAuthenticationDetail;
		this.userBusinessDetail = builder.userBusinessDetail;
	}

	// Builder

	public static class Builder implements IBuilder<User> {
		private UserAuthenticationDetail userAuthenticationDetail;
		private UserBusinessDetail userBusinessDetail;

		public Builder userAuthenticationDetails(
				UserAuthenticationDetail userAuthenticationDetail) {
			this.userAuthenticationDetail = userAuthenticationDetail;
			return this;
		}

		public Builder userBusinessDetails(UserBusinessDetail userBusinessDetail) {
			this.userBusinessDetail = userBusinessDetail;
			return this;
		}

		// Validation has to be performed on a newly created instance, not on
		// Builder's fields (Builder is not thread-safe).

		public User build() {
			User user = new User(this);
			Validate.notNull(user.getUserAuthenticationDetail());
			Validate.notNull(user.getUserBusinessDetail());
			return user;
		}
	}

	// Getters and Setters

	public UserAuthenticationDetail getUserAuthenticationDetail() {
		return userAuthenticationDetail;
	}

	public void setUserAuthenticationDetail(
			UserAuthenticationDetail userAuthenticationDetail) {
		this.userAuthenticationDetail = userAuthenticationDetail;
	}

	public UserBusinessDetail getUserBusinessDetail() {
		return userBusinessDetail;
	}

	public void setUserBusinessDetail(UserBusinessDetail userBusinessDetail) {
		this.userBusinessDetail = userBusinessDetail;
	}

	// For code brevity, toString() method is created via reflection. You can
	// provide custom implementation, if needed.

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
