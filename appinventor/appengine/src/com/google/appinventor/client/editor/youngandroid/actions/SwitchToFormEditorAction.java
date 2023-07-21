package com.google.appinventor.client.editor.youngandroid.actions;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.youngandroid.DesignToolbar;
import com.google.appinventor.client.Ode;
import com.google.gwt.user.client.Command;
import java.util.logging.Logger;

public class SwitchToFormEditorAction implements Command {
  private static final Logger LOG = Logger.getLogger(SwitchToFormEditorAction.class.getName());

  @Override
  public void execute() {
    final DesignToolbar toolbar = Ode.getInstance().getDesignToolbar();
    final ProjectEditor editor = Ode.getCurrentProjectEditor();
    if (toolbar.getCurrentProject() == null) {
      LOG.warning("DesignToolbar.currentProject is null. "
          + "Ignoring SwitchToFormEditorAction.execute().");
      return;
    }
    if (editor.currentView != ProjectEditor.View.FORM) {
      // We are leaving a blocks editor, so take a screenshot
      editor.screenShotMaybe(new Runnable() {
        @Override
        public void run() {
          long projectId = Ode.getInstance().getCurrentYoungAndroidProjectRootNode().getProjectId();
          editor.switchToScreen(projectId, toolbar.getCurrentProject().currentScreen, ProjectEditor.View.FORM);
          toolbar.toggleEditor(false);      // Gray out the Designer button and enable the blocks button
          Ode.getInstance().getTopToolbar().updateFileMenuButtons(1);
        }
      }, false);
    }
  }
}
