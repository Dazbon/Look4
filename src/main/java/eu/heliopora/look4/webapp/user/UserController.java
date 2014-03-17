/**
 * 
 */
package eu.heliopora.look4.webapp.user;

/**
 * @author pjavorek
 * 
 */

import java.util.HashMap;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class UserController extends SelectorComposer<Window> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -694636425084135413L;
    private UserService userService = new UserServiceImpl();
    private ListModel<User> userList = new ListModelList<User>();
    private User user = new User();
    private Integer selectionIndex = 0;
    private Boolean activeFilter = false;
    private String filterKey = "";
    List<Component> component;


    /* Wire main window opened in TAB */
    @Wire
    private Window userTabWindow;

    /* Wire textbox for entering data */
    @Wire("#incHeader #hlHeaderDlg #incFilter > #txboxFilter")
    private Textbox txboxFilter;

    /* Wire listbox with users */
    @Wire("#inclbUsers > #lbUserList")
    private Listbox lbUserList;

    /* Wire checkbox include Disabled */
    @Wire("#incHeader #hlHeaderDlg #incDisabled > #cbDisable")
    private Checkbox cbDisable;

    /* Wire button Add */
    @Wire("#incButtonsDlg #hlButtons > #btnAdd")
    private Button btnAdd;

    /* Wire button Modify */
    @Wire("#incButtonsDlg #hlButtons > #btnModify")
    private Button btnModify;

    /* Wire button Enable */
    @Wire("#incButtonsDlg #hlButtons > #btnEnable")
    private Button btnEnable;

    /* Wire button Disable */
    @Wire("#incButtonsDlg #hlButtons > #btnDisable")
    private Button btnDisable;


    @Listen("onSelect = #inclbUsers > #lbUserList")
    public void liSelected() {
        User user;
        userList = lbUserList.getModel();
        selectionIndex = lbUserList.getSelectedIndex();
        user = userList.getElementAt(selectionIndex);
        btnEnable.setDisabled(!user.getbDeleted());
        btnDisable.setDisabled(user.getbDeleted());
        btnModify.setDisabled(false);
    }


    /* Button Apply filter */
    @Listen("onClick = #incHeader #hlHeaderDlg #incFilter #btnApplyFilter")
    public void bApplyFilter() {
        filterKey = txboxFilter.getValue();
        if (filterKey != "" || filterKey != null) {
            userList = lbUserList.getModel();
            userList = userService.filter(txboxFilter.getValue(), cbDisable.isChecked());
            lbUserList.setModel(userList);
            activeFilter = true;
        }
    }

    /* Button Cancel filter */
    @Listen("onClick = #incHeader #hlHeaderDlg #incFilter #btnCancelFilter")
    public void bCancelFilter() {
        activeFilter = false;
        userList = userService.findAll(cbDisable.isChecked());
        lbUserList.setModel(userList);
    }

    /* Button Refresh view */
    @Listen("onClick = #incHeader #hlHeaderDlg #incFilter #btnRefreshView")
    public void bRefreshView() {
        lbUserList.setModel(userList);
    }

    /* Button Add... */
    @Listen("onClick = #incButtonsDlg #btnAdd")
    public void bAddUser() {
        openDialog(false);
    }

    /* Button Modify... */
    @Listen("onClick = #incButtonsDlg #btnModify")
    public void bModifyUser() {
        selectionIndex = lbUserList.getSelectedIndex();
        openDialog(true);
    }

    private void openDialog(boolean bModify) {
        Window win;
        String sPage, sTitle;
        User user;
        final HashMap<String, Object> map = new HashMap<String, Object>();
        sTitle = (bModify ? Labels.getLabel("application.title.modifyuser") : Labels.getLabel("application.title.addusers"));
        map.put("parentWindow", userTabWindow);
        map.put("Modify", bModify);
        if (bModify) {
            user = userList.getElementAt(lbUserList.getSelectedIndex());
            map.put("userData", user);
        }
        sPage = "/layout/users/users_edit.zul";
        win = (Window) Executions.getCurrent().createComponents(sPage, userTabWindow, map);
        win.setTitle(sTitle);
        win.setMinwidth(800);
        win.setMinheight(300);
        win.doModal();
        if (bModify) {
            lbUserList.setSelectedIndex(selectionIndex);
        }
    }

    /* Button Enable */
    @Listen("onClick=#incButtonsDlg #btnEnable")
    public void bEnableUser() {
        Integer lIndex = lbUserList.getSelectedIndex();
        String filterKey = "";
        if (lIndex >= 0) {
            User selUser = userList.getElementAt(lIndex);
            selUser.setbDeleted(false);
            userList = lbUserList.getModel();
            userList = userService.modifyUser(selUser, (ListModelList<User>) userList);
            if (activeFilter) {
                filterKey = txboxFilter.getValue();
            }
            userList = userService.filter(filterKey, cbDisable.isChecked());
            lbUserList.setModel(userList);
        }
        btnDisable.setDisabled(true);
        btnEnable.setDisabled(true);
        btnModify.setDisabled(true);
        lbUserList.setSelectedIndex(lIndex);
        lbUserList.setFocus(true);
    }

    /* Button Disable */
    @Listen("onClick = #incButtonsDlg #btnDisable")
    public void bDisableUser() {
        Integer lIndex = lbUserList.getSelectedIndex();
        userList = lbUserList.getModel();
        if (lIndex >= 0) {
            User selUser = userList.getElementAt(lIndex);
            selUser.setbDeleted(true);
            userList = lbUserList.getModel();
            userList = userService.modifyUser(selUser, (ListModelList<User>) userList);
            if (activeFilter) {
                filterKey = txboxFilter.getValue();
            }
            userList = userService.filter(filterKey, cbDisable.isChecked());
            lbUserList.setModel(userList);
        }
        btnDisable.setDisabled(true);
        btnEnable.setDisabled(true);
        btnModify.setDisabled(true);
    }

    /* Checkbox disabled listen */
    @Listen("onCheck = #incHeader #hlHeaderDlg #incDisabled > #cbDisable")
    public void includeDis() {
        if (activeFilter) {
            userList = userService.filter(filterKey, cbDisable.isChecked());
        } else {
            userList = userService.findAll(cbDisable.isChecked());
        }
        lbUserList.setModel(userList);
    }

    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        btnModify.setDisabled(true);
        btnEnable.setDisabled(true);
        btnDisable.setDisabled(true);
        userList = userService.findAll(cbDisable.isChecked());
        lbUserList.setModel(userList);
        Hbox hbOrg = (Hbox) Path.getComponent("//mainWindow/idMainWindow/hbOrgUnit");
        Combobox cbOrg;
        cbOrg = (Combobox) Path.getComponent("//mainWindow/idMainWindow/hbOrgUnit/cbOrgUnit");
        if (cbOrg != null) {
            alert(cbOrg.getValue());
        }
        // component = hbOrg.getChildren();
        // alert("doaftercompose");
    }

    @Listen("onCancel = #userTabWindow")
    public void onCancel() {
        alert("CANCEL DIALOG");
    }

    @Listen("onSaveUser = #userTabWindow")
    public void onSaveUser(Event event) {
        final HashMap<String, Object> map = (HashMap<String, Object>) event.getData();
        boolean bModify;
        Integer iCount;
        User user = (User) map.get("Userdata");
        bModify = (Boolean) map.get("Modify");
        if (bModify) {
            /* Modify current selection */
            userList = lbUserList.getModel();
            userList = userService.modifyUser(user, (ListModelList<User>) userList);
            lbUserList.setModel(userList);

        } else {
            /* Add new user */
            Listitem liItem;
            userService.addUser(user);
            userList = userService.findAll(cbDisable.isChecked());
            if (activeFilter) {
                userList = userService.filter(txboxFilter.getValue(), cbDisable.isChecked());
            }
            lbUserList.setModel(userList);
            /*
             * lbUserList.setFocus(true); iCount = lbUserList.getItemCount(); liItem =
             * lbUserList.getItemAtIndex(iCount-1); lbUserList.setSelectedItem(liItem);
             * lbUserList.setSelectedIndex(iCount-1);
             */
        }

    }
}
