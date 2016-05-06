package team10.studentwellbeingapp;

/**
 * Created by Jamie and Leo on 17/04/2016.
 * class that provides connection methods to query database
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppointmentAccessorNew {
   
    private String freeURL = "http://homepages.cs.ncl.ac.uk/l.rickayzen1/freeappt.php";
    private String bookURL = "http://homepages.cs.ncl.ac.uk/l.rickayzen1/bookappt.php";
    private String cancelURL = "http://homepages.cs.ncl.ac.uk/l.rickayzen1/cancelappt.php";
    private String signupURL = "http://homepages.cs.ncl.ac.uk/l.rickayzen1/signup.php";
    private String loginURL = "http://homepages.cs.ncl.ac.uk/l.rickayzen1/logon.php";
    private String freeUserURL = "http://homepages.cs.ncl.ac.uk/l.rickayzen1/userappt.php";

    public AppointmentAccessorNew(){
        //read in URL's from configuration file
        try {
            BufferedReader in = new BufferedReader(new FileReader("urlconfig.txt"));
            this.freeURL = in.readLine();
            this.bookURL = in.readLine();
            this.cancelURL = in.readLine();
            this.signupURL = in.readLine();
            this.loginURL = in.readLine();
            this.freeUserURL = in.readLine();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //establishes connection with php script, writes output via POST
    private void connection(URL url, String data, URLConnection conn) throws IOException{
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();
    }

    //returns arraylist of free appt from mysql given a certian date
    public AppointmentDay getFreeAppointments(String date, String student){

        URL url;
        AppointmentDay appointments = new AppointmentDay(date, student);

        try {

            url = new URL(freeURL);
            String data = URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
            URLConnection conn = url.openConnection();

            connection(url, data, conn);
            //connect to php script

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;

            while((line = in.readLine()) != null){
                sb.append(line);
               Log.w("connectionStatus", line);
            }

            JSONObject apps = new JSONObject(sb.toString());

            JSONArray arr = apps.getJSONArray("appointments");

            for(int i = 0; i<arr.length(); i++){
                Appointment appointment = new Appointment(arr.getJSONObject(i).getString("datetime"), "Dr Smith", arr.getJSONObject(i).getString("aid"));
                appointments.add(appointment);
            }
            //read php response of appointments, add to arraylist
            in.close();

        } catch (MalformedURLException | JSONException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return appointments;
        //if no appointments available


    }

    //books an appointment into the mysql db
    public void bookAppointment(String student, String password, String aid){

        URL url;

        try {

            //http://localhost:80/bookappt.php
            url = new URL(bookURL);
            URLConnection conn = url.openConnection();

            String data = URLEncoder.encode("student", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("aid", "UTF-8") + "=" + URLEncoder.encode(aid, "UTF-8");

            connection(url, data, conn);
            //call php script

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;

            while((line = in.readLine()) != null){
                Log.w("book result", line);
                sb.append(line);
            }
            //System.out.println(sb);
            in.close();
            //read echo from php script if there is any
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //cancels an appointment in the mysql db
    public void cancelAppointment(String student, String password, String aid) throws IllegalArgumentException{
        URL url;
        Log.w("cancel", student);
        Log.w("cancel", password);
        Log.w("cancel", aid);
        try {

            url = new URL(cancelURL);
            URLConnection conn = url.openConnection();


            String data = URLEncoder.encode("student", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("aid", "UTF-8") + "=" + URLEncoder.encode(aid, "UTF-8");

            connection(url, data, conn);
            //establish url connection

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;

            while((line = in.readLine()) != null){
                Log.w("cancel", line);
                sb.append(line);
            }
            if(sb.toString().equals("invalid user")){
                throw new IllegalArgumentException("invalid user");
            }
            System.out.println(sb);
            in.close();
            //read echo from php script, throw exception if invalid user is enter

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //sign up user
    public boolean signUp(String student, String password, String email){
        URL url;
        try{
            url = new URL(signupURL);
            URLConnection conn = url.openConnection();

            String data = URLEncoder.encode("student_number", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8") + "&";
            data += URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&";
            data += URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

            connection(url, data, conn);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line = "";

            while((line = in.readLine()) != null){
                sb.append(line);
                Log.w("signup", line);
            }

            System.out.println(sb);
            if(sb.toString().equals("2000")){
                return false;
            }
            return true;


        }catch(MalformedURLException e){
            return false;
        }catch(IOException e){
            return false;
        }
    }

    //check if user account is valid
    public loginResult logIn(String student, String password){
        URL url;
        try{
            url = new URL(loginURL);
            URLConnection conn = url.openConnection();

            String data = URLEncoder.encode("student_number", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8") + "&";
            data += URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&";

            connection(url, data, conn);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line = "";

            while((line = in.readLine()) != null){
                sb.append(line);
                Log.w("loginResult", line);
            }

            System.out.println(sb);

            if(sb.equals("1002") || sb.toString().contains("too many failed attempts") ||  sb.toString().contains("1001")){
                return new loginResult(false, "too many failed attempts");
            }
            if(sb.equals("1001") || sb.toString().contains("invalid username/password") || sb.toString().contains("1001") ){
                return new loginResult(false, "invalid username/password");
            }
            if(sb.equals("1000")){
                return new loginResult(false, "query failed");
            }
            return new loginResult(true, "success");
        }catch(MalformedURLException e){
            return new loginResult(false, "exception failure");
        }catch(IOException e){
            return new loginResult(false,"exception failure");
        }

    }

    //get list of users appointments
    public ArrayList<Appointment> getUserAppointments(String student, String password) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        URL url;

        try {

            url = new URL(freeUserURL);
            String data = URLEncoder.encode("student", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8") + "&";
            data += URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            URLConnection conn = url.openConnection();

            connection(url, data, conn);
            //connect to php script

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;

            while((line = in.readLine()) != null){
                Log.w("user result", line);
                sb.append(line);
                //appointments.add(new Appointment(line.substring(0, 10), null, line.substring(11)));
            }

            JSONObject apps = new JSONObject(sb.toString());

            JSONArray arr = apps.getJSONArray("appointments");

            for(int i = 0; i<arr.length(); i++){
                Appointment appointment = new Appointment(arr.getJSONObject(i).getString("datetime"), arr.getJSONObject(i).getString("councillor"), arr.getJSONObject(i).getString("aid"));
                appointments.add(appointment);
            }
            //read php response of appointments, add to arraylist
            in.close();

        } catch (MalformedURLException | JSONException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        //if user has no appointments booked
        if(appointments.isEmpty()) {
            return null;
        }
        return appointments;
    }


    /* Dialog that is presented to user if data cannot be retrieved*/


}
