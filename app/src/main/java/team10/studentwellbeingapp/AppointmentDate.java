package team10.studentwellbeingapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jamie on 11/11/2015.
 */
public class AppointmentDate {
    //need to change this to Calender or DateTime
    private Calendar day;
    private SimpleDateFormat formattedDate;
    private String[] appointments;

    //used to pass error message from async task back to foreground
    private String errorMessage;

    public AppointmentDate(Calendar day, SimpleDateFormat formattedDate, String[] appointments) {
        this.day = day;
        this.formattedDate = formattedDate;
        this.appointments = appointments;

    }

    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public String[] getAppointments() {
        return appointments;
    }

    public String getformattedDate() {
        formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        return formattedDate.format(day.getTime());

    }



    public void setAppointments(String[] appointments) {
        this.appointments = appointments;
    }
}
