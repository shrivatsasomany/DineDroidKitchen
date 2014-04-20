package com.main.dinedroid.kitchencom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.main.dinedroid.kitchen.main;
import com.main.dinedroid.models.Table;

public class KitchenOrderCommunicationListener implements Runnable {

	private Socket mySocket;
	private ObjectInputStream in;
	private String[] commands;
	
	public KitchenOrderCommunicationListener(Socket mySocket, ObjectInputStream in, String[] commands)
	{
		this.mySocket = mySocket;
		this.in = in;
		this.commands = commands;
	}
	@Override
	public void run() {
		/**
		 * If the command is Set_Order
		 * Get the entire table from the connected server
		 * Add it to the list of tables with an order using the
		 * KitchenController.
		 */
		if(commands[1].equals("Set_Order"))
		{
			try {
				Table t = (Table)in.readObject();
				boolean result = false;
				if(t!=null)
				{
					result = main.kc.addOrder(t);
				}
				/**
				 * If there was a problem adding to the table,
				 * Ask the KitchenController to refresh all orders
				 */
				if(!result)
				{
					main.kc.downloadOrders();
				}
			} catch (ClassNotFoundException | IOException e) {
				/**
				 * If there is a problem, ask the KitchenController
				 * to refresh all orders
				 */
				main.kc.downloadOrders();
				e.printStackTrace();
				
			}
		}
		
	}

}
