package com.google.appinventor.client.editor.youngandroid.actions;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.youngandroid.DesignToolbar;
import com.google.appinventor.client.Ode;
import com.google.appinventor.client.explorer.project.Project;
import com.google.gwt.user.client.Command;
import java.util.logging.Logger;

public class SwitchToBlocksEditorAction implements Command {
  private static final Logger LOG = Logger.getLogger(SwitchToBlocksEditorAction.class.getName());

  @Override
  public void execute() {
    final DesignToolbar toolbar = Ode.getInstance().getDesignToolbar();
    final ProjectEditor editor = Ode.getCurrentProjectEditor();
    if (toolbar.getCurrentProject() == null) {
      LOG.warning("DesignToolbar.currentProject is null. "
          + "Ignoring SwitchToBlocksEditorAction.execute().");
      return;
    }
    if (editor.getCurrentView() != ProjectEditor.View.BLOCKS) {
      long projectId = Ode.getInstance().getCurrentYoungAndroidProjectRootNode().getProjectId();
      editor.switchToScreen(projectId, toolbar.getCurrentProject().currentScreen, ProjectEditor.View.BLOCKS);
      toolbar.toggleEditor(true);       // Gray out the blocks button and enable the designer button
      Ode.getInstance().getTopToolbar().updateFileMenuButtons(1);
    }
  }
}
