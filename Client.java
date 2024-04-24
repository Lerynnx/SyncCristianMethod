/**
	Description: This is a simple client that connects to a server and sends a message to it.
	The server will respond with the current time to sync by Cristian Method
	Author: Lerynnx (GitHub)
	Date: 24/04/2024
	Version: 1.0
	Do not remove this Attribution if you use this code.
	Notify the author if you want to use this code before using it.
*/

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {

	public static void main (String args[]) {
		Socket s = null;

		try{
			String address = "localhost";
			int serverPort = 7896;
			
			// Create a socket and connect to the server
			s = new Socket(address, serverPort);
			// Input stream to receive data from the server
			DataInputStream in = new DataInputStream( s.getInputStream());
			// Output stream to send data to the server
			DataOutputStream out =new DataOutputStream( s.getOutputStream());

			//Get init time
			long timeStart = System.currentTimeMillis();
			
			// Send the message "Time" to the server
			out.writeUTF("Time"); 

			// Read the response from the server
			long serverTime = in.readLong();

			//Get end time
			long timeEnd = System.currentTimeMillis();
			
			//Now we can update client time
			System.out.println("Updated time: " + (serverTime + (timeStart - timeEnd)/2));
			
			//Verbose
			System.out.println("Server time: "+serverTime+"ms Init time: "+timeStart+"ms End time: "+timeEnd+"ms");
			
		}catch (UnknownHostException e){
			System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){
			System.out.println("readline:"+e.getMessage());
		}finally {
			// Close the socket
			if(s!=null) try {s.close();}catch (IOException e){
				System.out.println("close:"+e.getMessage());
			}
		}
	}
}