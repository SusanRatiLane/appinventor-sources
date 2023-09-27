// Copyright 2018 Makeroid, All rights reserved.

package com.google.appinventor.client.widgets.material.button;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.utils.MakeroidGwtUtils;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.Element;

/*
 * A class representing the Material Design Lite's Button
 * https://getmdl.io/components/index.html#buttons-section
 */
public class MaterialButton extends Button {

  private MaterialButtonType type;
  static int BASE_ID = 0;
  int id = ++BASE_ID;
  HTML tooltipHTML;
  boolean isEnabled = true;
  private final static String MATERIAL_DESIGN_ICON_STYLE = "mdi"; // Icons from https://materialdesignicons.com/
  private final static String FONTAWESOME_STYLE = "fa";

  /*
   * Creates a button of given type with the given caption.
   * If the type is ICON, then the Material Icon(https://material.io/icons)
   * of the given text is set as caption.
   */
  public MaterialButton(String text, MaterialButtonType type, String tooltip) {
    super();
    setType(type);
    if(type == MaterialButtonType.ICON) {
      setIcon(text);
    } else {
      setHTML(text);
    }
    applyMdlButtonStyles();
    if(Ode.getLiteMode().equals("False"))
      MakeroidGwtUtils.upgradeElement(getElement());
    getElement().setId("material-button-" + id);
    if(!tooltip.isEmpty() && tooltip != null) {
      this.tooltipHTML = new HTML("<div class=\"mdl-tooltip\" data-mdl-for=\"material-button-" + id + "\" id=\"material-tooltip-" + id + "\">" + tooltip + "</div>");
      RootPanel.get().add(tooltipHTML);
    } else {
      this.tooltipHTML = null;
    }
  }

  public MaterialButton(String text, MaterialButtonType type) {
    this(text, type, "");
  }

  /*
   * Creates a FLAT button with caption.
   */
  public MaterialButton(String caption) {
    this(caption, MaterialButtonType.FLAT, "");
  }

  /*
   * Creates a button without caption of the given type.
   */
  public MaterialButton(MaterialButtonType type) {
    super();
    setType(type);
    applyMdlButtonStyles();
  }

  public MaterialButton(Element button) {
    super(button);
  }

  @Override
  protected void onLoad() {
    if(Ode.getLiteMode().equals("False"))
      MakeroidGwtUtils.upgradeElement(getElement());
    if(this.tooltipHTML != null)
      MakeroidGwtUtils.upgradeElement(DOM.getElementById("material-tooltip-" + id));
  }

  @Override
  public void setHTML(String html) {
    super.setHTML(html);
    if(Ode.getLiteMode().equals("True"))
      return;
    MakeroidGwtUtils.downgradeElement(getElement());
    MakeroidGwtUtils.upgradeElement(getElement());
  }

  @Override
  public void setText(String text) {
    super.setText(text);
    if(Ode.getLiteMode().equals("True"))
      return;
    MakeroidGwtUtils.downgradeElement(getElement());
    MakeroidGwtUtils.upgradeElement(getElement());
  }

  @Override
  public void setEnabled(boolean enable) {
    if(isEnabled == enable)
      return;
    super.setEnabled(enable);
    isEnabled = enable;
    if(enable)
    {
      removeStyleName("mdl-button--disabled");

    }
    else
      addStyleName("mdl-button--disabled");
  }

  private void setType(MaterialButtonType type) {
    this.type = type;
    addStyleName(type.getMdlStyle());
  }

  public void setIcon(String iconName) {
    if (type != MaterialButtonType.ICON) {
      throw new IllegalStateException("The button is not of type ICON"); // we don't allow changing button types.(FLAT ot RAISED cannot have icons)
    }
    SafeHtml iconHtml;
    if(iconName.startsWith(MATERIAL_DESIGN_ICON_STYLE)) {
      iconHtml = MakeroidGwtUtils.getMaterialDesignIconHtml(iconName);
    } else if(iconName.startsWith(FONTAWESOME_STYLE)) {
      iconHtml = MakeroidGwtUtils.getFontAwesomeIconHtml(iconName);
    } else {
      iconHtml = MakeroidGwtUtils.getMaterialIconHtml(iconName);
    }
    setHTML(iconHtml);
  }

  private void applyMdlButtonStyles() {
    addStyleName("mdl-button");
    addStyleName("mdl-js-button mdl-js-ripple-effect");
  }
}
