import java.io.*;
import java.net.*;

/*
This is the TestServer. It is on port 1234 and checks to see if the client is connected to the server.
If it is connected, then it takes the output of the client and makes it into the input of the server.
All of the vehicle information is then transferred into the server, and you will be able to see it
on the console.

Make sure to run the server first and then the clients.
-Jin
 */
public class TestServer {
	
	public static void vehicleInfo(int port) {
		  try (ServerSocket serverSocket = new ServerSocket(port)) {
	            System.out.println("Server is running on port " + port);

	            while (true) {
	                Socket clientSocket = serverSocket.accept();
	                System.out.println("Client is connected");

	                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

	                // Receiving vehicle owner information
	                String ownerID = inputStream.readUTF();
	                String firstName = inputStream.readUTF();
	                String lastName = inputStream.readUTF();
	                String make = inputStream.readUTF();
	                String model = inputStream.readUTF();
	                String vin = inputStream.readUTF();
	                String residencyTime = inputStream.readUTF();

	                System.out.println("Received Vehicle Owner Information:");
	                System.out.println("Owner ID: " + ownerID);
	                System.out.println("First Name: " + firstName);
	                System.out.println("Last Name: " + lastName);
	                System.out.println("Make: " + make);
	                System.out.println("Model: " + model);
	                System.out.println("VIN: " + vin);
	                System.out.println("Residency Time: " + residencyTime);
	                
	                String fileName = "VCRTS-DATA";
	                String filePath = FileCreationFinal.createFolder(fileName);
	                FileCreationFinal.vehicleOwnerFileCreate(filePath,ownerID, firstName, lastName, make, model, vin, residencyTime);

	                clientSocket.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Error: " + e.getMessage());
	        }
		
		
	}
	public static void jobOwnerInfo(int port) {
		
		  	  try (ServerSocket serverSocket = new ServerSocket(port)) {
	            System.out.println("Server is running on port " + port);

	            while (true) {
	                Socket clientSocket = serverSocket.accept();
	                System.out.println("Client is connected");

	                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

	                // Receiving job owner information
	                String clientID = inputStream.readUTF();
	                String firstName = inputStream.readUTF();
	                String lastName = inputStream.readUTF();
	                String jobName = inputStream.readUTF();
	                String jobDuration = inputStream.readUTF();
	                String deadline = inputStream.readUTF();

	                System.out.println("Received Vehicle Owner Information:");
	                System.out.println("Client ID: " + clientID);
	                System.out.println("First Name: " + firstName);
	                System.out.println("Last Name: " + lastName);
	                System.out.println("Job Name: " + jobName);
	                System.out.println("Job Duration: " + jobDuration);
	                System.out.println("Deadline: " + deadline);
	               
	                
	                String fileName = "VCRTS-DATA";
	                String filePath = FileCreationFinal.createFolder(fileName);
	                FileCreationFinal.jobOwnerFileCreate(filePath,clientID, firstName, lastName,jobName, jobDuration, deadline);

	                clientSocket.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Error: " + e.getMessage());
	        }
		 
	}
    public static void main(String[] args) {
        int port = 1234;
        //vehicleInfo(port);
       jobOwnerInfo(port);
    }
}
