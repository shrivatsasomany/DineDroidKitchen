package com.main.dinedroid.kitchencom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.main.dinedroid.kitchen.KitchenController;
import com.main.dinedroid.kitchen.main;
import com.main.dinedroid.menu.Menu;
import com.main.dinedroid.models.Table;

public class ServerPusher extends Thread {
	private final int SET_AVAILIABILITY = 1;
	private final int SET_ORDER_STATUS = 2;
	private int command, itemId, tableId;
	private boolean availability;
	private int status;
	private Socket mySocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public ServerPusher(int command, int itemId, boolean availability){
		this.command = command;
		this.itemId = itemId;
		this.availability = availability;
	}
	public ServerPusher(int command, int tableId, int status){
		this.command = command;
		this.tableId = tableId;
		this.status = status;
	}

	public void run(){
		switch(command){
		case SET_AVAILIABILITY:  
			try {
				mySocket = new Socket("",4322);
				out = new ObjectOutputStream(mySocket.getOutputStream());
				out.flush();
				out.writeObject("Menu||Set_Item_Status||"+itemId+"||"+availability);
				out.flush();
				in = new ObjectInputStream(mySocket.getInputStream());
				boolean result = in.readBoolean();
				if(!result)
				{
					showMessage("Error: Could not set item availability! This is a server error");
					main.kc.downloadMenu(); //Acts like a rollback
				}
				in.close();
				out.close();
				mySocket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				showMessage("Error: Cannot communicate with server");
				e.printStackTrace();
			} 
			break;
		case SET_ORDER_STATUS: 
			try {
				mySocket = new Socket("",4322);
				out = new ObjectOutputStream(mySocket.getOutputStream());
				out.flush();
				out.writeObject("Table||Set_Order_Status||"+tableId+"||"+status);
				out.flush();
				in = new ObjectInputStream(mySocket.getInputStream());
				boolean result = in.readBoolean();
				if(!result)
				{
					showMessage("Error: Could not set status! This is a server error");
					main.kc.downloadOrders(); //Acts like a rollback
				}
				in.close();
				out.close();
				mySocket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				showMessage("Error: Cannot communicate with server");
				main.kc.downloadOrders(); //Acts like a rollback
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
