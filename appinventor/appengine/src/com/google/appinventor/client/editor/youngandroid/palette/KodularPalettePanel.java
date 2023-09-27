/*Author: Vishwas Adiga
 *Exclusive usage rights to Makeroid
 */

package com.google.appinventor.client.editor.youngandroid.palette;

import com.google.appinventor.client.ComponentsTranslation;
import com.google.appinventor.client.editor.simple.SimpleComponentDatabase;
import com.google.appinventor.client.editor.simple.components.MockComponent;
import com.google.appinventor.client.editor.simple.components.utils.PropertiesUtil;
import com.google.appinventor.client.editor.simple.palette.DropTargetProvider;
import com.google.appinventor.client.editor.simple.palette.SimpleComponentDescriptor;
import com.google.appinventor.client.editor.simple.palette.SimplePaletteItem;
import com.google.appinventor.client.editor.simple.palette.SimplePalettePanel;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.appinventor.client.explorer.project.ComponentDatabaseChangeListener;
import com.google.appinventor.client.wizards.ComponentImportWizard;
import com.google.appinventor.client.widgets.material.button.MaterialButton;
import com.google.appinventor.client.widgets.material.button.MaterialButtonType;
import com.google.appinventor.common.version.AppInventorFeatures;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.appinventor.client.Ode;
import com.google.appinventor.client.boxes.PaletteBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;

import static com.google.appinventor.client.Ode.MESSAGES;

public class KodularPalettePanel extends Composite implements SimplePalettePanel, ComponentDatabaseChangeListener {

  private final SimpleComponentDatabase COMPONENT_DATABASE;
  private final YaFormEditor editor;
  private final Map<ComponentCategory, PaletteHelper> paletteHelpers;
  private final HorizontalPanel paletteContainer;
  private final VerticalPanel categoryPanel;
  public final VerticalPanel searchPanel;
  private final Map<ComponentCategory, ScrollPanel> componentsPanels;
  private final Map<ComponentCategory, HorizontalPanel> categoryItems;
  private final Map<ComponentCategory, VerticalPanel> parentCategories;
  private final Map<ComponentCategory, HTML> parentCategoryLabels;
  private final Map<String, SimplePaletteItem> simplePaletteItems;
  private ComponentCategory currentCategory;

  private Boolean searchModeActive;

  private DropTargetProvider dropTargetProvider;

