// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.OdeAsyncCallback;
import com.google.appinventor.client.editor.youngandroid.BlocklyPanel;
import com.google.appinventor.client.editor.youngandroid.DesignToolbar;
import com.google.appinventor.client.editor.youngandroid.YaBlocksEditor;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.appinventor.client.explorer.project.Project;
import com.google.appinventor.client.settings.Settings;
import com.google.appinventor.client.settings.project.ProjectSettings;
import com.google.appinventor.common.version.AppInventorFeatures;
import com.google.appinventor.shared.rpc.RpcResult;
import com.google.appinventor.shared.rpc.project.FileNode;
import com.google.appinventor.shared.rpc.project.ProjectRootNode;
import com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidSourceNode;
import com.google.appinventor.shared.settings.SettingsConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.Callback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * Abstract superclass for all project editors.
 * Each ProjectEditor is associated with a single project and may have multiple
 * FileEditors open in a DeckPanel.
 * 
 * TODO(sharon): consider merging this into YaProjectEditor, since we now
 * only have one type of project editor. 
 *
 * @author lizlooney@google.com (Liz Looney)
 */
public abstract class ProjectEditor extends Composite {
  private static final Logger LOG = Logger.getLogger(ProjectEditor.class.getName());
  // Stack of screens switched to from the Companion
  // We implement screen switching in the Companion by having it tell us
  // to switch screens. We then load into the companion the new Screen
  // We save where we were because the companion can have us return from
  // a screen. If we switch projects in the browser UI, we clear this
  // list of screens as we are effectively running a different application
  // on the device.
  public static LinkedList<String> pushedScreens = Lists.newLinkedList();

  protected final ProjectRootNode projectRootNode;
  protected final long projectId;
  protected final Project project;

  // Invariants: openFileEditors, fileIds, and deckPanel contain corresponding
  // elements, i.e., if a FileEditor is in openFileEditors, its fileid should be
  // in fileIds and the FileEditor should be in deckPanel. If selectedFileEditor
  // is non-null, it is one of the file editors in openFileEditors and the 
  // one currently showing in deckPanel. 
  private final Map<String, FileEditor> openFileEditors;
  protected final List<String> fileIds;
  private final HashMap<String,String> locationHashMap = new HashMap<String,String>();
  private final DeckPanel projectDeckPanel;
  private FileEditor selectedFileEditor;
  private final TreeMap<String, Boolean> screenHashMap = new TreeMap<String, Boolean>();

  protected ProjectEditor.DesignProject currentProject;


  // Map of project id to project info for all projects we've ever shown
  // in the Designer in this session.
  public Map<Long, ProjectEditor.DesignProject> projectMap = Maps.newHashMap();

  /**
   * Creates a {@code ProjectEditor} instance.
   *
   * @param projectRootNode  the project root node
   */
  public ProjectEditor(ProjectRootNode projectRootNode) {
    this.projectRootNode = projectRootNode;
    projectId = projectRootNode.getProjectId();
    project = Ode.getInstance().getProjectManager().getProject(projectId);

    openFileEditors = Maps.newHashMap();
    fileIds = new ArrayList<String>();

    projectDeckPanel = new DeckPanel();

//    VerticalPanel panel = new VerticalPanel();
//    panel.add(deckPanel);
    projectDeckPanel.setSize("100%", "100%");
//    panel.setSize("100%", "100%");
    initWidget(projectDeckPanel);
    // Note: I'm not sure that the setSize call below does anything useful.
    setSize("100%", "100%");
  }

  /*
   * PushScreen -- Static method called by Blockly when the Companion requests
   * That we switch to a new screen. We keep track of the Screen we were on
   * and push that onto a stack of Screens which we pop when requested by the
   * Companion.
   */
    public static boolean pushScreen(String screenName) {
      DesignToolbar designToolbar = Ode.getInstance().getDesignToolbar();
      long projectId = Ode.getInstance().getCurrentYoungAndroidProjectId();
      String currentScreen = Ode.getCurrentProjectEditor().currentProject.currentScreen;
      if (!Ode.getCurrentProjectEditor().currentProject.screens.containsKey(screenName)) // No such screen -- can happen
        return false;                                                    // because screen is user entered here.
      pushedScreens.addFirst(currentScreen);
      Ode.getCurrentProjectEditor().switchToScreen(projectId, screenName, View.BLOCKS);
      return true;
    }

