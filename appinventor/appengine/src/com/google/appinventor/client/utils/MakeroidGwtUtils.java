/**
 * ©Kodular 2017 - 2019 | FOR MAKEROID ONLY AND NOT PRIVATE USE!
 * Some of the reusable functions are grouped in this class.
 * @author sivagiri@kodular.io (Sivagiri Visakan)
 */

package com.google.appinventor.client.utils;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;


public final class MakeroidGwtUtils {

  private MakeroidGwtUtils(){

  }

  /**
   * Generates HTML markup for Material Icons.
   * (https://www.material.io.icons)
   *
   * @param iconName Name of the icon as found at https://www.material.io.icons
   *        For example: extension
   * @return SafeHtml The HTML markup for the given material icon.
   */
  public static SafeHtml getMaterialIconHtml(String iconName) {
    return new SafeHtmlBuilder().appendHtmlConstant("<i class=\"material-icons-outlined\">" + iconName + "</i>").toSafeHtml();
  }

  /**
   * Generates the HTML markup for Font Awesome icons.
   * https://www.fontawesome.com
   *
   * @param iconName Name of the icon with the 'fa-' tag.
   *        For example: fa-google
   *        Refer https://fontawesome.com/cheatsheet
   * @return SafeHtml The HTML markup for the given FontAwesome icon.
   */
  public static SafeHtml getFontAwesomeIconHtml(String iconName) {
    return new SafeHtmlBuilder().appendHtmlConstant("<i class=\"fa " + iconName +
        "\"></i>").toSafeHtml();
  }

  /**
   * Generates the HTML for Material Design Icon.
   * https://materialdesignicons.com/
   * Note that this is different from Google's Material Icons.
   *
   * @param iconName Name of the icon including 'mdi-'
   *        For example: 'mdi-google'
   *        Refer https://materialdesignicons.com/cheatsheet.
   * @return SafeHtml The HTML markup for given iconName.
   */
  public static SafeHtml getMaterialDesignIconHtml(String iconName) {
    return new SafeHtmlBuilder().appendHtmlConstant("<i class=\"mdi " + iconName +
        "\"></i>").toSafeHtml();
  }

  public static native void upgradeElement(final Element element)
    /*-{
      $wnd.componentHandler.upgradeElement(element);
    }-*/;

  public static native void downgradeElement(final Element element)
    /*-{
      $wnd.componentHandler.downgradeElements(element);
    }-*/;

  public static void switchCssClass(UIObject uiObject, String oldClass, String newClass) {
    uiObject.removeStyleName(oldClass);
    uiObject.addStyleName(newClass);
  }
}
