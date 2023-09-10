package com.flight.flight_system.Data;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightData {

    /**
     * Get current time
     * @return time
     */
    public String getCurrentTime() {
        // Get the current time in the system's default time zone
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return now.format(formatter);
    }

    /**
     * Get current weather information
     * using openweathermap API
     *
     * @return weather
     */
    private String getWeatherData() {
        String apiKey = "key";
        String city = "city";
        String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        try {
            URL url = new URL(weatherUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray weatherArray = jsonObject.getJSONArray("weather");
                if (weatherArray.length() > 0) {
                    JSONObject weatherData = weatherArray.getJSONObject(0);
                    String weatherDescription = weatherData.getString("description");
                    return weatherDescription;
                } else {
                    // Weather data not found in the response
                    return "Unknown weather";
                }
            } else {
                // Error response from the API
                System.err.println("Error: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error fetching weather";
    }
}
