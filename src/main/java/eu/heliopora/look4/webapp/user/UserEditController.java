package eu.heliopora.look4.webapp.user;

import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class UserEditController extends SelectorComposer<Window>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4167756507769097437L;
	private User userData;
	private Window parentDialog;
	private Boolean bModify;

	@Wire ("#UserDialog")
	private Window UserDialog;
	
	/************************************************************************************
	 * 														  							*
	 * 																					*
	 * Wire all input values from grid													*
	 * 																					*
	 ***********************************************************************************/
	
	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwUserName > #tbxUserName")
	private Textbox tbxUserName;

	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwFName > #tbxFName")
	private Textbox tbxFName;
	
	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwLName > #tbxLName")
	private Textbox tbxLName;

	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwPWD > #tbxPWD")
	private Textbox tbxPWD;

	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwRePWD > #tbxRePWD")
	private Textbox tbxRePWD;
	
	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwGroup > #cbxGroup")
	private Combobox cbxGroup;

	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwRole > #cbxRole")
	private Combobox cbxRole;

	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwPersID > #tbxPersID")
	private Textbox tbxPersID;

	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwPhone > #tbxPhone")
	private Textbox tbxPhone;

	@Wire ("#UserDialog #incUserEditForm #grUserEdit #rwsUserEdit #rwEmail > #tbxEmail")
	private Textbox tbxEmail;

	/*
	 * Wire buttons
	 */
	
	@Wire ("#btnSave")
	private Button btnSave;
	
	@Wire ("#btnCancel")
	private Button btnCancel;
	
	@Listen ("onClick = #btnCancel")
	public void cancelForm(){
        UserDialog.detach();
        Events.sendEvent(new Event("onCancel", parentDialog, null));
	}
	
	@Listen ("onClick = #btnSave")
	public void saveForm(){
		final HashMap<String, Object> map = new HashMap<String, Object>();
		getDataGrid();
		if (tbxUserName.getErrorMessage() == null){
			map.put("Userdata",userData);
			map.put("Modify", bModify);
			UserDialog.detach();
			Events.sendEvent(new Event("onSaveUser", parentDialog, map));
		}
	}
	
	public void getDataGrid(){
		userData.setUserName(tbxUserName.getValue());
		userData.setFirstName(tbxFName.getValue());
		userData.setLastName(tbxLName.getValue());
		userData.setUserPWD(tbxPWD.getValue());
		userData.setFkGroup(cbxGroup.getValue());
		userData.setPersonalID(tbxPersID.getValue());
		userData.setPhone(tbxPhone.getValue());
		userData.setEmail(tbxEmail.getValue());
	}
	
	public void doAfterCompose(Window comp) throws Exception {
       	super.doAfterCompose(comp);
       	final Execution execution = Executions.getCurrent();
        parentDialog=(Window)execution.getArg().get("parentWindow");
        bModify=(Boolean)execution.getArg().get("Modify");
        if (bModify) {
	        userData=(User)execution.getArg().get("userData");
	        tbxUserName.setValue(userData.getUserName());
	        tbxFName.setValue(userData.getFirstName());
	        tbxLName.setValue(userData.getLastName());
	        tbxPWD.setValue(userData.getUserPWD());
	        tbxRePWD.setValue(userData.getUserPWD());
	        cbxGroup.setValue(userData.getFkGroup());
	        tbxPersID.setValue(userData.getPersonalID());
	        tbxPhone.setValue(userData.getPhone());
	        tbxEmail.setValue(userData.getEmail());
        }
        else {
        	userData = new User();
        }
    }

}
