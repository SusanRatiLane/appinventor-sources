// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2014 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.text.Spannable;
import android.view.Gravity;
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
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
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
  private final LinearLayout chartLayout;
  private LineChart lineChart;
  private final TextView title;
  private final TextView yaxis;
  private final TextView xaxis;
  
  private LineData data;
  private ILineDataSet set;
  private Map<String,ILineDataSet> lineSet;

  private static final boolean DEFAULT_ENABLED = false;

  private int backgroundColor;
  private static final int DEFAULT_BACKGROUND_COLOR = Component.COLOR_WHITE;

  // The text color of the ListView's items.  All items have the same text color
  private int textColor;
  private static final int DEFAULT_TEXT_COLOR = Component.COLOR_BLACK;

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
  boolean pointLabels;
  boolean showLegend;
  Description chartDescription;
  Legend chartLegend;
  /**
   * Creates a new Chart component.
   * @param container  container that the component will be placed in
   */
  public Chart(ComponentContainer container) {
    super(container);
    this.container = container;
    
    title = new TextView(container.$context());
    title.setId(1);
    yaxis = new TextView(container.$context());
    yaxis.setId(2);
    xaxis = new TextView(container.$context());
    xaxis.setId(3);
    
    chartLayout = new LinearLayout(container.$context());
    chartLayout.setLayoutParams(new LinearLayout.LayoutParams(
    		LinearLayout.LayoutParams.MATCH_PARENT,
    		LinearLayout.LayoutParams.MATCH_PARENT));
    chartLayout.setOrientation(LinearLayout.VERTICAL);
    
    lineChart = new LineChart(container.$context());
    lineChart.setId(4);
    LinearLayout.LayoutParams lineChartParams = new LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    lineChartParams.weight = 1;
    
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
    lineChart.setBackgroundColor(DEFAULT_BACKGROUND_COLOR);

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

    chartLegend = lineChart.getLegend();
    chartLegend.setEnabled(showLegend);
    
    chartDescription = new Description();
    chartDescription.setText("");
    lineChart.setDescription(chartDescription);
    
    // set the colors and initialize the elements
    // note that the TextColor and ElementsFromString setters
    // need to have the textColor set first, since they reset the
    // adapter
    
    BackgroundColor(DEFAULT_BACKGROUND_COLOR);

    //textColor = DEFAULT_TEXT_COLOR;
    TextColor(DEFAULT_TEXT_COLOR);
    textSize = DEFAULT_TEXT_SIZE;
    //TextSize(textSize);
    
    title.setText("Title");
    LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    title.setGravity(Gravity.CENTER);
    //title.setTextColor(textColor);
    
    yaxis.setText("Y Axis");
    yaxis.setRotation(-95);
    LinearLayout.LayoutParams yAxisParams = new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    yAxisParams.setMargins(0, 0, 0, 0);
    yaxis.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    
    xaxis.setText("X Axis");
    LinearLayout.LayoutParams xAxisParams = new LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    xaxis.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

    LinearLayout horizLayout = new LinearLayout(container.$context());
    LinearLayout.LayoutParams horizLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    horizLayout.setOrientation(LinearLayout.HORIZONTAL);
    
    chartLayout.addView(title,titleParams);
    chartLayout.addView(horizLayout,horizLayoutParams);
    
    LinearLayout vertLayout = new LinearLayout(container.$context());
    LinearLayout.LayoutParams vertLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    vertLayout.setOrientation(LinearLayout.VERTICAL);
    vertLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    
    horizLayout.addView(yaxis,yAxisParams);
    horizLayout.addView(vertLayout, vertLayoutParams);
    
    vertLayout.addView(lineChart,lineChartParams);
    vertLayout.addView(xaxis,xAxisParams);
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
   *
   * @return  
   */
  @SimpleProperty(
      category = PropertyCategory.APPEARANCE)
  public String ChartTitle() {
    return title.getText().toString();
  }

  /**
   *
   * @param
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
      defaultValue = "Title")
  @SimpleProperty
  public void ChartTitle(String text) {
	  title.setText(text);
  }
  
  /**
  *
  * @return  
  */
 @SimpleProperty(
     category = PropertyCategory.APPEARANCE)
 public String ChartYLabel() {
   return yaxis.getText().toString();
 }

 /**
  *
  * @param
  */
 @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
     defaultValue = "Y Axis")
 @SimpleProperty
 public void ChartYLabel(String text) {
	  yaxis.setText(text);
 }
 
 /**
 *
 * @return  
 */
@SimpleProperty(
    category = PropertyCategory.APPEARANCE)
public String ChartXLabel() {
  return xaxis.getText().toString();
}

/**
 *
 * @param
 */
@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
    defaultValue = "X Axis")
