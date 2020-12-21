// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2018-2020 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.client.editor.simple.components.utils.SVGPanel;
import com.google.appinventor.shared.settings.SettingsConstants;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Mock Switch component, inherited from MockToggleBase
 *
 * @author srlane@mit.edu (Susan Rati Lane)
 */
public final class MockSwitch extends MockToggleBase<HorizontalPanel> {

  /**
   * Component type name.
   */
  public static final String TYPE = "Switch";
  protected final HorizontalPanel panel;
  public Boolean checked = false;  // the "on" property of the switch is equivalent to "checked"
  public String thumbColorActive = "white";
  public String thumbColorInactive = "gray";
  public String trackColorActive = "lime";
  public String trackColorInactive = "lightgray";

  public InlineHTML switchLabel;
  public SVGPanel switchGraphic;
  public Boolean isInitialized = false;

  /**
   * Creates a new MockSwitch component.
   *
   * @param editor  editor of source file the component belongs to
   */
  public MockSwitch(SimpleEditor editor) {

    super(editor, TYPE, images.toggleswitch());

    panel = new HorizontalPanel();
    switchLabel = new InlineHTML();
    toggleWidget = panel;
    isInitialized = false;
    initWrapper(toggleWidget);
  }

  /**
   * Draw the SVG graphic of the toggle switch. It can be drawn in either checked or
   * unchecked positions, each with their own colors.
   *
   */
  private void paintSwitch() {
    if (isInitialized) {
      panel.remove(switchGraphic);
      panel.remove(switchLabel);
    } else {
      isInitialized = true;
    }
    panel.add(switchLabel);
    switchGraphic = new SVGPanel();
    int switchHeight = 14;  // pixels (Android asset is 28 px at 160 dpi)

    int switchWidth = (int) Math.round(switchHeight * 2);
    switchGraphic.setWidth(switchWidth + "px");
    switchGraphic.setHeight(switchHeight + "px");

    switchGraphic.setInnerSVG("<rect x=\"0\" y=\"0\" rx=\"" +
            switchHeight/2 + "\" yx=\"" + switchWidth/2 + "\" stroke-width=\"1\" stroke=\"black\"" +
            "height=\"" + switchHeight + "\" width=\"" + switchWidth + "\" fill=\"" + (checked? trackColorActive : trackColorInactive) + "\" />" +
            "<circle cx=\"" + (checked? switchWidth - switchHeight/2: switchHeight/2) + "\" fill=\"" + (checked? thumbColorActive : thumbColorInactive) + "\" " +
            "cy=\"" + (switchHeight/2) + "\" r=\"" + (switchHeight/2 - 1) + "\"/>");
    panel.add(switchGraphic);
    panel.setCellWidth(switchGraphic, switchWidth + "px");
    panel.setCellHorizontalAlignment(switchGraphic, HasHorizontalAlignment.ALIGN_RIGHT);
    panel.setCellVerticalAlignment(switchGraphic, HasVerticalAlignment.ALIGN_MIDDLE);
    toggleWidget = panel;
    updatePreferredSize();
    refreshForm();
  }

  private void paintClassicSwitch() {
    if (isInitialized) {
      panel.remove(switchLabel);
      panel.remove(switchGraphic);
    } else {
      isInitialized = true;
      switchLabel.setText(switchLabel.getText());
    }
    switchGraphic = new SVGPanel();
    int switchHeight = 20;  // pixels (Android asset is 28 px at 160 dpi)

    int switchWidth = 20;
    switchGraphic.setWidth(switchWidth + "px");
    switchGraphic.setHeight(switchHeight + "px");

    if(checked) {
      switchGraphic.setInnerSVG("<g  transform=\"translate(35.5 27.5)\">\n" +
              "<path  d=\"M6.838,12.553l-1.366-1.53L4.355,12.094l2.407,2.665L12.8,8.5l-1-1.012Z\" transform=\"translate(-35.466 -29.759)\" fill=\"#179213\" opacity=\"0.54\"/>\n" +
              "<rect width=\"16\" height=\"16\" rx=\"2\" transform=\"translate(-35 -27)\" fill=\"none\" stroke=\"#707070\" stroke-linejoin=\"round\" stroke-width=\"1\"/>\n" +
              "</g>");
    } else {
      switchGraphic.setInnerSVG("<g fill=\"#fff\" stroke=\"#707070\" stroke-width=\"1\">\n" +
              "<rect width=\"16\" height=\"16\" rx=\"2\" stroke=\"none\"/>\n" +
              "<rect x=\"0.5\" y=\"0.5\" width=\"15\" height=\"15\" rx=\"1.5\" fill=\"none\"/>\n" +
              "</g>");
    }
    panel.add(switchGraphic);
    panel.setCellWidth(switchGraphic, switchWidth + "px");
    panel.setCellHorizontalAlignment(switchGraphic, HasHorizontalAlignment.ALIGN_LEFT);
    panel.setCellVerticalAlignment(switchGraphic, HasVerticalAlignment.ALIGN_MIDDLE);
    panel.add(switchLabel);
    toggleWidget = panel;
    refreshForm();
  }

