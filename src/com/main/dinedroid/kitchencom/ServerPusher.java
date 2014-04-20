package com.main.dinedroid.kitchencom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.main.dinedroid.kitchen.KitchenController;
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
	KitchenController kc = new KitchenController();
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
		case SET_AVAILIABILITY:  try {
								mySocket = new Socket("",4322);
								out = new ObjectOutputStream(mySocket.getOutputStream());
								out.writeObject("Kitchen||Set_Item_Availability||itemId||availability");
								out.close();
								mySocket.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
								break;
		case SET_ORDER_STATUS: try {
								mySocket = new Socket("",4322);
								out = new ObjectOutputStream(mySocket.getOutputStream());
								out.writeObject("Kitchen||Set_Order_Status||tableId||status");
								out.close();
								mySocket.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
								break;
				}
		}

}
