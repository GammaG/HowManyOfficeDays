package com.howManyOfficeDays.controller

import com.howManyOfficeDays.greendao.DaoSession
import com.howManyOfficeDays.model.TrackingModel

class TrackingDaoController(private val mDaoSession: DaoSession) {

    private var trackingModel: TrackingModel? = null


    init {
        initialSetup()
    }

    private fun initialSetup() {

    }

}
