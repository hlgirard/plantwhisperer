package data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "plant_table")
public class Plant {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "name")
    @NonNull
    private String mName;

    @ColumnInfo(name = "date_updated")
    private long mDateUpdated;

    @ColumnInfo(name = "humidity_level")
    private int mHumidityLevel;

    @ColumnInfo(name = "topic")
    @NonNull
    private String mTopic;

    @ColumnInfo(name = "location")
    private String mLocation;

    @ColumnInfo(name = "mqtt_error")
    private int mMqttError;

    // Constructor
    public Plant(@NonNull String name, long dateUpdated, int humidityLevel, String topic, String location, int mqttError) {
        mName = name;
        mDateUpdated = dateUpdated;
        mHumidityLevel = humidityLevel;
        mTopic = topic;
        mLocation = location;
        mMqttError = mqttError;
    }

    // Setters
    public void setId(int newId){id = newId;}
    public void setName(String name) {mName = name;}
    public void setHumidityLevel(int humidity){mHumidityLevel = humidity;}
    public void setTopic(String topic){mTopic = topic;}
    public void setDateUpdated(long dateMilli){mDateUpdated = dateMilli;}
    public void setLocation(String location){mLocation = location;}
    public void setMqttError(int mqttError){mMqttError = mqttError;}

    // Getters
    public int getId() {return id;}
    public String getName(){return mName;}
    public int getHumidityLevel(){return mHumidityLevel;}
    public String getTopic(){return mTopic;}
    public long getDateUpdated(){return mDateUpdated;}
    public String getLocation(){return mLocation;}
    public int getMqttError(){return mMqttError;}

}
