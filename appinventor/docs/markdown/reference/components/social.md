---
layout: documentation
title: Social
---

[&laquo; Back to index](index.html)
# Social

Table of Contents:

* [ContactPicker](#ContactPicker)
* [EmailPicker](#EmailPicker)
* [PhoneCall](#PhoneCall)
* [PhoneNumberPicker](#PhoneNumberPicker)
* [Sharing](#Sharing)
* [Texting](#Texting)
* [Twitter](#Twitter)

## ContactPicker  {#ContactPicker}

Component enabling a user to select a contact.



### Properties  {#ContactPicker-Properties}

{:.properties}

{:id="ContactPicker.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer.

{:id="ContactPicker.ContactName" .text .ro .bo} *ContactName*
: Name property getter method.

{:id="ContactPicker.ContactUri" .text .ro .bo} *ContactUri*
: "URI that specifies the location of the contact on the device.",

{:id="ContactPicker.EmailAddress" .text .ro .bo} *EmailAddress*
: EmailAddress property getter method.

{:id="ContactPicker.EmailAddressList" .list .ro .bo} *EmailAddressList*
: EmailAddressList property getter method.

{:id="ContactPicker.Enabled" .boolean} *Enabled*
: Returns true if the ContactPicker is active and clickable.

{:id="ContactPicker.FontBold" .boolean} *FontBold*
: Returns true if the text of the ContactPicker should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="ContactPicker.FontItalic" .boolean} *FontItalic*
: Returns true if the text of the ContactPicker should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="ContactPicker.FontSize" .number} *FontSize*
: Returns the text font size of the ContactPicker, measured in sp(scale-independent pixels).

{:id="ContactPicker.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the ContactPicker as default, serif, sans
 serif, or monospace.

{:id="ContactPicker.Height" .number .bo} *Height*
: Specifies the vertical height of the ContactPicker, measured in pixels.

{:id="ContactPicker.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the ContactPicker as a percentage of the height of the Screen.

{:id="ContactPicker.Image" .text} *Image*
: Returns the path of the button's image.

{:id="ContactPicker.PhoneNumber" .text .ro .bo} *PhoneNumber*
: PhoneNumber property getter method.

{:id="ContactPicker.PhoneNumberList" .list .ro .bo} *PhoneNumberList*
: PhoneNumberList property getter method.

{:id="ContactPicker.Picture" .text .ro .bo} *Picture*
: Picture URI for this contact, which can be
 used to retrieve the contact's photo and other fields.

{:id="ContactPicker.Shape" .number .do} *Shape*
: Returns the style of the button.

{:id="ContactPicker.ShowFeedback" .boolean} *ShowFeedback*
: Specifies if a visual feedback should be shown when a ContactPicker with an assigned image
 is pressed.

{:id="ContactPicker.Text" .text} *Text*
: Returns the text displayed by the ContactPicker.

{:id="ContactPicker.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the button's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="ContactPicker.TextColor" .color} *TextColor*
: Returns the text color of the ContactPicker as an alpha-red-green-blue
 integer.

{:id="ContactPicker.Visible" .boolean} *Visible*
: Returns true iff the ContactPicker is visible.

{:id="ContactPicker.Width" .number .bo} *Width*
: Specifies the horizontal width of the ContactPicker, measured in pixels.

{:id="ContactPicker.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the ContactPicker as a percentage of the width of the Screen.

### Events  {#ContactPicker-Events}

{:.events}

{:id="ContactPicker.AfterPicking"} AfterPicking()
: Event to be raised after the picker activity returns its
 result and the properties have been filled in.

{:id="ContactPicker.BeforePicking"} BeforePicking()
: Event to raise when the ContactPicker is clicked or the list is shown
 using the Open block.  This event occurs before the list of items is displayed, and 
 can be used to prepare the list before it is shown.

{:id="ContactPicker.GotFocus"} GotFocus()
: Indicates the cursor moved over the button so it is now possible
 to click it.

{:id="ContactPicker.LostFocus"} LostFocus()
: Indicates the cursor moved away from the button so it is now no
 longer possible to click it.

{:id="ContactPicker.TouchDown"} TouchDown()
: Indicates when a button is touch down

{:id="ContactPicker.TouchUp"} TouchUp()
: Indicates when a button touch ends

### Methods  {#ContactPicker-Methods}

{:.methods}

{:id="ContactPicker.Open" class="method"} <i/> Open()
: Opens the ContactPicker, as though the user clicked on it.

{:id="ContactPicker.ViewContact" class="method"} <i/> ViewContact(*uri*{:.text})
: return nothing, just call another activity which is view contact

## EmailPicker  {#EmailPicker}

Text box using auto-completion to pick out an email address from contacts.



### Properties  {#EmailPicker-Properties}

{:.properties}

{:id="EmailPicker.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the EmailPicker as an alpha-red-green-blue
 integer.

{:id="EmailPicker.Enabled" .boolean} *Enabled*
: Returns true if the EmailPicker is active and useable.

{:id="EmailPicker.FontBold" .boolean .do} *FontBold*
: Returns true if the text of the EmailPicker should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="EmailPicker.FontItalic" .boolean .do} *FontItalic*
: Returns true if the text of the EmailPicker should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="EmailPicker.FontSize" .number} *FontSize*
: Returns the text font size of the EmailPicker, measured in sp(scale-independent pixels).

{:id="EmailPicker.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the EmailPicker as default, serif, sans
 serif, or monospace.

{:id="EmailPicker.Height" .number .bo} *Height*
: Specifies the vertical height of the EmailPicker, measured in pixels.

{:id="EmailPicker.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the EmailPicker as a percentage of the height of the Screen.

{:id="EmailPicker.Hint" .text} *Hint*
: Hint property getter method.

{:id="EmailPicker.Text" .text} *Text*
: Returns the textbox contents.

{:id="EmailPicker.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the textbox's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="EmailPicker.TextColor" .color} *TextColor*
: Returns the text color of the EmailPicker as an alpha-red-green-blue
 integer.

{:id="EmailPicker.Visible" .boolean} *Visible*
: Returns true iff the EmailPicker is visible.

{:id="EmailPicker.Width" .number .bo} *Width*
: Specifies the horizontal width of the EmailPicker, measured in pixels.

{:id="EmailPicker.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the EmailPicker as a percentage of the width of the Screen.

### Events  {#EmailPicker-Events}

{:.events}

{:id="EmailPicker.GotFocus"} GotFocus()
: Event raised when this component is selected for input, such as by
 the user touching it.

{:id="EmailPicker.LostFocus"} LostFocus()
: Event raised when the EmailPicker is no longer selected for input, such
 as if the user touches a different text box.

### Methods  {#EmailPicker-Methods}

{:.methods}

{:id="EmailPicker.RequestFocus" class="method"} <i/> RequestFocus()
: Request focus to current EmailPicker.

## PhoneCall  {#PhoneCall}

Component for making a phone call to a programatically-specified number.

 TODO(markf): Note that the initial carrier for Android phones only supports 3 participants
              in a conference call, so that's all that the current implementation of this
              component supports.  In the future we can generalize this to more participants.



### Properties  {#PhoneCall-Properties}

{:.properties}

{:id="PhoneCall.PhoneNumber" .text} *PhoneNumber*
: PhoneNumber property getter method.

### Events  {#PhoneCall-Events}

{:.events}

{:id="PhoneCall.IncomingCallAnswered"} IncomingCallAnswered(*phoneNumber*{:.text})
: Event indicating that an incoming phone call is answered.

{:id="PhoneCall.PhoneCallEnded"} PhoneCallEnded(*status*{:.number},*phoneNumber*{:.text})
: Event indicating that a phone call has ended.
 status: 1:incoming call is missed or rejected; 2:incoming call is answered before hanging up; 3:Outgoing call is hung up.

{:id="PhoneCall.PhoneCallStarted"} PhoneCallStarted(*status*{:.number},*phoneNumber*{:.text})
: Event indicating that a phone call has started.
 status: 1:incoming call is ringing; 2:outgoing call is dialled.

### Methods  {#PhoneCall-Methods}

{:.methods}

{:id="PhoneCall.MakePhoneCall" class="method"} <i/> MakePhoneCall()
: Makes a phone call using the number in the PhoneNumber property.

{:id="PhoneCall.MakePhoneCallDirect" class="method"} <i/> MakePhoneCallDirect()
: Makes a phone call using the number in the PhoneNumber property.

## PhoneNumberPicker  {#PhoneNumberPicker}

Component enabling a user to select a contact's phone number.



### Properties  {#PhoneNumberPicker-Properties}

{:.properties}

{:id="PhoneNumberPicker.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer.

{:id="PhoneNumberPicker.ContactName" .text .ro .bo} *ContactName*
: Name property getter method.

{:id="PhoneNumberPicker.ContactUri" .text .ro .bo} *ContactUri*
: "URI that specifies the location of the contact on the device.",

{:id="PhoneNumberPicker.EmailAddress" .text .ro .bo} *EmailAddress*
: EmailAddress property getter method.

{:id="PhoneNumberPicker.EmailAddressList" .list .ro .bo} *EmailAddressList*
: EmailAddressList property getter method.

{:id="PhoneNumberPicker.Enabled" .boolean} *Enabled*
: Returns true if the PhoneNumberPicker is active and clickable.

{:id="PhoneNumberPicker.FontBold" .boolean} *FontBold*
: Returns true if the text of the PhoneNumberPicker should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="PhoneNumberPicker.FontItalic" .boolean} *FontItalic*
: Returns true if the text of the PhoneNumberPicker should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="PhoneNumberPicker.FontSize" .number} *FontSize*
: Returns the text font size of the PhoneNumberPicker, measured in sp(scale-independent pixels).

{:id="PhoneNumberPicker.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the PhoneNumberPicker as default, serif, sans
 serif, or monospace.

{:id="PhoneNumberPicker.Height" .number .bo} *Height*
: Specifies the vertical height of the PhoneNumberPicker, measured in pixels.

{:id="PhoneNumberPicker.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the PhoneNumberPicker as a percentage of the height of the Screen.

{:id="PhoneNumberPicker.Image" .text} *Image*
: Returns the path of the button's image.

{:id="PhoneNumberPicker.PhoneNumber" .text .ro .bo} *PhoneNumber*
: PhoneNumber property getter method.

{:id="PhoneNumberPicker.PhoneNumberList" .list .ro .bo} *PhoneNumberList*
: PhoneNumberList property getter method.

{:id="PhoneNumberPicker.Picture" .text .ro .bo} *Picture*
: Picture URI for this contact, which can be
 used to retrieve the contact's photo and other fields.

{:id="PhoneNumberPicker.Shape" .number .do} *Shape*
: Returns the style of the button.

{:id="PhoneNumberPicker.ShowFeedback" .boolean} *ShowFeedback*
: Specifies if a visual feedback should be shown when a PhoneNumberPicker with an assigned image
 is pressed.

{:id="PhoneNumberPicker.Text" .text} *Text*
: Returns the text displayed by the PhoneNumberPicker.

{:id="PhoneNumberPicker.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the button's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="PhoneNumberPicker.TextColor" .color} *TextColor*
: Returns the text color of the PhoneNumberPicker as an alpha-red-green-blue
 integer.

{:id="PhoneNumberPicker.Visible" .boolean} *Visible*
: Returns true iff the PhoneNumberPicker is visible.

{:id="PhoneNumberPicker.Width" .number .bo} *Width*
: Specifies the horizontal width of the PhoneNumberPicker, measured in pixels.

{:id="PhoneNumberPicker.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the PhoneNumberPicker as a percentage of the width of the Screen.

### Events  {#PhoneNumberPicker-Events}

{:.events}

{:id="PhoneNumberPicker.AfterPicking"} AfterPicking()
: Event to be raised after the picker activity returns its
 result and the properties have been filled in.

{:id="PhoneNumberPicker.BeforePicking"} BeforePicking()
: Event to raise when the PhoneNumberPicker is clicked or the list is shown
 using the Open block.  This event occurs before the list of items is displayed, and 
 can be used to prepare the list before it is shown.

{:id="PhoneNumberPicker.GotFocus"} GotFocus()
: Indicates the cursor moved over the button so it is now possible
 to click it.

{:id="PhoneNumberPicker.LostFocus"} LostFocus()
: Indicates the cursor moved away from the button so it is now no
 longer possible to click it.

{:id="PhoneNumberPicker.TouchDown"} TouchDown()
: Indicates when a button is touch down

{:id="PhoneNumberPicker.TouchUp"} TouchUp()
: Indicates when a button touch ends

### Methods  {#PhoneNumberPicker-Methods}

{:.methods}

{:id="PhoneNumberPicker.Open" class="method"} <i/> Open()
: Opens the PhoneNumberPicker, as though the user clicked on it.

{:id="PhoneNumberPicker.ViewContact" class="method"} <i/> ViewContact(*uri*{:.text})
: return nothing, just call another activity which is view contact

## Sharing  {#Sharing}

Component for sharing files and/or messages through Android's built-in sharing
 functionality.



### Methods  {#Sharing-Methods}

{:.methods}

{:id="Sharing.ShareFile" class="method"} <i/> ShareFile(*file*{:.text})
: Shares a file using Android' built-in sharing.

{:id="Sharing.ShareFileWithMessage" class="method"} <i/> ShareFileWithMessage(*file*{:.text},*message*{:.text})
: Shares a file along with a message using Android' built-in sharing.

{:id="Sharing.ShareMessage" class="method"} <i/> ShareMessage(*message*{:.text})
: Shares a message using Android' built-in sharing.

## Texting  {#Texting}

A component capable of sending and receiving text messages via SMS.



### Properties  {#Texting-Properties}

{:.properties}

{:id="Texting.GoogleVoiceEnabled" .boolean} *GoogleVoiceEnabled*
: If this property is true, then SendMessage will attempt to send messages using
 Google voice.

{:id="Texting.Message" .text} *Message*
: The text message to that will be sent when the SendMessage method is called.

{:id="Texting.PhoneNumber" .text} *PhoneNumber*
: Sets the phone number to send the text message to when the SendMessage function is called.

{:id="Texting.ReceivingEnabled" .number} *ReceivingEnabled*
: Gets whether you want the [MessageReceived](#Texting.MessageReceived) event to
 get run when a new text message is received.

### Events  {#Texting-Events}

{:.events}

{:id="Texting.MessageReceived"} MessageReceived(*number*{:.text},*messageText*{:.text})
: Event that's raised when a text message is received by the phone.

### Methods  {#Texting-Methods}

{:.methods}

{:id="Texting.SendMessage" class="method"} <i/> SendMessage()
: Send a text message

{:id="Texting.SendMessageDirect" class="method"} <i/> SendMessageDirect()
: Send a text message directly

## Twitter  {#Twitter}

Component for accessing Twitter.



### Properties  {#Twitter-Properties}

{:.properties}

{:id="Twitter.ConsumerKey" .text} *ConsumerKey*
: ConsumerKey property getter method.

{:id="Twitter.ConsumerSecret" .text} *ConsumerSecret*
: ConsumerSecret property getter method.

{:id="Twitter.DirectMessages" .list .ro .bo} *DirectMessages*
: This property contains a list of the most recent messages mentioning the logged-in user.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>Authorized</code> event.</li> <li> Call the <code>RequestDirectMessages</code> method.</li> <li> Wait for the <code>DirectMessagesReceived</code> event.</li></ol>
The value of this property will then be set to the list of direct messages retrieved (and maintain that value until any subsequent call to <code>RequestDirectMessages</code>).

{:id="Twitter.Followers" .list .ro .bo} *Followers*
: This property contains a list of the followers of the logged-in user.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>IsAuthorized</code> event.</li> <li> Call the <code>RequestFollowers</code> method.</li> <li> Wait for the <code>FollowersReceived</code> event.</li></ol>
The value of this property will then be set to the list of followers (and maintain its value until any subsequent call to <code>RequestFollowers</code>).

{:id="Twitter.FriendTimeline" .list .ro .bo} *FriendTimeline*
: This property contains the 20 most recent messages of users being followed.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>IsAuthorized</code> event.</li> <li> Specify users to follow with one or more calls to the <code>Follow</code> method.</li> <li> Call the <code>RequestFriendTimeline</code> method.</li> <li> Wait for the <code>FriendTimelineReceived</code> event.</li> </ol>
The value of this property will then be set to the list of messages (and maintain its value until any subsequent call to <code>RequestFriendTimeline</code>.

{:id="Twitter.Mentions" .list .ro .bo} *Mentions*
: This property contains a list of mentions of the logged-in user.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>IsAuthorized</code> event.</li> <li> Call the <code>RequestMentions</code> method.</li> <li> Wait for the <code>MentionsReceived</code> event.</li></ol>
The value of this property will then be set to the list of mentions (and will maintain its value until any subsequent calls to <code>RequestMentions</code>).

{:id="Twitter.SearchResults" .list .ro .bo} *SearchResults*
: This property, which is initially empty, is set to a list of search results after the program: <ol><li>Calls the <code>SearchTwitter</code> method.</li> <li>Waits for the <code>SearchSuccessful</code> event.</li></ol>
The value of the property will then be the same as the parameter to <code>SearchSuccessful</code>.  Note that it is not necessary to call the <code>Authorize</code> method before calling <code>SearchTwitter</code>.

{:id="Twitter.TwitPic_API_Key" .text .bo} *TwitPic_API_Key*
: TwitPicAPIkey property getter method.

{:id="Twitter.Username" .text .ro .bo} *Username*
: The user name of the authorized user. Empty if there is no authorized user.

### Events  {#Twitter-Events}

{:.events}

{:id="Twitter.DirectMessagesReceived"} DirectMessagesReceived(*messages*{:.list})
: Indicates when all the direct messages requested through
 [RequestDirectMessages](#Twitter.RequestDirectMessages) have been received.

{:id="Twitter.FollowersReceived"} FollowersReceived(*followers2*{:.list})
: Indicates when all of your followers requested through
 [RequestFollowers](#Twitter.RequestFollowers) have been received.

{:id="Twitter.FriendTimelineReceived"} FriendTimelineReceived(*timeline*{:.list})
: Indicates when the friend timeline requested through
 [RequestFriendTimeline](#Twitter.RequestFriendTimeline) has been received.

{:id="Twitter.IsAuthorized"} IsAuthorized()
: Indicates when the login has been successful.

{:id="Twitter.MentionsReceived"} MentionsReceived(*mentions*{:.list})
: Indicates when all the mentions requested through
 [RequestMentions](#Twitter.RequestMentions) have been received.

{:id="Twitter.SearchSuccessful"} SearchSuccessful(*searchResults*{:.list})
: Indicates when the search requested through [SearchTwitter](#Twitter.SearchTwitter)
 has completed.

### Methods  {#Twitter-Methods}

{:.methods}

{:id="Twitter.Authorize" class="method"} <i/> Authorize()
: Authenticate to Twitter using OAuth

{:id="Twitter.CheckAuthorized" class="method"} <i/> CheckAuthorized()
: Check whether we already have a valid Twitter access token

{:id="Twitter.DeAuthorize" class="method"} <i/> DeAuthorize()
: Remove authentication for this app instance

{:id="Twitter.DirectMessage" class="method"} <i/> DirectMessage(*user*{:.text},*message*{:.text})
: Sends a direct message to a specified username.

{:id="Twitter.Follow" class="method"} <i/> Follow(*user*{:.text})
: Starts following a user.

{:id="Twitter.RequestDirectMessages" class="method"} <i/> RequestDirectMessages()
: Gets the most recent messages sent directly to you.

{:id="Twitter.RequestFollowers" class="method"} <i/> RequestFollowers()
: Gets who is following you.

{:id="Twitter.RequestFriendTimeline" class="method"} <i/> RequestFriendTimeline()
: Gets the most recent 20 messages in the user's timeline.

{:id="Twitter.RequestMentions" class="method"} <i/> RequestMentions()
: Gets the most recent messages where your username is mentioned.

{:id="Twitter.SearchTwitter" class="method"} <i/> SearchTwitter(*query*{:.text})
: Search for tweets or labels

{:id="Twitter.StopFollowing" class="method"} <i/> StopFollowing(*user*{:.text})
: Stops following a user.

{:id="Twitter.Tweet" class="method"} <i/> Tweet(*status*{:.text})
: Sends a Tweet of the currently logged in user.

{:id="Twitter.TweetWithImage" class="method"} <i/> TweetWithImage(*status*{:.text},*imagePath*{:.text})
: Tweet with Image, Uploaded to Twitter
