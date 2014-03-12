package eu.heliopora.look4.application.users.entities;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.heliopora.look4.commons.entities.base.BasePersistentObjectImpl;
import eu.heliopora.look4.commons.interfaces.IBuilder;

public class UserAuthenticationDetail extends BasePersistentObjectImpl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// If you modify these fields in any way, you have to modify Builder's fields and its build() method as well

	private String username;

	private String password;
	
	private Date lastLogin;

	private boolean credentialsNonExpired;

	private boolean enabled;
	
	private User user;
	
	// No-arguments constructor required by JPA

	protected UserAuthenticationDetail() {

	}
	
	// Instances can only be created via Builder

	private UserAuthenticationDetail(Builder builder) {
		super();
		this.username = builder.username;
		this.password = builder.password;
		this.lastLogin = builder.lastLogin;
		this.credentialsNonExpired = builder.credentialsNonExpired;
		this.enabled = builder.enabled;
		this.user = builder.user;
	}
	
	// Builder
	
	public static class Builder implements IBuilder<UserAuthenticationDetail> {
		private String username;
		private String password;
		private Date lastLogin;
		private boolean credentialsNonExpired;
		private boolean enabled;
		private User user;
		
		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder lastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
			return this;
		}

		public Builder credentialsNonExpired(boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
			return this;
		}

		public Builder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}
		
		public Builder enabled(User user) {
			this.user = user;
			return this;
		}
		
		// Validation has to be performed on a newly created instance, not on Builder's fields (Builder is not thread-safe).

		public UserAuthenticationDetail build() {
			UserAuthenticationDetail userAuthenticationDetail = new UserAuthenticationDetail(this);
			Validate.notNull(userAuthenticationDetail.getUsername());
			Validate.notNull(userAuthenticationDetail.getPassword());
			Validate.notNull(userAuthenticationDetail.getEnabled());
			Validate.notNull(userAuthenticationDetail.getCredentialsNonExpired());
			return new UserAuthenticationDetail(this);
		}
	}
	
	// Getters and Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	// For code brevity, toString() method is created via reflection. You can provide custom implementation, if needed.
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
