// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Mock Chart component.
 *
 */
public final class MockChart extends MockVisibleComponent{

  private final AbsolutePanel chartWidget;
  /**
   * Component type name.
   */
  public static final String TYPE = "Chart";

  /**
   * Creates a new MockChart component.
   *
   * @param editor  editor of source file the component belongs to
   */
  public MockChart(SimpleEditor editor) {
    super(editor, TYPE, images.chart());
    
    chartWidget = new AbsolutePanel();
    chartWidget.setStylePrimaryName("ode-SimpleMockComponent");

    initComponent(chartWidget);
  }
}
