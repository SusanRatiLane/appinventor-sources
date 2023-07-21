package com.google.appinventor.client;

import com.google.appinventor.client.editor.FileEditor;
import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.simple.components.MockForm;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.appinventor.client.explorer.project.Project;
import com.google.appinventor.client.explorer.project.ProjectChangeListener;
import com.google.appinventor.shared.rpc.project.ProjectNode;
import com.google.appinventor.shared.settings.SettingsConstants;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;

import static com.google.appinventor.client.Ode.MESSAGES;

public class DeviceViewOptions extends FlowPanel implements ProjectChangeListener {
  private final ListBox listboxPhoneTablet; // A ListBox for Phone/Tablet/Monitor preview sizes
  private final ListBox listboxPhonePreview; // A ListBox for Holo/Material/iOS preview styles
  private final int[][] drop_lst = { {320, 505}, {480, 675}, {768, 1024} };
  private final String[] drop_lst_phone_preview = { "Android Material", "Android Holo", "iOS" };
  ProjectEditor projectEditor;
  MockForm form;

  public DeviceViewOptions() {
    listboxPhoneTablet = new ListBox();
    listboxPhoneTablet.addItem("Phone size");
    listboxPhoneTablet.addItem("Tablet size");
    listboxPhoneTablet.addItem("Monitor size");
    listboxPhoneTablet.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        if (projectEditor != null) {
          int idx = listboxPhoneTablet.getSelectedIndex();
          int width = drop_lst[idx][0];
          int height = drop_lst[idx][1];
          String val = Integer.toString(idx) + "," + Integer.toString(width) + "," + Integer.toString(height);
          // here, we can change settings by putting val into it
          projectEditor.changeProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
              SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_TABLET, val);
          changeFormPreviewSize(idx, width, height);
        }
      }
    });
    this.add(listboxPhoneTablet);

    listboxPhonePreview = new ListBox() ;
    listboxPhonePreview.addItem("Android 5+ Devices");
    listboxPhonePreview.addItem("Android 3.0-4.4.2 Devices");
    listboxPhonePreview.addItem("iOS 13 Devices");
    listboxPhonePreview.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        if (projectEditor != null) {
          int idx = listboxPhonePreview.getSelectedIndex();
          String val = drop_lst_phone_preview[idx];
          // here, we can change settings by putting chosenStyle value into it
          projectEditor.changeProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
              SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_PREVIEW, val);
          changeFormPhonePreview(idx, val);
        }
      }
    });
    this.add(listboxPhoneTablet);
    Ode.getInstance().setDeviceViewOptions(this);
  }

  public void refresh() {
    FileEditor currentFileEditor = Ode.getInstance().getCurrentFileEditor();
    form = ((YaFormEditor)currentFileEditor).getForm();
    projectEditor = Ode.getCurrentProjectEditor();

    // onLoad is called immediately after a widget becomes attached to the browser's document.
    String sizing = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_SIZING);
    boolean fixed = (sizing.equals("Fixed"));
    listboxPhoneTablet.setVisible(!fixed);
    if (fixed) {
      changeFormPreviewSize(0, 320, 505);
    } else {
      getUserSettingChangeSize();
    }

    // onLoad is called immediately after a widget becomes attached to the browser's document.
    String previewStyle = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_THEME);
    boolean classic = (previewStyle.equals("Classic"));
    listboxPhonePreview.setVisible(!classic);
    if (classic) {
      changeFormPhonePreview(-1, "Classic");
    } else {
      getUserSettingChangePreview();
    }
  }

  private void changeFormPreviewSize(int idx, int width, int height) {

    if (form == null)
      return;

    form.changePreviewSize(width, height, idx);

    String info = " (" + height + "," + width + ")";
    if (idx == 0) {
      listboxPhoneTablet.setItemText(idx, MESSAGES.previewPhoneSize() + info);
      listboxPhoneTablet.setItemText(1, MESSAGES.previewTabletSize());
      listboxPhoneTablet.setItemText(2, MESSAGES.previewMonitorSize());
    } else if (idx == 1) {
      listboxPhoneTablet.setItemText(idx, MESSAGES.previewTabletSize() + info);
      listboxPhoneTablet.setItemText(0, MESSAGES.previewPhoneSize());
      listboxPhoneTablet.setItemText(2, MESSAGES.previewMonitorSize());
    } else {
      listboxPhoneTablet.setItemText(idx, MESSAGES.previewMonitorSize() + info);
      listboxPhoneTablet.setItemText(0, MESSAGES.previewPhoneSize());
      listboxPhoneTablet.setItemText(1, MESSAGES.previewTabletSize());
    }
    // change settings
  }

  private void changeFormPhonePreview(int idx, String chosenVal) {

    if (form == null)
      return;

    form.changePhonePreview(idx, chosenVal);

    String info = " (" + chosenVal + ")";
    if (idx == 0) {
      listboxPhonePreview.setItemText(idx, MESSAGES.previewAndroidMaterial() + info);
      listboxPhonePreview.setItemText(1, MESSAGES.previewAndroidHolo());
      listboxPhonePreview.setItemText(2, MESSAGES.previewIOS());
    } else if (idx == 1) {
      listboxPhonePreview.setItemText(idx, MESSAGES.previewAndroidHolo() + info);
      listboxPhonePreview.setItemText(0, MESSAGES.previewAndroidMaterial());
      listboxPhonePreview.setItemText(2, MESSAGES.previewIOS());
    } else if (idx == 2){
      listboxPhonePreview.setItemText(idx, MESSAGES.previewIOS() + info);
      listboxPhonePreview.setItemText(0, MESSAGES.previewAndroidMaterial());
      listboxPhonePreview.setItemText(1, MESSAGES.previewAndroidHolo());
    }
    // change settings
  }
  // get width and height stored in user settings, and change the preview size.
  private void getUserSettingChangeSize() {
    String val = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_TABLET);
    int idx = 0;
    int width = 320;
    int height = 505;

    if (val.equals("True")) {
      idx = 1;
      width = drop_lst[idx][0];
      height = drop_lst[idx][1];
    }

    String[] parts = val.split(",");
    if (parts.length == 3) {
      idx = Integer.parseInt(parts[0]);
      width = Integer.parseInt(parts[1]);
      height = Integer.parseInt(parts[2]);
    }
    listboxPhoneTablet.setItemSelected(idx, true);
    changeFormPreviewSize(idx, width, height);
  }

  // get Phone Preview stored in user settings, and change the preview style.
  private void getUserSettingChangePreview() {
    String val = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_PREVIEW);
    int idx = 0;

    if (val.equals("Classic")) {
      val = "Android Material";
    }

    if (val.equals("Android Holo")) {
      idx = 1;
    } else if (val.equals("iOS")) {
      idx = 2;
    }
    listboxPhonePreview.setItemSelected(idx, true);
    changeFormPhonePreview(idx, val);
  }

  @Override
  public void onProjectLoaded(Project project) {
    refresh();
  }

  @Override
  public void onProjectNodeAdded(Project project, ProjectNode node) {

  }

  @Override
  public void onProjectNodeRemoved(Project project, ProjectNode node) {

  }
}
