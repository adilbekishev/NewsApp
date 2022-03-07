package dev.mohito.news.news_api

import android.annotation.SuppressLint
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalSerializationApi
@Serializer(forClass = Date::class)
internal object DateSerializer : KSerializer<Date?> {
    @SuppressLint("SimpleDateFormat")
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

    override fun deserialize(decoder: Decoder): Date? {
        return df.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Date?) {
        if (value == null) encoder.encodeNull()
        else encoder.encodeString(df.format(value))
    }
}