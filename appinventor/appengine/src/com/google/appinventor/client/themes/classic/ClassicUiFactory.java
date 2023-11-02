package com.google.appinventor.client.themes.classic;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.OdeUiBinderClassic;
import com.google.appinventor.client.TopToolbar;
import com.google.appinventor.client.TopToolbarUiBinderClassic;
import com.google.appinventor.client.explorer.youngandroid.ProjectToolbar;
import com.google.appinventor.client.explorer.youngandroid.ProjectToolbarUiBinderClassic;
import com.google.appinventor.client.themes.interfaces.UiFactory;
import com.google.appinventor.client.widgets.Toolbar;
import com.google.appinventor.client.wizards.youngandroid.NewYoungAndroidProjectWizard;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;

public class ClassicUiFactory implements UiFactory {
  public NewYoungAndroidProjectWizard createNewYoungAndroidProjectWizard() {
    return new ClassicNewYoungAndroidProjectWizard();
  }

  public FlowPanel createOde(Ode ode) {
    return ((OdeUiBinderClassic) GWT.create(OdeUiBinderClassic.class)).createAndBindUi(ode);
  }

  public FlowPanel createTopToolbar(TopToolbar target) {
    return ((TopToolbarUiBinderClassic) GWT.create(TopToolbarUiBinderClassic.class))
        .createAndBindUi(target);
  }

  public Toolbar createProjectToolbar(ProjectToolbar target) {
    return ((ProjectToolbarUiBinderClassic) GWT.create(ProjectToolbarUiBinderClassic.class))
        .createAndBindUi(target);
  }
}