  public static void popScreen() {
      DesignToolbar designToolbar = Ode.getInstance().getDesignToolbar();
      long projectId = Ode.getInstance().getCurrentYoungAndroidProjectId();
      String newScreen;
      if (pushedScreens.isEmpty()) {
        return;                   // Nothing to do really
      }
      newScreen = pushedScreens.removeFirst();
      Ode.getCurrentProjectEditor().switchToScreen(projectId, newScreen, View.BLOCKS);
    }

  // Called from Javascript when Companion is disconnected
    public static void clearScreens() {
      pushedScreens.clear();
    }

  /**
   * Processes the project before loading into the project editor.
   * To do any any pre-processing of the Project
   * Calls the loadProject() after prepareProject() is fully executed.
   * Currently, prepareProject loads all external components associated with project.
   */
  public abstract void processProject();

  /**
   * Called when the ProjectEditor widget is loaded after having been hidden. 
   * Subclasses must implement this method, taking responsibility for causing 
   * the onShow method of the selected file editor to be called and for updating 
   * any other UI elements related to showing the project editor.
   */
  protected abstract void onShow();
  
  /**
   * Called when the ProjectEditor widget is about to be unloaded. Subclasses
   * must implement this method, taking responsibility for causing the onHide 
   * method of the selected file editor to be called and for updating any 
   * other UI elements related to hiding the project editor.
   */
  protected abstract void onHide();

  public abstract void switchToScreen(long projectId, String screenName, View view);

  public abstract void removeScreen(long projectId, String name);

  public abstract void addProject(long projectId, String projectName);

  public abstract void addScreen(long projectId, String name, FileEditor formEditor,
                        FileEditor blocksEditor);

  public View getCurrentView() {
    return currentView;
  }
  public final void setScreenCheckboxState(String screen, Boolean isChecked) {
    screenHashMap.put(screen, isChecked);
    changeProjectSettingsProperty(
        SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_SCREEN_CHECKBOX_STATE_MAP,
        getScreenCheckboxMapString());
  }

  public final Boolean getScreenCheckboxState(String screen) {
    if(!screenHashMap.containsKey(screen)) {
      setScreenCheckboxState(screen, false);
    }
    return screenHashMap.get(screen);
  }

  public final String getScreenCheckboxMapString() {
    String screenCheckboxMap = "";
    int count = 0;
    Set<String> screens = screenHashMap.keySet();
    int size = screens.size();
    for (String screen : screens) {
      Boolean isChecked = screenHashMap.get(screen);
      if (isChecked == null) {
        continue;
      }
      String isCheckedString = (isChecked) ? "True" : "False";
      String separator = (count == size) ? "" : " ";
      screenCheckboxMap += screen + ":" + isCheckedString + separator;
    }
    return screenCheckboxMap;
  }

  public final void buildScreenHashMap() {
    String screenCheckboxMap = getProjectSettingsProperty(
        SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_SCREEN_CHECKBOX_STATE_MAP
    );    String[] pairs = screenCheckboxMap.split(" ");
    for (String pair : pairs) {
      String[] mapping = pair.split(":");
      String screen = mapping[0];
      Boolean isChecked = Boolean.parseBoolean(mapping[1]);
      screenHashMap.put(screen, isChecked);
    }
  }

  /**
   * Adds a file editor to this project editor.
   *
   * @param fileEditor  file editor to add
   */
  public final void addFileEditor(FileEditor fileEditor) {
    String fileId = fileEditor.getFileId();
    openFileEditors.put(fileId, fileEditor);
    fileIds.add(fileId);
    
    projectDeckPanel.add(fileEditor);
  }

