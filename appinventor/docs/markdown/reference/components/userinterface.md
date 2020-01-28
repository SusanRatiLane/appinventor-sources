---
layout: documentation
title: User Interface
---

[&laquo; Back to index](index.html)
# User Interface

Table of Contents:

* [Button](#Button)
* [CheckBox](#CheckBox)
* [DatePicker](#DatePicker)
* [Image](#Image)
* [Label](#Label)
* [ListPicker](#ListPicker)
* [ListView](#ListView)
* [Notifier](#Notifier)
* [PasswordTextBox](#PasswordTextBox)
* [Screen](#Screen)
* [Slider](#Slider)
* [Spinner](#Spinner)
* [Switch](#Switch)
* [TextBox](#TextBox)
* [TimePicker](#TimePicker)
* [WebViewer](#WebViewer)

## Button  {#Button}

Button with the ability to launch events on initialization, focus
 change, or a user click.  It is implemented using
 {



### Properties  {#Button-Properties}

{:.properties}

{:id="Button.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer.

{:id="Button.Enabled" .boolean} *Enabled*
: Returns true if the Button is active and clickable.

{:id="Button.FontBold" .boolean} *FontBold*
: Returns true if the text of the Button should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="Button.FontItalic" .boolean} *FontItalic*
: Returns true if the text of the Button should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="Button.FontSize" .number} *FontSize*
: Returns the text font size of the Button, measured in sp(scale-independent pixels).

{:id="Button.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the Button as default, serif, sans
 serif, or monospace.

{:id="Button.Height" .number .bo} *Height*
: Specifies the vertical height of the Button, measured in pixels.

{:id="Button.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the Button as a percentage of the height of the Screen.

{:id="Button.Image" .text} *Image*
: Returns the path of the button's image.

{:id="Button.Shape" .number .do} *Shape*
: Returns the style of the button.

{:id="Button.ShowFeedback" .boolean} *ShowFeedback*
: Specifies if a visual feedback should be shown when a Button with an assigned image
 is pressed.

{:id="Button.Text" .text} *Text*
: Returns the text displayed by the Button.

{:id="Button.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the button's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="Button.TextColor" .color} *TextColor*
: Returns the text color of the Button as an alpha-red-green-blue
 integer.

{:id="Button.Visible" .boolean} *Visible*
: Returns true iff the Button is visible.

{:id="Button.Width" .number .bo} *Width*
: Specifies the horizontal width of the Button, measured in pixels.

{:id="Button.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the Button as a percentage of the width of the Screen.

### Events  {#Button-Events}

{:.events}

{:id="Button.Click"} Click()
: Indicates a user has clicked on the button.

{:id="Button.GotFocus"} GotFocus()
: Indicates the cursor moved over the button so it is now possible
 to click it.

{:id="Button.LongClick"} LongClick()
: Indicates a user has long clicked on the button.

{:id="Button.LostFocus"} LostFocus()
: Indicates the cursor moved away from the button so it is now no
 longer possible to click it.

{:id="Button.TouchDown"} TouchDown()
: Indicates when a button is touch down

{:id="Button.TouchUp"} TouchUp()
: Indicates when a button touch ends

## CheckBox  {#CheckBox}

Check box with the ability to detect initialization, focus
 change (mousing on or off of it), and user clicks.



### Properties  {#CheckBox-Properties}

{:.properties}

{:id="CheckBox.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the CheckBox as an alpha-red-green-blue
 integer.

{:id="CheckBox.Checked" .boolean} *Checked*
: Returns true if the checkbox is checked.

{:id="CheckBox.Enabled" .boolean} *Enabled*
: Returns true if the CheckBox is active and clickable.

{:id="CheckBox.FontBold" .boolean .do} *FontBold*
: Returns true if the text of the CheckBox should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="CheckBox.FontItalic" .boolean .do} *FontItalic*
: Returns true if the text of the CheckBox should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="CheckBox.FontSize" .number} *FontSize*
: Returns the text font size of the CheckBox, measured in sp(scale-independent pixels).

{:id="CheckBox.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the CheckBox as default, serif, sans
 serif, or monospace.

{:id="CheckBox.Height" .number .bo} *Height*
: Specifies the vertical height of the CheckBox, measured in pixels.

{:id="CheckBox.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the CheckBox as a percentage of the height of the Screen.

{:id="CheckBox.Text" .text} *Text*
: Returns the text displayed by the CheckBox.

{:id="CheckBox.TextColor" .color} *TextColor*
: Returns the text color of the CheckBox as an alpha-red-green-blue
 integer.

{:id="CheckBox.Visible" .boolean} *Visible*
: Returns true iff the CheckBox is visible.

{:id="CheckBox.Width" .number .bo} *Width*
: Specifies the horizontal width of the CheckBox, measured in pixels.

{:id="CheckBox.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the CheckBox as a percentage of the width of the Screen.

### Events  {#CheckBox-Events}

{:.events}

{:id="CheckBox.Changed"} Changed()
: Default Changed event handler.

{:id="CheckBox.GotFocus"} GotFocus()
: Default GotFocus event handler.

{:id="CheckBox.LostFocus"} LostFocus()
: Default LostFocus event handler.

## DatePicker  {#DatePicker}

A button allowing a user to launch a DatePickerDialog. This component is
 is based off the ButtonBase class instead of the base Picker class because
 unlike the other pickers, the DatePicker does not need to launch a new
 activity and get a result. The DatePicker is launched as a dialog.



### Properties  {#DatePicker-Properties}

{:.properties}

{:id="DatePicker.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer.

{:id="DatePicker.Day" .number .ro .bo} *Day*
: Returns the Day of the month that was last picked using the DatePicker.

{:id="DatePicker.Enabled" .boolean} *Enabled*
: Returns true if the DatePicker is active and clickable.

{:id="DatePicker.FontBold" .boolean} *FontBold*
: Returns true if the text of the DatePicker should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="DatePicker.FontItalic" .boolean} *FontItalic*
: Returns true if the text of the DatePicker should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="DatePicker.FontSize" .number} *FontSize*
: Returns the text font size of the DatePicker, measured in sp(scale-independent pixels).

{:id="DatePicker.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the DatePicker as default, serif, sans
 serif, or monospace.

{:id="DatePicker.Height" .number .bo} *Height*
: Specifies the vertical height of the DatePicker, measured in pixels.

{:id="DatePicker.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the DatePicker as a percentage of the height of the Screen.

{:id="DatePicker.Image" .text} *Image*
: Returns the path of the button's image.

{:id="DatePicker.Instant" .InstantInTime .ro .bo} *Instant*
: Returns instant of the date that was last picked using the DatePicker.

{:id="DatePicker.Month" .number .ro .bo} *Month*
: Returns the number of the Month that was last picked using the DatePicker.

{:id="DatePicker.MonthInText" .text .ro .bo} *MonthInText*
: Returns the name of the Month that was last picked using the DatePicker.

{:id="DatePicker.Shape" .number .do} *Shape*
: Returns the style of the button.

{:id="DatePicker.ShowFeedback" .boolean} *ShowFeedback*
: Specifies if a visual feedback should be shown when a DatePicker with an assigned image
 is pressed.

{:id="DatePicker.Text" .text} *Text*
: Returns the text displayed by the DatePicker.

{:id="DatePicker.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the button's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="DatePicker.TextColor" .color} *TextColor*
: Returns the text color of the DatePicker as an alpha-red-green-blue
 integer.

{:id="DatePicker.Visible" .boolean} *Visible*
: Returns true iff the DatePicker is visible.

{:id="DatePicker.Width" .number .bo} *Width*
: Specifies the horizontal width of the DatePicker, measured in pixels.

{:id="DatePicker.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the DatePicker as a percentage of the width of the Screen.

{:id="DatePicker.Year" .number .ro .bo} *Year*
: Returns the Year that was last picked using the DatePicker.

### Events  {#DatePicker-Events}

{:.events}

{:id="DatePicker.AfterDateSet"} AfterDateSet()
: Runs when the user sets the date in the Dialog.

{:id="DatePicker.GotFocus"} GotFocus()
: Indicates the cursor moved over the button so it is now possible
 to click it.

{:id="DatePicker.LostFocus"} LostFocus()
: Indicates the cursor moved away from the button so it is now no
 longer possible to click it.

{:id="DatePicker.TouchDown"} TouchDown()
: Indicates when a button is touch down

{:id="DatePicker.TouchUp"} TouchUp()
: Indicates when a button touch ends

### Methods  {#DatePicker-Methods}

{:.methods}

{:id="DatePicker.LaunchPicker" class="method"} <i/> LaunchPicker()
: Launches the DatePicker popup.

{:id="DatePicker.SetDateToDisplay" class="method"} <i/> SetDateToDisplay(*year*{:.number},*month*{:.number},*day*{:.number})
: Allows the user to set the date to be displayed when the date picker opens.
Valid values for the month field are 1-12 and 1-31 for the day field.

{:id="DatePicker.SetDateToDisplayFromInstant" class="method"} <i/> SetDateToDisplayFromInstant(*instant*{:.InstantInTime})
: Allows the user to set the date from the instant to be displayed when the date picker opens.

## Image  {#Image}

Component for displaying images and animations.



### Properties  {#Image-Properties}

{:.properties}

{:id="Image.Animation" .text .wo .bo} *Animation*
: Animation property setter method.

{:id="Image.Height" .number .bo} *Height*
: Specifies the vertical height of the Image, measured in pixels.

{:id="Image.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the Image as a percentage of the height of the Screen.

{:id="Image.Picture" .text} *Picture*
: Returns the path of the image's picture.

{:id="Image.RotationAngle" .number} *RotationAngle*
: Specifies the angle at which the image picture appears rotated.

{:id="Image.ScalePictureToFit" .boolean .wo} *ScalePictureToFit*
: Specifies whether the image should be resized to match the size of the ImageView.

{:id="Image.Scaling" .number .bo} *Scaling*
: This property determines how the picture scales according to the Height or Width of the Image. Scale proportionally (0) preserves the picture aspect ratio. Scale to fit (1) matches the Image area, even if the aspect ratio changes.

{:id="Image.Visible" .boolean} *Visible*
: Returns true iff the Image is visible.

{:id="Image.Width" .number .bo} *Width*
: Specifies the horizontal width of the Image, measured in pixels.

{:id="Image.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the Image as a percentage of the width of the Screen.

## Label  {#Label}

Label containing a text string.



### Properties  {#Label-Properties}

{:.properties}

{:id="Label.BackgroundColor" .color} *BackgroundColor*
: Returns the label's background color as an alpha-red-green-blue
 integer.

{:id="Label.FontBold" .boolean .do} *FontBold*
: Returns true if the label's text should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="Label.FontItalic" .boolean .do} *FontItalic*
: Returns true if the label's text should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="Label.FontSize" .number} *FontSize*
: Returns the label's text's font size, measured in sp(scale-independent pixels).

{:id="Label.FontTypeface" .number .do} *FontTypeface*
: Returns the label's text's font face as default, serif, sans
 serif, or monospace.

{:id="Label.HTMLContent" .text .ro .bo} *HTMLContent*
: Returns the content of the Label as HTML. This is only useful if the
 HTMLFormat property is true.

{:id="Label.HTMLFormat" .boolean .do} *HTMLFormat*
: Returns the label's text's format

{:id="Label.HasMargins" .boolean} *HasMargins*
: Returns true if the label should have  margins.

{:id="Label.Height" .number .bo} *Height*
: Specifies the vertical height of the Label, measured in pixels.

{:id="Label.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the Label as a percentage of the height of the Screen.

{:id="Label.Text" .text} *Text*
: Returns the text displayed by the label.

{:id="Label.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the label's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="Label.TextColor" .color} *TextColor*
: Returns the label's text color as an alpha-red-green-blue
 integer.

{:id="Label.Visible" .boolean} *Visible*
: Returns true iff the Label is visible.

{:id="Label.Width" .number .bo} *Width*
: Specifies the horizontal width of the Label, measured in pixels.

{:id="Label.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the Label as a percentage of the width of the Screen.

## ListPicker  {#ListPicker}

A button allowing a user to select one among a list of text strings.



### Properties  {#ListPicker-Properties}

{:.properties}

{:id="ListPicker.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer.

{:id="ListPicker.Elements" .list .bo} *Elements*
: Elements property getter method

{:id="ListPicker.ElementsFromString" .text .wo} *ElementsFromString*
: ElementsFromString property setter method

{:id="ListPicker.Enabled" .boolean} *Enabled*
: Returns true if the ListPicker is active and clickable.

{:id="ListPicker.FontBold" .boolean} *FontBold*
: Returns true if the text of the ListPicker should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="ListPicker.FontItalic" .boolean} *FontItalic*
: Returns true if the text of the ListPicker should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="ListPicker.FontSize" .number} *FontSize*
: Returns the text font size of the ListPicker, measured in sp(scale-independent pixels).

{:id="ListPicker.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the ListPicker as default, serif, sans
 serif, or monospace.

{:id="ListPicker.Height" .number .bo} *Height*
: Specifies the vertical height of the ListPicker, measured in pixels.

{:id="ListPicker.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the ListPicker as a percentage of the height of the Screen.

{:id="ListPicker.Image" .text} *Image*
: Returns the path of the button's image.

{:id="ListPicker.ItemBackgroundColor" .color} *ItemBackgroundColor*
: Property for ItemBackgroundColor

{:id="ListPicker.ItemTextColor" .color} *ItemTextColor*
: Property for ItemTextColor

{:id="ListPicker.Selection" .text} *Selection*
: Selection property getter method.

{:id="ListPicker.SelectionIndex" .number .bo} *SelectionIndex*
: Selection index property getter method.

{:id="ListPicker.Shape" .number .do} *Shape*
: Returns the style of the button.

{:id="ListPicker.ShowFeedback" .boolean} *ShowFeedback*
: Specifies if a visual feedback should be shown when a ListPicker with an assigned image
 is pressed.

{:id="ListPicker.ShowFilterBar" .boolean} *ShowFilterBar*
: Property for ShowFilterBar

{:id="ListPicker.Text" .text} *Text*
: Returns the text displayed by the ListPicker.

{:id="ListPicker.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the button's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="ListPicker.TextColor" .color} *TextColor*
: Returns the text color of the ListPicker as an alpha-red-green-blue
 integer.

{:id="ListPicker.Title" .text} *Title*
: Title property getter method.

{:id="ListPicker.Visible" .boolean} *Visible*
: Returns true iff the ListPicker is visible.

{:id="ListPicker.Width" .number .bo} *Width*
: Specifies the horizontal width of the ListPicker, measured in pixels.

{:id="ListPicker.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the ListPicker as a percentage of the width of the Screen.

### Events  {#ListPicker-Events}

{:.events}

{:id="ListPicker.AfterPicking"} AfterPicking()
: Event to be raised after the picker activity returns its
 result and the properties have been filled in.

{:id="ListPicker.BeforePicking"} BeforePicking()
: Event to raise when the ListPicker is clicked or the list is shown
 using the Open block.  This event occurs before the list of items is displayed, and 
 can be used to prepare the list before it is shown.

{:id="ListPicker.GotFocus"} GotFocus()
: Indicates the cursor moved over the button so it is now possible
 to click it.

{:id="ListPicker.LostFocus"} LostFocus()
: Indicates the cursor moved away from the button so it is now no
 longer possible to click it.

{:id="ListPicker.TouchDown"} TouchDown()
: Indicates when a button is touch down

{:id="ListPicker.TouchUp"} TouchUp()
: Indicates when a button touch ends

### Methods  {#ListPicker-Methods}

{:.methods}

{:id="ListPicker.Open" class="method"} <i/> Open()
: Opens the ListPicker, as though the user clicked on it.

## ListView  {#ListView}

ListView Component. Non-Visible component to create a ListView in the Screen from a series of
 elements added from a comma separated set of text elements. It is similar to the ListPicker
 component but this one is placed on screen instead of opening a new Activity.
 TOFO(hal): Think about generalizing this to include more than text/



### Properties  {#ListView-Properties}

{:.properties}

{:id="ListView.BackgroundColor" .color} *BackgroundColor*
: Returns the listview's background color as an alpha-red-green-blue
 integer, i.e., `0xAARRGGBB`.  An alpha of `00`
 indicates fully transparent and `FF` means opaque.

{:id="ListView.Elements" .list .bo} *Elements*
: Set a list of text elements to build a ListView

{:id="ListView.ElementsFromString" .text .wo} *ElementsFromString*
: Specifies the text elements of the ListView.

{:id="ListView.Height" .number .bo} *Height*
: Determines the height of the list on the view.

{:id="ListView.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the ListView as a percentage of the height of the Screen.

{:id="ListView.Selection" .text} *Selection*
: Returns the text in the ListView at the position set by SelectionIndex

{:id="ListView.SelectionColor" .color} *SelectionColor*
: Returns the listview's selection color as an alpha-red-green-blue
 integer, i.e., `0xAARRGGBB`.  An alpha of `00`
 indicates fully transparent and `FF` means opaque.
 Is not supported on Icecream Sandwich or earlier

{:id="ListView.SelectionIndex" .number .bo} *SelectionIndex*
: Selection index property getter method.

{:id="ListView.ShowFilterBar" .boolean} *ShowFilterBar*
: Sets true or false to determine whether the search filter box is displayed in the ListView

{:id="ListView.TextColor" .color} *TextColor*
: Returns the listview's text item color as an alpha-red-green-blue
 integer, i.e., `0xAARRGGBB`.  An alpha of `00`
 indicates fully transparent and `FF` means opaque.

{:id="ListView.TextSize" .number} *TextSize*
: Returns the listview's text font Size

{:id="ListView.Visible" .boolean} *Visible*
: Returns true iff the ListView is visible.

{:id="ListView.Width" .number .bo} *Width*
: Determines the width of the list on the view.

{:id="ListView.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the ListView as a percentage of the width of the Screen.

### Events  {#ListView-Events}

{:.events}

{:id="ListView.AfterPicking"} AfterPicking()
: Simple event to be raised after the an element has been chosen in the list.
 The selected element is available in the Selection property.

## Notifier  {#Notifier}

The Notifier component displays alert messages and creates Android log entries through
 the following methods:
 <ul>
 <li> ShowMessageDialog: user must dismiss the message by pressing a button.
 <li> ShowChooseDialog: displays two buttons to let the user choose one of two responses,
      for example, yes or no, after which the AfterChoosing event is raised.
 <li> ShowTextDialog: lets the user enter text in response to the message, after
      which the AfterTextInput event is raised.
 <li> ShowPasswordDialog: lets the user enter password in response to the message, after
      which the AfterTextInput event is raised.
 <li> ShowAlert: displays an alert that goes away by itself after
      a short time.
 <li> ShowProgressDialog: displays an alert with a loading spinner that cannot be dismissed by
      the user. Can only be dismissed by using the DismissProgressDialog block.
 <li> DismissProgressDialog: Dismisses the progress dialog displayed by ShowProgressDialog.
 <li> LogError: logs an error message to the Android log.
 <li> LogInfo: logs an info message to the Android log.
 <li> LogWarning: logs a warning message to the Android log.
 </ul>



### Properties  {#Notifier-Properties}

{:.properties}

{:id="Notifier.BackgroundColor" .color .wo} *BackgroundColor*
: Specifies the alert's background color.

{:id="Notifier.NotifierLength" .number .do} *NotifierLength*
: Specifies the length of time that the alert is shown -- either "short" or "long".

{:id="Notifier.TextColor" .color} *TextColor*
: Returns the alert's text color.

### Events  {#Notifier-Events}

{:.events}

{:id="Notifier.AfterChoosing"} AfterChoosing(*choice*{:.text})
: Event after the user has made a selection for ShowChooseDialog.

{:id="Notifier.AfterTextInput"} AfterTextInput(*response*{:.text})
: Event raised after the user has responded to ShowTextDialog.

{:id="Notifier.ChoosingCanceled"} ChoosingCanceled()
: Event raised when the user canceled ShowChooseDialog.

{:id="Notifier.TextInputCanceled"} TextInputCanceled()
: Event raised when the user canceled ShowTextDialog.

### Methods  {#Notifier-Methods}

{:.methods}

{:id="Notifier.DismissProgressDialog" class="method"} <i/> DismissProgressDialog()
: Dismisses the alert created by the ShowProgressDialog block

{:id="Notifier.LogError" class="method"} <i/> LogError(*message*{:.text})
: Log an error message.

{:id="Notifier.LogInfo" class="method"} <i/> LogInfo(*message*{:.text})
: Log an information message.

{:id="Notifier.LogWarning" class="method"} <i/> LogWarning(*message*{:.text})
: Log a warning message.

{:id="Notifier.ShowAlert" class="method"} <i/> ShowAlert(*notice*{:.text})
: Display a temporary notification

{:id="Notifier.ShowChooseDialog" class="method"} <i/> ShowChooseDialog(*message*{:.text},*title*{:.text},*button1Text*{:.text},*button2Text*{:.text},*cancelable*{:.boolean})
: Displays an alert with two buttons that have specified text.  If cancelable is true,
 there is an additional button marked CANCEL that cancels the dialog.
 Raises the AfterChoosing event when the choice has been made, and returns the text of
 the button that was pressed.

{:id="Notifier.ShowMessageDialog" class="method"} <i/> ShowMessageDialog(*message*{:.text},*title*{:.text},*buttonText*{:.text})
: Display an alert dialog with a single button that dismisses the alert.

{:id="Notifier.ShowPasswordDialog" class="method"} <i/> ShowPasswordDialog(*message*{:.text},*title*{:.text},*cancelable*{:.boolean})
: Shows a dialog box in which the user can enter password (input is masked),
 after which the AfterTextInput event is raised.

{:id="Notifier.ShowProgressDialog" class="method"} <i/> ShowProgressDialog(*message*{:.text},*title*{:.text})
: Display a progress dialog that cannot be dismissed by the user. To dismiss
 this alert, you must use the DismissProgressDialog block

{:id="Notifier.ShowTextDialog" class="method"} <i/> ShowTextDialog(*message*{:.text},*title*{:.text},*cancelable*{:.boolean})
: Shows a dialog box in which the user can enter text, after which the
 AfterTextInput event is raised.

## PasswordTextBox  {#PasswordTextBox}

Text box for entering passwords.



### Properties  {#PasswordTextBox-Properties}

{:.properties}

{:id="PasswordTextBox.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the PasswordTextBox as an alpha-red-green-blue
 integer.

{:id="PasswordTextBox.Enabled" .boolean} *Enabled*
: Returns true if the PasswordTextBox is active and useable.

{:id="PasswordTextBox.FontBold" .boolean .do} *FontBold*
: Returns true if the text of the PasswordTextBox should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="PasswordTextBox.FontItalic" .boolean .do} *FontItalic*
: Returns true if the text of the PasswordTextBox should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="PasswordTextBox.FontSize" .number} *FontSize*
: Returns the text font size of the PasswordTextBox, measured in sp(scale-independent pixels).

{:id="PasswordTextBox.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the PasswordTextBox as default, serif, sans
 serif, or monospace.

{:id="PasswordTextBox.Height" .number .bo} *Height*
: Specifies the vertical height of the PasswordTextBox, measured in pixels.

{:id="PasswordTextBox.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the PasswordTextBox as a percentage of the height of the Screen.

{:id="PasswordTextBox.Hint" .text} *Hint*
: Hint property getter method.

{:id="PasswordTextBox.PasswordVisible" .boolean .bo} *PasswordVisible*
: Visibility of password.

{:id="PasswordTextBox.Text" .text} *Text*
: Returns the textbox contents.

{:id="PasswordTextBox.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the textbox's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="PasswordTextBox.TextColor" .color} *TextColor*
: Returns the text color of the PasswordTextBox as an alpha-red-green-blue
 integer.

{:id="PasswordTextBox.Visible" .boolean} *Visible*
: Returns true iff the PasswordTextBox is visible.

{:id="PasswordTextBox.Width" .number .bo} *Width*
: Specifies the horizontal width of the PasswordTextBox, measured in pixels.

{:id="PasswordTextBox.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the PasswordTextBox as a percentage of the width of the Screen.

### Events  {#PasswordTextBox-Events}

{:.events}

{:id="PasswordTextBox.GotFocus"} GotFocus()
: Event raised when the PasswordTextBox is selected for input, such as by
 the user touching it.

{:id="PasswordTextBox.LostFocus"} LostFocus()
: Event raised when the PasswordTextBox is no longer selected for input, such
 as if the user touches a different text box.

### Methods  {#PasswordTextBox-Methods}

{:.methods}

{:id="PasswordTextBox.RequestFocus" class="method"} <i/> RequestFocus()
: Request focus to current PasswordTextBox.

## Screen  {#Screen}

Component underlying activities and UI apps, not directly accessible to Simple programmers.

 <p>This is the root container of any Android activity and also the
 superclass for Simple/Android UI applications.

 The main form is always named "Screen1".

 NOTE WELL: There are many places in the code where the name "Screen1" is
 directly referenced. If we ever change App Inventor to support renaming
 screens and Screen1 in particular, we need to make sure we find all those
 places and make the appropriate code changes.



### Properties  {#Screen-Properties}

{:.properties}

{:id="Screen.AboutScreen" .text} *AboutScreen*
: AboutScreen property getter method.

{:id="Screen.AccentColor" .color .do} *AccentColor*
: This is the accent color used for highlights and other user interface accents.

{:id="Screen.AlignHorizontal" .number} *AlignHorizontal*
: Returns a number that encodes how contents of the screen are aligned horizontally.
 The choices are: 1 = left aligned, 2 = horizontally centered, 3 = right aligned

{:id="Screen.AlignVertical" .number} *AlignVertical*
: Returns a number that encodes how contents of the arrangement are aligned vertically.
 The choices are: 1 = top, 2 = vertically centered, 3 = aligned at the bottom.
 Vertical alignment has no effect if the screen is scrollable.

{:id="Screen.AppName" .text .wo .do} *AppName*
: Specifies the App Name.

{:id="Screen.BackgroundColor" .color} *BackgroundColor*
: BackgroundColor property getter method.

{:id="Screen.BackgroundImage" .text} *BackgroundImage*
: Returns the path of the background image.

{:id="Screen.BlocksToolkit" .text .wo .do} *BlocksToolkit*
: A JSON string representing the subset for the screen

{:id="Screen.CloseScreenAnimation" .text} *CloseScreenAnimation*
: Returns the type of close screen animation (default, fade, zoom, slidehorizontal,
 slidevertical and none).

{:id="Screen.Height" .number .ro .bo} *Height*
: Height property getter method.

{:id="Screen.Icon" .text .wo .do} *Icon*
: Specifies the name of the application icon.

{:id="Screen.OpenScreenAnimation" .text} *OpenScreenAnimation*
: Returns the type of open screen animation (default, fade, zoom, slidehorizontal,
 slidevertical and none).

{:id="Screen.PrimaryColor" .color .do} *PrimaryColor*
: This is the primary color used for Material UI elements, such as the ActionBar.

{:id="Screen.PrimaryColorDark" .color .do} *PrimaryColorDark*
: This is the primary color used for darker elements in Material UI.

{:id="Screen.ScreenOrientation" .text} *ScreenOrientation*
: The requested screen orientation. Commonly used values are
      unspecified (-1), landscape (0), portrait (1), sensor (4), and user (2).  " +
      "See the Android developer documentation for ActivityInfo.Screen_Orientation for the " +
      "complete list of possible settings.

 ScreenOrientation property getter method.

{:id="Screen.Scrollable" .boolean} *Scrollable*
: Scrollable property getter method.

{:id="Screen.ShowListsAsJson" .boolean .do} *ShowListsAsJson*
: ShowListsAsJson Property Setter
 This only appears in the designer for screen 1

{:id="Screen.ShowStatusBar" .boolean} *ShowStatusBar*
: ShowStatusBar property getter method.

{:id="Screen.Sizing" .text .wo .do} *Sizing*
: Sizing Property Setter

{:id="Screen.Theme" .text .wo .do} *Theme*
: Sets the theme used by the application.

{:id="Screen.Title" .text} *Title*
: Title property getter method.

{:id="Screen.TitleVisible" .boolean} *TitleVisible*
: TitleVisible property getter method.

{:id="Screen.TutorialURL" .text .wo .do} *TutorialURL*
: A URL to use to populate the Tutorial Sidebar while editing a project. Used as a teaching aid.

{:id="Screen.VersionCode" .number .wo .do} *VersionCode*
: Specifies the Version Code.

{:id="Screen.VersionName" .text .wo .do} *VersionName*
: Specifies the Version Name.

{:id="Screen.Width" .number .ro .bo} *Width*
: Width property getter method.

### Events  {#Screen-Events}

{:.events}

{:id="Screen.BackPressed"} BackPressed()
: Device back button pressed.

{:id="Screen.ErrorOccurred"} ErrorOccurred(*component*{:.component},*functionName*{:.text},*errorNumber*{:.number},*message*{:.text})
: ErrorOccurred event handler.

{:id="Screen.Initialize"} Initialize()
: Initialize event handler.

{:id="Screen.OtherScreenClosed"} OtherScreenClosed(*otherScreenName*{:.text},*result*{:.any})
: Event raised when another screen has closed and control has returned to this screen.

{:id="Screen.PermissionDenied"} PermissionDenied(*component*{:.component},*functionName*{:.text},*permissionName*{:.text})
: Event to handle when the app user has denied a needed permission.

{:id="Screen.PermissionGranted"} PermissionGranted(*permissionName*{:.text})
: Event to handle when the app user has granted a needed permission. This event is only run when permission is
 granted in response to the AskForPermission method.

{:id="Screen.ScreenOrientationChanged"} ScreenOrientationChanged()
: Screen orientation changed

### Methods  {#Screen-Methods}

{:.methods}

{:id="Screen.AskForPermission" class="method"} <i/> AskForPermission(*permissionName*{:.text})
: Ask the user to grant access to a dangerous permission.

{:id="Screen.HideKeyboard" class="method"} <i/> HideKeyboard()
: Hide the soft keyboard

## Slider  {#Slider}

This class is used to display a Slider.
 <p>The Slider is a progress bar that adds a draggable thumb. You can touch the thumb and drag
 left or right to set the slider thumb position. As Slider thumb is dragged, it will trigger
 PositionChanged event, reporting the position of the Slider thumb. The Slider uses the following
 default values. However these values can be changed through designer or block editor
 <ul>
 <li>MinValue</li>
 <li>MaxValue</li>
 <li>ThumbPosition</li>
 </ul></p>



### Properties  {#Slider-Properties}

{:.properties}

{:id="Slider.ColorLeft" .color} *ColorLeft*
: Returns the color of the slider bar to the left of the thumb, as an alpha-red-green-blue
 integer, i.e., `0xAARRGGBB`.  An alpha of `00`
 indicates fully transparent and `FF` means opaque.

{:id="Slider.ColorRight" .color} *ColorRight*
: Returns the color of the slider bar to the right of the thumb, as an alpha-red-green-blue
 integer, i.e., `0xAARRGGBB`.  An alpha of `00`
 indicates fully transparent and `FF` means opaque.

{:id="Slider.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the Slider as a percentage of the height of the Screen.

{:id="Slider.MaxValue" .number} *MaxValue*
: Sets the maximum value of slider.  Changing the maximum value also resets Thumbposition to be halfway between the minimum and the (new) maximum. If the new maximum is less than the current minimum, then minimum and maximum will both be set to this value.  Setting MaxValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.

{:id="Slider.MinValue" .number} *MinValue*
: Sets the minimum value of slider.  Changing the minimum value also resets Thumbposition to be halfway between the (new) minimum and the maximum. If the new minimum is greater than the current maximum, then minimum and maximum will both be set to this value.  Setting MinValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.

{:id="Slider.ThumbEnabled" .boolean} *ThumbEnabled*
: Sets whether or not the slider thumb should be shown

{:id="Slider.ThumbPosition" .number} *ThumbPosition*
: Sets the slider thumb position.

{:id="Slider.Visible" .boolean} *Visible*
: Returns true iff the Slider is visible.

{:id="Slider.Width" .number .bo} *Width*
: Specifies the horizontal width of the Slider, measured in pixels.

{:id="Slider.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the Slider as a percentage of the width of the Screen.

### Events  {#Slider-Events}

{:.events}

{:id="Slider.PositionChanged"} PositionChanged(*thumbPosition*{:.number})
: Indicates that position of the slider thumb has changed.

## Spinner  {#Spinner}

Component for Spinner



### Properties  {#Spinner-Properties}

{:.properties}

{:id="Spinner.Elements" .list .bo} *Elements*
: Elements property getter method

{:id="Spinner.ElementsFromString" .text .wo} *ElementsFromString*
: ElementsFromString property setter method

{:id="Spinner.Height" .number .bo} *Height*
: Specifies the vertical height of the Spinner, measured in pixels.

{:id="Spinner.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the Spinner as a percentage of the height of the Screen.

{:id="Spinner.Prompt" .text} *Prompt*
: Prompt property getter method

{:id="Spinner.Selection" .text} *Selection*
: Selection property getter method.

{:id="Spinner.SelectionIndex" .number .bo} *SelectionIndex*
: Selection index property getter method.

{:id="Spinner.Visible" .boolean} *Visible*
: Returns true iff the Spinner is visible.

{:id="Spinner.Width" .number .bo} *Width*
: Specifies the horizontal width of the Spinner, measured in pixels.

{:id="Spinner.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the Spinner as a percentage of the width of the Screen.

### Events  {#Spinner-Events}

{:.events}

{:id="Spinner.AfterSelecting"} AfterSelecting(*selection*{:.text})
: Indicates a user has selected an item

### Methods  {#Spinner-Methods}

{:.methods}

{:id="Spinner.DisplayDropdown" class="method"} <i/> DisplayDropdown()
: To display the dropdown list without the user having to click it

## Switch  {#Switch}

Toggle switch with the ability to detect initialization, focus
 change (mousing on or off of it), and user clicks.



### Properties  {#Switch-Properties}

{:.properties}

{:id="Switch.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the Switch as an alpha-red-green-blue
 integer.

{:id="Switch.Enabled" .boolean} *Enabled*
: Returns true if the Switch is active and clickable.

{:id="Switch.FontBold" .boolean .do} *FontBold*
: Returns true if the text of the Switch should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="Switch.FontItalic" .boolean .do} *FontItalic*
: Returns true if the text of the Switch should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="Switch.FontSize" .number} *FontSize*
: Returns the text font size of the Switch, measured in sp(scale-independent pixels).

{:id="Switch.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the Switch as default, serif, sans
 serif, or monospace.

{:id="Switch.Height" .number .bo} *Height*
: Specifies the vertical height of the Switch, measured in pixels.

{:id="Switch.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the Switch as a percentage of the height of the Screen.

{:id="Switch.On" .boolean} *On*
: Returns true if the checkbox is checked.

{:id="Switch.Text" .text} *Text*
: Returns the text displayed by the Switch.

{:id="Switch.TextColor" .color} *TextColor*
: Returns the text color of the Switch as an alpha-red-green-blue
 integer.

{:id="Switch.ThumbColorActive" .color .do} *ThumbColorActive*
: Returns the switch's thumb color (button that toggles back and forth)
 when the switch is ON/Checked

{:id="Switch.ThumbColorInactive" .color} *ThumbColorInactive*
: Returns the switch's thumb color (button that toggles back and forth)
 when the switch is Off/Unchecked

{:id="Switch.TrackColorActive" .color} *TrackColorActive*
: Returns the switch's track color

{:id="Switch.TrackColorInactive" .color} *TrackColorInactive*
: Property for TrackColorInactive

{:id="Switch.Visible" .boolean} *Visible*
: Returns true iff the Switch is visible.

{:id="Switch.Width" .number .bo} *Width*
: Specifies the horizontal width of the Switch, measured in pixels.

{:id="Switch.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the Switch as a percentage of the width of the Screen.

### Events  {#Switch-Events}

{:.events}

{:id="Switch.Changed"} Changed()
: Default Changed event handler.

{:id="Switch.GotFocus"} GotFocus()
: Default GotFocus event handler.

{:id="Switch.LostFocus"} LostFocus()
: Default LostFocus event handler.

## TextBox  {#TextBox}

A box in which the user can enter text.



### Properties  {#TextBox-Properties}

{:.properties}

{:id="TextBox.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the TextBox as an alpha-red-green-blue
 integer.

{:id="TextBox.Enabled" .boolean} *Enabled*
: Returns true if the TextBox is active and useable.

{:id="TextBox.FontBold" .boolean .do} *FontBold*
: Returns true if the text of the TextBox should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="TextBox.FontItalic" .boolean .do} *FontItalic*
: Returns true if the text of the TextBox should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="TextBox.FontSize" .number} *FontSize*
: Returns the text font size of the TextBox, measured in sp(scale-independent pixels).

{:id="TextBox.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the TextBox as default, serif, sans
 serif, or monospace.

{:id="TextBox.Height" .number .bo} *Height*
: Specifies the vertical height of the TextBox, measured in pixels.

{:id="TextBox.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the TextBox as a percentage of the height of the Screen.

{:id="TextBox.Hint" .text} *Hint*
: Hint property getter method.

{:id="TextBox.MultiLine" .boolean} *MultiLine*
: Multi line property getter method.

{:id="TextBox.NumbersOnly" .boolean} *NumbersOnly*
: NumbersOnly property getter method.

{:id="TextBox.ReadOnly" .boolean} *ReadOnly*
: Whether the TextBox is read-only. By default, this is true.

{:id="TextBox.Text" .text} *Text*
: Returns the textbox contents.

{:id="TextBox.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the textbox's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="TextBox.TextColor" .color} *TextColor*
: Returns the text color of the TextBox as an alpha-red-green-blue
 integer.

{:id="TextBox.Visible" .boolean} *Visible*
: Returns true iff the TextBox is visible.

{:id="TextBox.Width" .number .bo} *Width*
: Specifies the horizontal width of the TextBox, measured in pixels.

{:id="TextBox.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the TextBox as a percentage of the width of the Screen.

### Events  {#TextBox-Events}

{:.events}

{:id="TextBox.GotFocus"} GotFocus()
: Event raised when the TextBox is selected for input, such as by
 the user touching it.

{:id="TextBox.LostFocus"} LostFocus()
: Event raised when the TextBox is no longer selected for input, such
 as if the user touches a different text box.

### Methods  {#TextBox-Methods}

{:.methods}

{:id="TextBox.HideKeyboard" class="method"} <i/> HideKeyboard()
: Hide the soft keyboard

{:id="TextBox.RequestFocus" class="method"} <i/> RequestFocus()
: Request focus to current TextBox.

## TimePicker  {#TimePicker}

A button allowing a user to launch the TimePickerDialog. This component is
 is based off the ButtonBase class instead of the base Picker class because
 unlike the other pickers, the TimePicker does not need to launch a new
 activity and get a result. The TimePicker is launched as a dialog.



### Properties  {#TimePicker-Properties}

{:.properties}

{:id="TimePicker.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer.

{:id="TimePicker.Enabled" .boolean} *Enabled*
: Returns true if the TimePicker is active and clickable.

{:id="TimePicker.FontBold" .boolean} *FontBold*
: Returns true if the text of the TimePicker should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="TimePicker.FontItalic" .boolean} *FontItalic*
: Returns true if the text of the TimePicker should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="TimePicker.FontSize" .number} *FontSize*
: Returns the text font size of the TimePicker, measured in sp(scale-independent pixels).

{:id="TimePicker.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the TimePicker as default, serif, sans
 serif, or monospace.

{:id="TimePicker.Height" .number .bo} *Height*
: Specifies the vertical height of the TimePicker, measured in pixels.

{:id="TimePicker.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the TimePicker as a percentage of the height of the Screen.

{:id="TimePicker.Hour" .number .ro .bo} *Hour*
: Returns the hour of the time that was last picked using the timepicker.
 The time returned is always in the 24hour format.

{:id="TimePicker.Image" .text} *Image*
: Returns the path of the button's image.

{:id="TimePicker.Instant" .InstantInTime .ro .bo} *Instant*
: Returns the instant in time that was last picked using the DatePicker.

{:id="TimePicker.Minute" .number .ro .bo} *Minute*
: Returns the hour of the time that was last picked using the timepicker.
 The time returned is always in the 24hour format.

{:id="TimePicker.Shape" .number .do} *Shape*
: Returns the style of the button.

{:id="TimePicker.ShowFeedback" .boolean} *ShowFeedback*
: Specifies if a visual feedback should be shown when a TimePicker with an assigned image
 is pressed.

{:id="TimePicker.Text" .text} *Text*
: Returns the text displayed by the TimePicker.

{:id="TimePicker.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the button's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="TimePicker.TextColor" .color} *TextColor*
: Returns the text color of the TimePicker as an alpha-red-green-blue
 integer.

{:id="TimePicker.Visible" .boolean} *Visible*
: Returns true iff the TimePicker is visible.

{:id="TimePicker.Width" .number .bo} *Width*
: Specifies the horizontal width of the TimePicker, measured in pixels.

{:id="TimePicker.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the TimePicker as a percentage of the width of the Screen.

### Events  {#TimePicker-Events}

{:.events}

{:id="TimePicker.AfterTimeSet"} AfterTimeSet()
: Indicates the user has set the time.

{:id="TimePicker.GotFocus"} GotFocus()
: Indicates the cursor moved over the button so it is now possible
 to click it.

{:id="TimePicker.LostFocus"} LostFocus()
: Indicates the cursor moved away from the button so it is now no
 longer possible to click it.

{:id="TimePicker.TouchDown"} TouchDown()
: Indicates when a button is touch down

{:id="TimePicker.TouchUp"} TouchUp()
: Indicates when a button touch ends

### Methods  {#TimePicker-Methods}

{:.methods}

{:id="TimePicker.LaunchPicker" class="method"} <i/> LaunchPicker()
: Launches the TimePicker popup.

{:id="TimePicker.SetTimeToDisplay" class="method"} <i/> SetTimeToDisplay(*hour*{:.number},*minute*{:.number})
: Set the time to be shown in the Time Picker popup. Current time is shown by default.

{:id="TimePicker.SetTimeToDisplayFromInstant" class="method"} <i/> SetTimeToDisplayFromInstant(*instant*{:.InstantInTime})
: Set the time from the instant to be shown in the Time Picker popup. Current time is shown by default.

## WebViewer  {#WebViewer}

Component for displaying web pages
 This is a very limited form of browser.  You can view web pages and
 click on links. It also handles  Javascript. There are lots of things that could be added,
 but this component is mostly for viewing individual pages.  It's not intended to take
 the place of the browser.



### Properties  {#WebViewer-Properties}

{:.properties}

{:id="WebViewer.CurrentPageTitle" .text .ro .bo} *CurrentPageTitle*
: Returns the title of the page currently being viewed

{:id="WebViewer.CurrentUrl" .text .ro .bo} *CurrentUrl*
: Returns the URL currently being viewed

{:id="WebViewer.FollowLinks" .boolean} *FollowLinks*
: Indicates whether to follow links when they are tapped in the WebViewer

{:id="WebViewer.Height" .number .bo} *Height*
: Specifies the vertical height of the %type%, measured in pixels.

{:id="WebViewer.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the WebViewer as a percentage of the height of the Screen.

{:id="WebViewer.HomeUrl" .text} *HomeUrl*
: Returns the URL of the page the WebVewier should load

{:id="WebViewer.IgnoreSslErrors" .boolean} *IgnoreSslErrors*
: Determines whether SSL Errors are ignored. Set to true to use self signed certificates

{:id="WebViewer.PromptforPermission" .boolean} *PromptforPermission*
: Determine if the user should be prompted for permission to use the geolocation API while in
 the webviewer.

{:id="WebViewer.UsesLocation" .boolean .wo .do} *UsesLocation*
: Specifies whether or not this WebViewer can access the JavaScript
 Location API.

{:id="WebViewer.Visible" .boolean} *Visible*
: Returns true iff the WebViewer is visible.

{:id="WebViewer.WebViewString" .text .bo} *WebViewString*
: Gets the web view string

{:id="WebViewer.Width" .number .bo} *Width*
: Specifies the horizontal width of the %type%, measured in pixels.

{:id="WebViewer.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the WebViewer as a percentage of the width of the Screen.

### Events  {#WebViewer-Events}

{:.events}

{:id="WebViewer.PageLoaded"} PageLoaded(*url*{:.text})
: When a page is finished loading this event is run.

{:id="WebViewer.WebViewStringChange"} WebViewStringChange(*value*{:.text})
: When the JavaScript calls AppInventor.setWebViewString this event is run.

### Methods  {#WebViewer-Methods}

{:.methods}

{:id="WebViewer.CanGoBack" class="method returns boolean"} <i/> CanGoBack()
: 

{:id="WebViewer.CanGoForward" class="method returns boolean"} <i/> CanGoForward()
: 

{:id="WebViewer.ClearCaches" class="method"} <i/> ClearCaches()
: Clear the webview cache, both ram and disk. This is useful
 when using the webviewer to poll a page that may not be sending
 appropriate cache control headers. This is particularly useful
 when using the webviwer to look at a Fusion Table.

{:id="WebViewer.ClearLocations" class="method"} <i/> ClearLocations()
: Clear Stored Location permissions. When the geolocation API is used in
 the WebViewer, the end user is prompted on a per URL basis for whether
 or not permission should be granted to access their location. This
 function clears this information for all locations.

 As the permissions interface is not available on phones older then
 Eclair, this function is a no-op on older phones.

{:id="WebViewer.GoBack" class="method"} <i/> GoBack()
: Go back to the previously viewed page.

{:id="WebViewer.GoForward" class="method"} <i/> GoForward()
: Go forward in the history list

{:id="WebViewer.GoHome" class="method"} <i/> GoHome()
: Loads the  page from the home URL.  This happens automatically when
 home URL is changed.

{:id="WebViewer.GoToUrl" class="method"} <i/> GoToUrl(*url*{:.text})
: Load the given URL
