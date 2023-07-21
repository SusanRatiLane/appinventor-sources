package com.google.appinventor.client.editor.youngandroid.actions;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.youngandroid.DesignToolbar;
import com.google.appinventor.client.Ode;
import com.google.gwt.user.client.Command;

public class SwitchScreenAction implements Command {
  private final long projectId;
  private final String name;  // screen name

  public SwitchScreenAction(long projectId, String screenName) {
    this.projectId = projectId;
    this.name = screenName;
  }

  @Override
  public void execute() {
    // If we are in the blocks view, we should take a screenshot
    // of the blocks as we switch to a different screen
    final ProjectEditor editor = Ode.getCurrentProjectEditor();
    if (editor.getCurrentView() == ProjectEditor.View.BLOCKS) {
      editor.screenShotMaybe(new Runnable() {
        @Override
        public void run() {
          editor.switchToScreen(projectId, name, editor.getCurrentView());
        }
      }, false);
    } else {
      editor.switchToScreen(projectId, name, editor.getCurrentView());
    }
  }
}
