package com.main.dinedroid.kitchencom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class KitchenListener implements Runnable {

	/**
	 * ALL commands are of the form:<br>
	 * <b>Controller</b>||<b>Command</b>||<b>ID</b>||<b>ID</b><br>
	 * 1:Asks the server to invoke the appropriate controller<br>
	 * 2:Asks the controller to invoke the appropriate command<br>
	 * 3:Any IDs<br>
	 * 4:Any IDs<br>
	 * All this gets put into the commands array in the same order, after being split.
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			ServerSocket listen = new ServerSocket(4323);
			/**
			 * Start listening on port 4323
			 */
			while(true)
			{
				Socket socket = listen.accept();
				String clientInetAddr = socket.getInetAddress().toString();
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); //Get the input stream
				System.out.println("Connected to: " + clientInetAddr);
				try
				{

					String getCommand = (String) in.readObject(); //Read in the string command
					String[] commands = getCommand.split("\\|\\|"); //Split the command to get the distinct entities
					String getCase = commands[0];
					System.out.println(getCase);
					System.out.println("Command <<"+commands[0]+">>");


					if(getCase.equals("Menu"))
					{
						Thread t = new Thread(new KitchenMenuCommunicationController(socket, in, commands));
						t.run();
					}

					if(getCase.equals("Order"))
					{
						Thread t = new Thread(new KitchenOrderCommunicationListener(socket, in, commands));
						t.run();
					}
				}

				catch(IOException e)
				{
					System.err.println(e.getMessage());
				}

				in.close();
				//socket.close();
			}
		}
		catch (Exception e) {
			System.err.println("Error in run()");
			e.printStackTrace();
		}
	}
}