  /**
   * Creates a new component palette panel.
   *
   * @param editor parent editor of this panel
   */
  public KodularPalettePanel(YaFormEditor editor) {
    this.editor = editor;
    COMPONENT_DATABASE = SimpleComponentDatabase.getInstance(editor.getProjectId());

    paletteContainer = new HorizontalPanel();

    categoryPanel = new VerticalPanel();
    categoryPanel.setStylePrimaryName("palette-categories");

    componentsPanels = new HashMap<ComponentCategory, ScrollPanel>();

    paletteHelpers = new HashMap<ComponentCategory, PaletteHelper>();
    // If a category has a palette helper, add it to the paletteHelpers map here.
//    paletteHelpers.put(ComponentCategory.USERINTERFACE, new UserInterfacePaletteHelper());
//    paletteHelpers.put(ComponentCategory.LAYOUT_GENERAL, new LayoutGeneralPaletteHelper());
//    paletteHelpers.put(ComponentCategory.LISTVIEWS, new LayoutListViewsPaletteHelper());
//    paletteHelpers.put(ComponentCategory.NAVIGATION, new LayoutNavigationPaletteHelper());
//    paletteHelpers.put(ComponentCategory.VIEWS, new LayoutViewsPaletteHelper());
//    paletteHelpers.put(ComponentCategory.ANIMATION, new AnimationPaletteHelper());
//    paletteHelpers.put(ComponentCategory.CONNECTIVITY, new ConnectivityPaletteHelper());
//    paletteHelpers.put(ComponentCategory.MEDIA, new MediaPaletteHelper());
//    paletteHelpers.put(ComponentCategory.STORAGE, new StoragePaletteHelper());
//    paletteHelpers.put(ComponentCategory.UTILITIES, new UtilitiesPaletteHelper());
//    paletteHelpers.put(ComponentCategory.GOOGLE, new GooglePaletteHelper());
//    paletteHelpers.put(ComponentCategory.MONETIZATION_GENERAL, new MonetizationGeneralPaletteHelper());
//    paletteHelpers.put(ComponentCategory.ADVERTISING, new MonetizationAdvertisingPaletteHelper());
//    paletteHelpers.put(ComponentCategory.EXPERIMENTAL, new ExperimentalPaletteHelper());
    paletteHelpers.put(ComponentCategory.LEGOMINDSTORMS, new LegoPaletteHelper());
//    paletteHelpers.put(ComponentCategory.DEPRECATED, new DeprecatedPaletteHelper());
//    paletteHelpers.put(ComponentCategory.DYNAMIC, new DynamicPaletteHelper());

    simplePaletteItems = new HashMap<String, SimplePaletteItem>();

    categoryItems = new HashMap<ComponentCategory, HorizontalPanel>();
    parentCategories = new HashMap<ComponentCategory, VerticalPanel>();
    parentCategoryLabels = new HashMap<ComponentCategory, HTML>();

    searchPanel = new VerticalPanel();
    searchPanel.setStylePrimaryName("palette__searchpanel--inactive");
    searchPanel.getElement().setId("palette__searchpanel");

    for(final ComponentCategory category : ComponentCategory.values()) {
      if(showCategory(category)) {
        if(!category.isParentCategory()) continue;
        if(category.hasChildCategories()) {
          final VerticalPanel childContainer = new VerticalPanel();
          childContainer.setStylePrimaryName("palette-child-categories--hidden");

          for(ComponentCategory child : category.getChildren()) {
            HorizontalPanel cc = initCategory(child);
            cc.addStyleName("palette-child-category");
            childContainer.add(cc);
          }
          //Assign caption to be shown for parent category
          String title = ComponentsTranslation.getCategoryName(category.getName());

          final HTML expandLabel = new HTML("<i class=\"material-icons-outlined\">unfold_more</i>");
          expandLabel.setStylePrimaryName("palette-category-expand-label");

          MaterialButton categoryIcon = new MaterialButton(MaterialButtonType.ICON);
          categoryIcon.setIcon(category.getIcon());
          // TODO(diego@kodular.io): Improve GA4
          categoryIcon.getElement().setAttribute("onclick", "gtag('event', 'Designer', {'action': 'Categories', 'label': '" + category.getName() + "'});");

          ClickHandler categoryItemClickHandler = new ClickHandler() {
            public void onClick(ClickEvent event) {
              setActiveChildCategory(childContainer);
              if("<i class=\"material-icons-outlined\">unfold_more</i>".equals(expandLabel.getHTML()))
                expandLabel.setHTML("<i class=\"material-icons-outlined\">unfold_less</i>");
              else
                expandLabel.setHTML("<i class=\"material-icons-outlined\">unfold_more</i>");
            }
          };

          categoryIcon.addClickHandler(categoryItemClickHandler);
          categoryIcon.setStylePrimaryName("palette-category-icon-button");

          Label categoryName = new Label(title);
          categoryName.setStylePrimaryName("palette-category-name-label");
          categoryName.addClickHandler(categoryItemClickHandler);

          expandLabel.addClickHandler(categoryItemClickHandler);

          HorizontalPanel categoryContainer = new HorizontalPanel();
          categoryContainer.setStylePrimaryName("palette-category--inactive");

          categoryContainer.add(categoryIcon);
          categoryContainer.add(categoryName);
          categoryContainer.add(expandLabel);

          categoryPanel.add(categoryContainer);
          categoryPanel.add(childContainer);

          parentCategories.put(category, childContainer);
          parentCategoryLabels.put(category, expandLabel);
          categoryItems.put(category, categoryContainer);

        } else {
          categoryPanel.add(initCategory(category));
        }
      }
    }

    paletteContainer.add(searchPanel);
    paletteContainer.add(categoryPanel);

    for (ComponentCategory category : componentsPanels.keySet()) {
      paletteContainer.add(componentsPanels.get(category));
    }

    initExtensionPanel();
    initWidget(paletteContainer);

  }

