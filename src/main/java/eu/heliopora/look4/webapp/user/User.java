package eu.heliopora.look4.webapp.user;

public class User {

	private Long UID;
	private Boolean bDeleted;
	private String firstName;
	private String lastName;
	private String userName;
	private String userPWD;
	private String personalID;
	private String phone;
	private String email;
	private String fkGroup;
	private String fk_orgUnit;
	
	public User(){
		
	}
	
	public User(Long UID, 
				Boolean bDeleted, 
				String firstName, 
				String lastName, 
				String userName, 
				String userPWD, 
				String personalID,
				String phone,
				String email,
				String fkGroup,
				String fk_orgUnit){
		this.setUID(UID);
		this.setbDeleted(bDeleted);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserName(userName);
		this.setUserPWD(userPWD);
		this.setPersonalID(personalID);
		this.setPhone(phone);
		this.setEmail(email);
		this.setFkGroup(fkGroup);
		this.setFk_orgUnit(fk_orgUnit);
	}

	public Long getUID() {
		return UID;
	}

	public void setUID(Long uID) {
		UID = uID;
	}

	public Boolean getbDeleted() {
		return bDeleted;
	}

	public void setbDeleted(Boolean bDeleted) {
		this.bDeleted = bDeleted;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPWD() {
		return userPWD;
	}

	public void setUserPWD(String userPWD) {
		this.userPWD = userPWD;
	}

	public String getPersonalID() {
		return personalID;
	}

	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFkGroup() {
		return fkGroup;
	}

	public void setFkGroup(String fkGroup) {
		this.fkGroup = fkGroup;
	}

	public String getFk_orgUnit() {
		return fk_orgUnit;
	}

	public void setFk_orgUnit(String fk_orgUnit) {
		this.fk_orgUnit = fk_orgUnit;
	}
	
	
}
