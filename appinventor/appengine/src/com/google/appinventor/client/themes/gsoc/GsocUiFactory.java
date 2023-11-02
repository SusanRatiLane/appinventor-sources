package com.google.appinventor.client.themes.gsoc;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.OdeUiBinderGsoc;
import com.google.appinventor.client.ProjectToolbarUiBinderGsoc;
import com.google.appinventor.client.TopToolbar;
import com.google.appinventor.client.TopToolbarUiBinderGsoc;
import com.google.appinventor.client.explorer.youngandroid.ProjectToolbar;
import com.google.appinventor.client.themes.interfaces.UiFactory;
import com.google.appinventor.client.widgets.Toolbar;
import com.google.appinventor.client.wizards.youngandroid.NewYoungAndroidProjectWizard;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;

public class GsocUiFactory implements UiFactory {
  public NewYoungAndroidProjectWizard createNewYoungAndroidProjectWizard() {
    return new GsocNewYoungAndroidProjectWizard();
  }

  public FlowPanel createOde(Ode ode) {
    return ((OdeUiBinderGsoc) GWT.create(OdeUiBinderGsoc.class)).createAndBindUi(ode);
  }

  public FlowPanel createTopToolbar(TopToolbar target) {
    return ((TopToolbarUiBinderGsoc) GWT.create(TopToolbarUiBinderGsoc.class)).createAndBindUi(target);
  }

  public Toolbar createProjectToolbar(ProjectToolbar target) {
    return ((ProjectToolbarUiBinderGsoc) GWT.create(ProjectToolbarUiBinderGsoc.class)).createAndBindUi(target);
  }
}
