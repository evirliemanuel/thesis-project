package com.lieverandiver.thesisproject.helper;

import android.util.Log;

import com.lieverandiver.thesisproject.R;
import com.remswork.project.alice.model.support.Time;

public class ScheduleHelper {

    public String display(String room, String time, String period) {
        TimeHelper timeHelper = new TimeHelper();
        Log.i("myTAG", timeHelper.convert(time).getTime().toString(Time.Style.NORMAL));
        Log.i("myTAG", timeHelper.convert(time).addHour(new TimeHelper().convert(period).getTime().getHour()).getTime().toString(Time.Style.NORMAL));
        Log.i("myTAG", timeHelper.convert(time).addHour(new TimeHelper().convert(period).getTime().getHour()).addMinute(new TimeHelper().convert(period).getTime().getMinute()).getTime().toString(Time.Style.NORMAL));
        Log.i("myTAG", new TimeHelper().convert(period).getTime().getHour() + "");
        Log.i("myTAG", new TimeHelper().convert(period).getTime().getMinute() + "");

        Time t = timeHelper
                .convert(time)
                .addHour(new TimeHelper().convert(period).getTime().getHour())
                .addMinute(new TimeHelper().convert(period).getTime().getMinute())
                .getTime();
        return "Room " + room + " / " + time + " - " + t.toString(Time.Style.NORMAL);
    }


    public int imageDisplay (String day) {
        if(day.equalsIgnoreCase("Monday"))
                return R.drawable.logo_day_monday;
        if(day.equalsIgnoreCase("Tuesday"))
            return R.drawable.logo_day_tuesday;
        if(day.equalsIgnoreCase("Wednesday"))
            return R.drawable.logo_day_wednesday;
        if(day.equalsIgnoreCase("Thursday"))
            return R.drawable.logo_day_thursday;
        if(day.equalsIgnoreCase("Friday"))
            return R.drawable.logo_day_friday;
        if(day.equalsIgnoreCase("Saturday"))
            return R.drawable.logo_day_saturday;
        else
            return R.drawable.logo_day_sunday;
    }
}
