package com.main.dinedroid.kitchencom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream.GetField;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.main.dinedroid.kitchen.KitchenController;
import com.main.dinedroid.kitchen.main;
import com.main.dinedroid.menu.Menu;
import com.main.dinedroid.models.Order;
import com.main.dinedroid.models.Restore;
import com.main.dinedroid.models.Table;

public class ServerPuller extends Thread {
	private final int GET_MENU = 1;
	private final int GET_ORDERS = 2;
	private int command;
	private Socket mySocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	KitchenController kc = new KitchenController();

	public ServerPuller(int command){
		this.command = command;
	}

	public void run(){
		switch(command){
		case GET_MENU:  
			try {
				mySocket = new Socket("",4322);
				out = new ObjectOutputStream(mySocket.getOutputStream());
				out.writeObject("Menu||Get_Menu");
				in = new ObjectInputStream(mySocket.getInputStream());
				Menu menu = (Menu)in.readObject();
				kc.setMenu(menu);
				out.close();
				mySocket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				showMessage("Error: Cannot communicate with the server");
				e.printStackTrace();
			} 
			break;
		case GET_ORDERS: 
			try {
				mySocket = new Socket("",4322);
				out = new ObjectOutputStream(mySocket.getOutputStream());
				out.writeObject("Table||Get_All_Tables");
				in = new ObjectInputStream(mySocket.getInputStream());
				ArrayList<Table> tables = (ArrayList<Table>)in.readObject();
				kc.setTables(tables);
				out.close();
				mySocket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				showMessage("Error: Cannot communicate with the server");
				e.printStackTrace();
			} 	
			break;
		}
	}

	public void showMessage(final String message)
	{
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, message);
			}
		});
		t.run();
	}
}
