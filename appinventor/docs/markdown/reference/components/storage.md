---
layout: documentation
title: Storage
---

[&laquo; Back to index](index.html)
# Storage

Table of Contents:

* [CloudDB](#CloudDB)
* [File](#File)
* [TinyDB](#TinyDB)
* [TinyWebDB](#TinyWebDB)

## CloudDB  {#CloudDB}

The CloudDB component stores and retrieves information in the Cloud using Redis, an
 open source library. The component has methods to store a value under a tag and to
 retrieve the value associated with the tag. It also possesses a listener to fire events
 when stored values are changed. It also posseses a sync capability which helps CloudDB
 to sync with data collected offline.



### Properties  {#CloudDB-Properties}

{:.properties}

{:id="CloudDB.DefaultRedisServer" .text .wo .do} *DefaultRedisServer*
: The Default Redis Server to use.

{:id="CloudDB.ProjectID" .text .ro} *ProjectID*
: Getter for the ProjectID.

{:id="CloudDB.RedisPort" .number .ro} *RedisPort*
: The Redis Server port to use. Defaults to 6381

{:id="CloudDB.RedisServer" .text .ro} *RedisServer*
: The Redis Server to use to store data. A setting of "DEFAULT" means that the MIT server will be used.

{:id="CloudDB.Token" .text .ro .do} *Token*
: Getter for the authTokenSignature.

{:id="CloudDB.UseSSL" .boolean .ro .do} *UseSSL*
: Set to true to use SSL to talk to CloudDB/Redis server. This should be set to True for the "DEFAULT" server.

### Events  {#CloudDB-Events}

{:.events}

{:id="CloudDB.CloudDBError"} CloudDBError(*message*{:.text})
: Indicates that the communication with the CloudDB signaled an error.

{:id="CloudDB.DataChanged"} DataChanged(*tag*{:.text},*value*{:.any})
: Indicates that the data in the CloudDB project has changed.
 Launches an event with the tag and value that have been updated.

{:id="CloudDB.FirstRemoved"} FirstRemoved(*value*{:.any})
: Event triggered by the "RemoveFirstFromList" function. The argument "value" is the object that was the first in the list, and which is now removed.

{:id="CloudDB.GotValue"} GotValue(*tag*{:.text},*value*{:.any})
: Indicates that a GetValue request has succeeded.

{:id="CloudDB.TagList"} TagList(*value*{:.list})
: Indicates that a GetTagList request has succeeded.

### Methods  {#CloudDB-Methods}

{:.methods}

{:id="CloudDB.AppendValueToList" class="method"} <i/> AppendValueToList(*tag*{:.text},*itemToAdd*{:.any})
: Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.

{:id="CloudDB.ClearTag" class="method"} <i/> ClearTag(*tag*{:.text})
: Asks CloudDB to forget (delete or set to "null") a given tag.

{:id="CloudDB.CloudConnected" class="method returns boolean"} <i/> CloudConnected()
: returns True if we are on the network and will likely be able to connect to the CloudDB server.

{:id="CloudDB.GetTagList" class="method"} <i/> GetTagList()
: GetTagList asks CloudDB to retrieve all the tags belonging to this project.

 The resulting list is returned in GotTagList

{:id="CloudDB.GetValue" class="method"} <i/> GetValue(*tag*{:.text},*valueIfTagNotThere*{:.any})
: GetValue asks CloudDB to get the value stored under the given tag.
 It will pass valueIfTagNotThere to GotValue if there is no value stored
 under the tag.

{:id="CloudDB.RemoveFirstFromList" class="method"} <i/> RemoveFirstFromList(*tag*{:.text})
: Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the "FirstRemoved" event will be triggered.

{:id="CloudDB.StoreValue" class="method"} <i/> StoreValue(*tag*{:.text},*valueToStore*{:.any})
: Asks CloudDB to store the given value under the given tag.

## File  {#File}

A Component for working with files and directories on the device.



### Events  {#File-Events}

{:.events}

{:id="File.AfterFileSaved"} AfterFileSaved(*fileName*{:.text})
: Event indicating that a request has finished.

{:id="File.GotText"} GotText(*text*{:.text})
: Event indicating that a request has finished.

### Methods  {#File-Methods}

{:.methods}

{:id="File.AppendToFile" class="method"} <i/> AppendToFile(*text*{:.text},*fileName*{:.text})
: Appends text to a specified file on the phone.
 Calls the Write function to write to the file asynchronously to prevent
 the UI from hanging when there is a large write.

{:id="File.Delete" class="method"} <i/> Delete(*fileName*{:.text})
: Delete the specified file.

{:id="File.ReadFrom" class="method"} <i/> ReadFrom(*fileName*{:.text})
: Retrieve the text stored in a specified file.

{:id="File.SaveFile" class="method"} <i/> SaveFile(*text*{:.text},*fileName*{:.text})
: Stores the text to a specified file on the phone.
 Calls the Write function to write to the file asynchronously to prevent
 the UI from hanging when there is a large write.

## TinyDB  {#TinyDB}

Persistently store YAIL values on the phone using tags to store and retrieve.



### Properties  {#TinyDB-Properties}

{:.properties}

{:id="TinyDB.Namespace" .text} *Namespace*
: Namespace for storing data.

### Methods  {#TinyDB-Methods}

{:.methods}

{:id="TinyDB.ClearAll" class="method"} <i/> ClearAll()
: Clear the entire data store

{:id="TinyDB.ClearTag" class="method"} <i/> ClearTag(*tag*{:.text})
: Clear the entry with the given tag

{:id="TinyDB.GetTags" class="method returns any"} <i/> GetTags()
: Return a list of all the tags in the data store

{:id="TinyDB.GetValue" class="method returns any"} <i/> GetValue(*tag*{:.text},*valueIfTagNotThere*{:.any})
: Retrieve the value stored under the given tag.  If there's no such tag, then return valueIfTagNotThere.

{:id="TinyDB.StoreValue" class="method"} <i/> StoreValue(*tag*{:.text},*valueToStore*{:.any})
: Store the given value under the given tag.  The storage persists on the
 phone when the app is restarted.

## TinyWebDB  {#TinyWebDB}

The TinyWebDB component communicates with a Web service to store
 and retrieve information.  Although this component is usable, it is
 very limited and meant primarily as a demonstration for people who
 would like to create their own components that talk to the Web.
 The accompanying Web service is at
 (http://tinywebdb.appinventor.mit.edu).  The component has methods to
 store a value under a tag and to retrieve the value associated with
 the tag.  The interpretation of what "store" and "retrieve" means
 is up to the Web service.  In this implementation, all tags and
 values are strings (text).  This restriction may be relaxed in
 future versions.



### Properties  {#TinyWebDB-Properties}

{:.properties}

{:id="TinyWebDB.ServiceURL" .text} *ServiceURL*
: Returns the URL of the web service database.

### Events  {#TinyWebDB-Events}

{:.events}

{:id="TinyWebDB.GotValue"} GotValue(*tagFromWebDB*{:.text},*valueFromWebDB*{:.any})
: Indicates that a GetValue server request has succeeded.

{:id="TinyWebDB.ValueStored"} ValueStored()
: Event indicating that a StoreValue server request has succeeded.

{:id="TinyWebDB.WebServiceError"} WebServiceError(*message*{:.text})
: Indicates that the communication with the Web service signaled an error

### Methods  {#TinyWebDB-Methods}

{:.methods}

{:id="TinyWebDB.GetValue" class="method"} <i/> GetValue(*tag*{:.text})
: GetValue asks the Web service to get the value stored under the given tag.
 It is up to the Web service what to return if there is no value stored
 under the tag.  This component just accepts whatever is returned.

{:id="TinyWebDB.StoreValue" class="method"} <i/> StoreValue(*tag*{:.text},*valueToStore*{:.any})
: Asks the Web service to store the given value under the given tag
