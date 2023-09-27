/*
Copyright 2017 Makeroid | All rights reserved
Released under the Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0
*/

package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Categories for grouping components in the palette within the Designer.
 */
public enum ComponentCategory {
  // TODO(user): i18n category names
  //name, isParent, parentCategory, hasChildren
  USERINTERFACE("User Interface", "devices_other"),
  LAYOUT("Layout", false, "table_chart"),
//  LAYOUT_GENERAL("General", false, ComponentCategory.LAYOUT, false, "dashboard"),
//  LISTVIEWS("Listviews", false, ComponentCategory.LAYOUT, false, "dns"),
//  NAVIGATION("Navigation", false, ComponentCategory.LAYOUT, false, "control_camera"),
//  VIEWS("Views", false, ComponentCategory.LAYOUT, false, "view_array"),
  MEDIA("Media", "camera"),
  ANIMATION("Drawing and Animation", "color_lens"),
  MAPS("Maps", "map"),
  CHARTS("Charts", "insert_chart"),
  DATASCIENCE("Data Science", "calculate"),
  SENSORS("Sensors", "explore"),
  SOCIAL("Social", "supervised_user_circle"),
  STORAGE("Storage", "file_copy"),
  UTILITIES("Utilities", "work_outline"),
  DYNAMIC("Dynamic", "code"),
  CONNECTIVITY("Connectivity", "rss_feed"),
  GOOGLE("Google", "mdi-google"),
  MONETIZATION("Monetization", true, "monetization_on"),
  MONETIZATION_GENERAL("General", false, ComponentCategory.MONETIZATION, false, "shopping_cart"),
  ADVERTISING("Advertising", false, ComponentCategory.MONETIZATION, false, "branding_watermark"),
  LEGOMINDSTORMS("LEGO\u00AE MINDSTORMS\u00AE", false, "widgets"),
//  EV3("LEGO\u00AE EV3", false, ComponentCategory.LEGOMINDSTORMS, false, "gamepad"),
//  NXT("LEGO\u00AE NXT", false, ComponentCategory.LEGOMINDSTORMS, false, "gamepad"),
  EXPERIMENTAL("Experimental", "new_releases"),
  DEPRECATED("Deprecated", "format_line_spacing"),
  EXTENSION("Extension", "extension"),
  FUTURE("Future", "update"),
  INTERNAL("For internal use only"),
  UNINITIALIZED("Uninitialized");  // UNINITIALIZED is used as a default value so Swing libraries can still compile

  // Mapping of component categories to names consisting only of lower-case letters,
  // suitable for appearing in URLs.
  private static final Map<String, String> DOC_MAP = new HashMap<>();
  static {
    DOC_MAP.put("User Interface", "user-interface");
    DOC_MAP.put("Layout", "layout");
    DOC_MAP.put("LayoutGeneral", "layout/general");
    DOC_MAP.put("Listviews", "layout/listviews");
    DOC_MAP.put("Views", "layout/views");
    DOC_MAP.put("Navigation", "layout/navigation");
    DOC_MAP.put("Media", "media");
    DOC_MAP.put("Drawing and Animation", "drawing-and-animation");
    DOC_MAP.put("Maps", "maps");
    DOC_MAP.put("Sensors", "sensors");
    DOC_MAP.put("Social", "social");
    DOC_MAP.put("Storage", "storage");
    DOC_MAP.put("Device", "device");
    DOC_MAP.put("Dynamic", "dynamic");
    DOC_MAP.put("Connectivity", "connectivity");
    DOC_MAP.put("Google", "google");
    DOC_MAP.put("LEGO\u00AE MINDSTORMS\u00AE", "lego-mindstorms");
    DOC_MAP.put("LEGO\u00AE EV3", "lego-mindstorms/ev3");
    DOC_MAP.put("LEGO\u00AE NXT", "lego-mindstorms/nxt");
    DOC_MAP.put("Monetization", "monetization");
    DOC_MAP.put("MonetizationGeneral", "monetization/general");
    DOC_MAP.put("Advertising", "monetization/advertising");
    DOC_MAP.put("Utilities", "utilities");
    DOC_MAP.put("Experimental", "experimental");
    DOC_MAP.put("Deprecated", "deprecated");
    DOC_MAP.put("Extension", "extension");
  }

  private String name;
  private Boolean isParent;
  private ComponentCategory parentCategory;
  private Boolean hasChildren;
  private String iconName;

  ComponentCategory(String categoryName, Boolean isParent, ComponentCategory parentCategory, Boolean hasChildren, String iconName) {
    this.name = categoryName;
    this.isParent = isParent;
    this.parentCategory = parentCategory;
    this.hasChildren = hasChildren;
    this.iconName = iconName;
  }

  ComponentCategory(String categoryName, String iconName) {
    this(categoryName, true, null, false, iconName);
  }

  ComponentCategory(String categoryName) {
    this(categoryName, true, null, false, "offline_bolt");
  }

  ComponentCategory(String categoryName, Boolean hasChildren, String iconName) {
    this(categoryName, true, null, hasChildren, iconName);
  }

  /**
   * Returns the display name of this category, as used on the Designer palette, such
   * as "Experimental".  To get the enum name (such as "EXPERIMENTAL"),
   * use {@link #toString}.
   *
   * @return the display name of this category
   */
  public String getName() {
    return name;
  }

  /**
   * Returns true if this category is a parent category
   *
   * @return true if this category is a parent category
   */
  public Boolean isParentCategory() {
    return isParent;
  }

  /**
   * Returns true if this category has any child categories
   *
   * @return has child categories
   */
  public Boolean hasChildCategories() {
    return hasChildren;
  }

  /**
   * Returns the parent category of this category
   *
   * @return parent category
   */
  public ComponentCategory getParentCategory() {
    return parentCategory;
  }

  /**
   * Returns the icon name of this category
   *
   * @return icon of this category
   */
  public String getIcon() {
    return iconName;
  }

  public List<ComponentCategory> getChildren() {
    List<ComponentCategory>children = new ArrayList();
    if(!hasChildCategories())
      return children;
    for(ComponentCategory cc : values()) {
      if(cc.getParentCategory() == this) {
        children.add(cc);
      }
    }
    return children;
  }

  /**
   * Returns a version of the name of this category consisting of only lower-case
   * letters, meant for use in a URL.  For example, for the category with the enum
   * name "EXPERIMENTAL" and display name "Experimental", this returns
   * "experimental".
   *
   * @return a name for this category consisting of only lower-case letters
   */
  public String getDocName() {
    //remove ambiguities in component category names
    if("LAYOUT_GENERAL".equals(name()))
      return DOC_MAP.get("LayoutGeneral");
    else if("MONETIZATION_GENERAL".equals(name()))
      return DOC_MAP.get("MonetizationGeneral");
    return DOC_MAP.get(name);
  }
}
