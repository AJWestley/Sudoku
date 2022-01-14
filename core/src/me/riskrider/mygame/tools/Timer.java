package me.riskrider.mygame.tools;

public class Timer {

    private int seconds, minutes;
    private float timer;

    public Timer () {
        seconds = 0;
        minutes = 0;
        timer = 0;
    }

    public void updateTime (float delta) {
        timer += delta;
        seconds = (int) Math.floor(timer);
        if (seconds >= 60) {
            minutes++;
            timer = 0;
        }
    }

    public void reset() {
        seconds = 0;
        minutes = 0;
        timer = 0;
    }

    public String toString () {
        String minuteString = "", secondString = "";
        if (minutes < 10) minuteString = "0" + minutes;
        else minuteString = "" + minutes;
        if (seconds < 10) secondString = "0" + seconds;
        else secondString = "" + seconds;
        return minuteString + ":" + secondString;
    }
}
