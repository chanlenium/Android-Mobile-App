# UI element application
> Demo link : https://appetize.io/app/b0kxayw9qcu6jd0gbuyk514u74?device=pixel4&scale=75&orientation=portrait&osVersion=10.0

## Objectives
> Familiar with `Relative layout` 
> Simple design UI elements `radiobutton`, `checkbox`
> Cutomize UI elements by appying style
> Print text message according to user selection (Handle & implement event listener)

## Outline
> Design UI interface using `relative layout`
    > To set relatvie layout, `active_main.xml` is edited as
    ```
    <?xml version="1.0" encoding="utf-8"?>
    <!-- Set Relative Layout -->
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    ```

>* When the user input is validated, the app displays welcome message *"Hi, Last Fisrt name"* and prints user first/last name in *logcat*.
>* When the user input is invalid (i.e., both first and last nameas are empty), the app displays message *"Hi, there"* and sends *toast message*.
>* User is constrainted to input letters only in the text editor using `android:digits="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ "`.

## Screenshot
<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/02_Views%20and%20Activities/MyWorkshop01/screenshot.png" width="600" height="300" />


 