  /**
   * Inserts a file editor in this editor at the specified index.
   *
   * @param fileEditor  file editor to insert
   * @param beforeIndex  the index before which fileEditor will be inserted
   */
  public final void insertFileEditor(FileEditor fileEditor, int beforeIndex) {
    String fileId = fileEditor.getFileId();
    openFileEditors.put(fileId, fileEditor);
    fileIds.add(beforeIndex, fileId);
    projectDeckPanel.insert(fileEditor, beforeIndex);
    LOG.info("Inserted file editor for " + fileEditor.getFileId() + " at pos " + beforeIndex);
  }

  public final void insertFormFileEditor(FileEditor fileEditor, int beforeIndex) {
    insertFileEditor(fileEditor, beforeIndex);

  }

  public final void insertBlocksFileEditor(FileEditor fileEditor, int beforeIndex) {
    insertFileEditor(fileEditor, beforeIndex);

  }


  /**
   * Selects the given file editor in the deck panel and calls its onShow()
   * method. Calls onHide() for a previously selected file editor if there was 
   * one (and it wasn't the same one).
   * 
   * Note: all actions that cause the selected file editor to change should
   * be going through DesignToolbar.SwitchScreenAction.execute(), which calls
   * this method. If you're thinking about calling this method directly from 
   * somewhere else, please reconsider!
   *
   * @param fileEditor  file editor to select
   */
  public final void selectFileEditor(FileEditor fileEditor) {
    int index = projectDeckPanel.getWidgetIndex(fileEditor);
    if (index == -1) {
      if (fileEditor != null) {
        LOG.warning("Can't find widget for fileEditor " + fileEditor.getFileId());
      } else {
        LOG.warning("Not expecting selectFileEditor(null)");
      }
    }
    LOG.info("ProjectEditor: got selectFileEditor for "
        + ((fileEditor == null) ? null : fileEditor.getFileId())
        +  " selectedFileEditor is " 
        + ((selectedFileEditor == null) ? null : selectedFileEditor.getFileId()));
    if (selectedFileEditor != null && selectedFileEditor != fileEditor) {
      selectedFileEditor.onHide();
    }
    // Note that we still want to do the following statements even if 
    // selectedFileEditor == fileEditor already. This handles the case of switching back
    // to a previously opened project from another project.
    selectedFileEditor = fileEditor;
    projectDeckPanel.showWidget(index);
    selectedFileEditor.onShow();
  }

  /**
   * Take a screenshot when the user leaves a blocks editor
   *
   * Take note of the "deferred" flag. If set, we run the runnable
   * after i/o is finished. Otherwise we run it immediately while i/o
   * may still be happening. We wait in the case of logout or window
   * closing, where we want to hold things up until i/o is done.
   *
   * @param next a runnable to run when we are finished
   * @param deferred whether to run the runnable immediately or after i/o is finished
   */

  public void screenShotMaybe(final Runnable next, final boolean deferred) {
    // Only take screenshots if we are an enabled feature
    if (!AppInventorFeatures.takeScreenShots()) {
      next.run();
      return;
    }
    // If we are not in the blocks editor, we do nothing
    // but we do run our callback
    if (currentView != View.BLOCKS) {
      next.run();
      return;
    }
    String image = "";
    FileEditor editor = Ode.getInstance().getCurrentFileEditor();
    final long projectId = editor.getProjectId();
    final FileNode fileNode = editor.getFileNode();
    Ode.getInstance().getCurrentFileEditor().getBlocksImage(new Callback<String,String>() {
      @Override
      public void onSuccess(String result) {
        int comma = result.indexOf(",");
        if (comma < 0) {
          LOG.info("screenshot invalid");
          next.run();
          return;
        }
        result = result.substring(comma+1); // Strip off url header
        String screenShotName = fileNode.getName();
        int period = screenShotName.lastIndexOf(".");
        screenShotName = "screenshots/" + screenShotName.substring(0, period) + ".png";
        LOG.info("ScreenShotName = " + screenShotName);
        Ode.getInstance().getProjectService().screenshot(Ode.getInstance().getSessionId(),
            projectId, screenShotName, result,
            new OdeAsyncCallback<RpcResult>() {
              @Override
              public void onSuccess(RpcResult result) {
                if (deferred) {
                  next.run();
                }
              }
              public void OnFailure(Throwable caught) {
                super.onFailure(caught);
                if (deferred) {
                  next.run();
                }
              }
            });
        if (!deferred) {
          next.run();
        }
      }
      @Override
      public void onFailure(String error) {
        LOG.info("Screenshot failed: " + error);
        next.run();
      }
    });
  }

