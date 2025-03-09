package com.howManyOfficeDays.controller

import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.howManyOfficeDays.R
import com.howManyOfficeDays.greendao.DaoSession
import com.howManyOfficeDays.greendao.TrackingModelDao
import com.howManyOfficeDays.greendao.TrackingModel
import kotlin.math.ceil

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

    private fun setOfficeDays(officeDays: Int) {
        editOfficeDays.setText(officeDays.toString())
    }

    private fun setWorkingDays(workingDays: Int) {
        editWorkingDays.setText(workingDays.toString())
    }

    private fun setPercentageGoal(percentageGoal: Int) {
        editPercentage.setText(percentageGoal.toString())
    }

    @SuppressLint("SetTextI18n")
    private fun setDaysLeft(daysLeft: Int) {
        daysLeftTextView.text = "$daysLeft days left"
    }

    @SuppressLint("SetTextI18n")
    private fun setPercentageArchived(percentageArchived: Long) {
        goalTextView.text = "$percentageArchived%"
    }

    fun updateOfficeDays(officeDays: Int) {
        if (officeDays < 0) {
            trackingModel.officeDays = 0
        } else {
            trackingModel.officeDays = officeDays
        }
        setOfficeDays(trackingModel.officeDays)
        trackingModelDao.save(trackingModel)
    }

    fun reduceOfficeDay() {
        if (trackingModel.officeDays > 0) {
            trackingModel.officeDays--
            trackingModelDao.save(trackingModel)
            setOfficeDays(trackingModel.officeDays)
        }
    }

    fun addOfficeDay() {
        trackingModel.officeDays++
        trackingModelDao.save(trackingModel)
        setOfficeDays(trackingModel.officeDays)
    }

    fun updateWorkingDays(workingDays: Int) {
        if (workingDays < 0) {
            trackingModel.workingDays = 0
        } else {
            trackingModel.workingDays = workingDays
        }
        setWorkingDays(trackingModel.workingDays)
        trackingModelDao.save(trackingModel)
    }

    fun reduceWorkingDay() {
        if (trackingModel.workingDays > 0) {
            trackingModel.workingDays--
            trackingModelDao.save(trackingModel)
            setWorkingDays(trackingModel.workingDays)
        }
    }

    fun addWorkingDay() {
        trackingModel.workingDays++
        trackingModelDao.save(trackingModel)
        setWorkingDays(trackingModel.workingDays)
    }

    fun updatePercentageGoal(percentageGoal: Int) {
        if (percentageGoal < 0) {
            trackingModel.percentageGoal = 0
        } else {
            trackingModel.percentageGoal = percentageGoal
        }
        setPercentageGoal(trackingModel.percentageGoal)
        trackingModelDao.save(trackingModel)
    }

    fun reducePercentageGoal() {
        if (trackingModel.percentageGoal > 0) {
            trackingModel.percentageGoal--
            trackingModelDao.save(trackingModel)
            setPercentageGoal(trackingModel.percentageGoal)
        }
    }

    fun addPercentageGoal() {
        trackingModel.percentageGoal++
        trackingModelDao.save(trackingModel)
        setPercentageGoal(trackingModel.percentageGoal)
    }

    private fun setDaysLeft() {
        val neededOfficeDays =
            (trackingModel.workingDays.toDouble() / 100) * trackingModel.percentageGoal.toDouble()
        val roundedUpValue = ceil(neededOfficeDays).toInt()
        setDaysLeft(roundedUpValue)
    }

    private fun setPercentageLeft() {

    }

}
