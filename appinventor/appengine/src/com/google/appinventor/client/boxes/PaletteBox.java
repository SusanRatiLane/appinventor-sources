// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.boxes;

import static com.google.appinventor.client.Ode.MESSAGES;
import static com.google.appinventor.client.Ode.handleUserLocale;

import com.google.appinventor.client.widgets.TextButton;
import com.google.appinventor.client.widgets.boxes.Box;
import com.google.appinventor.client.widgets.properties.EditableProperty;
import com.google.appinventor.client.widgets.properties.PropertyEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Box implementation for palette panels.
 *
 */
public final class PaletteBox extends Box {

  // Singleton palette box instance
  private static final PaletteBox INSTANCE = new PaletteBox();

  /**
   * Return the palette box.
   *
   * @return  palette box
   */
  public static PaletteBox getPaletteBox() {
    return INSTANCE;
  }

  /**
   * Creates new palette box.
   */
  private PaletteBox() {
    super(MESSAGES.paletteBoxCaption(),
        200,       // height
        false,     // minimizable
        false,     // removable
        false,     // startMinimized
        false,     // usePadding
        false);    // highlightCaption
  }

  public void addPropertyToPalette(EditableProperty property) {
    VerticalPanel fp = new VerticalPanel();
    HorizontalPanel hp = new HorizontalPanel();
    hp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
    hp.getElement().setAttribute("style", "width: 100%; box-sizing: border-box; padding: 4px");
    Label label = new Label(property.getCaption());
    hp.add(label);
    PropertyEditor editor = property.getEditor();
    hp.add(editor);
    fp.add(hp);
    fp.add(body.getWidget());
    fp.setWidth("100%");
    setContent(fp);
  }
}