  private HorizontalPanel initCategory(final ComponentCategory category) {
    //Create new vertical panel for each category
    VerticalPanel categoryCorrespondingPanel = new VerticalPanel();
    categoryCorrespondingPanel.setStylePrimaryName("palette-category-componentslist");   //All panels hidden except user interface

    //Put vertical panel in scrollpanel
    ScrollPanel categoryCorrespondingScrollPanel = new ScrollPanel();
    categoryCorrespondingScrollPanel.setStylePrimaryName("palette-category-componentslist-scroll--hidden");
    categoryCorrespondingScrollPanel.setAlwaysShowScrollBars(true);

    categoryCorrespondingScrollPanel.setWidget(categoryCorrespondingPanel);
    this.componentsPanels.put(category, categoryCorrespondingScrollPanel);

    //Assign caption to be shown for each category
    String title = ComponentsTranslation.getCategoryName(category.getName());

    MaterialButton categoryIcon = new MaterialButton(MaterialButtonType.ICON);
    categoryIcon.setIcon(category.getIcon());
    // TODO(diego@kodular.io): Improve GA4
    categoryIcon.getElement().setAttribute("onclick", "gtag('event', 'Designer', {'action': 'Categories', 'label': '" + category.getName() + "'});");
    ClickHandler categoryItemClickHandler = new ClickHandler() {
      public void onClick(ClickEvent event) {
        setActiveCategory(category);
      }
    };

    categoryIcon.addClickHandler(categoryItemClickHandler);
    categoryIcon.setStylePrimaryName("palette-category-icon-button");

    Label categoryName = new Label(title);
    categoryName.setStylePrimaryName("palette-category-name-label");
    categoryName.addClickHandler(categoryItemClickHandler);

    HorizontalPanel categoryContainer = new HorizontalPanel();
    categoryContainer.setStylePrimaryName("palette-category--inactive");

    if(ComponentCategory.USERINTERFACE.equals(category)) {
      //Set user interface panel to active, by default
      categoryCorrespondingScrollPanel.setStylePrimaryName("palette-category-componentslist-scroll");
      currentCategory =  category;
      categoryContainer.setStylePrimaryName("palette-category--active");
    }

    categoryContainer.add(categoryIcon);
    categoryContainer.add(categoryName);
    categoryItems.put(category, categoryContainer);
    setSearchMode(false);
    return categoryContainer;
  }

  private void setActiveCategory(ComponentCategory activeCategory) {
    if(currentCategory != null){
      // Set current category to inactive.
      ScrollPanel currentCategoryPanel = componentsPanels.get(currentCategory);
      currentCategoryPanel.setStylePrimaryName("palette-category-componentslist-scroll--hidden");
      categoryItems.get(currentCategory).setStylePrimaryName("palette-category--inactive");

      if(!currentCategory.isParentCategory())
        categoryItems.get(currentCategory.getParentCategory()).removeStyleName("palette-category--child-selected");
    }


    if(activeCategory != null) {
      //hide all the child categories if the current category is one
      for(ComponentCategory category : parentCategories.keySet()) {
        if(!activeCategory.isParentCategory()) {
          if(activeCategory.getParentCategory() != category) {
            parentCategories.get(category).setStylePrimaryName("palette-child-categories--hidden");
            parentCategoryLabels.get(category).setHTML("<i class=\"material-icons-outlined\">unfold_more</i>");
          }
        } else {
          parentCategories.get(category).setStylePrimaryName("palette-child-categories--hidden");
          parentCategoryLabels.get(category).setHTML("<i class=\"material-icons-outlined\">unfold_more</i>");
        }
      }
      //Set current view to visible
      ScrollPanel activeCategoryPanel = componentsPanels.get(activeCategory);
      activeCategoryPanel.setStylePrimaryName("palette-category-componentslist-scroll");

      //Set current icon and label to primary
      categoryItems.get(activeCategory).setStylePrimaryName("palette-category--active");

      currentCategory = activeCategory;
      if(!currentCategory.isParentCategory())
        categoryItems.get(currentCategory.getParentCategory()).addStyleName("palette-category--child-selected");
    }
  }

  private void setActiveChildCategory(VerticalPanel panel) {
    if("palette-child-categories--hidden".equals(panel.getStylePrimaryName()))
      panel.setStylePrimaryName("palette-child-categories--visible");
    else
      panel.setStylePrimaryName("palette-child-categories--hidden");
  }

