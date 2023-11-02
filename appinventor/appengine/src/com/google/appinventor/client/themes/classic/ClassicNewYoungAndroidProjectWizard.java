package com.google.appinventor.client.themes.classic;

import com.google.appinventor.client.widgets.LabeledTextBox;
import com.google.appinventor.client.wizards.Dialog;
import com.google.appinventor.client.wizards.youngandroid.NewYoungAndroidProjectWizard;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;

public class ClassicNewYoungAndroidProjectWizard extends NewYoungAndroidProjectWizard {
  interface NewYoungAndroidProjectWizardUiBinder extends
      UiBinder<Dialog, ClassicNewYoungAndroidProjectWizard> {}

  // UI element for project name
  @UiField
  Dialog addDialog;
  @UiField
  Button addButton;
  @UiField Button cancelButton;
  @UiField
  LabeledTextBox projectNameTextBox;

  public ClassicNewYoungAndroidProjectWizard() {
    super(GWT.create(NewYoungAndroidProjectWizardUiBinder.class));
  }

  @Override
  public Dialog getAddDialog() {
    return addDialog;
  }

  @Override
  public Button getAddButton() {
    return addButton;
  }

  @Override
  public Button getCancelButton() {
    return cancelButton;
  }

  @Override
  public LabeledTextBox getProjectNameTextBox() {
    return projectNameTextBox;
  }
}
