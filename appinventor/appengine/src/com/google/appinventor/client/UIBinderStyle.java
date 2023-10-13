package com.google.appinventor.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.FlowPanel;

public class UIBinderStyle {
  public static final String design = "com.google.appinventor.client.style.classic.";

  @UiTemplate(design + "Ode.ui.xml")
  interface OdeUiBinder extends UiBinder<FlowPanel, Ode> {}

  OdeUiBinder getOdeUiBinder() {
    return GWT.create(OdeUiBinder.class);
  }
}
