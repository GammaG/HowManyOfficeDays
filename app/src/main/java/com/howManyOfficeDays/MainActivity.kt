package com.howManyOfficeDays

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.howManyOfficeDays.controller.TrackingDaoController
import com.howManyOfficeDays.greendao.DaoMaster
import com.howManyOfficeDays.greendao.DaoSession


class MainActivity : AppCompatActivity() {

    private var addOfficeDayBtn: Button? = null
    private var reduceOfficeDayBtn: Button? = null
    private var addWorkingDayBtn: Button? = null
    private var reduceWorkingDayBtn: Button? = null
    private var addPercentageBtn: Button? = null
    private var reducePercentageBtn: Button? = null
    private var resetBtn: Button? = null

    private lateinit var mDaoSession: DaoSession
    private lateinit var trackingDaoController: TrackingDaoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUIElements()
        setUpGreenDaoSession()
        setUpController()
    }

    private fun initializeUIElements() {
        addOfficeDayBtn = findViewById(R.id.addOfficeDayBtn)
        reduceOfficeDayBtn = findViewById(R.id.reduceOfficeDayBtn)
        addWorkingDayBtn = findViewById(R.id.addWorkingDayBtn)
        reduceWorkingDayBtn = findViewById(R.id.reduceWorkingDayBtn)
        addPercentageBtn = findViewById(R.id.addPercentageBtn)
        reducePercentageBtn = findViewById(R.id.reducePercentageBtn)
        resetBtn = findViewById(R.id.resetBtn)
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
}


