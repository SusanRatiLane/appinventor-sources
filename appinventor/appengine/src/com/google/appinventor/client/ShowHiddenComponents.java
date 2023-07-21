package com.google.appinventor.client;

import com.google.appinventor.client.editor.FileEditor;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;

import static com.google.appinventor.client.Ode.MESSAGES;

public class ShowHiddenComponents extends CheckBox  {

  YaFormEditor formEditor;

  public ShowHiddenComponents() {
    super (MESSAGES.showHiddenComponentsCheckbox());
  }

  @Override
  public void onLoad() {
    addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (formEditor != null) {
          formEditor.setDisplayHiddenComponents(event.getValue());
        }
      }
    });
    Ode.getInstance().setShowHiddenComponents(this);
  }

  public void refresh() {
    FileEditor currentFileEditor = Ode.getInstance().getCurrentFileEditor();
    if (currentFileEditor instanceof YaFormEditor) {
      formEditor = (YaFormEditor) currentFileEditor;
      this.setValue(formEditor.shouldDisplayHiddenComponents());
    }
  }
}
