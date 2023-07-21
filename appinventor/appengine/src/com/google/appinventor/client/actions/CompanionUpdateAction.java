package com.google.appinventor.client.actions;

import static com.google.appinventor.client.Ode.MESSAGES;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.ProjectEditor;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;

public class CompanionUpdateAction implements Command {
  @Override
  public void execute() {
    ProjectEditor.DesignProject currentProject = Ode.getInstance().getDesignToolbar().getCurrentProject();
    if (currentProject == null) {
      Window.alert(MESSAGES.companionUpdateMustHaveProject());
      return;
    }
    ProjectEditor.Screen screen = currentProject.screens.get(currentProject.currentScreen);
    screen.blocksEditor.updateCompanion();
  }
}
