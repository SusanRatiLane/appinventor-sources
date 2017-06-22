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
import com.github.mikephil.charting.data.BaseDataSet;
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
  private Map<String,ILineDataSet> lineSet;

  // The adapter contains spannables rather than strings, since we will be changing the item
  // colors using ForegroundColorSpan
  private ArrayAdapter<Spannable> adapter;
  private ArrayAdapter<Spannable> adapterCopy;
  private YailList items;
  private int selectionIndex;
  private String selection;
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
  boolean showLegend = true;
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
    lineSet = new HashMap<String,ILineDataSet>();
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
  public void ShowLegend(boolean showLegend) {
    this.showLegend = showLegend;
    Legend chartLegend = lineChart.getLegend();
    chartLegend.setEnabled(showLegend);
  }

  /**
   * Returns true or false depending on the visibility of the Chart element
   * @return true or false (visibility)
   */
  @SimpleProperty(category = PropertyCategory.BEHAVIOR,
      description = "Returns current state of ShowChart for visibility.")
  public boolean ShowLegend() {
    return showLegend;
  }

  private LineDataSet createSet(String series) {

      LineDataSet set = new LineDataSet(null, series);
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
  public void AddSingleData(String series, float datapoint) {	  
      data = lineChart.getData();
      testview.setText("inside");
      
      if (!lineSet.keySet().contains(series)) {
    	  set = createSet(series);
          data.addDataSet(set);
    	  lineSet.put(series, data.getDataSetByLabel(series, true));
    	  
      }
      if (!data.getXVals().contains(String.valueOf(lineSet.get(series).getEntryCount()))) {
          data.addXValue(String.valueOf(lineSet.get(series).getEntryCount()));
      }
      set = lineSet.get(series);
      set.addEntry(new Entry(datapoint, lineSet.get(series).getEntryCount()));
      data.notifyDataChanged();
      lineChart.setData(data);

      // let the chart know it's data has changed
      lineChart.notifyDataSetChanged();

      lineChart.moveViewTo(data.getXValCount() - 7, 50f, AxisDependency.LEFT);
	  
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void AddStringData(String series, String itemstring) {
	  if (itemstring != "") {
		  String[] itemArray = itemstring.split(",");
		  for (int i=0; i<itemArray.length; i++) {
			  AddSingleData(series,Float.valueOf(itemArray[i]));
		  }  
	  }  
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void AddListData(String series, YailList itemlist) {
	  Object[] listData = itemlist.toArray();
	  if (listData.length > 0) {
		  for (int i=0; i<listData.length; i++) {
			  AddSingleData(series,Float.valueOf(listData[i].toString()));
		  }  
	  }  
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void ClearAllData() {
	  data = lineChart.getData();
      data.clearValues();
      lineSet.clear();
	  lineChart.invalidate();
      lineChart.setData(new LineData());

	  // let the chart know it's data has changed
	  lineChart.invalidate();
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void ClearLineData(String series) {
	  data = lineChart.getData();
	  data.getDataSetByLabel(series, true).clear();
      lineSet.remove(series);
	  lineChart.invalidate();
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void SetListData(String series, YailList itemlist) {
	  ClearLineData(series);
	  AddListData(series,itemlist);
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void SetStringData(String series, String itemstring) {
	  ClearLineData(series);
	  AddStringData(series,itemstring);
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void SetSingleData(String series, float datapoint) {
	  ClearLineData(series);
	  AddSingleData(series,datapoint);
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="")
  public void SetSeriesColor(String series, int color) {
	  LineDataSet colorSet = (LineDataSet)lineSet.get(series);
	  colorSet.setColor(color);
	  colorSet.setCircleColor(color);
	  lineChart.invalidate();
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="")
  public int GetSeriesColor(String series) {
	  LineDataSet colorSet = (LineDataSet)lineSet.get(series);
	  return colorSet.getColor();
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="The data elements specified as a string with the " +
      "items separated by commas such as: 1,2,8,4,3,10,5. " + 
	  "Each number before the comma will be a datapoint " + 
      "on the chart.")
  public void SetSeriesFill(String series, boolean fill, int color) {
	  LineDataSet fillSet = (LineDataSet)lineSet.get(series);
	  fillSet.setDrawFilled(fill);
	  fillSet.setFillColor(color);
	  lineChart.invalidate();
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="")
  public int GetSeriesFill(String series) {
	  LineDataSet colorSet = (LineDataSet)lineSet.get(series);
	  return colorSet.getFillColor();
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
    lineChart.getAxisLeft().setTextColor(textColor);
    lineChart.getXAxis().setTextColor(textColor);
    lineChart.getLegend().setTextColor(textColor);
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
}


}
