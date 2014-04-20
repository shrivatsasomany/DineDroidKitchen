package com.main.dinedroid.kitchen;

import java.util.ArrayList;

import com.main.dinedroid.kitchencom.ServerPuller;
import com.main.dinedroid.kitchencom.ServerPusher;
import com.main.dinedroid.kitchenlisteners.OrderChangeListener;
import com.main.dinedroid.menu.FoodItem;
import com.main.dinedroid.menu.Menu;
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
		downloadMenu();
		downloadOrders();
	}

	public void downloadMenu(){
		Thread t = new Thread(new ServerPuller(GET_MENU));
		t.run();
	}

	public void downloadOrders(){
		Thread t = new Thread(new ServerPuller(GET_ORDERS));
		t.run();
	}
	
	public ArrayList<Table> getTables()
	{
		return tables;
	}
	
	public Menu getMenu()
	{
		return menu;
	}
	

	public boolean addOrder(Table t)
	{
		for(int i = 0; i < tables.size(); ++i)
		{
			if(tables.get(i).equals(t))
			{
				tables.set(i, t);
				return true;
			}
		}
		boolean result = tables.add(t);
		callChangedListeners("Order");
		return result;
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

	/**
	 * List of changed listeners for waiters
	 */
	private ArrayList<OrderChangeListener> changedListeners = new ArrayList <OrderChangeListener>();
	/**
	 * Add a change listener to the list of listeners
	 * @param l A change listener
	 */
	public void addChangedListener(OrderChangeListener l)
	{
		changedListeners.add(l);
	}

	/**
	 * Call the changed listeners in the list and invoke DoSomething for each
	 * @param changeType A string
	 */
	public void callChangedListeners(String changeType)
	{
		for (int i = 0;i < changedListeners.size();++i)
		{
			changedListeners.get(i).DoSomething(changeType);
		}
	}

}