  /**
   * Set thumb color for switch in checked state
   * Thumb color is the color the color of the button that toggles back and forth
   *
   */
  private void setThumbColorActiveProperty(String text) {
    thumbColorActive = MockComponentsUtil.getColor(text).toString();
    if (checked && !phonePreview.equals("Classic")) {
      DOM.setStyleAttribute(toggleWidget.getWidget(1).getElement().getFirstChildElement().getNextSiblingElement(),
              "fill", thumbColorActive);
    }
  }

  /**
   * Set thumb color for switch in UNhecked state
   * Thumb color is the color the color of the button that toggles back and forth
   *
   */
  private void setThumbColorInactiveProperty(String text) {
    thumbColorInactive = MockComponentsUtil.getColor(text).toString();
    if (!checked && !phonePreview.equals("Classic")) {
      DOM.setStyleAttribute(toggleWidget.getWidget(1).getElement().getFirstChildElement().getNextSiblingElement(),
              "fill", thumbColorInactive);
    }
  }

  /**
   * Set track color for switch in checked state
   * Track color is the color of the track that the toggle button slides back and forth along
   *
   */
  private void setTrackColorActiveProperty(String text) {
    trackColorActive = MockComponentsUtil.getColor(text).toString();
    if (checked && !phonePreview.equals("Classic")) {
      DOM.setStyleAttribute(toggleWidget.getWidget(1).getElement().getFirstChildElement(), "fill",
              trackColorActive);
    }
  }

  /**
   * Set track color for switch in UNchecked state
   * Track color is the color of the track that the toggle button slides back and forth along
   *
   */
  private void setTrackColorInactiveProperty(String text) {
    trackColorInactive = MockComponentsUtil.getColor(text).toString();
    if (!checked && !phonePreview.equals("Classic")) {
      DOM.setStyleAttribute(toggleWidget.getWidget(1).getElement().getFirstChildElement(), "fill",
              trackColorInactive);
    }
  }

  /*
   * Sets the switch's Checked property to a new value.
   */
  private void setOnProperty(String text) {

    checked = Boolean.parseBoolean(text);
    if(phonePreview.equals("Classic")) {
      paintClassicSwitch();
    } else {
      paintSwitch();
    }
  }

  /*
   * Sets the switch's Text property to a new value.
   */
  protected void setTextProperty(String text) {
    panel.remove(switchLabel);
    switchLabel.setText(text);
    if(phonePreview.equals("Classic")) {
      panel.insert(switchLabel, 1);
    } else {
      panel.insert(switchLabel, 0);
    }
    toggleWidget = panel;
    updatePreferredSize();
  }

  @Override
  protected void setFontSizeProperty(String text) {
    MockComponentsUtil.setWidgetFontSize(switchLabel, text);
    updatePreferredSize();
  }

  @Override
  protected void setFontTypefaceProperty(String text) {
    MockComponentsUtil.setWidgetFontTypeface(switchLabel, text);
    updatePreferredSize();
  }

  @Override
  public void onPropertyChange(String propertyName, String newValue) {
    super.onPropertyChange(propertyName, newValue);

    // Apply changed properties to the mock component
    if (propertyName.equals(PROPERTY_NAME_ON)) {
      setOnProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_THUMBCOLORACTIVE)) {
      setThumbColorActiveProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_THUMBCOLORINACTIVE)) {
      setThumbColorInactiveProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_TRACKCOLORACTIVE)) {
      setTrackColorActiveProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_TRACKCOLORINACTIVE)) {
      setTrackColorInactiveProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_HEIGHT)) {
      if(phonePreview.equals("Classic")) {
        paintClassicSwitch();
      } else {
        paintSwitch();
      }
      refreshForm();
    }
  }

  /*
   * We add the DesignPreviewChangeListener here instead of in the
   * constructor because at construction time we do not have a
   * container so getForm() fails.
   */

  @Override
  protected void setContainer(MockContainer container) {
    super.setContainer(container);
    getForm().addDesignPreviewChangeListener(this);
  }

  @Override
  public void onDesignPreviewChanged() {
    phonePreview = editor.getProjectEditor().getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
      SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_PREVIEW);
    if(phonePreview.equals("Classic")) {
      paintClassicSwitch();
    } else {
      paintSwitch();
    }
  }

}
