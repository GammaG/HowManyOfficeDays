package com.howManyOfficeDays.model

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Generated
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.NotNull


@Entity(nameInDb = "time")
class Time {
    @JvmField
    @Id
    var id: Long? = null

    @JvmField
    var hours: Int = 0

    @JvmField
    var minutes: Int = 0

    constructor()

    @Generated(hash = 1093068256)
    constructor(id: Long?, hours: Int, minutes: Int) {
        this.id = id
        this.hours = hours
        this.minutes = minutes
    }
}
