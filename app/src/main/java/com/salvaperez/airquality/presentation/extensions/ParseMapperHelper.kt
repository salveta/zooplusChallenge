package com.salvaperez.airquality.presentation.extensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class ParseMapperHelper {

    fun parseDecimal(value: Double): String {
        val decimal = BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN)
        return decimal.toString()
    }

    fun getDateTime(s: String): String {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yy")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun getHourDateTime(s: String): String {
        return try {
            val sdf = SimpleDateFormat("HH:ss")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun parseAirQuality(date: Int): String {
        when (date) {
            1 -> {
                return "Good"
            }
            2 -> {
                return "Fair"
            }
            3 -> {
                return "Moderate"
            }
            4 -> {
                return "Poor"
            }
            5 -> {
                return "Very Poor"
            }
            else -> {
                return "Not matched"
            }
        }
    }
}