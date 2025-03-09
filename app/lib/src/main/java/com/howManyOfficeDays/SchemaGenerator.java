package com.howManyOfficeDays;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.DaoGenerator;

public class SchemaGenerator {
    public static void main(String[] args) throws Exception {
        // Define a schema with version number and default package
        Schema schema = new Schema(1, "com.howManyOfficeDays.greendao");

        // Assuming 'schema' is your DaoSession object
        Entity trackingModel = schema.addEntity("TrackingModel"); // Table name
        trackingModel.addIdProperty(); // id is the primary key
        trackingModel.addIntProperty("officeDays");
        trackingModel.addIntProperty("workingDays");
        trackingModel.addIntProperty("percentageGoal");
        trackingModel.addIntProperty("daysLeft");
        trackingModel.addLongProperty("percentageArchived");


        // Generate the DAO classes
        new DaoGenerator().generateAll(schema, "src/main/java");
    }
}
