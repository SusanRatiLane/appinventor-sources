// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime.shadows;

import android.os.Handler;

import com.google.appinventor.components.runtime.util.AsynchUtil;

import java.util.ArrayList;
import java.util.List;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@Implements(AsynchUtil.class)
public class ShadowAsynchUtil {

  private static boolean runningOnUiThread = true;

  private static final List<Runnable> runnables = new ArrayList<Runnable>();

  public static List<Runnable> getPendingRunnables() {
    return runnables;
  }

  public static void runAllPendingRunnables() {
    while (runnables.size() > 0) {
      List<Runnable> pending = new ArrayList<Runnable>(runnables);
      runnables.clear();
      runningOnUiThread = false;
      try {
        for (Runnable r : pending) {
          r.run();
        }
      } finally {
        runningOnUiThread = true;
      }
    }
  }

  @Implementation
  public static void runAsynchronously(final Runnable call) {
    runnables.add(call);
  }

  @Implementation
  public static void runAsynchronously(final Handler androidUIHandler,
                                       final Runnable call,
                                       final Runnable callback) {
    runnables.add(call);
    runnables.add(callback);
  }

  @Implementation
  public static boolean isUiThread() {
    return runningOnUiThread;
  }
}
