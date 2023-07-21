// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.youngandroid;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.youngandroid.actions.SendToGalleryAction;
import com.google.appinventor.client.editor.youngandroid.actions.SwitchScreenAction;
import com.google.appinventor.client.widgets.DropDownButton;
import com.google.appinventor.client.widgets.DropDownItem;
import com.google.appinventor.client.widgets.Toolbar;
import com.google.appinventor.client.widgets.ToolbarItem;
import com.google.appinventor.common.version.AppInventorFeatures;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;

import java.util.Collection;
import java.util.logging.Logger;

import static com.google.appinventor.client.Ode.MESSAGES;

/**
 * The design toolbar houses command buttons in the Young Android Design
 * tab (for the UI designer (a.k.a, Form Editor) and Blocks Editor).
 *
 */
public class DesignToolbar extends Toolbar {
  private static final Logger LOG = Logger.getLogger(DesignToolbar.class.getName());

  private volatile boolean lockPublishButton = false; // Used to prevent double-clicking the
                                                     // SendToGallery button

  private static final String WIDGET_NAME_TUTORIAL_TOGGLE = "TutorialToggle";
  private static final String WIDGET_NAME_REMOVEFORM = "RemoveForm";
  private static final String WIDGET_NAME_SCREENS_DROPDOWN = "ScreensDropdown";
  private static final String WIDGET_NAME_SWITCH_TO_BLOCKS_EDITOR = "SwitchToBlocksEditor";
  private static final String WIDGET_NAME_SWITCH_TO_FORM_EDITOR = "SwitchToFormEditor";
  private static final String WIDGET_NAME_SENDTOGALLERY = "SendToGallery";

  @UiField public Label projectNameLabel;


  // Project currently displayed in designer
  private ProjectEditor.DesignProject currentProject;

  interface DesignToolbarUiBinder extends UiBinder<Toolbar, DesignToolbar> {}
  private static final DesignToolbarUiBinder UI_BINDER = GWT.create(DesignToolbarUiBinder.class);

  @UiField DropDownButton pickFormItem;
  @UiField ToolbarItem addFormItem;
  @UiField ToolbarItem removeFormItem;
  @UiField ToolbarItem switchToDesign;
  @UiField ToolbarItem switchToBlocks;

  /**
   * Initializes and assembles all commands into buttons in the toolbar.
   */
  public DesignToolbar() {
    super();

    populateToolbar(UI_BINDER.createAndBindUi(this));
    if (Ode.getInstance().isReadOnly() || !AppInventorFeatures.allowMultiScreenApplications()) {
      removeItem(addFormItem);
      removeItem(removeFormItem);
    }

    // Is the Gallery Enabled (new gallery)?
    if (Ode.getSystemConfig().getGalleryEnabled() && !Ode.getInstance().getGalleryReadOnly()) {
      add(new ToolbarItem(WIDGET_NAME_SENDTOGALLERY,
          MESSAGES.publishToGalleryButton(), new SendToGalleryAction(() -> {
            if (!lockPublishButton) {
              lockPublishButton = true;
              return true;
            }
            return false;
          }, () -> lockPublishButton = false)));
    }

    // Gray out the Designer button and enable the blocks button
    toggleEditor(false);
    Ode.getInstance().getTopToolbar().updateFileMenuButtons(0);
  }


  public void removeDropDownButtonItem(String name) {
    removeDropDownButtonItem(WIDGET_NAME_SCREENS_DROPDOWN, name);
  }

  public void setDropDownButtonCaption(String newScreenName) {
    setDropDownButtonCaption(WIDGET_NAME_SCREENS_DROPDOWN, newScreenName);
  }

  public void clearDropDownMenu() {
    clearDropDownMenu(WIDGET_NAME_SCREENS_DROPDOWN);
  }

  public void switchToProject(long projectId, String projectName, Collection<String> screenNames) {
    for (String screenName : screenNames) {
      addDropDownButtonItem(WIDGET_NAME_SCREENS_DROPDOWN, new DropDownItem(screenName,
          screenName, new SwitchScreenAction(projectId, screenName)));
    }
    projectNameLabel.setText(projectName);
  }

  public void addScreen(long projectId, String name) {
    addDropDownButtonItem(WIDGET_NAME_SCREENS_DROPDOWN, new DropDownItem(name,
        name, new SwitchScreenAction(projectId, name)));
  }

  public void toggleEditor(boolean blocks) {
    setButtonEnabled(switchToBlocks.getName(), !blocks);
    setButtonEnabled(switchToDesign.getName(), blocks);

    boolean notOnScreen1 = getCurrentProject() != null
        && !"Screen1".equals(getCurrentProject().currentScreen);
    setButtonEnabled(WIDGET_NAME_REMOVEFORM, notOnScreen1);
  }

  public ProjectEditor.DesignProject getCurrentProject() {
    return currentProject;
  }



  public void setTutorialToggleVisible(boolean value) {
    setButtonVisible(WIDGET_NAME_TUTORIAL_TOGGLE, value);
  }

}
