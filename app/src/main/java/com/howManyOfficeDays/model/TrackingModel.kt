package com.howManyOfficeDays.model

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.Property

@Entity(nameInDb = "TrackingModel")
class TrackingModel {

    @Id
    var id: Long? = null

    @Property(nameInDb = "officeDays")
    var officeDays: Int = 0

    @Property(nameInDb = "officeDaysLeft")
    var officeDaysleft: Int = 0

    @Property(nameInDb = "workingDays")
    var workingDays: Int = 0

    @Property(nameInDb = "percentageGoal")
    var percentageGoal: Int = 0

    @Property(nameInDb = "daysLeft")
    var daysLeft: Int = 0

    @Property(nameInDb = "percentageArchived")
    var percentageArchived: Long = 0
}
