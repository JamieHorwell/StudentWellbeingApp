package team10.studentwellbeingapp;

import java.util.ArrayList;

public class AppointmentDay extends ArrayList<Appointment> {

    private String date;

    public AppointmentDay(String date){
        this.date = date;
    }

    public void addAppointment(Appointment appointment){
        this.add(appointment);
    }


    public String getDate(){
        return date;
    }

    public void printAppointments(){
        for(int i = 0; i < this.size(); i++){
            System.out.println(this.get(i).getDatetime() + " " + this.get(i).getCouncillor());
        }
    }

}