  /**
   * Returns the file editor for the given file ID.
   *
   * @param fileId  file ID of the file
   */
  public final FileEditor getFileEditor(String fileId) {
    return openFileEditors.get(fileId);
  }
  
  /**
   * Returns the set of open file editors
   */
  public final Iterable<FileEditor> getOpenFileEditors() {
    return Collections.unmodifiableCollection(openFileEditors.values());
  }
  
  /**
   * Returns the currently selected file editor
   */
  protected final FileEditor getSelectedFileEditor() {
    return selectedFileEditor;
  }

  /**
   * Closes the file editors for the given file IDs, without saving.
   * This is used when the files are about to be deleted. If  
   * selectedFileEditor is closed, sets selectedFileEditor to null.
   *
   * @param closeFileIds  file IDs of the files to be closed
   */
  public final void closeFileEditors(String[] closeFileIds) {
    for (String fileId : closeFileIds) {
      FileEditor fileEditor = openFileEditors.remove(fileId);
      if (fileEditor == null) {
        LOG.severe("File editor is unexpectedly null for " + fileId);
        continue;
      }
      int index = projectDeckPanel.getWidgetIndex(fileEditor);
      fileIds.remove(index);
      projectDeckPanel.remove(fileEditor);
      if (selectedFileEditor == fileEditor) {
        selectedFileEditor.onHide();
        selectedFileEditor = null;
      }
      fileEditor.onClose();
    }
  }
  
  /**
   * Returns the value of a project settings property.
   *
   * @param category  property category
   * @param name  property name
   * @return the property value
   */
  public final String getProjectSettingsProperty(String category, String name) {
    ProjectSettings projectSettings = project.getSettings();
    Settings settings = projectSettings.getSettings(category);
    return settings.getPropertyValue(name);
  }

  /**
   * Changes the value of a project settings property.
   *
   * @param category  property category
   * @param name  property name
   * @param newValue  new property value
   */
  public final void changeProjectSettingsProperty(String category, String name, String newValue) {
    ProjectSettings projectSettings = project.getSettings();
    Settings settings = projectSettings.getSettings(category);
    String currentValue = settings.getPropertyValue(name);
    if (!newValue.equals(currentValue)) {
      LOG.info("ProjectEditor: changeProjectSettingsProperty: " + name + " " + currentValue +
                 " => " + newValue);
      settings.changePropertyValue(name, newValue);
      // Deal with the Tutorial Panel
      Ode ode = Ode.getInstance();
      if (name.equals("TutorialURL")) {
        ode.setTutorialURL(newValue);
      }
      ode.getEditorManager().scheduleAutoSave(projectSettings);
    }
  }

  /**
   * Keep track of components that require the
   * "android.permission.ACCESS_FINE_LOCATION" (and related
   * permissions). This code is in particular for use of the WebViewer
   * component. The WebViewer exports the Javascript location
   * API. However it cannot be used by an app with location
   * permissions. Each WebViewer has a "UsesLocation" property which
   * is only available from the designer. Each WebViewer then
   * registers its value here. Each time this hashtable is updated we
   * recompute whether or not location permission is needed based on a
   * logical OR of all of the WebViewer components registered. Note:
   * Even if no WebViewer component requires location permission, other
   * components, such as the LocationSensor may require it. That is
   * handled via the @UsesPermissions mechanism and is independent of
   * this code.
   *
   * @param componentName The name of the component registering location permission
   * @param newValue either "True" or "False" indicating whether permission is need.
   */

  public final void recordLocationSetting(String componentName, String newValue) {
    LOG.info("ProjectEditor: recordLocationSetting(" + componentName + "," + newValue + ")");
    locationHashMap.put(componentName, newValue);
    recomputeLocationPermission();
  }

