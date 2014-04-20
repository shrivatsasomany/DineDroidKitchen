package com.main.dinedroid.kitchen;

import java.util.ArrayList;

import com.main.dinedroid.kitchencom.ServerPuller;
import com.main.dinedroid.kitchencom.ServerPusher;
import com.main.dinedroid.menu.FoodItem;
import com.main.dinedroid.menu.Menu;
import com.main.dinedroid.models.Order;
import com.main.dinedroid.models.Table;


public class KitchenController {
	private int GET_MENU = 1;
	private int GET_ORDERS = 2;
	private int SET_AVAILABILITY = 1;
	private int SET_ORDER_STATUS = 2;
	Menu menu = new Menu();
	ArrayList<Table> tables = new ArrayList<Table>();
	int[] commands = new int[3];
	
	public KitchenController()
	{
		//Load menu from server
		//Load orders from server
		getMenu();
		getOrders();
	}
	
	public void getMenu(){
		Thread t = new Thread(new ServerPuller(GET_MENU));
		t.run();
	}
	
	public void getOrders(){
		Thread t = new Thread(new ServerPuller(GET_ORDERS));
		t.run();
	}

	public void setMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.menu = menu;
	}
	
	public void setAvailability(int itemId, boolean availability){
		FoodItem item = menu.findItem(itemId);
		item.setAvailable(availability);
		Thread t = new Thread(new ServerPusher(SET_AVAILABILITY,itemId,availability));
	}

	public void setTables(ArrayList<Table> tables) {
		for(Table t : tables){
			if(t.getOrder() != null){
				tables.add(t);
			}
		}
		// TODO Auto-generated method stub
	}
	 public void setOrderStatus(int tableId, int status){
		 for(Table t : tables){
			 if(t.getId() == tableId){
				 t.setOrderStatus(status);
			 }
		 }
		 Thread t = new Thread(new ServerPusher(SET_ORDER_STATUS,tableId,status));
		 t.run();
	 }

}
