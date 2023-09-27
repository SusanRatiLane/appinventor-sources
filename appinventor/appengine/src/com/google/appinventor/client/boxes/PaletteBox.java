// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.boxes;

import static com.google.appinventor.client.Ode.MESSAGES;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.FileEditor;
import com.google.appinventor.client.editor.simple.palette.SimplePalettePanel;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.appinventor.client.widgets.boxes.Box;
import com.google.appinventor.client.widgets.material.button.MaterialButton;
import com.google.appinventor.client.widgets.material.button.MaterialButtonType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

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

  private MaterialButton searchButton;
  private HorizontalPanel searchBar;
  private TextBox searchTextBox;

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
    addStyleName("palette-box");

    searchBar = new HorizontalPanel();
    searchBar.setStylePrimaryName("palette-header-search-bar--inactive");

    searchButton = new MaterialButton("search", MaterialButtonType.ICON, MESSAGES.gallerySearch());
    searchButton.setStylePrimaryName("palette-header-search-button");
    searchButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        FileEditor fileEditor = Ode.getInstance().getCurrentFileEditor();
        if(!(fileEditor instanceof YaFormEditor))
          return;
        YaFormEditor formEditor = (YaFormEditor) fileEditor;
        formEditor.getComponentPalettePanel().setSearchMode(!formEditor.getComponentPalettePanel().getSearchMode());
        searchTextBox.setText("");
      }
    });

    searchTextBox = new TextBox();
    searchTextBox.addKeyUpHandler(new KeyUpHandler() {
      public void onKeyUp(KeyUpEvent event) {
        FileEditor fileEditor = Ode.getInstance().getCurrentFileEditor();
        if(!(fileEditor instanceof YaFormEditor))
          return;
        YaFormEditor formEditor = (YaFormEditor) fileEditor;

        if (formEditor.getComponentPalettePanel().getSearchMode()) {
          formEditor.getComponentPalettePanel().searchByComponentName(searchTextBox.getText());
        }
      }
    });
    searchTextBox.setStylePrimaryName("palette-header-search-input");

    searchBar.add(searchTextBox);
    searchBar.add(searchButton);
    headerContainer.add(searchBar, DockPanel.LINE_START);
  }

  public void setSearchBarEnabled(boolean enabled) {
    if(enabled) {
      //Enable search bar
      searchButton.setHTML("<i class=\"material-icons\">close</i>");
      searchBar.setStylePrimaryName("palette-header-search-bar--active");
      captionLabel.setVisible(false);
      searchTextBox.setFocus(true);
    } else {
      //Disable search bar
      searchButton.setHTML("<i class=\"material-icons\">search</i>");
      searchBar.setStylePrimaryName("palette-header-search-bar--inactive");
      captionLabel.setVisible(true);
      searchTextBox.setFocus(false);
    }
  }

  @Override
  public void setContent(Widget w) {
    super.setContent(w);
    if (w instanceof SimplePalettePanel) {
      searchBar.setVisible(((SimplePalettePanel) w).showSearchBox());
    }
  }
}
