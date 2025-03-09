package com.howManyOfficeDays

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.howManyOfficeDays.controller.TrackingDaoController
import com.howManyOfficeDays.greendao.DaoMaster
import com.howManyOfficeDays.greendao.DaoSession


class MainActivity : AppCompatActivity() {

    private lateinit var addOfficeDayBtn: Button
    private lateinit var reduceOfficeDayBtn: Button
    private lateinit var addWorkingDayBtn: Button
    private lateinit var reduceWorkingDayBtn: Button
    private lateinit var addPercentageBtn: Button
    private lateinit var reducePercentageBtn: Button
    private lateinit var resetBtn: Button
    private lateinit var editOfficeDays: EditText
    private lateinit var editWorkingDays: EditText
    private lateinit var editPercentage: EditText

    private lateinit var mDaoSession: DaoSession
    private lateinit var trackingDaoController: TrackingDaoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUIElements()
        setUpGreenDaoSession()
        setUpController()
        setUpListeners()
    }

    private fun initializeUIElements() {
        addOfficeDayBtn = findViewById(R.id.addOfficeDayBtn)
        reduceOfficeDayBtn = findViewById(R.id.reduceOfficeDayBtn)
        addWorkingDayBtn = findViewById(R.id.addWorkingDayBtn)
        reduceWorkingDayBtn = findViewById(R.id.reduceWorkingDayBtn)
        addPercentageBtn = findViewById(R.id.addPercentageBtn)
        reducePercentageBtn = findViewById(R.id.reducePercentageBtn)
        resetBtn = findViewById(R.id.resetBtn)
        editOfficeDays = findViewById(R.id.editOfficeDays)
        editWorkingDays = findViewById(R.id.editWorkingDays)
        editPercentage = findViewById(R.id.editPercentage)

    }

    private fun setUpGreenDaoSession() {
        mDaoSession = DaoMaster(
            DaoMaster.DevOpenHelper(this, "officeDays.db").writableDb
        ).newSession()
    }

    private fun setUpController() {
        val mainView = findViewById<View>(android.R.id.content) // Root view of the activity
        trackingDaoController = TrackingDaoController(mDaoSession, mainView)
    }

    private fun setUpListeners() {
        addOfficeDayBtn.setOnClickListener { trackingDaoController.addOfficeDay() }
        reduceOfficeDayBtn.setOnClickListener { trackingDaoController.reduceOfficeDay() }
        addWorkingDayBtn.setOnClickListener { trackingDaoController.addWorkingDay() }
        reduceWorkingDayBtn.setOnClickListener { trackingDaoController.reduceWorkingDay() }
        addPercentageBtn.setOnClickListener { trackingDaoController.addPercentageGoal() }
        reducePercentageBtn.setOnClickListener { trackingDaoController.reducePercentageGoal() }
        resetBtn.setOnClickListener { trackingDaoController.reset() }

        editOfficeDays.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val inputText = editOfficeDays.text.toString()
                val number: Int = inputText.toIntOrNull() ?: 0
                trackingDaoController.updateOfficeDays(number)
                true
            } else {
                false
            }
        }

        editWorkingDays.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val inputText = editWorkingDays.text.toString()
                val number: Int = inputText.toIntOrNull() ?: 0
                trackingDaoController.updateWorkingDays(number)
                true
            } else {
                false
            }
        }

        editPercentage.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val inputText = editPercentage.text.toString()
                val number: Int = inputText.toIntOrNull() ?: 0
                trackingDaoController.updatePercentageGoal(number)
                true
            } else {
                false
            }
        }

    }

}


