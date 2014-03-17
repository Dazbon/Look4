package eu.heliopora.look4.webapp.user;

import java.util.List;

import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

public interface UserService {

	/* Find All users*/
	public ListModelList<User> findAll(Boolean disableChecked);
	
	/* Filter based on keyword in textbox */
	public ListModelList<User> filter(String keyword, Boolean disableChecked);
	
	/* Modify user */
	public ListModelList<User> modifyUser(User user, ListModelList<User> modelUser);
	
	/* Add user */
	public void addUser(User user);
}
