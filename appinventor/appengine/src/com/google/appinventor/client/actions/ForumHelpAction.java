// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2023 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.actions;

import com.google.appinventor.client.Ode;
import com.google.appinventor.shared.rpc.user.Config;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import static com.google.appinventor.client.Ode.MESSAGES;

public class ForumHelpAction implements Command {
  static DialogBox db;
  static Frame frame;

  @Override
  public void execute() {
    AsyncCallback<String> callback = new AsyncCallback<String>() {
      @Override
      public void onSuccess(String communityUsername) {
        Ode.CLog("status: " + (communityUsername != null));
        if (communityUsername == null) {
          // Show login form
          showLoginFrame();
          showCommunityLoginButton();
        }
        else {
          showAskQuestionFrame(communityUsername);
        }
      }

      @Override
      public void onFailure(Throwable caught) {
        // error
        Ode.CLog("error: " + caught.getMessage());
      }
    };
    Ode.getInstance().getUserInfoService().getCommunityLoginUsername(callback);
  }

  private static void showLoginFrame() {
    db = new DialogBox(false, true);
    db.setText(MESSAGES.forumHelpMenuItem());
    db.setStyleName("ode-DialogBox");
    db.setHeight("320px");
    db.setWidth("600px");
    db.setGlassEnabled(true);
    db.setAnimationEnabled(true);
    db.center();
    VerticalPanel DialogBoxContents = new VerticalPanel();
    Label lblMessage = new Label("Loading...");
    Button btn = new Button("Close");
    btn.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        db.hide();
      }
    });
    Config config = Ode.getInstance().getSystemConfig();
    String userEmail = Ode.getInstance().getUser().getUserEmail();
    frame = new Frame("/CommunityLogin.html");
    frame.setWidth("600px");
    frame.setHeight("320px");
    frame.getElement().setId("CommunityLoginFrame");
    DialogBoxContents.add(frame);
    listenToSubmit(db);
    db.setWidget(DialogBoxContents);
    db.show();
    // checkIfAlreadyLoggedIn();
  }

  private static void checkIfAlreadyLoggedIn() {
    AsyncCallback<String> callback = new AsyncCallback<String>() {
      @Override
      public void onSuccess(String communityLoginUsername) {
        Ode.CLog("status: " + (communityLoginUsername != null));
        if (communityLoginUsername == null)
          showCommunityLoginButton();
        else {
          showAskQuestionFrame(communityLoginUsername);
        }
      }

      @Override
      public void onFailure(Throwable caught) {
        // error
        Ode.CLog("error: " + caught.getMessage());
      }
    };
    Ode.getInstance().getUserInfoService().getCommunityLoginUsername(callback);
  }

  private static void showAskQuestionFrame(final String userEmail) {
    if (db != null) {
      db.hide();
      db = null;
      frame = null;
    }
    db = new DialogBox(false, true);
    db.setText(MESSAGES.forumHelpMenuItem());
    db.setStyleName("ode-DialogBox");
    db.setHeight("500px");
    db.setWidth("700px");
    db.setGlassEnabled(true);
    db.setAnimationEnabled(true);
    db.center();

    HorizontalPanel DialogBoxContents = new HorizontalPanel();
    Config config = Ode.getInstance().getSystemConfig();
    Ode.CLog(userEmail);
    Frame frameX = new Frame("/AskHelp.html");
    frameX.setWidth("400px");
    frameX.setHeight("500px");
    frameX.getElement().setId("AskHelpFrame");
    frameX.addLoadHandler(new LoadHandler() {
      @Override
      public void onLoad(LoadEvent event) {
        updateEmail(userEmail);
      }
    });

    Frame frameY = new Frame("/SimilarQuestions.html");
    frameY.setWidth("400px");
    frameY.setHeight("500px");
    frameY.getElement().setId("SimilarTopicsFrame");
    DialogBoxContents.add(frameX);
    DialogBoxContents.add(frameY);

    db.setWidget(DialogBoxContents);
    db.show();
    askQuestionListener(userEmail, Ode.getInstance().getCurrentYoungAndroidProjectId() + "");
  }


  private static void fetchSimilarTopics(String query) {
    Ode.getInstance().getSubmitPostService().getSimilarTopics(query, new AsyncCallback<String>() {
      @Override
      public void onSuccess(String response) {
        updateSimilarTopics(response);
      }

      @Override
      public void onFailure(Throwable error) {
        Ode.CLog(error.getMessage());
      }
    });
  }

  private static void fetchCategories() {
    Ode.getInstance().getSubmitPostService().getDiscourseCategories(new AsyncCallback<String>() {
      @Override
      public void onSuccess(String response) {
        Ode.CLog(response);
        updateCategories(response);
      }

      @Override
      public void onFailure(Throwable error) {
        Ode.CLog(error.getMessage());
      }
    });
  }

  private static void submitPost(String username, String title, String description, int categoryId, boolean attachProject, String projectId) { // passing project id as string because long is not safe to access in JSNI
    Ode.getInstance().getSubmitPostService().submitPost(Ode.getInstance().getUser().getUserId()
        , username, title, description, categoryId
        , attachProject, projectId, new AsyncCallback<String>() {
          @Override
          public void onSuccess(String response) {
            Ode.CLog(response);
            JSONObject jsonObject = JSONParser.parseStrict(response).isObject();
            if (jsonObject != null) {
              if (jsonObject.get("errors") == null || jsonObject.get("errors").isArray() == null) {
                submitPostResult(false, null);
              } else {
                JSONArray errors = jsonObject.get("errors").isArray();
                if (errors.get(0) != null)
                  submitPostResult(true, errors.get(0).isString().stringValue());
                else
                  submitPostResult(true, "Something went wrong.");
              }
            } else {
              submitPostResult(true, "Something went wrong.");
            }
          }

          @Override
          public void onFailure(Throwable reason) {
            Ode.CLog("ERROR: " + reason.getMessage() + reason.getStackTrace()[0].getLineNumber());
            submitPostResult(true, "Something went wrong.");
          }
        });
  }

  private static void logoutFromCommunity() {
    Ode.getInstance().getUserInfoService().logoutFromCommunity(new AsyncCallback<Void>() {
      @Override
      public void onSuccess(Void res) {
      }

      @Override
      public void onFailure(Throwable caught) {
        Ode.CLog("error: " + caught.getMessage());
      }
    });
  }

  private static void closeDialog(DialogBox db, String response) {
    db.hide();
  }

  private static native void askQuestionListener(String userEmail, String projectId) /*-{
    $wnd.onmessage = function(r) {
      console.log(r)
      var d = r.data
      var json = JSON.parse(d)
      console.log(json.type)
      if(json.type == 'close') {
        @com.google.appinventor.client.actions.ForumHelpAction::closeDialog(Lcom/google/gwt/user/client/ui/DialogBox;Ljava/lang/String;)(@com.google.appinventor.client.actions.ForumHelpAction::db, d);
      }
      else if (json.type == 'submit')
      {
        console.log("Printing data: ", json);
        var title = json.title
        var category = json.category
        var description = json.description
        var attach = json.isChecked
        @com.google.appinventor.client.actions.ForumHelpAction::submitPost(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;)(userEmail, title, description, category, attach, projectId)
      } else if (json.type == 'logout') {
        console.log("type is logout")
        @com.google.appinventor.client.actions.ForumHelpAction::closeDialog(Lcom/google/gwt/user/client/ui/DialogBox;Ljava/lang/String;)(@com.google.appinventor.client.actions.ForumHelpAction::db, d);
        @com.google.appinventor.client.actions.ForumHelpAction::logoutFromCommunity()();
      } else if (json.type == 'get_user_email') {
        $doc.getElementById("AskHelpFrame").contentWindow.postMessage({
          type: 'email',
          username: userEmail
        });
      } else if (json.type == 'categories') {
        @com.google.appinventor.client.actions.ForumHelpAction::fetchCategories()();
      } else if (json.type == 'similar_topics') {
        @com.google.appinventor.client.actions.ForumHelpAction::fetchSimilarTopics(Ljava/lang/String;)(json.text);
      }
    }
  }-*/;

  private static native void updateCategories(String response) /*-{
    $doc.getElementById("AskHelpFrame").contentWindow.postMessage({
      type: 'categories',
      categories: response
    });
  }-*/;

  private static native void updateSimilarTopics(String response) /*-{
    $doc.getElementById("SimilarTopicsFrame").contentWindow.postMessage({
      type: 'similar_topics',
      topics: response
    });
  }-*/;

  private static native void submitPostResult(boolean error, String errorMessage) /*-{
    if (error) {
      $doc.getElementById("AskHelpFrame").contentWindow.postMessage({
        type: 'error',
        message: errorMessage
      });
    } else {
      $doc.getElementById("AskHelpFrame").contentWindow.postMessage({
        type: 'upload_success',
      });
    }

  }-*/;

  private static native void updateEmail(String username) /*-{
    $doc.getElementById("AskHelpFrame").contentWindow.postMessage({
      type: 'email',
      username: username
    });
  }-*/;

  private static native void showCommunityLoginButton() /*-{
    console.log("Sending Message")
    $doc.getElementById("CommunityLoginFrame").contentWindow.postMessage("show")
  }-*/;

  private static native void listenToSubmit(DialogBox db) /*-{
    $wnd.onmessage = function(r) {
      console.log("From ODE: ", r)
      var type;
      type = JSON.parse(r.data).type;
      console.log(r)
      if (type == 'close')
        @com.google.appinventor.client.actions.ForumHelpAction::closeDialog(Lcom/google/gwt/user/client/ui/DialogBox;Ljava/lang/String;)(db, r)
      else if (type == 'check_status') {
        @com.google.appinventor.client.actions.ForumHelpAction::checkIfAlreadyLoggedIn()()
      }
    }
  }-*/;
}

