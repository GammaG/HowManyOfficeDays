package com.howManyOfficeDays

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.howManyOfficeDays.controller.TimeController
import com.howManyOfficeDays.model.DaoMaster
import com.howManyOfficeDays.model.DaoMaster.DevOpenHelper
import com.howManyOfficeDays.model.DaoSession

class MainActivity : AppCompatActivity() {
    private var timeEditText: EditText? = null
    private var addHourButton: Button? = null
    private var reduceHourButton: Button? = null
    private var addQuarterButton: Button? = null
    private var reduceQuarterButton: Button? = null
    private var addFiveMinutesButton: Button? = null
    private var reduceFiveMinutesButton: Button? = null
    private var mDaoSession: DaoSession? = null
    private var timeController: TimeController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // initializeUIElements()
       // setUpGreenDaoSession()
       // setUpController()
    }

    private fun initializeUIElements() {
       // timeEditText = findViewById(R.id.timeEditText)
        // addHourButton = findViewById(R.id.addHourButton)
        // reduceHourButton = findViewById(R.id.reduceHourButton)
        // addQuarterButton = findViewById(R.id.addQuarterButton)
        //reduceQuarterButton = findViewById(R.id.reduceQuarterButton)
        //reduceFiveMinutesButton = findViewById(R.id.reduceFiveMinutesButton)
        //addFiveMinutesButton = findViewById(R.id.addFiveMinutesButton)
    }

    private fun setUpGreenDaoSession() {
        try {
            mDaoSession = DaoMaster(
                DevOpenHelper(this, "overtime.db").writableDb
            ).newSession()
        } catch (ignored: Exception) {
        }
    }

    private fun setUpController() {
        timeController = mDaoSession?.let { timeEditText?.let { it1 -> TimeController(it, it1) } }
        addHourButton!!.setOnClickListener { timeController!!.addOneHour() }
        reduceHourButton!!.setOnClickListener { timeController!!.reduceOneHour() }
        addQuarterButton!!.setOnClickListener { timeController!!.addQuarter() }
        reduceQuarterButton!!.setOnClickListener { timeController!!.reduceQuarter() }
        addFiveMinutesButton!!.setOnClickListener { timeController!!.addFiveMinutes() }
        reduceFiveMinutesButton!!.setOnClickListener { timeController!!.reduceFiveMinutes() }
    }
}
