---
layout: documentation
title: Media
---

[&laquo; Back to index](index.html)
# Media

Table of Contents:

* [Camcorder](#Camcorder)
* [Camera](#Camera)
* [ImagePicker](#ImagePicker)
* [Player](#Player)
* [Sound](#Sound)
* [SoundRecorder](#SoundRecorder)
* [SpeechRecognizer](#SpeechRecognizer)
* [TextToSpeech](#TextToSpeech)
* [VideoPlayer](#VideoPlayer)
* [YandexTranslate](#YandexTranslate)

## Camcorder  {#Camcorder}

Camcorder provides access to the phone's camcorder



### Events  {#Camcorder-Events}

{:.events}

{:id="Camcorder.AfterRecording"} AfterRecording(*clip*{:.text})
: Indicates that a video was recorded with the camera and provides the path to
 the stored picture.

### Methods  {#Camcorder-Methods}

{:.methods}

{:id="Camcorder.RecordVideo" class="method"} <i/> RecordVideo()
: Records a video, then raises the AfterRecoding event.

## Camera  {#Camera}

Camera provides access to the phone's camera



### Properties  {#Camera-Properties}

{:.properties}

{:id="Camera.UseFront" .boolean .bo} *UseFront*
: Returns true if the front-facing camera is to be used (when available)

### Events  {#Camera-Events}

{:.events}

{:id="Camera.AfterPicture"} AfterPicture(*image*{:.text})
: Indicates that a photo was taken with the camera and provides the path to
 the stored picture.

### Methods  {#Camera-Methods}

{:.methods}

{:id="Camera.TakePicture" class="method"} <i/> TakePicture()
: Takes a picture, then raises the AfterPicture event.
 If useFront is true, adds an extra to the intent that requests the front-facing camera.

## ImagePicker  {#ImagePicker}

Component enabling a user to select an image from the phone's gallery.



### Properties  {#ImagePicker-Properties}

{:.properties}

{:id="ImagePicker.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer.

{:id="ImagePicker.Enabled" .boolean} *Enabled*
: Returns true if the ImagePicker is active and clickable.

{:id="ImagePicker.FontBold" .boolean} *FontBold*
: Returns true if the text of the ImagePicker should be bold.
 If bold has been requested, this property will return true, even if the
 font does not support bold.

{:id="ImagePicker.FontItalic" .boolean} *FontItalic*
: Returns true if the text of the ImagePicker should be italic.
 If italic has been requested, this property will return true, even if the
 font does not support italic.

{:id="ImagePicker.FontSize" .number} *FontSize*
: Returns the text font size of the ImagePicker, measured in sp(scale-independent pixels).

{:id="ImagePicker.FontTypeface" .number .do} *FontTypeface*
: Returns the text font face of the ImagePicker as default, serif, sans
 serif, or monospace.

{:id="ImagePicker.Height" .number .bo} *Height*
: Specifies the vertical height of the ImagePicker, measured in pixels.

{:id="ImagePicker.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the ImagePicker as a percentage of the height of the Screen.

{:id="ImagePicker.Image" .text} *Image*
: Returns the path of the button's image.

{:id="ImagePicker.Selection" .text .ro .bo} *Selection*
: Path to the file containing the image that was selected.

{:id="ImagePicker.Shape" .number .do} *Shape*
: Returns the style of the button.

{:id="ImagePicker.ShowFeedback" .boolean} *ShowFeedback*
: Specifies if a visual feedback should be shown when a ImagePicker with an assigned image
 is pressed.

{:id="ImagePicker.Text" .text} *Text*
: Returns the text displayed by the ImagePicker.

{:id="ImagePicker.TextAlignment" .number .do} *TextAlignment*
: Returns the alignment of the button's text: center, normal
 (e.g., left-justified if text is written left to right), or
 opposite (e.g., right-justified if text is written left to right).

{:id="ImagePicker.TextColor" .color} *TextColor*
: Returns the text color of the ImagePicker as an alpha-red-green-blue
 integer.

{:id="ImagePicker.Visible" .boolean} *Visible*
: Returns true iff the ImagePicker is visible.

{:id="ImagePicker.Width" .number .bo} *Width*
: Specifies the horizontal width of the ImagePicker, measured in pixels.

{:id="ImagePicker.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the ImagePicker as a percentage of the width of the Screen.

### Events  {#ImagePicker-Events}

{:.events}

{:id="ImagePicker.AfterPicking"} AfterPicking()
: Event to be raised after the picker activity returns its
 result and the properties have been filled in.

{:id="ImagePicker.BeforePicking"} BeforePicking()
: Event to raise when the ImagePicker is clicked or the list is shown
 using the Open block.  This event occurs before the list of items is displayed, and 
 can be used to prepare the list before it is shown.

{:id="ImagePicker.GotFocus"} GotFocus()
: Indicates the cursor moved over the button so it is now possible
 to click it.

{:id="ImagePicker.LostFocus"} LostFocus()
: Indicates the cursor moved away from the button so it is now no
 longer possible to click it.

{:id="ImagePicker.TouchDown"} TouchDown()
: Indicates when a button is touch down

{:id="ImagePicker.TouchUp"} TouchUp()
: Indicates when a button touch ends

### Methods  {#ImagePicker-Methods}

{:.methods}

{:id="ImagePicker.Open" class="method"} <i/> Open()
: Opens the ImagePicker, as though the user clicked on it.

## Player  {#Player}

Multimedia component that plays audio and optionally
 vibrates.  It is built on top of {



### Properties  {#Player-Properties}

{:.properties}

{:id="Player.IsPlaying" .boolean .ro .bo} *IsPlaying*
: Reports whether the media is playing.

{:id="Player.Loop" .boolean} *Loop*
: Reports whether the playing should loop.

{:id="Player.PlayOnlyInForeground" .boolean} *PlayOnlyInForeground*
: Gets the policy whether playing should only work in foreground.

{:id="Player.Source" .text} *Source*
: Returns the path to the audio source

{:id="Player.Volume" .number .wo} *Volume*
: Sets the volume property to a number between 0 and 100.

### Events  {#Player-Events}

{:.events}

{:id="Player.Completed"} Completed()
: Indicates that the media has reached the end

{:id="Player.OtherPlayerStarted"} OtherPlayerStarted()
: Indicates that the other player has requested the focus of media

### Methods  {#Player-Methods}

{:.methods}

{:id="Player.Pause" class="method"} <i/> Pause()
: Suspends playing the media if it is playing.

{:id="Player.Start" class="method"} <i/> Start()
: Plays the media.  If it was previously paused, the playing is resumed.
 If it was previously stopped, it starts from the beginning.

{:id="Player.Stop" class="method"} <i/> Stop()
: Stops playing the media and seeks to the beginning of the song.

{:id="Player.Vibrate" class="method"} <i/> Vibrate(*milliseconds*{:.number})
: Vibrates for specified number of milliseconds.

## Sound  {#Sound}

Multimedia component that plays sounds and optionally vibrates.  A
 sound is specified via filename.  See also
 {



### Properties  {#Sound-Properties}

{:.properties}

{:id="Sound.MinimumInterval" .number} *MinimumInterval*
: Returns the minimum interval required between calls to Play(), in
 milliseconds.
 Once the sound starts playing, all further Play() calls will be ignored
 until the interval has elapsed.

{:id="Sound.Source" .text} *Source*
: Returns the sound's filename.

### Events  {#Sound-Events}

{:.events}

### Methods  {#Sound-Methods}

{:.methods}

{:id="Sound.Pause" class="method"} <i/> Pause()
: Pauses playing the sound if it is being played.

{:id="Sound.Play" class="method"} <i/> Play()
: Plays the sound.

{:id="Sound.Resume" class="method"} <i/> Resume()
: Resumes playing the sound after a pause.

{:id="Sound.Stop" class="method"} <i/> Stop()
: Stops playing the sound if it is being played.

{:id="Sound.Vibrate" class="method"} <i/> Vibrate(*millisecs*{:.number})
: Vibrates for the specified number of milliseconds.

## SoundRecorder  {#SoundRecorder}

Multimedia component that records audio using
 {



### Properties  {#SoundRecorder-Properties}

{:.properties}

{:id="SoundRecorder.SavedRecording" .text} *SavedRecording*
: Returns the path to the saved recording

### Events  {#SoundRecorder-Events}

{:.events}

{:id="SoundRecorder.AfterSoundRecorded"} AfterSoundRecorded(*sound*{:.text})
: Provides the location of the newly created sound.

{:id="SoundRecorder.StartedRecording"} StartedRecording()
: Indicates that the recorder has started, and can be stopped.

{:id="SoundRecorder.StoppedRecording"} StoppedRecording()
: Indicates that the recorder has stopped, and can be started again.

### Methods  {#SoundRecorder-Methods}

{:.methods}

{:id="SoundRecorder.Start" class="method"} <i/> Start()
: Starts recording.

{:id="SoundRecorder.Stop" class="method"} <i/> Stop()
: Stops recording.

## SpeechRecognizer  {#SpeechRecognizer}

Component for using the built in VoiceRecognizer to convert speech to text.
 For more details, please see:
 http://developer.android.com/reference/android/speech/RecognizerIntent.html



### Properties  {#SpeechRecognizer-Properties}

{:.properties}

{:id="SpeechRecognizer.Result" .text .ro .bo} *Result*
: Result property getter method.

{:id="SpeechRecognizer.UseLegacy" .boolean} *UseLegacy*
: If true, an app can retain their older behaviour.

### Events  {#SpeechRecognizer-Events}

{:.events}

{:id="SpeechRecognizer.AfterGettingText"} AfterGettingText(*result*{:.text},*partial*{:.boolean})
: Simple event to raise after the VoiceReco activity has returned.
 Partial is 'false' when SpeechRecognizer stops automatically after listening,
 else it is 'true' if SpeechRecognizer returns partial results.

{:id="SpeechRecognizer.BeforeGettingText"} BeforeGettingText()
: Simple event to raise when VoiceReco is invoked but before the VoiceReco
 activity is started.

### Methods  {#SpeechRecognizer-Methods}

{:.methods}

{:id="SpeechRecognizer.GetText" class="method"} <i/> GetText()
: Solicits speech input from the user.  After the speech is converted to
 text, the AfterGettingText event will be raised.

{:id="SpeechRecognizer.Stop" class="method"} <i/> Stop()
: Function used to forcefully stop listening speech in cases where
 SpeechRecognizer cannot stop automatically.
 This function works only when UseLegacy property is set to 'false'.

## TextToSpeech  {#TextToSpeech}

Component for converting text to speech using either the built-in TTS library or the
 TextToSpeech Extended library (which must be pre-installed for Android versions earlier
 than Donut).



### Properties  {#TextToSpeech-Properties}

{:.properties}

{:id="TextToSpeech.AvailableCountries" .list .ro .bo} *AvailableCountries*
: List of the country codes available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.

{:id="TextToSpeech.AvailableLanguages" .list .ro .bo} *AvailableLanguages*
: List of the languages available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.

{:id="TextToSpeech.Country" .text} *Country*
: Sets the country for this TextToSpeech component.

{:id="TextToSpeech.Language" .text} *Language*
: Sets the language for this TextToSpeech component.

{:id="TextToSpeech.Pitch" .number} *Pitch*
: Sets the speech pitch for the TextToSpeech. 1.0 is the normal pitch, lower values lower the tone of
 the synthesized voice, greater values increase it.

{:id="TextToSpeech.Result" .boolean .ro .bo} *Result*
: Result property getter method.

{:id="TextToSpeech.SpeechRate" .number} *SpeechRate*
: Sets the speech rate

### Events  {#TextToSpeech-Events}

{:.events}

{:id="TextToSpeech.AfterSpeaking"} AfterSpeaking(*result*{:.boolean})
: Event to raise after the message is spoken.

{:id="TextToSpeech.BeforeSpeaking"} BeforeSpeaking()
: Event to raise when Speak is invoked, before the message is spoken.

### Methods  {#TextToSpeech-Methods}

{:.methods}

{:id="TextToSpeech.Speak" class="method"} <i/> Speak(*message*{:.text})
: Speaks the given message.

## VideoPlayer  {#VideoPlayer}

Implementation of VideoPlayer, using {



### Properties  {#VideoPlayer-Properties}

{:.properties}

{:id="VideoPlayer.FullScreen" .boolean .bo} *FullScreen*
: Returns whether the VideoPlayer's video is currently being
 shown in fullscreen mode or not.

{:id="VideoPlayer.Height" .number .bo} *Height*
: Specifies the component's vertical height, measured in pixels.

{:id="VideoPlayer.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the VideoPlayer as a percentage of the height of the Screen.

{:id="VideoPlayer.Source" .text .wo} *Source*
: Sets the video source.

 <p/>
 See [MediaUtil's determineMediaSource](#MediaUtil.determineMediaSource) for information about what a
 path can be.

{:id="VideoPlayer.Visible" .boolean} *Visible*
: Returns true iff the VideoPlayer is visible.

{:id="VideoPlayer.Volume" .number .wo} *Volume*
: Sets the volume property to a number between 0 and 100.

{:id="VideoPlayer.Width" .number .bo} *Width*
: Specifies the component's horizontal width, measured in pixels.

{:id="VideoPlayer.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the VideoPlayer as a percentage of the width of the Screen.

### Events  {#VideoPlayer-Events}

{:.events}

{:id="VideoPlayer.Completed"} Completed()
: Indicates that the video has reached the end

### Methods  {#VideoPlayer-Methods}

{:.methods}

{:id="VideoPlayer.GetDuration" class="method returns number"} <i/> GetDuration()
: Returns duration of the video in milliseconds.

{:id="VideoPlayer.Pause" class="method"} <i/> Pause()
: Pauses playback of the video.  Playback can be resumed at the same location by calling the <code>Start</code> method.

{:id="VideoPlayer.SeekTo" class="method"} <i/> SeekTo(*ms*{:.number})
: Seeks to the requested time (specified in milliseconds) in the video. If the video is paused, the frame shown will not be updated by the seek. The player can jump only to key frames in the video, so seeking to times that differ by short intervals may not actually move to different frames.

{:id="VideoPlayer.Start" class="method"} <i/> Start()
: Plays the media specified by the source. These won't normally be used in
 the most elementary applications, because videoView brings up its own
 player controls when the video is touched.

{:id="VideoPlayer.Stop" class="method"} <i/> Stop()
: Resets to start of video and pauses it if video was playing.

## YandexTranslate  {#YandexTranslate}

Component for YandexTranslate



### Events  {#YandexTranslate-Events}

{:.events}

{:id="YandexTranslate.GotTranslation"} GotTranslation(*responseCode*{:.text},*translation*{:.text})
: Event indicating that a request has finished and has returned data (translation).

### Methods  {#YandexTranslate-Methods}

{:.methods}

{:id="YandexTranslate.RequestTranslation" class="method"} <i/> RequestTranslation(*languageToTranslateTo*{:.text},*textToTranslate*{:.text})
: Performs an HTTP GET request to the Yandex.Translate service
