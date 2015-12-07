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

public class AppointmentAccessor {
	
	String freeURL;
	String bookURL;
	String cancelURL;
	String signupURL;
	
	public AppointmentAccessor(){
		//read in URL's from configuration file
		try {
			BufferedReader in = new BufferedReader(new FileReader("urlconfig.txt"));
			this.freeURL = in.readLine();
			this.bookURL = in.readLine();
			this.cancelURL = in.readLine();
			this.signupURL = in.readLine();
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
	public ArrayList<Appointment> getFreeAppointments(String date){
		
		URL url;
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
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
				appointments.add(new Appointment(line.substring(0, 10), null, line.substring(11)));
			}
			//read php response of appointments, add to arraylist
			in.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return appointments;
	}
	
	
	//books an appointment into the mysql db
	public void bookAppointment(String student, String date, String time){
		
		URL url;
		
		try {
			
			//http://localhost:80/bookappt.php
			url = new URL(bookURL);
			URLConnection conn = url.openConnection();
			
			String datetime = date + " " + time;
			
			String data = URLEncoder.encode("student", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8");
			data += "&" + URLEncoder.encode("datetime", "UTF-8") + "=" + URLEncoder.encode(datetime, "UTF-8");
			
			connection(url, data, conn);
			//call php script
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));	
			
			StringBuilder sb = new StringBuilder();
			
			String line;
			
			while((line = in.readLine()) != null){
				sb.append(line);
			}
			in.close();
			//read echo from php script if there is any
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//cancels an appointment in the mysql db
	public void cancelAppointment(String student, String date, String time) throws IllegalArgumentException{
		URL url;
		
		try {
			
			url = new URL("http://localhost:80/cancelappt.php");
			URLConnection conn = url.openConnection();
			
			String datetime = date + " " + time;
			
			String data = URLEncoder.encode("student", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8");
			data += "&" + URLEncoder.encode("datetime", "UTF-8") + "=" + URLEncoder.encode(datetime, "UTF-8");
			
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
			in.close();
			//read echo from php script, throw exception if invalid user is enter
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//!!!!not yet fully implemented!!!
	public void signUp(String student, String password, String email){
		URL url;
		try{
			url = new URL(signupURL);
			URLConnection conn = url.openConnection();
			
			String data = URLEncoder.encode("student_number", "UTF-8") + "=" + URLEncoder.encode(student, "UTF-8");
				   data += URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
				   data += URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
				   
			connection(url, data, conn);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));	
			
			StringBuilder sb = new StringBuilder();
			
			String line;
			
			while((line = in.readLine()) != null){
				sb.append(line);
			}
			
			if(line.equals("Invalid email")){
				throw new IllegalArgumentException("Invalid Email Adress");
			}
			
		}catch(MalformedURLException e){
			
		}catch(IOException e){
			
		}
	}
}
