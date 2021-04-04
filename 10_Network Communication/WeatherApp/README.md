## Weather App (Networking in android)
* Demo link : https://appetize.io/app/ku41j29vekvag40a6kabx8crqw?device=nexus5&scale=75&orientation=portrait&osVersion=8.1

## Objectives
* Retrieving data from a server and displaying it
* Using JSON /JSON Array
* Using bachground threads
* Familiar with `Fragment` and `ViewModel` (`LiveData`)

## Outline
* The App is composed of `Model`, `Fragment`, and `MainActivity` (Network control)
### Model
* Implement `Weather` and `WeatherViewModel`
    * Implements `Parcelable` interface for data serialization 
```
public class WeatherModel implements Parcelable {
    String cityName;
    String weatherIcon;
    String weatherDescription;
    int weatherTemperature;
    Float latitude;
    Float longitude;

    // constructor
    // getter or setter
```
* Create `ViewModel` class by extending ViewModel to use `LiveData`
```
public class WeatherViewModel extends ViewModel {
    public MutableLiveData<WeatherModel> weatherViewModel = new MutableLiveData<>();   // reference to LiveData

    public void setWeatherViewModel(WeatherModel input) {
        this.weatherViewModel.setValue(input);
    }

    public LiveData<WeatherModel> getWeather(){
        return weatherViewModel;
    }
}
```

### Fragment
* Declare variables and set value using `ViewModel`
```
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_response, container, false);
        // associate fields in this class with components of view(fragment_weather_response.xml)
        cityNameTv = view.findViewById(R.id.cityNameTv);
        temperatureTv = view.findViewById(R.id.temperatureTv);
        weatherDescriptionTv = view.findViewById(R.id.weatherDescriptionTv);
        latitudeTv = view.findViewById(R.id.latitudeTv);
        longitudeTv = view.findViewById(R.id.longitudeTv);
        weatherIv = view.findViewById(R.id.weatherIv);

        // set values using ViewModel
        weatherViewModel.getWeather().observe(this, currentValue ->{
            cityNameTv.setText("The weather of city " + currentValue.getCityName().toUpperCase());
            temperatureTv.setText(currentValue.getWeatherTemperature() + " \u2103");
            weatherDescriptionTv.setText(currentValue.getWeatherDescription());
            latitudeTv.setText(currentValue.getLatitude() + "\u00B0");
            longitudeTv.setText(currentValue.getLongitude() + "\u00B0");
            Glide.with(this)
                .load("https://openweathermap.org/img/w/" + currentValue.getWeatherIcon() + ".png")
                .override(300, 300)
                .into(weatherIv);
        });
        return view;
    }
```

### Networking
* Add permission to manifest file 
```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```
* Check if the device is connected
```
private boolean isConnnected() {
    boolean connectionResult;
    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    if(networkInfo != null && networkInfo.isConnected()){
        connectionResult = true;
    }else{
        connectionResult = false;
    }
    return connectionResult;
}
```
* Get a connection to remote server(url)  and Download the data
```
// Getting good connection and downloading data (Must be done in a background)
URL myUrl = new URL(url);   // convert url string to URL object
HttpURLConnection httpURLConnection = (HttpURLConnection) myUrl.openConnection();
httpURLConnection.setRequestMethod("GET");    // to retrieve information from server
httpURLConnection.connect();
int connectionResponseCode = httpURLConnection.getResponseCode();   // 200: OK, 404 or 500: Error
Log.d(TAG, "downloadData: response code = " + connectionResponseCode);
inputStream = httpURLConnection.getInputStream();
data = processResponse(inputStream); // retrieved data from server
```
* Retrieving data as JSON Object 
```
JSONObject response = new JSONObject(data); // convert String to JSON object
weatherModel = new WeatherModel(
                               cityNameEt.getText().toString().trim(),
                               ((JSONObject) response.getJSONArray("weather").get(0)).getString("icon"),
                               ((JSONObject) response.getJSONArray("weather").get(0)).getString("main"),
                               (int) (Float.parseFloat(response.getJSONObject("main").getString("temp")) -273.15 ),
                               Float.valueOf(response.getJSONObject("coord").getString("lat")),
                               Float.valueOf(response.getJSONObject("coord").getString("lon")));

weatherViewModel.setWeatherViewModel(weatherModel);
fragmentTransaction.add(R.id.weatherDetail, new WeatherResponseFragment(weatherViewModel)).commit();
```

## Screenshots
<img src="https://github.com/chanlenium/Android-Mobile-App/blob/main/10_Network%20Communication/WeatherApp/screenshots.png"> 
