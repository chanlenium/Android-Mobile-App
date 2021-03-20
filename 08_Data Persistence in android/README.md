# Data Persistence

## Topics
>* Reading and writing to files on the device
>* Different storage options (Internal storage, External storage, Shared preferences)

## Contents
* Storing data *permanently* so that it can be retained if the device is shutdown
    * Files on the device, key-value pairs(Shared preferences), Database, and Networking(remote server)
* Android provides two types of physical storage locations: 
    * internal storage (smaller in size, more reliable place)
    * external storage (Removable volumes such as an SD card) 
* Internal Storage
    * to store sensitive information that other apps shouldn't access
    * Stored in the application’s sandbox
    * Files are deleted along with your app when it is uninstalled
    * No permissions required for reading/writing files her
* External Storage
    * External to your application’s sandbox
    * Android defines the storage-related permissions to access any file outside the app-specific directories on external storage
    * `READ_EXTERNAL_STORAGE`, `WRITE_EXTERNAL_STORAGE`, `MANAGE_EXTERNAL_STORAGE`
* Shared Preferences
    * Allow us to store and retrieve data in key/value pair
    * Each value has its own key for storage and retrieval of that data
