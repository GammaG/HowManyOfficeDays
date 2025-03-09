package com.howManyOfficeDays

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.howManyOfficeDays.greendao.DaoMaster
import com.howManyOfficeDays.greendao.DaoSession

class MainActivity : AppCompatActivity() {

    private var addOfficeDayBtn: Button? = null
    private var editOfficeDays: EditText? = null
    private var reduceOfficeDayBtn: Button? = null
    private var daysLeftTextView: TextView? = null
    private var goalTextView: TextView? = null
    private var addWorkingDayBtn: Button? = null
    private var editWorkingDays: EditText? = null
    private var reduceWorkingDayBtn: Button? = null
    private var addPercentageBtn: Button? = null
    private var editPercentage: EditText? = null
    private var reducePercentage: Button? = null
    private var resetBtn: Button? = null

    private var mDaoSession: DaoSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUIElements()
        setUpGreenDaoSession()
        setUpController()
    }

    private fun initializeUIElements() {
        addOfficeDayBtn = findViewById(R.id.addOfficeDayBtn)
        editOfficeDays = findViewById(R.id.editOfficeDays)
        reduceOfficeDayBtn = findViewById(R.id.reduceOfficeDayBtn)
        daysLeftTextView = findViewById(R.id.daysLeftTextView)
        goalTextView = findViewById(R.id.goalTextview)
        addWorkingDayBtn = findViewById(R.id.addWorkingDayBtn)
        editWorkingDays = findViewById(R.id.editWorkingDays)
        reduceWorkingDayBtn = findViewById(R.id.reduceWorkingDayBtn)
        addPercentageBtn = findViewById(R.id.addPercentageBtn)
        editPercentage = findViewById(R.id.editPercentage)
        reducePercentage = findViewById(R.id.reducePercentage)
        resetBtn = findViewById(R.id.resetBtn)
    }

    private fun setUpGreenDaoSession() {
        try {
            var mDaoSession = DaoMaster(
                DaoMaster.DevOpenHelper(this, "officeDays.db").writableDb
            ).newSession()
        } catch (ignored: Exception) {
        }
    }

    private fun setUpController() {

    }


}
