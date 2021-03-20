# Fragments

## Topics
>* Fragment Life Cycle
>* Adding Fragment
>* Communication between `Activity` and `Fragment`
>* ViewPager2 to display in a swipeable format

## Contents
* Activity contains `View` (e.g., TextView, ImageView) and `View Group` (e.g., Linear Layout, Relative Layout)
    * Activity may contain more than one fragment
* Fragment is a *modular* section of an activity (or activity's sub view)
    * flexible UI designs
    * has its own lifecycle
    * diectly affected by host activity's lifecycle
    * can be added or removed while the activity is running
    * can be declared in layout XML using `<fragment>` element
<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/07_Fragments/fragmentLifeCycle.jpg" />

* It is recommended that 3 callback function should be implemented
    * `onCreate()`, `onCreateView()`, `onPause()`
<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/07_Fragments/callback.JPG" width="500" height="300" />

* ViewPager2 displays Views or Fragments in a swipeable format
    * Add `ViewPagerAdapter` which extends `FragmentStateAdapter`
