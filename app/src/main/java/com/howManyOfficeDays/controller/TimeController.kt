package com.howManyOfficeDays.controller

import android.annotation.SuppressLint
import android.widget.EditText
import com.howManyOfficeDays.model.DaoSession
import com.howManyOfficeDays.model.Time
import com.howManyOfficeDays.model.TimeDao

class TimeController(private val mDaoSession: DaoSession, private val timeEditText: EditText) {
    private var timeDao: TimeDao? = null
    private var time: Time? = null


    init {
        initialSetup()
    }

    @SuppressLint("SetTextI18n")
    private fun initialSetup() {
        timeDao = mDaoSession.timeDao
        if (timeDao!!.loadAll().isNotEmpty()) {
            time = timeDao!!.loadAll()[0]
            updateTextAndDatabase()
        } else {
            timeEditText.setText("0:00")
            time = Time()
            time!!.hours = 0
            time!!.minutes = 0
            timeDao!!.save(time)
        }
    }


    fun addOneHour() {
        time!!.hours += 1
        updateTextAndDatabase()
    }


    fun reduceOneHour() {
        val hours = time!!.hours
        if (hours == 0) {
            time!!.minutes = 0
        } else time!!.hours -= 1
        updateTextAndDatabase()
    }


    fun addQuarter() {
        var minutes = time!!.minutes
        minutes += 15
        if (minutes >= 60) {
            addOneHour()
            minutes -= 60
        }
        time!!.minutes = minutes
        updateTextAndDatabase()
    }


    fun reduceQuarter() {
        var minutes = time!!.minutes
        minutes -= 15
        if (minutes < 0) {
            if (time!!.hours == 0) minutes = 0
            else {
                reduceOneHour()
                minutes += 60
            }
        }
        time!!.minutes = minutes
        updateTextAndDatabase()
    }


    fun addFiveMinutes() {
        var minutes = time!!.minutes
        minutes += 5
        if (minutes >= 60) {
            addOneHour()
            minutes = 0
        }
        time!!.minutes = minutes
        updateTextAndDatabase()
    }


    fun reduceFiveMinutes() {
        var minutes = time!!.minutes
        minutes -= 5
        if (minutes < 0) {
            if (time!!.hours == 0) minutes = 0
            else {
                reduceOneHour()
                minutes += 60
            }
        }
        time!!.minutes = minutes
        updateTextAndDatabase()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTextAndDatabase() {
        val localText = time!!.hours.toString() + ":" + (time!!.minutes)
        timeEditText.setText(localText)
        timeDao!!.save(time)
    }
}
