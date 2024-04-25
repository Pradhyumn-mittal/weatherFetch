# WeatherFetch

Welcome to WeatherFetch - your ultimate solution for fetching weather information based on pincode! This Maven-built project, powered by Spring Boot and Java 17, offers a robust platform for seamless weather data retrieval.

## Key Features

- **Single REST API Endpoint**: Retrieve weather information for a specific day and pincode.
- **Google Maps Integration**: Convert pincode to latitude and longitude using Google Maps Geocoding API. WeatherFetch stores longitude and latitude, reducing calls to the GeoCoding API.
- **OpenWeather Integration**: Fetch weather information based on latitude and longitude from OpenWeather API.
- **Caching Mechanism**: Optimize API calls by caching pincode details and weather information.
- **Database Storage**: Store pincode latitude, longitude, and weather information in a relational database.
- **Kafka Integration**: Consume pincode details from PinMapper project and store them in the database.
- **Swagger Documentation**: Explore and test the API effortlessly with interactive Swagger documentation.
- **Test-Driven Development**: Ensure reliability and stability with comprehensive JUnit test coverage.

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/Pradhyumn-mittal/weatherFetch.git
   ```

2. **Navigate to the project directory**:

   ```bash
   cd weatherFetch
   ```

3. **Build the project**:

   ```bash
   mvn clean install
   ```

4. **Configure environment variables**:

   - `OPEN_WEATHER_API_KEY`: Your OpenWeather API key.
   - `GOOGLE_MAPS_API_KEY`: Your Google Maps API key.
   - Configure database connection details in `application.properties`.

5. **Run the application**:

   ```bash
   java -jar target/weather-fetch-0.0.1-SNAPSHOT.jar
   ```

6. **Access Swagger documentation**: Open your browser and navigate to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Kafka Consumer

WeatherFetch integrates with the PinMapper project by consuming pincode details from Kafka and storing them in the database. The Kafka consumer listens to a specific topic where PinMapper publishes pincode details.

## Required Documentation

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [MongoDB Java Driver Documentation](https://mongodb.github.io/mongo-java-driver/)
- [Redisson Documentation](https://redisson.org/documentation.html)
- [OpenWeather API Documentation](https://openweathermap.org/api)
- [Google Maps Geocoding API Documentation](https://developers.google.com/maps/documentation/geocoding/start)

For further assistance or inquiries, feel free to contact us at Pradhyumn.work@gmail.com!

Let WeatherFetch be your guide to explore the world of weather effortlessly. Happy fetching! 🌟
