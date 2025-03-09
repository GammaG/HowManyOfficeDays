package com.howManyOfficeDays.controller

import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.howManyOfficeDays.R
import com.howManyOfficeDays.greendao.DaoSession
import com.howManyOfficeDays.greendao.TrackingModelDao
import com.howManyOfficeDays.greendao.TrackingModel

class TrackingDaoController(private val mDaoSession: DaoSession, private val view: View) {

    private lateinit var trackingModel: TrackingModel
    private lateinit var trackingModelDao: TrackingModelDao

    //UI Elements
    private lateinit var editOfficeDays: EditText
    private lateinit var daysLeftTextView: TextView
    private lateinit var goalTextView: TextView
    private lateinit var editWorkingDays: EditText
    private lateinit var editPercentage: EditText

    init {
        getRelevantViews()
        initialSetup()
    }

    private fun initialSetup() {

        trackingModelDao = mDaoSession.trackingModelDao
        if (trackingModelDao.loadAll().size > 0) {
            trackingModel = trackingModelDao.loadAll()[0]
            updateFieldsFromDatabase()
        } else {
            trackingModel = TrackingModel()
            trackingModel.daysLeft = 0
            trackingModel.officeDays = 0
            trackingModel.workingDays = 0
            trackingModel.percentageGoal = 0
            trackingModel.percentageArchived = 0
            trackingModelDao.save(trackingModel)
        }
    }

    private fun getRelevantViews() {
        editOfficeDays = view.findViewById(R.id.editOfficeDays)
        editWorkingDays = view.findViewById(R.id.editWorkingDays)
        editPercentage = view.findViewById(R.id.editPercentage)
        daysLeftTextView = view.findViewById(R.id.daysLeftTextView)
        goalTextView = view.findViewById(R.id.goalTextview)
    }

    private fun updateFieldsFromDatabase() {
        setOfficeDays(trackingModel.officeDays)
        setWorkingDays(trackingModel.workingDays)
        setPercentageGoal(trackingModel.percentageGoal)
        setDaysLeft(trackingModel.daysLeft)
        setPercentageArchived(trackingModel.percentageArchived)
    }

    fun setOfficeDays(officeDays: Int) {
        editOfficeDays.setText(officeDays.toString())
    }

    fun setWorkingDays(workingDays: Int) {
        editWorkingDays.setText(workingDays.toString())
    }

    fun setPercentageGoal(percentageGoal: Int) {
        editPercentage.setText(percentageGoal.toString())
    }

    @SuppressLint("SetTextI18n")
    fun setDaysLeft(daysLeft: Int) {
        daysLeftTextView.text = "$daysLeft days left"
    }

    @SuppressLint("SetTextI18n")
    fun setPercentageArchived(percentageArchived: Long) {
        goalTextView.text = "$percentageArchived%"
    }
}
