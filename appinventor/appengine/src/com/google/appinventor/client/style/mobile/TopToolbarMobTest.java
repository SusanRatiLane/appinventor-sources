// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2023-2024 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.style.mobile;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.TopToolbar;
import com.google.appinventor.client.widgets.DropDownButton;
import com.google.appinventor.client.widgets.Toolbar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import java.util.logging.Logger;

public class TopToolbarMobTest extends TopToolbar {
  private static final Logger LOG = Logger.getLogger(TopToolbarMobTest.class.getName());
  interface TopToolbarUiBinderMob extends UiBinder<Toolbar, TopToolbarMobTest> {}
  private static final TopToolbarUiBinderMob uibinder =
      GWT.create(TopToolbarUiBinderMob.class);

  @UiField DropDownButton fileDropDown;
  @UiField DropDownButton connectDropDown;
  @UiField DropDownButton buildDropDown;
  @UiField DropDownButton settingsDropDown;
  @UiField DropDownButton adminDropDown;
  @UiField (provided = true) Boolean hasWriteAccess;

  @Override
  public void bindUI() {
    // The boolean needs to be reversed here so it is true when items need to be visible.
    // UIBinder can't negate the boolean itself.
    LOG.info("bindUI MobTest");
    readOnly = Ode.getInstance().isReadOnly();
    hasWriteAccess = !readOnly;

    initWidget(uibinder.createAndBindUi(this));
    super.fileDropDown = fileDropDown;
    super.connectDropDown = connectDropDown;
    super.buildDropDown = buildDropDown;
    super.settingsDropDown = settingsDropDown;
    super.adminDropDown = adminDropDown;
  }

}
