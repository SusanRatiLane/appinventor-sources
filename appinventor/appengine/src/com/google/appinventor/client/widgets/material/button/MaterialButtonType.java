// Copyright 2018 Makeroid, All rights reserved

package com.google.appinventor.client.widgets.material.button;


public enum MaterialButtonType {
  RAISED("mdl-button--raised"),
  FLAT("mdl-button"),
  ICON("mdl-button--icon");

  private String mdlStyle;

  private MaterialButtonType(String style) {
    mdlStyle = style;
  }

  public String getMdlStyle() {
    return mdlStyle;
  }
}
