package com.google.appinventor.client.editor.youngandroid.palette;

import com.google.appinventor.client.editor.simple.palette.SimplePalettePanel;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.appinventor.client.utils.Promise;
import com.google.appinventor.client.utils.Promise.WrappedException;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaletteFactory {
  private static final Logger LOG = Logger.getLogger(PaletteFactory.class.getName());
  public interface Factory {
    SimplePalettePanel create(YaFormEditor editor);
  }
  private static Factory constructor = null;
  private static Promise<Object> initializer = null;

  public static Promise<Object> initialize() {
    if (initializer != null) {
      return initializer;
    }
    initializer = new Promise<>(((resolve, reject) -> {
      String palette = Window.Location.getParameter("ui");
      if (!"next".equals(palette)) {
        GWT.runAsync(new RunAsyncCallback() {
          @Override
          public void onFailure(Throwable throwable) {
            LOG.log(Level.SEVERE, "Unable to initialize", throwable);
            reject.apply(new WrappedException(throwable));
          }

          @Override
          public void onSuccess() {
            constructor = YoungAndroidPalettePanel::new;
            resolve.apply(null);
          }
        });
      } else {
        GWT.runAsync(new RunAsyncCallback() {
          @Override
          public void onFailure(Throwable throwable) {
            LOG.log(Level.SEVERE, "Unable to initialize", throwable);
            reject.apply(new WrappedException(throwable));
          }

          @Override
          public void onSuccess() {
            constructor = KodularPalettePanel::new;
            resolve.apply(null);
          }
        });
      }
    }));
    return initializer;
  }

  public static SimplePalettePanel create(YaFormEditor editor) {
    if (constructor == null) {
      throw new IllegalStateException();
    }
    return constructor.create(editor);
  }
}
