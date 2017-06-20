// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2014 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.text.Spannable;
import android.view.View;
import android.widget.ArrayAdapter;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.YailList;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

/**
 * Chart Component. More info to come 
 * @author keving17@mit.edu (Kevin Ng)
 */

@DesignerComponent(version = YaVersion.CHART_COMPONENT_VERSION,
    description = "<p>displays a line chart </p>",
    category = ComponentCategory.USERINTERFACE)
@SimpleObject
@UsesLibraries(libraries = "mpandroidchart.jar")
public final class Chart extends AndroidViewComponent {

  private static final String LOG_TAG = "Chart";

  protected final ComponentContainer container;
  private final RelativeLayout chartLayout;
  private LineChart lineChart;
  private final TextView testview;
  private LineData data;
  private ILineDataSet set;
  private Map<String,Integer> lineSet;

  // The adapter contains spannables rather than strings, since we will be changing the item
  // colors using ForegroundColorSpan
  private ArrayAdapter<Spannable> adapter;
  private ArrayAdapter<Spannable> adapterCopy;
  private YailList items;
  private int selectionIndex;
  private String selection;
  private boolean showChart= true;
  private static final boolean DEFAULT_ENABLED = false;

  private int backgroundColor;
  private static final int DEFAULT_BACKGROUND_COLOR = Component.COLOR_NONE;

  // The text color of the ListView's items.  All items have the same text color
  private int textColor;
  private static final int DEFAULT_TEXT_COLOR = Component.COLOR_WHITE;

  private int selectionColor;
  private static final int DEFAULT_SELECTION_COLOR = Component.COLOR_LTGRAY;

  private int textSize;
  private static final int DEFAULT_TEXT_SIZE = 22;
  
  String legend = "legend";
  boolean drawFilled = true;
  boolean drawSmooth = true;
  int[] colors = ColorTemplate.COLORFUL_COLORS;
  int animateSpeed = 1000;
  boolean touchEnabled = true;
  boolean dragEnabled= true;
  boolean scaleEnabled= true;
  boolean pinchZoom = true;
  boolean xGrid = true;
  boolean leftYGrid = true;
  boolean rightYGrid = false;
  boolean rightYLabels = false;
  boolean pointLabels = false;
  boolean showLegend = false;
  String chartDescription = "";

  /**
   * Creates a new Chart component.
   * @param container  container that the component will be placed in
   */
  public Chart(ComponentContainer container) {
    super(container);
    this.container = container;
    items = YailList.makeEmptyList();
    chartLayout = new RelativeLayout(container.$context());
    chartLayout.setLayoutParams(new RelativeLayout.LayoutParams(
    		ViewGroup.LayoutParams.MATCH_PARENT,
    		ViewGroup.LayoutParams.MATCH_PARENT));
    
    lineChart = new LineChart(container.$context());
    lineChart.setLayoutParams(new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT));
    
    testview = new TextView(container.$context());

    // enable / disable grid background
    lineChart.setDrawGridBackground(false);

    // enable value highlighting
    lineChart.setHighlightPerTapEnabled(false);

    // enable touch gestures - horizontal and vertical line to pinpoint data point
    lineChart.setTouchEnabled(touchEnabled);

    // enable scaling and dragging
    lineChart.setDragEnabled(dragEnabled);
    lineChart.setScaleEnabled(scaleEnabled);

    // if disabled, scaling can be done on x- and y-axis separately
    lineChart.setPinchZoom(pinchZoom);

    // set an alternative background color
    lineChart.setBackgroundColor(Color.TRANSPARENT);


//    lineChart.setData(data);
    lineChart.setData(new LineData());
    lineSet = new HashMap<String,Integer>();
    lineChart.animateY(animateSpeed);


    XAxis upperXAxis = lineChart.getXAxis();
    upperXAxis.setDrawGridLines(xGrid);
    upperXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

    YAxis leftYAxis = lineChart.getAxisLeft();
    leftYAxis.setDrawGridLines(leftYGrid);
    YAxis rightYAxis = lineChart.getAxisRight();
    rightYAxis.setDrawGridLines(rightYGrid);
    rightYAxis.setEnabled(rightYLabels);

    Legend chartLegend = lineChart.getLegend();
    chartLegend.setEnabled(showLegend);

