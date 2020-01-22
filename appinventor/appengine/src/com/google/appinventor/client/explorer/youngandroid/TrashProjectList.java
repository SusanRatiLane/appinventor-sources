// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2019 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.explorer.youngandroid;

import com.google.appinventor.client.Ode;
import static com.google.appinventor.client.Ode.MESSAGES;
import com.google.appinventor.client.explorer.project.Project;

/**
 * The deleted project list shows all projects in a table.
 *
 * <p> The project name, date created, and date modified will be shown in the table.
 *
 * @author lizlooney@google.com (Liz Looney)
 */
public class TrashProjectList extends ProjectList {

  /**
   * Creates a new TrashProjectList
   */
  public TrashProjectList() {
    super();
    // It is important to listen to project manager events as soon as possible.
    Ode.getInstance().getProjectManager().addProjectManagerEventListener(this);
  }

  // TODO(user): This method was made public so it can be called
  // directly from from Ode when the Project List View is selected
  // from another view.  Ode now clears any selected projects and
  // calls this to refresh the table as a result. Not sure this is
  // correct thing do to. The alternative is to add a call to the
  // ProjectManagerEventListener interface that this is the
  // implementation of.
  public void refreshTable(boolean needToSort) {
    super.refreshTable(needToSort);
    Ode.getInstance().getProjectToolbar().updateTrashButtons();
  }

  // ProjectManagerEventListener implementation

  @Override
  public void onProjectAdded(Project project) { }

  @Override
  public void onDeletedProjectAdded(Project project) {
    allProjects.add(project);
    projectWidgets.put(project, new ProjectWidgets(project));
    if (!projectListLoading) {
      refreshTable(true);
    }
  }

//  @Override
//  public void onProjectRemoved(Project project) { }

  @Override
  public void onDeletedProjectRemoved(Project project) {
    allProjects.remove(project);
    projectWidgets.remove(project);

    refreshTable(false);

    selectedProjects.remove(project);
    Ode.getInstance().getProjectToolbar().updateTrashButtons();
  }

  @Override
  public void onProjectsLoaded() {
    projectListLoading = false;
    refreshTable(true);
  }

}
