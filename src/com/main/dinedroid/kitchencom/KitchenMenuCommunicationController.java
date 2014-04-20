package com.main.dinedroid.kitchencom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.main.dinedroid.kitchen.main;
import com.main.dinedroid.menu.Menu;

public class KitchenMenuCommunicationController implements Runnable {

	private Socket mySocket;
	private ObjectInputStream in;
	private String[] commands;
	public KitchenMenuCommunicationController(Socket mySocket, ObjectInputStream in, String[] commands)
	{
		this.mySocket = mySocket;
		this.in = in;
		this.commands = commands;
	}
	@Override
	public void run() {
		/**
		 * If the command is Set_Menu
		 * Get the menu from the connected server
		 * Set the menu using the KitchenController
		 */
		if(commands[1].equals("Set_Menu"))
		{
			try {
				Menu menu = (Menu)in.readObject();
				if(menu!=null)
				{
					main.kc.setMenu(menu);
				}

			} catch (ClassNotFoundException | IOException e) {
				/**
				 * If there were any problems getting the menu
				 * (Bad socket or corrupt data)
				 * Ask the KitchenController to query the server for the menu
				 * as a fall back option. 
				 */
				main.kc.downloadMenu();
				e.printStackTrace();
			}
		}
	}

}
