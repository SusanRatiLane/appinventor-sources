package com.google.appinventor.client;

import com.google.appinventor.client.wizards.Dialog;
import com.google.appinventor.client.wizards.youngandroid.NewYoungAndroidProjectWizard;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.FlowPanel;

public class UIBinderStyle {
  public static final String design = "com.google.appinventor.client.style.classic.";

  @UiTemplate(design + "Ode.ui.xml")
  public interface OdeUiBinder extends UiBinder<FlowPanel, Ode> {}

  @UiTemplate(design + "NewYoungAndroidProjectWizard.ui.xml")
  public interface NewYoungAndroidProjectWizardUiBinder extends UiBinder<Dialog, NewYoungAndroidProjectWizard> {}

  public OdeUiBinder getOdeUiBinder() {
    return GWT.create(OdeUiBinder.class);
  }

  public NewYoungAndroidProjectWizardUiBinder getNewYoungAndroidProjectWizardUiBinder() {
    return GWT.create(NewYoungAndroidProjectWizardUiBinder.class);
  }
}