  private void recomputeLocationPermission() {
    String usesLocation = "False";
    for (String c : locationHashMap.values()) {
      LOG.info("ProjectEditor:recomputeLocationPermission: " + c);
      if (c.equals("True")) {
        usesLocation = "True";
        break;
      }
    }
    changeProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS, SettingsConstants.YOUNG_ANDROID_SETTINGS_USES_LOCATION,
      usesLocation);
  }

  public void clearLocation(String componentName) {
    LOG.info("ProjectEditor:clearLocation: clearing " + componentName);
    locationHashMap.remove(componentName);
    recomputeLocationPermission();
  }

  /**
   * Notification that the file with the given file ID has been saved.
   *
   * @param fileId  file ID of the file that was saved
   */
  public final void onSave(String fileId) {
    FileEditor fileEditor = openFileEditors.get(fileId);
    if (fileEditor != null) {
      fileEditor.onSave();
    }
  }

  // GWT Widget methods

  @Override
  protected void onLoad() {
    // onLoad is called immediately after a widget becomes attached to the browser's document.
    // onLoad will be called both when a project is opened the first time and when an
    // already-opened project is re-opened.
    // This is different from the ProjectEditor method loadProject, which is called to load the
    // project just after the editor is created.
    LOG.info("ProjectEditor: got onLoad for project " + projectId);
    super.onLoad();
    String tutorialURL = getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
                                                    SettingsConstants.YOUNG_ANDROID_SETTINGS_TUTORIAL_URL);
    if (!tutorialURL.isEmpty()) {
      Ode ode = Ode.getInstance();
      ode.setTutorialURL(tutorialURL);
    }

    onShow();
  }

  @Override
  protected void onUnload() {
    // onUnload is called immediately before a widget becomes detached from the browser's document.
    Ode ode = Ode.getInstance();
    ode.setTutorialVisible(false);
    ode.getDesignToolbar().setTutorialToggleVisible(false);
    LOG.info("ProjectEditor: got onUnload for project " + projectId);
    super.onUnload();
    onHide();
  }

  /*
   * A Screen groups together the form editor and blocks editor for an
   * application screen. Name is the name of the screen (form) displayed
   * in the screens pull-down.
   */
  public static class Screen {
    public final String screenName;
    public final FileEditor formEditor;
    public final FileEditor blocksEditor;

    public Screen(String name, FileEditor formEditor, FileEditor blocksEditor) {
      this.screenName = name;
      this.formEditor = formEditor;
      this.blocksEditor = blocksEditor;
    }
  }
  // Enum for type of view showing in the design tab
  public enum View {
    FORM,   // Form editor view
    BLOCKS  // Blocks editor view
  }
  public View currentView = View.FORM;

  /*
   * A project as represented in the DesignToolbar. Each project has a name
   * (as displayed in the DesignToolbar on the left), a set of named screens,
   * and an indication of which screen is currently being edited.
   */
  public static class DesignProject {
    public final String name;
    public final Map<String, Screen> screens; // screen name -> Screen
    public String currentScreen; // name of currently displayed screen
    private final long projectId;

    public DesignProject(String name, long projectId) {
      this.name = name;
      this.projectId = projectId;
      screens = Maps.newHashMap();
      // Screen1 is initial screen by default
      currentScreen = YoungAndroidSourceNode.SCREEN1_FORM_NAME;
      // Let BlocklyPanel know which screen to send Yail for
      BlocklyPanel.setCurrentForm(projectId + "_" + currentScreen);
    }

    // Returns true if we added the screen (it didn't previously exist), false otherwise.
    public boolean addScreen(String name, FileEditor formEditor, FileEditor blocksEditor) {
      if (screens.containsKey(name)) {
        return false;
      }
      screens.put(name, new Screen(name, formEditor, blocksEditor));
      return true;
    }

    public void removeScreen(String name) {
      screens.remove(name);
    }

    public void setCurrentScreen(String name) {
      currentScreen = name;
    }

    public long getProjectId() {
      return projectId;
    }

  }

}
