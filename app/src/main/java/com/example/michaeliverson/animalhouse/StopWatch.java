package com.example.michaeliverson.animalhouse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class StopWatch extends FragmentActivity implements Runnable,StopStart.callBackStart,StopStart.callBackStop,StopStart.callBackClear {

    private volatile Boolean stopStart = false;
    private Thread timer;
    private Fragment timeDisplay;
    private Fragment controls;
    private String Time;
    private float time = 0.0f;
    private String seconds;
    private String minutes;
    private String hours;
    private String milliseconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        this.timeDisplay = (TimerDisplay)
                getSupportFragmentManager().findFragmentById(R.id.fgTimerDisplay);
        this.controls = (StopStart)getSupportFragmentManager().findFragmentById(R.id.fgStopWatch);
    }

    @Override
    public void run() {

        try
        {
            do {
                Thread.sleep(10);
                time += 10.0f;
                Time = computeTime(time);
                Message message  = new Message();
                message.obj = Time;
                handel.sendMessage(message);
            }while (stopStart);
        }catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }



    private String computeTime(float time)
    {
       float  secs = (long)(time/1000);
       float  mins = (long)((time/1000)/60);
       float  hrs = (long)(((time/1000)/60)/60);

		/* Convert the seconds to String
		 * and format to ensure it has
		 * a leading zero when required
		 */
        secs = secs % 60;
        seconds=String.valueOf(secs);
        if(secs == 0){
            seconds = "00";
        }
        if(secs <10 && secs > 0){
            seconds = "0"+seconds;
        }

		/* Convert the minutes to String and format the String */

        mins = mins % 60;
        minutes=String.valueOf(mins);
        if(mins == 0){
            minutes = "00";
        }
        if(mins <10 && mins > 0){
            minutes = "0"+minutes;
        }

    	/* Convert the hours to String and format the String */

        hours=String.valueOf(hrs);
        if(hrs == 0){
            hours = "00";
        }
        if(hrs <10 && hrs > 0){
            hours = "0"+hours;
        }

        // Compute milles
        milliseconds = String.valueOf((long)time);
        if(milliseconds.length()==2){
            milliseconds = "0"+milliseconds;
        }
        if(milliseconds.length()<=1){
            milliseconds = "00";
        }
        milliseconds = milliseconds.substring(milliseconds.length()-3, milliseconds.length()-2);

		/* Setting the timer text to the elapsed time */
        return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
    }

    private Handler handel = new Handler()
    {
        @Override
        public String getMessageName(Message message) {
            return super.getMessageName(message);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String time = (String)msg.obj;
            sendText(time);
        }
    };


    public void sendText(String time)
    {
        this.Time = time;
    }

    public String sendTime()
    {
        return this.Time;
    }

    @Override
    public void onStartButtonClick() {
        if (timer != null)
        {
            this.timer = new Thread(this);
            this.stopStart = true;
            this.timer.start();
        }
    }

    @Override
    public void onStopButtonClick() {
        this.stopStart = false;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClearButtonClick() {
        this.stopStart = false;
        this.time = 0.0f;
        this.hours = "0";
        this.milliseconds="00";
        this.seconds = "00";
        this.minutes = "00";
        Time = hours + ":" + minutes + ":" + seconds + "." + milliseconds;
        sendText(Time);
    }
}
