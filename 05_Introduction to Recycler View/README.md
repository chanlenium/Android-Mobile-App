# Recycler View

## Topics
>* Recycler View
>* Adapter class
>* View Holder class

## Contents
* Recyler view : composed of views (i.e., row items)
* Layout Manager : arrange each row in the Recyler view
* Adapter : Manage data, Responsible for making a view(each row)
* views (each row items) : managed by *ViewHolder* which is a subclass in Adapater class
    * After making *ViewHolder* obejct in *onCreateViewHolder* method, 
    * *Adapter* binds data with that ViewHolder object using *onBindViewHolder* method 
* Model(data) : actual data

<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/05_Introduction%20to%20Recycler%20View/recyclerView.JPG" width="900" height="350" />
