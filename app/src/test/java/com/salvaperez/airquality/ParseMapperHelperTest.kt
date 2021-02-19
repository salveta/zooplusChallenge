package com.salvaperez.airquality

import com.salvaperez.airquality.presentation.extensions.ParseMapperHelper
import org.assertj.core.api.Assertions
import org.junit.Test

class ParseMapperHelperTest {

    @Test
    fun `parse decimal`() {
        val value = 23.352352345345
        val result = ParseMapperHelper().parseDecimal(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("23.35")
    }

    @Test
    fun `parse get time`() {
        val value = "1613268000"
        val result = ParseMapperHelper().getDateTime(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("14/02/21")
    }

    @Test
    fun `parse get hour date time`() {
        val value = "1613268000"
        val result = ParseMapperHelper().getHourDateTime(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("03:00")
    }

    @Test
    fun `parse get quality good`() {
        val value = 1
        val result = ParseMapperHelper().parseAirQuality(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("Good")
    }

    @Test
    fun `parse get quality Fair`() {
        val value = 2
        val result = ParseMapperHelper().parseAirQuality(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("Fair")
    }

    @Test
    fun `parse get quality Moderate`() {
        val value = 3
        val result = ParseMapperHelper().parseAirQuality(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("Moderate")
    }

    @Test
    fun `parse get quality Poor`() {
        val value = 4
        val result = ParseMapperHelper().parseAirQuality(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("Poor")
    }

    @Test
    fun `parse get quality Very Poor`() {
        val value = 5
        val result = ParseMapperHelper().parseAirQuality(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("Very Poor")
    }

    @Test
    fun `parse get quality return not matched`() {
        val value = 6
        val result = ParseMapperHelper().parseAirQuality(value)
        Assertions.assertThat(result).isEqualToIgnoringCase("Not matched")
    }
}