package team10.studentwellbeingapp;

import java.util.Date;


public class Appointment {

    private String student;
    private String datetime;
    private String councillor;

    public Appointment(String datetime, String councillor){
        this.datetime = datetime;
        this.councillor = councillor;
    }

    public String getDatetime(){
        return datetime;
    }

    public String getStudent(){
        return student;
    }

    public String getCouncillor(){
        return councillor;
    }

    @Override
    public String toString() {
        return "Appointment Time: " + datetime.toString() + " with " +  councillor.toString();
    }
}
