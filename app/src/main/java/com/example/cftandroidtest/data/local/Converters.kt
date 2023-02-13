package com.example.cftandroidtest.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.cftandroidtest.domain.model.Bank
import com.example.cftandroidtest.domain.model.Country
import com.example.cftandroidtest.domain.model.Number
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromBank(bank: Bank): String? {
        return moshi.adapter(Bank::class.java).toJson(bank)
    }

    @TypeConverter
    fun toBank(bankString: String): Bank? {
        return moshi.adapter(Bank::class.java).fromJson(bankString)
    }

    @TypeConverter
    fun fromCountry(country: Country): String? {
        return moshi.adapter(Country::class.java).toJson(country)
    }

    @TypeConverter
    fun toCountry(countryString: String): Country? {
        return moshi.adapter(Country::class.java).fromJson(countryString)
    }

    @TypeConverter
    fun fromNumber(number: Number): String? {
        return moshi.adapter(Number::class.java).toJson(number)
    }

    @TypeConverter
    fun toNumber(numberString: String): Number? {
        return moshi.adapter(Number::class.java).fromJson(numberString)
    }
}