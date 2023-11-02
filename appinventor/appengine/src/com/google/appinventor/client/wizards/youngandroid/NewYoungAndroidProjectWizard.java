// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.wizards.youngandroid;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.explorer.project.Project;
import com.google.appinventor.client.tracking.Tracking;
import com.google.appinventor.client.widgets.LabeledTextBox;
import com.google.appinventor.client.widgets.Validator;
import com.google.appinventor.client.wizards.Dialog;
import com.google.appinventor.client.wizards.NewProjectWizard;
import com.google.appinventor.client.youngandroid.TextValidators;
import com.google.appinventor.common.utils.StringUtils;
import com.google.appinventor.shared.rpc.project.youngandroid.NewYoungAndroidProjectParameters;
import com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidProjectNode;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import java.util.logging.Logger;


/**
 * Wizard for creating new Young Android projects.
 *
 * @author markf@google.com (Mark Friedman)
 */
public abstract class NewYoungAndroidProjectWizard {
  private static final Logger LOG = Logger.getLogger(NewYoungAndroidProjectWizard.class.getName());

  /**
   * Creates a new YoungAndroid project wizard.
   */
  protected NewYoungAndroidProjectWizard(UiBinder<Dialog, NewYoungAndroidProjectWizard> theme) {
    theme.createAndBindUi(this);
    getProjectNameTextBox().setValidator(new Validator() {
      @Override
      public boolean validate(String value) {
        errorMessage = TextValidators.getErrorMessage(value);
        getProjectNameTextBox().setErrorMessage(errorMessage);
        if (errorMessage.length() > 0) {
          getAddButton().setEnabled(false);
          return false;
        }
        errorMessage = TextValidators.getWarningMessages(value);
        getAddButton().setEnabled(true);
        return true;
      }
      @Override
      public String getErrorMessage() {
        return errorMessage;
      }
    });

    getProjectNameTextBox().getTextBox().addKeyDownHandler(new KeyDownHandler() {
      @Override
      public void onKeyDown(KeyDownEvent event) {
        int keyCode = event.getNativeKeyCode();
        if (keyCode == KeyCodes.KEY_ENTER) {
          getAddButton().click();
        } else if (keyCode == KeyCodes.KEY_ESCAPE) {
          getCancelButton().click();
        }
      }});

    getProjectNameTextBox().getTextBox().addKeyUpHandler(new KeyUpHandler() {
      @Override
      public void onKeyUp(KeyUpEvent event) { //Validate the text each time a key is lifted
        getProjectNameTextBox().validate();
      }
    });

    getProjectNameTextBox().setFocus(true);
  }

  public abstract Dialog getAddDialog();

  public abstract Button getAddButton();

  public abstract Button getCancelButton();

  public abstract LabeledTextBox getProjectNameTextBox();

  public void show() {
    getAddDialog().center();
  }

  @UiHandler("cancelButton")
  public void cancelAdd(ClickEvent e) {
    getAddDialog().hide();
  }

  @UiHandler("addButton")
  public void addProject(ClickEvent e) {
    String projectName = getProjectNameTextBox().getText().trim();
    projectName = projectName.replaceAll("( )+", " ").replace(" ", "_");
    TextValidators.ProjectNameStatus status = TextValidators.checkNewProjectName(projectName);
    if (status == TextValidators.ProjectNameStatus.SUCCESS) {
      LOG.info("Project status success");
      doCreateProject(projectName);
      getAddDialog().hide();
    } else {
      LOG.info("Checking for error");
      String errorMessage = TextValidators.getErrorMessage(getProjectNameTextBox().getText());
      if (errorMessage.length() > 0) {
        LOG.info("Found error: " + errorMessage);
        getProjectNameTextBox().setErrorMessage(errorMessage);
      } else {
        errorMessage = TextValidators.getWarningMessages(getProjectNameTextBox().getText());
        if (errorMessage.length() > 0) {
          getProjectNameTextBox().setErrorMessage(errorMessage);
        } else {
          // Internationalize or change handling here.
          getProjectNameTextBox().setErrorMessage("There has been an unexpected error validating the project name.");
        }
      }
    }
  }

  public void doCreateProject(String projectName) {
      String packageName = StringUtils.getProjectPackage(
          Ode.getInstance().getUser().getUserEmail(), projectName);
      NewYoungAndroidProjectParameters parameters = new NewYoungAndroidProjectParameters(
          packageName);
      NewProjectWizard.NewProjectCommand callbackCommand = new NewProjectWizard.NewProjectCommand() {
        @Override
        public void execute(final Project project) {
          Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
              if (Ode.getInstance().screensLocked()) { // Wait until I/O finished
                Scheduler.get().scheduleDeferred(this); // on other project
              } else {
                Ode.getInstance().openYoungAndroidProjectInDesigner(project);
              }
            }
          });
        }
      };

      NewProjectWizard.createNewProject(YoungAndroidProjectNode.YOUNG_ANDROID_PROJECT_TYPE, projectName,
          parameters, callbackCommand);
      Tracking.trackEvent(Tracking.PROJECT_EVENT, Tracking.PROJECT_ACTION_NEW_YA, projectName);
  }
}