  private static boolean showCategory(ComponentCategory category) {
    if (category == ComponentCategory.UNINITIALIZED) {
      return false;
    }
    if (category == ComponentCategory.INTERNAL || category == ComponentCategory.DEPRECATED) {
      //If the user is Admin, show the internal category.
      return Ode.getInstance().getUser().getIsAdmin();
    }

    if (category == ComponentCategory.EXPERIMENTAL) {
      if (Ode.getExperimentalVisibilityPreference().equals("True")) { //If experimental is set to visible, then show the category.
        return true;
      } else {
        return false;
      }
    }
    if (category == ComponentCategory.FUTURE) {
      return AppInventorFeatures.enableFutureFeatures();
    }
    return true;
  }

  public void searchByComponentName(String componentName) {
    searchPanel.clear();
    int currentComponent = 0;
    if(componentName != "") {
      //Search for the component name in the set of all
      for (String component : COMPONENT_DATABASE.getComponentNames()) {
        if(ComponentsTranslation.getComponentName(component).toLowerCase().contains(componentName.toLowerCase())) {
          this.addComponent(component, true);
          currentComponent++;
          if(currentComponent >= 10) break;
        }
      }
    } else {
      for (String component : COMPONENT_DATABASE.getComponentNames()) {
        this.addComponent(component, true);
        currentComponent++;
        if(currentComponent >= 10) break;
      }
			/*Label noResultsLabel = new Label("No results to show");
			noResultsLabel.setStylePrimaryName("palette__label-no-results");
			searchPanel.add(noResultsLabel);*/
    }
  }

  public void setSearchMode(boolean mode) {
    searchModeActive = mode;
    PaletteBox.getPaletteBox().setSearchBarEnabled(searchModeActive);
    if(searchModeActive) {
      searchPanel.setStylePrimaryName("palette__searchpanel--active");
      ScrollPanel currentCategoryPanel = componentsPanels.get(currentCategory);
      currentCategoryPanel.setStylePrimaryName("palette-category-componentslist-scroll--hidden");
      categoryItems.get(currentCategory).setStylePrimaryName("palette-category--inactive");
      categoryPanel.setStylePrimaryName("palette-categories--hidden");
    } else {
      searchPanel.setStylePrimaryName("palette__searchpanel--inactive");
      ScrollPanel currentCategoryPanel = componentsPanels.get(currentCategory);
      currentCategoryPanel.setStylePrimaryName("palette-category-componentslist-scroll");
      categoryItems.get(currentCategory).setStylePrimaryName("palette-category--active");
      categoryPanel.setStylePrimaryName("palette-categories");
    }
  }

  public boolean getSearchMode() {
    return searchModeActive;
  }

  /**
   * Loads all components to be shown on this palette.  Specifically, for
   * each component (except for those whose category is UNINITIALIZED, or
   * whose category is INTERNAL and we're running on a production server,
   * or who are specifically marked as not to be shown on the palette),
   * this creates a corresponding {@link SimplePaletteItem} and adds it
   * to the panel corresponding to its category.
   */
  public void loadComponents() {
    for (String component : COMPONENT_DATABASE.getComponentNames()) {
      this.addComponent(component, false);
    }
  }

  @Override
  public void loadComponents(DropTargetProvider dropTargetProvider) {
    this.dropTargetProvider = dropTargetProvider;
    loadComponents();
  }

  @Override
  public void configureComponent(MockComponent mockComponent) {
    String componentType = mockComponent.getType();
    PropertiesUtil.populateProperties(mockComponent, COMPONENT_DATABASE.getPropertyDefinitions(componentType), editor);
  }

  @Override
  public void addComponent(String componentTypeName) {
    addComponent(componentTypeName, true);
  }