    lineChart.setDescription(chartDescription);
    	
    // set the colors and initialize the elements
    // note that the TextColor and ElementsFromString setters
    // need to have the textColor set first, since they reset the
    // adapter
    
    BackgroundColor(DEFAULT_BACKGROUND_COLOR);

    textColor = DEFAULT_TEXT_COLOR;
    TextColor(textColor);
    textSize = DEFAULT_TEXT_SIZE;
    TextSize(textSize);
    ElementsFromString("");

    chartLayout.addView(lineChart);
    testview.setText("Testing");
    chartLayout.addView(testview);
    container.$add(this);
    
  }

  @Override
  public View getView() {
    return chartLayout;
  }

  /**
  * Sets the height of the lineChart on the screen
  * @param height for height length
  */
  @Override
  @SimpleProperty(description = "Determines the height of the linechart on the view.",
      category =PropertyCategory.APPEARANCE)
  public void Height(int height) {
    if (height == LENGTH_PREFERRED) {
      height = LENGTH_FILL_PARENT;
    }
    super.Height(height);
    //lineChart.setBackgroundColor(Color.BLACK);
  }

  /**
  * Sets the width of the lineChart on the screen
  * @param width for width length
  */
  @Override
  @SimpleProperty(description = "Determines the width of the linechart on the view.",
      category = PropertyCategory.APPEARANCE)
  public void Width(int width) {
    if (width == LENGTH_PREFERRED) {
      width = LENGTH_FILL_PARENT;
    }
    super.Width(width);
  }

  /**
   * Sets true or false to determine whether the chart is displayed
   *
   * @param showChart set the visibility according to this input
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
      defaultValue = DEFAULT_ENABLED ? "True" : "False")
  @SimpleProperty(description = "Sets visibility of Chart. True will show the chart, " +
      "False will hide it.")
  public void ShowChart(boolean showChart) {
    this.showChart = showChart;
    if (showChart) {
      lineChart.setVisibility(View.VISIBLE);
    }
    else {
      lineChart.setVisibility(View.GONE);
    }
  }

  /**
   * Returns true or false depending on the visibility of the Chart element
   * @return true or false (visibility)
   */
  @SimpleProperty(category = PropertyCategory.BEHAVIOR,
      description = "Returns current state of ShowChart for visibility.")
  public boolean ShowChart() {
    return showChart;
  }

  /**
   * Set a list of text elements to build a lineChart
   * @param itemsList a YailList containing the data to be added to the lineChart
   */
  @SimpleProperty(description="List of text elements to show in the lineChart",
      category = PropertyCategory.BEHAVIOR)
  public void Elements(YailList itemsList) {
    items = ElementsUtil.elements(itemsList, "Chart");
    setAdapterData();
  }

  /**
   * Elements property getter method
   *
   * @return a YailList representing the list of strings to be picked from
   */
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public YailList Elements() {
    return items;
  }

  /**
   * Specifies the text elements of the lineChart.
   * @param itemstring a string containing a comma-separated list of the strings to be picked from
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING, defaultValue = "")
  @SimpleProperty(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.",  category = PropertyCategory.BEHAVIOR)
  public void ElementsFromString(String itemstring) {
  }
  
  private LineDataSet createSet(String line) {

      LineDataSet set = new LineDataSet(null, "DataSet 1");
      set.setLineWidth(2.5f);
      set.setCircleRadius(4.5f);
      set.setColor(Color.rgb(240, 99, 99));
      set.setCircleColor(Color.rgb(240, 99, 99));
      set.setHighLightColor(Color.rgb(190, 190, 190));
      set.setAxisDependency(AxisDependency.LEFT);
      set.setValueTextSize(10f);
      return set;
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void AddSingleData(String line, float datapoint) {
	  testview.setText(String.valueOf(datapoint));	
	  
      LineData data = lineChart.getData();
      ILineDataSet set = data.getDataSetByIndex(0);

      if ((set == null)||(!lineSet.keySet().contains(line))) {
          lineSet.put(line, lineSet.keySet().size());
          set = createSet(line);
          data.addDataSet(set);
      }
      
      //testview.setText(String.valueOf(data.getDataSetByIndex(randomDataSetIndex).getEntryCount()));
	  testview.setText("*"+String.valueOf(datapoint));
	  testview.setText("line:"+lineSet.toString()+" index:"+lineSet.get(line));

      data.addEntry(new Entry(datapoint, data.getDataSetByIndex(lineSet.get(line)).getEntryCount()), lineSet.get(line));
      data.addXValue(String.valueOf(data.getDataSetByIndex(lineSet.get(line)).getEntryCount()));
      data.notifyDataChanged();
      lineChart.setData(data);

      // let the chart know it's data has changed
      lineChart.notifyDataSetChanged();

      lineChart.setVisibleXRangeMaximum(6);
      //mChart.setVisibleYRangeMaximum(15, AxisDependency.LEFT);
//          
//          // this automatically refreshes the chart (calls invalidate())
      lineChart.moveViewTo(data.getXValCount() - 7, 50f, AxisDependency.LEFT);
	  
  }
 
  /**
   * Sets the items of the ListView through an adapter
   */
  public void setAdapterData(){
//    int size = items.size();
//    int displayTextSize = textSize;
//    Spannable [] objects = new Spannable[size];
//    for (int i = 1; i <= size; i++) {
//	      String itemString = YailList.YailListElementToString(items.get(i));
//    }
  }

  /**
   * Assigns a value to the backgroundColor
   * @param color  an alpha-red-green-blue integer for a color
   */

  public void setBackgroundColor(int color) {
      backgroundColor = color;
      lineChart.setBackgroundColor(color);
  }

  /**
   * Returns the lineChart's background color as an alpha-red-green-blue
   * integer, i.e., {@code 0xAARRGGBB}.  An alpha of {@code 00}
   * indicates fully transparent and {@code FF} means opaque.
   *
   * @return background color in the format 0xAARRGGBB, which includes
   * alpha, red, green, and blue components
   */
  @SimpleProperty(
      description = "The color of the lineChart background.",
      category = PropertyCategory.APPEARANCE)
  public int BackgroundColor() {
    return backgroundColor;
  }

  /**
   * Specifies the lineChart's background color as an alpha-red-green-blue
   * integer, i.e., {@code 0xAARRGGBB}.  An alpha of {@code 00}
   * indicates fully transparent and {@code FF} means opaque.
   *
   * @param argb background color in the format 0xAARRGGBB, which
   * includes alpha, red, green, and blue components
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
      defaultValue = Component.DEFAULT_VALUE_COLOR_NONE)
  @SimpleProperty
  public void BackgroundColor(int argb) {
      backgroundColor = argb;
      setBackgroundColor(backgroundColor);
  }

/**
 * Returns the listview's text item color as an alpha-red-green-blue
 * integer, i.e., {@code 0xAARRGGBB}.  An alpha of {@code 00}
 * indicates fully transparent and {@code FF} means opaque.
 *
 * @return background color in the format 0xAARRGGBB, which includes
 * alpha, red, green, and blue components
 */
