/**
	Description: This is a simple server program that listens for incoming client connections and sends the current UTC time to the client.
	Author: Lerynnx (GitHub)
	Date: 24/04/2024
	Version: 1.0
	Do not remove this Attribution if you use this code.
	Notify the author if you want to use this code before using it.
*/


import java.net.*;
import java.io.*;

public class ServerUTC {
	public static void main (String args[]) {
		try{
			int serverPort = 7896;

			// Create a server socket
			ServerSocket listenSocket = new ServerSocket(serverPort);

			while(true) {
				// Accept incoming client connections
				Socket clientSocket = listenSocket.accept();
				// Create a new Connection object for each client
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {
			System.out.println("Listen socket:"+e.getMessage());
		}
	}
}

//! This class should be in a separate file called Connection.java but for the sake of simplicity, I'm including it here
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;

			// Get input stream from client
			in = new DataInputStream( clientSocket.getInputStream());
			// Get output stream to client
			out =new DataOutputStream( clientSocket.getOutputStream());
			System.out.println("Server on");
			// Start the thread
			this.start();
		} catch(IOException e) {
			System.out.println("Connection:"+e.getMessage());
		}
	}

	public void run(){
		try {
			// Read data from client			                 
			String data = in.readUTF(); 
			
			if(data.equals("Time")) {
				
				//Simulated transmission time
				Thread.sleep(8000);
				
				// Send the result back to the client
				out.writeLong(System.currentTimeMillis()); 
			}else {
				// Send the result back to the client
				out.writeLong(0); 
			}
		}catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		}catch(IOException e) {
			System.out.println("readline:"+e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			try {clientSocket.close();}catch (IOException e){
				System.out.println("readline:"+e.getMessage());}
		}
	}
}