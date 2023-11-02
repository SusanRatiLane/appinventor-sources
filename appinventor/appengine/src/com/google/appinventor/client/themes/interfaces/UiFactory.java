package com.google.appinventor.client.themes.interfaces;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.TopToolbar;
import com.google.appinventor.client.explorer.youngandroid.ProjectToolbar;
import com.google.appinventor.client.widgets.Toolbar;
import com.google.appinventor.client.wizards.youngandroid.NewYoungAndroidProjectWizard;
import com.google.gwt.user.client.ui.FlowPanel;

public interface UiFactory {
  NewYoungAndroidProjectWizard createNewYoungAndroidProjectWizard();

  FlowPanel createOde(Ode ode);

  FlowPanel createTopToolbar(TopToolbar target);

  Toolbar createProjectToolbar(ProjectToolbar target);
}