@SimpleProperty(
    description = "The text color of the listview items.",
    category = PropertyCategory.APPEARANCE)
public int TextColor() {
  return textColor;
}

/**
 * Specifies the ListView item's text color as an alpha-red-green-blue
 * integer, i.e., {@code 0xAARRGGBB}.  An alpha of {@code 00}
 * indicates fully transparent and {@code FF} means opaque.
 *
 * @param argb background color in the format 0xAARRGGBB, which
 * includes alpha, red, green, and blue components
 */
@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
    defaultValue = Component.DEFAULT_VALUE_COLOR_WHITE)
@SimpleProperty
public void TextColor(int argb) {
    textColor = argb;
    setAdapterData();
}
/**
 * Returns the listview's text font Size
 *
 * @return text size as an float
 */
@SimpleProperty(
    description = "The text size of the listview items.",
    category = PropertyCategory.APPEARANCE)
public int TextSize() {
  return textSize;
}

/**
 * Specifies the ListView item's text font size
 *
 * @param integer value for font size
 */
@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER,
    defaultValue = DEFAULT_TEXT_SIZE + "")
@SimpleProperty
public void TextSize(int fontSize) {
    if(fontSize>1000)
      textSize = 999;
    else
      textSize = fontSize;
    setAdapterData();
}
}
