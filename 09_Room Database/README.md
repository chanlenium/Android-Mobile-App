# Room Database and Background Task

## Topics
>* Room database : Entity class, Data access object class, Database class
>* Android Threads : Main Thread, Running background task 

## Contents
* Using a `Database` in android
    * Android offer SQLite database
    * SQLite is an open-source SQL database that stores data to a text file on a device

* `Room` is a persistence library providing an abstraction layer over SQLite
    * `Room` allows programmer to define an object model(`Entity class`) and SQL queries he/she want to execute(`Dao` class)
    * Room API will create database and implements the boilerplate Data Access Objects(`DAO`) classes.
    * `Entity` Model class represents a table within the database
    * `Dao` class is used to define queries to *write* data to database, *retrieve* data from databases, *delete* and *update* queries 
    * `Database` class is an abstract class that extends RoomDatabase and contains the database holder

* `Threads` is independent sequences of execution
    * Each thread is a separate sequential flow of control within the overall program
    * When we start the application, the system creates a thread of execution called main thread also known as `UI Thread`
    * `MainThread` handles all interactions with the App UI elements, updating the state and their look on the device screen

* Time-consuming tasks that should be handled on a background thread
    * Network communications
    * Debase operations
    * Input and output file operations
    * Image and video processing
    * Complex math calculations
