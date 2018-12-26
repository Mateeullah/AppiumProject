package umucom.android.clinic_admin.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import umucom.android.clinic_admin.R;

/**
 * Created by umars on 2/21/2018.
 */

public class TimePickerFragment extends DialogFragment {

    public static final String ARG_TIME = "time";
    public static final String EXTRA_TIME = "com.bignerdranch.android.criminalintent.date";

    private TimePicker mTimePicker;
    private Calendar mTime;

    /*public static TimePickerFragment newInstance(Calendar time){
        Bundle args =  new Bundle();
        args.putSerializable(ARG_TIME,time);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;

    }*/
    public void setTime(Calendar time){
        mTime.set(Calendar.HOUR_OF_DAY,time.get(Calendar.HOUR_OF_DAY));
        mTime.set(Calendar.MINUTE,time.get(Calendar.MINUTE));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        Date date = new Date();
        mTime =Calendar.getInstance();
        mTime.setTime(date);

        int hour = mTime.get(Calendar.HOUR_OF_DAY);
        int minute = mTime.get(Calendar.MINUTE);
        //int am_pm = mCalendar.get(Calendar.AM_PM);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time,null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }
        else{
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.time_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int hours,minute;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                     hours = mTimePicker.getHour();
                     minute = mTimePicker.getMinute();
                }else{
                    hours = mTimePicker.getCurrentHour();
                    minute = mTimePicker.getCurrentMinute();
                }
                mTime.set(Calendar.HOUR_OF_DAY,hours);
                mTime.set(Calendar.MINUTE,minute);
                sendResult(Activity.RESULT_OK,mTime);

            }
        }).create();
    }
    private void sendResult(int resultCode,Calendar time){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME,time);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