  /**
   *  Loads a single Component to Palette. Used for adding Components.
   */
  public void addComponent(String componentTypeName, boolean addToSearchPanel) {
    int version = COMPONENT_DATABASE.getComponentVersion(componentTypeName);
    String versionName = COMPONENT_DATABASE.getComponentVersionName(componentTypeName);
    String dateBuilt = COMPONENT_DATABASE.getComponentBuildDate(componentTypeName);
    String helpString = COMPONENT_DATABASE.getHelpString(componentTypeName);
    String helpUrl = COMPONENT_DATABASE.getHelpUrl(componentTypeName);
    String categoryDocUrlString = COMPONENT_DATABASE.getCategoryDocUrlString(componentTypeName);
    String categoryString = COMPONENT_DATABASE.getCategoryString(componentTypeName);
    boolean showOnPalette = COMPONENT_DATABASE.getShowOnPalette(componentTypeName);
    boolean nonVisible = COMPONENT_DATABASE.getNonVisible(componentTypeName);
    boolean external = COMPONENT_DATABASE.getComponentExternal(componentTypeName);
    ComponentCategory category = ComponentCategory.valueOf(categoryString);
    if (showOnPalette && showCategory(category)) {
      SimplePaletteItem item = new SimplePaletteItem(
          new SimpleComponentDescriptor(componentTypeName, editor, version, versionName, dateBuilt, helpString, helpUrl,
              categoryDocUrlString, showOnPalette, nonVisible, external), dropTargetProvider, addToSearchPanel, category);
      if(!addToSearchPanel) { //palette item is not to be added to search panel
        if (simplePaletteItems.containsKey(componentTypeName)) { // We are upgrading
          removeComponent(componentTypeName);
        }
        simplePaletteItems.put(componentTypeName, item);
        addPaletteItem(item, category, false);
      } else {
        addPaletteItem(item, category, true);
      }
    }
  }


  public void removeComponent(String componentTypeName) {
    String categoryString = COMPONENT_DATABASE.getCategoryString(componentTypeName);
    ComponentCategory category = ComponentCategory.valueOf(categoryString);
    if (simplePaletteItems.containsKey(componentTypeName)) {
      removePaletteItem(simplePaletteItems.get(componentTypeName), category);
    }
  }



  /*
   * Adds a component entry to the palette.
   */
  private void addPaletteItem(SimplePaletteItem component, ComponentCategory category, Boolean addToSearchPanel) {
    if(!addToSearchPanel) {
      VerticalPanel categoryPanel = (VerticalPanel) componentsPanels.get(category).getWidget();
      PaletteHelper paletteHelper = paletteHelpers.get(category);
      if (paletteHelper != null) {
        paletteHelper.addPaletteItem(categoryPanel, component);
      } else {
        categoryPanel.add(component);
      }
    } else {
      searchPanel.add(component);
    }
  }

  private void removePaletteItem(SimplePaletteItem component, ComponentCategory category) {
    VerticalPanel categoryPanel = (VerticalPanel) (componentsPanels.get(category).getWidget());
    categoryPanel.remove(component);
  }

  private void initExtensionPanel() {
    MaterialButton addComponentButton = new MaterialButton(MESSAGES.importExtensionMenuItem(), MaterialButtonType.FLAT);
    addComponentButton.setStylePrimaryName("palette-panel__extension__import-button");
    addComponentButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        new ComponentImportWizard().center();
      }
    });

    VerticalPanel categoryPanel = (VerticalPanel) componentsPanels.get(ComponentCategory.EXTENSION).getWidget();
    categoryPanel.add(addComponentButton);
  }

  @Override
  public void onComponentTypeAdded(List<String> componentTypes) {
    for (String componentType : componentTypes) {
      this.addComponent(componentType, false);
    }
  }

  @Override
  public boolean beforeComponentTypeRemoved(List<String> componentTypes) {
    boolean result = true;
    for (String componentType : componentTypes) {
      this.removeComponent(componentType);
    }
    return result;
  }

  @Override
  public void onComponentTypeRemoved(Map<String, String> componentTypes) {

  }

  @Override
  public void onResetDatabase() {
    reloadComponents();
  }

  @Override
  public void clearComponents() {
    for (ComponentCategory category : componentsPanels.keySet()) {
      VerticalPanel categoryPanel = (VerticalPanel) componentsPanels.get(category).getWidget();
      categoryPanel.clear();
    }
  }

  @Override
  public void reloadComponents() {
    clearComponents();
    loadComponents();
  }

  @Override
  public boolean showSearchBox() {
    return true;
  }

  @Override
  public void clearComponentsExceptExtension() {

  }
}
