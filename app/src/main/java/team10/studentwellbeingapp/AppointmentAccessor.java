package team10.studentwellbeingapp;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppointmentAccessor {

    private String freeURL = "http://192.168.1.143:80/studentWellbeing/freeappt.php";
    private String bookURL = "http://192.168.1.143:80/studentWellbeing/bookappt.php";
    private String cancelURL =  "http://192.168.1.143:80/studentWellbeing/cancelappt.php";
    private String signupURL =  "http://192.168.1.143:80/studentWellbeing/signup.php";
    private String loginURL = "http://192.168.1.143:80/studentWellbeing/logon.php";

    public AppointmentAccessor(){
        //read in URL's from configuration file
//        try {
//            BufferedReader in = new BufferedReader(new FileReader("urlconfig.txt"));
//            this.freeURL = "http://192.168.0.14:80/studentWellbeing/freeappt.php";
//            this.bookURL = "http://192.168.0.14:80/studentWellbeing/bookappt.php";
//            this.cancelURL = "http://192.168.0.14:80/studentWellbeing/cancelappt.php";
//            this.signupURL = "http://192.168.0.14:80/studentWellbeing/signup.php";
//            this.loginURL = "http://192.168.0.14:80/studentWellbeing/logon.php";
//            in.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
    public AppointmentDay getFreeAppointments(String date){

        URL url;
        AppointmentDay appointments = new AppointmentDay(date);

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
                //appointments.add(new Appointment(line.substring(0, 10), null, line.substring(11)));

            }


            try {
                JSONObject apps = new JSONObject(sb.toString());

                JSONArray arr = apps.getJSONArray("appointments");


                for (int i = 0; i < arr.length(); i++) {
                    Appointment appointment = new Appointment(arr.getJSONObject(i).getString("datetime"), arr.getJSONObject(i).getString("councillor"));
                    Log.w("appointment", appointment.getDatetime());
                    appointments.add(appointment);
                }
                //read php response of appointments, add to arraylist
                in.close();
            }
            //ERROR HERE
            catch (JSONException e) {};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appointments;
    }


    //books an appointment into the mysql db
    public void bookAppointment(String student, String date, String time, String password){

        URL url;

        try {

            //http://localhost:80/bookappt.php
            url = new URL(bookURL);
            URLConnection conn = url.openConnection();

            String datetime = date + " " + time;

            String data = URLEncoder.encode("student", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8");
            data += "&" + URLEncoder.encode("datetime", "UTF-8") + "=" + URLEncoder.encode(datetime, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            connection(url, data, conn);
            //call php script

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;

            while((line = in.readLine()) != null){
                sb.append(line);
            }
            System.out.println(sb);
            in.close();
            //read echo from php script if there is any
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //cancels an appointment in the mysql db
    public void cancelAppointment(String student, String date, String time, String password) throws IllegalArgumentException{
        URL url;

        try {

            url = new URL(cancelURL);
            URLConnection conn = url.openConnection();

            String datetime = date + " " + time;

            String data = URLEncoder.encode("student", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8");
            data += "&" + URLEncoder.encode("datetime", "UTF-8") + "=" + URLEncoder.encode(datetime, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            connection(url, data, conn);
            //establish url connection

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String line;

            while((line = in.readLine()) != null){
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
    public void signUp(String student, String password, String email){
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
            }

            System.out.println(sb);

            if(sb.toString().equals("Invalid email")){
                throw new IllegalArgumentException("Invalid Email Adress");
            }
            if(sb.toString().equals("user already exists")){
                throw new IllegalArgumentException("User Already Exists");
            }

        }catch(MalformedURLException e){

        }catch(IOException e){

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
            }

            System.out.println(sb);

            if(sb.equals("too many failed attempts")){
                return new loginResult(false, "too many failed attempts");
            }
            if(sb.equals("invalid username/password")){
                return new loginResult(false, "invalid username/password");
            }
            return new loginResult(true, "success");
        }catch(MalformedURLException e){

        }catch(IOException e){

        }
        return new loginResult(false, "exception failiure");
    }
}
