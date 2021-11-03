// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.boxes;

import static com.google.appinventor.client.Ode.MESSAGES;

import android.view.View;
import com.google.appinventor.client.widgets.TextButton;
import com.google.appinventor.client.widgets.boxes.Box;
import com.google.appinventor.client.widgets.properties.EditableProperty;
import com.google.appinventor.client.widgets.properties.PropertyEditor;
import com.google.appinventor.components.runtime.ListAdapterWithRecyclerView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Box implementation for palette panels.
 *
 */
public final class PaletteBox extends Box {

  // Singleton palette box instance
  private static final PaletteBox INSTANCE = new PaletteBox();
  final PopupPanel customPopup = new PopupPanel(true, true);
  boolean customPopupShowing = false;
  TextButton popupButton =  new TextButton("*");

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

    popupButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        togglePopup();
      }
    });
    headerContainer.add(popupButton, DockPanel.EAST);
    headerContainer.setCellHorizontalAlignment(popupButton, VerticalPanel.ALIGN_RIGHT);
  }

  public void addPropertyToPopup(EditableProperty property) {
    VerticalPanel fp = new VerticalPanel();
    fp.setStylePrimaryName("ode-ChoicePropertyEditor");
    Label label = new Label(property.getCaption());
    label.setStyleName("ode-PropertyLabel");
    fp.add(label);
    PropertyEditor editor = property.getEditor();
    if(!editor.getStyleName().contains("PropertyEditor")) {
      editor.setStyleName("ode-PropertyEditor");
    }
    fp.add(editor);
    customPopup.setWidget(fp);
  }

  public void togglePopup(){
    if (customPopupShowing) {
      customPopup.hide();
    } else {
      customPopup.setPopupPosition(popupButton.getAbsoluteLeft(), popupButton.getAbsoluteTop()
          + popupButton.getOffsetHeight());
      customPopup.show();
    }
    customPopupShowing = !customPopupShowing;
  }
}
