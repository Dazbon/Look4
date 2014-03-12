package eu.heliopora.look4.application.security;

public interface SecureEntity {

	public boolean isDeletable();
	
	public boolean isModifiable();

	public void setDeletable(boolean deletable);
	
	public void setModifiable(boolean modifiable);
 
}
