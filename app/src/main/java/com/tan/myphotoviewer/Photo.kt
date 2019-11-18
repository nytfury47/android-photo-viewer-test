package com.tan.myphotoviewer

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Photo(photoJSON: JSONObject) : Serializable {

    private lateinit var photoDate: String
    lateinit var title: String
        private set
    lateinit var humanDate: String
        private set
    lateinit var explanation: String
        private set
    lateinit var url: String
        private set

    init {
        try {
            photoDate = photoJSON.getString(PHOTO_DATE)
            title = photoJSON.getString(PHOTO_TITLE)
            humanDate = convertDateToHumanDate()
            explanation = photoJSON.getString(PHOTO_EXPLANATION)
            url = photoJSON.getString(PHOTO_URL)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun convertDateToHumanDate(): String {
        var humanDate = ""

        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val humanDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val parsedDateFormat = dateFormat.parse(photoDate)
            val cal = Calendar.getInstance()
            cal.time = parsedDateFormat!!
            humanDate = humanDateFormat.format(cal.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return humanDate
    }

    companion object {
        private const val PHOTO_TITLE = "title"
        private const val PHOTO_DATE = "date"
        private const val PHOTO_EXPLANATION = "explanation"
        private const val PHOTO_URL = "url"
    }
}