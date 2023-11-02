package com.google.appinventor.client.actions;

import com.google.appinventor.client.Ode;
import com.google.gwt.user.client.Command;

public class NewAction implements Command {
  @Override
  public void execute() {
    Ode.getUiFactory().createNewYoungAndroidProjectWizard().show();
    // The wizard will switch to the design view when the new
    // project is created.
  }
}
