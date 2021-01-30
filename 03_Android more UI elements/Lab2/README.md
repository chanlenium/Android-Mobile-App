# UI element application
> Demo link : https://appetize.io/app/b0kxayw9qcu6jd0gbuyk514u74?device=pixel4&scale=75&orientation=portrait&osVersion=10.0

## Objectives
> Familiar with `Relative layout` 
> Simple design UI elements `radiobutton`, `checkbox`
> Cutomize UI elements by appying style
> Print text message according to user selection (Handle & implement event listener)

## Outline
> Design UI interface using `relative layout`
>> To set relatvie layout, `active_main.xml` is edited as
>> ```
    <?xml version="1.0" encoding="utf-8"?>
    <!-- Set Relative Layout -->
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    ```

> Use radio buttons for optional sets that are mutually exclusive (Must group them together inside a *RadioGroup*)
> Use checkboxes to select one or more options from a set (Checkbox is managed separately and you must register a click listener for each)
> To change color once clicking UI elements, *style* components are added in `style.xml` and refer it to each element
> Using user selection information, the App displays *order detail* message
>> If any elements are not selected, user is asked to select the unselected elements

## Screenshot
<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/02_Views%20and%20Activities/MyWorkshop01/screenshot.png" width="600" height="300" />


 
