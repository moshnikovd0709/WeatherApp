package com.example.weatherapplication

import com.squareup.moshi.Json

data class DailyForecasts(
    @Json(name = "DailyForecasts") val dailyForecasts: List<DailyForecast>
)

data class DailyForecast(
    @Json(name = "Temperature") val temperature: Temperature,
    @Json(name = "RealFeelTemperature") val rftemperature: Temperature,
    @Json(name = "Day") val day: Day
)

data class Day(
    @Json(name = "PrecipitationProbability") val precipitation: Double,
    @Json(name = "Wind") val wind: Wind
)

data class Wind(
    @Json(name = "Speed") val speed: Speed
)

data class Speed(
    @Json(name = "Value") val value: Double
)

data class Temperature(
    @Json(name = "Minimum") val min: Minimum,
    @Json(name = "Maximum") val max: Maximum
)

data class Minimum(
    @Json(name = "Value") val value: Double
)

data class Maximum(
    @Json(name = "Value") val value: Double
)