@SimpleProperty
public void ChartXLabel(String text) {
	  xaxis.setText(text);
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
    chartLegend = lineChart.getLegend();
    chartLegend.setEnabled(showLegend);
    lineChart.invalidate();
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
  
  /**
   *
   * @param 
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
      defaultValue = DEFAULT_ENABLED ? "True" : "False")
  @SimpleProperty(description = "")
  public void ShowPointLabels(boolean pointLabels) {
    this.pointLabels = pointLabels;
    
    data = lineChart.getData();
	List<ILineDataSet> setList = data.getDataSets();
	  
	for (int i = 0; i < setList.size(); i++) {
		setList.get(i).setDrawValues(pointLabels);
	}
    
    lineChart.invalidate();
  }

  /**
   * 
   * @return
   */
  @SimpleProperty(category = PropertyCategory.BEHAVIOR,
      description = "")
  public boolean ShowPointLabels() {
    return pointLabels;
  }

  private LineDataSet createSet(String series) {

      LineDataSet set = new LineDataSet(null, series);
      set.setLineWidth(2.5f);
      set.setCircleRadius(4.5f);
      set.setColor(Color.rgb(0, 0, 0));
      set.setCircleColor(Color.rgb(0, 0, 0));
      set.setHighLightColor(Color.rgb(128, 128, 128));
      set.setAxisDependency(AxisDependency.LEFT);
      set.setValueTextSize(10f);
      set.setDrawValues(pointLabels);
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
  public void AddXYData(String series, float x, float y) {	  
	  data = lineChart.getData();
      if (!lineSet.keySet().contains(series)) {
    	  set = createSet(series);
          data.addDataSet(set);
    	  lineSet.put(series, data.getDataSetByLabel(series, true));
    	  
      }

      set = lineSet.get(series);
      Entry newEntry = new Entry(x, y);
      
      //Should not be allowed to have two entries with same x value. 
      List<Entry> existingX = set.getEntriesForXValue(x);
      if (existingX.size() > 0) {
    	  for (int i=0; i<existingX.size(); i++) {
    		  set.removeEntry(existingX.get(i));
    	  }
      }
      set.addEntryOrdered(newEntry);
      data.notifyDataChanged();
      lineChart.setData(data);
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
  public void AddSingleData(String series, float y) {	  
	  data = lineChart.getData();
      if (!lineSet.keySet().contains(series)) {
    	  set = createSet(series);
          data.addDataSet(set);
    	  lineSet.put(series, data.getDataSetByLabel(series, true));
      }
      set = lineSet.get(series);
	  Entry newEntry = new Entry(set.getEntryCount(), y);
      
      //Should not be allowed to have two entries with same x value. 
      //Look at getEntriesForXValue(float xValue) to find existing entries and 
      //removeEntry(T e) them before addEntryOrdered(T e)
      List<Entry> existingX = set.getEntriesForXValue(data.getEntryCount());
      if (existingX.size() > 0) {
    	  for (int i=0; i<existingX.size(); i++) {
    		  set.removeEntry(existingX.get(i));
    	  }
      }
      set.addEntryOrdered(newEntry);
      data.notifyDataChanged();
      lineChart.setData(data);
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
  @SimpleFunction(description="")
  public void ClearLineData(String series) {
	  data = lineChart.getData();
	  data.removeDataSet(data.getDataSetByLabel(series, true));
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
	  if (lineSet.containsKey(series)) {
		  ClearLineData(series);
	  }
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
	  if (lineSet.containsKey(series)) {
		  ClearLineData(series);
	  }
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
	  if (lineSet.containsKey(series)) {
		  ClearLineData(series);
	  }
	  AddSingleData(series,datapoint);
  }
  
  /**
   * --
   * @param --
   */
  @SimpleFunction(description="")
  public YailList GetSeries() {
	  data = lineChart.getData();
	  List<ILineDataSet> setList = data.getDataSets();
	  List<String> temp = new ArrayList<String>();
	  
	  for (int i = 0; i < setList.size(); i++) {
		  temp.add(setList.get(i).getLabel());
	  }
	  YailList series = YailList.makeList(temp);
	  return series;
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
	  lineChart.notifyDataSetChanged();
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
      defaultValue = Component.DEFAULT_VALUE_COLOR_WHITE)
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
    defaultValue = Component.DEFAULT_VALUE_COLOR_BLACK)
@SimpleProperty
public void TextColor(int argb) {
    textColor = argb;
    xaxis.setTextColor(textColor);
    yaxis.setTextColor(textColor);
    title.setTextColor(textColor);
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

//Saving functionality borrowed from MPAndroidCharts
/**
 * Returns the bitmap that represents the chart.
 *
 * @return
 */
public Bitmap getChartBitmap() {
    // Define a bitmap with the same size as the view
    Bitmap returnedBitmap = Bitmap.createBitmap(chartLayout.getWidth(), chartLayout.getHeight(), Bitmap.Config.RGB_565);
    // Bind a canvas to it
    Canvas canvas = new Canvas(returnedBitmap);
    // Get the view's background
    Drawable bgDrawable = chartLayout.getBackground();
    if (bgDrawable != null)
        // has background drawable, then draw it on the canvas
        bgDrawable.draw(canvas);
    else
        // does not have background drawable, then draw white background on
        // the canvas
        canvas.drawColor(Color.WHITE);
    // draw the view on the canvas
    chartLayout.draw(canvas);
    // return the bitmap
    return returnedBitmap;
}

/**
 * Saves the current chart state with the given name to the given path on
 * the sdcard leaving the path empty "" will put the saved file directly on
 * the SD card chart is saved as a PNG image, example:
 * saveToPath("myfilename", "foldername1/foldername2");
 *
 * @param title
 * @param pathOnSD e.g. "folder1/folder2/folder3"
 * @return returns true on success, false on error
 */
public boolean saveToPath(String title, String pathOnSD) {

    Bitmap b = getChartBitmap();

    OutputStream stream = null;
    try {
        stream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()
                + pathOnSD + "/" + title
                + ".png");

        /*
         * Write bitmap to file using JPEG or PNG and 40% quality hint for
         * JPEG.
         */
        b.compress(CompressFormat.PNG, 40, stream);

        stream.close();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
}

/**
 * Saves the current state of the chart to the gallery as an image type. The
 * compression must be set for JPEG only. 0 == maximum compression, 100 = low
 * compression (high quality). NOTE: Needs permission WRITE_EXTERNAL_STORAGE
 *
 * @param fileName        e.g. "my_image"
 * @param subFolderPath   e.g. "ChartPics"
 * @param fileDescription e.g. "Chart details"
 * @param format          e.g. Bitmap.CompressFormat.PNG
 * @param quality         e.g. 50, min = 0, max = 100
 * @return returns true if saving was successful, false if not
 */
public boolean saveToGallery(String fileName, String subFolderPath, String fileDescription, Bitmap.CompressFormat format, int quality) {
    // restrain quality
    if (quality < 0 || quality > 100)
        quality = 50;

    long currentTime = System.currentTimeMillis();

    File extBaseDir = Environment.getExternalStorageDirectory();
    File file = new File(extBaseDir.getAbsolutePath() + "/DCIM/" + subFolderPath);
    if (!file.exists()) {
        if (!file.mkdirs()) {
            return false;
        }
    }

    String mimeType = "";
    switch (format) {
        case PNG:
            mimeType = "image/png";
            if (!fileName.endsWith(".png"))
                fileName += ".png";
            break;
        case WEBP:
            mimeType = "image/webp";
            if (!fileName.endsWith(".webp"))
                fileName += ".webp";
            break;
        case JPEG:
        default:
            mimeType = "image/jpeg";
            if (!(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")))
                fileName += ".jpg";
            break;
    }

    String filePath = file.getAbsolutePath() + "/" + fileName;
    FileOutputStream out = null;
    try {
        out = new FileOutputStream(filePath);

        Bitmap b = getChartBitmap();
        b.compress(format, quality, out);

        out.flush();
        out.close();

    } catch (IOException e) {
        e.printStackTrace();

        return false;
    }

    long size = new File(filePath).length();

    ContentValues values = new ContentValues(8);

    // store the details
    values.put(Images.Media.TITLE, fileName);
    values.put(Images.Media.DISPLAY_NAME, fileName);
    values.put(Images.Media.DATE_ADDED, currentTime);
    values.put(Images.Media.MIME_TYPE, mimeType);
    values.put(Images.Media.DESCRIPTION, fileDescription);
    values.put(Images.Media.ORIENTATION, 0);
    values.put(Images.Media.DATA, filePath);
    values.put(Images.Media.SIZE, size);

    return container.$context().getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, values) != null;
}

/**
 * Saves the current state of the chart to the gallery as a JPEG image. The
 * filename and compression can be set. 0 == maximum compression, 100 = low
 * compression (high quality). NOTE: Needs permission WRITE_EXTERNAL_STORAGE
 *
 * @param fileName e.g. "my_image"
 * @param quality  e.g. 50, min = 0, max = 100
 * @return returns true if saving was successful, false if not
 */
public boolean saveToGallery(String fileName, int quality) {
    return saveToGallery(fileName, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.JPEG, quality);
}

/**
 * --
 * @param --
 */
@SimpleFunction(description="")
public void ExportChart() {
	  //lineChart.saveToGallery("mychart-"+System.currentTimeMillis()+".jpg",100);
	  saveToGallery("mychart-"+System.currentTimeMillis()+".jpg",100);
}
}
