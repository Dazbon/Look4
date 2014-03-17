/**
 * 
 */
package eu.heliopora.look4.webapp.user;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

/**
 * @author pjavorek
 *
 */
public class UserServiceImpl implements UserService {

	// data model
	private long lID = 1;
	private ListModelList<User> userList = new ListModelList<User>();
	
	public UserServiceImpl(){
		/* in the future must be read from DB */
		/* for testing create just here */
		
		userList.add(new User(lID++, false, "James", "Bond", "JBond", "aaa", "007", "", "", "Admin", "Heliopora"));
		userList.add(new User(lID++, false, "Joe", "Doe", "JDoe", "aaa", "001", "", "", "Admin", "Heliopora"));
		userList.add(new User(lID++, false, "Thomas", "Smith", "TSmith", "aaa", "002", "", "", "User", "Heliopora"));
		userList.add(new User(lID++, false, "Rory", "McDonald", "RMcDonald", "aaa", "003", "", "", "User", "Heliopora"));
		userList.add(new User(lID++, false, "Jan", "Novak", "JNovak", "aaa", "101", "", "", "Admin", "Heliopora Czech"));
		userList.add(new User(lID++, false, "Franti�ek", "Novotn�", "FNovotny", "aaa", "102", "", "", "User", "Heliopora Czech"));
		userList.add(new User(lID++, true, "Josef", "Blond", "JBlond", "aaa", "107", "", "", "User", "Heliopora Czech"));
		userList.add(new User(lID++, true, "Jonas", "Afee", "JAfee", "aaa", "107", "", "", "User", "Heliopora Czech"));

	}
	
	
	/* (non-Javadoc)
	 * @see com.heliopora.helplook4.user.UserService#findAll()
	 */
	@Override
	public ListModelList<User> findAll(Boolean disableChecked) {
		// TODO Auto-generated method stub
		ListModelList<User> findUser = new ListModelList<User>();
		if (!disableChecked){		
			for (User u : userList){
				if (!u.getbDeleted()){
					findUser.add(u);
				}
			}		
		}
		else {
			findUser = userList;
		}
		return findUser;
	}

	/* (non-Javadoc)
	 * @see com.heliopora.helplook4.user.UserService#filter(java.lang.String)
	 */
	@Override
	public ListModelList<User> filter(String keyword, Boolean disableChecked) {
		// TODO Auto-generated method stub
		ListModelList<User> getModel = this.findAll(disableChecked);
		ListModelList<User> filterModel = new ListModelList<User>();
		String lowerKey = keyword.toLowerCase();
		for (User u: getModel){
			if (u.getUserName().toLowerCase().contains(lowerKey) || u.getPersonalID().toLowerCase().contains(lowerKey) || u.getFkGroup().toLowerCase().contains(lowerKey)) {
				filterModel.add(u);
			}
		}
		return filterModel;
	}

	/* (non-Javadoc)
	 * @see com.heliopora.helplook4.user.UserService#modifyUser()
	 */
	@Override
	public ListModelList<User> modifyUser(User user, ListModelList<User> modelUser) {
		// TODO Auto-generated method stub
		ListModelList<User> findList = new ListModelList<User>();
		Integer index = -1;
		for (User u: userList){
			index++;
			if (modelUser.indexOf(u) >= 0){
				if (u.getUID()==user.getUID()) {
					userList.set(index, user);
					findList.add(user);
				}
				else {
					findList.add(u);					
				}
			}
		}

		return findList;
	}

	/* (non-Javadoc)
	 * @see com.heliopora.helplook4.user.UserService#AddUser()
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		user.setUID(lID++);
		user.setbDeleted(false);
		userList.add(user);
	}

}
