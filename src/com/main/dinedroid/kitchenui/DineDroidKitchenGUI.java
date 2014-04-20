package com.main.dinedroid.kitchenui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.dinedroid.swing.CascadingJFrame;

import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DineDroidKitchenGUI extends CascadingJFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnOk;
	private JRadioButton rdbtnDelayed;
	private JRadioButton rdbtnProblem;
	private JPanel button_panel;
	private JLabel lblNewLabel;
	private JLabel orderInfoTitle;
	private JLabel lblStatus;
	private JList orderList;
	private JTextPane orderInfoTextPane;
	private JMenuBar menuBar;
	private JMenu mnSettings;
	private JMenu mnRefresh;
	private JMenuItem mntmRefreshOrders;
	private JMenuItem mntmRefreshMenu;
	private JMenuItem mntmSetServerAddress;

	/**
	 * Create the frame.
	 */
	public DineDroidKitchenGUI() {
		super("DineDroid Kitchen");
		setResizable(false);
		setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 386);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		tabbedPane.setBounds(6, 6, 520, 339);
		contentPane.add(tabbedPane);

		ButtonGroup statusButtons = new ButtonGroup();
		statusButtons.add(getRdbtnOk());
		statusButtons.add(getRdbtnDelayed());
		statusButtons.add(getRdbtnProblem());

		JPanel order_panel = new JPanel();
		tabbedPane.addTab("Orders", null, order_panel, null);
		order_panel.setLayout(null);

		JScrollPane orderScrollPane = new JScrollPane();
		orderScrollPane.setBounds(6, 32, 116, 255);
		order_panel.add(orderScrollPane);
		orderScrollPane.setViewportView(getOrderList());

		JScrollPane orderInfoScrollPane = new JScrollPane();
		orderInfoScrollPane.setBounds(126, 32, 257, 255);
		order_panel.add(orderInfoScrollPane);
		orderInfoScrollPane.setViewportView(getOrderInfoTextPane());
		order_panel.add(getButton_panel());
		order_panel.add(getLblNewLabel());
		order_panel.add(getOrderInfoTitle());
		order_panel.add(getLblStatus());

		JPanel menu_panel = new JPanel();
		tabbedPane.addTab("Menu", null, menu_panel, null);
		menu_panel.setLayout(null);
		menu_panel.add(getScrollPane());
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 6, 487, 281);
		}
		return scrollPane;
	}

	private JRadioButton getRdbtnOk() {
		if (rdbtnOk == null) {
			rdbtnOk = new JRadioButton("OK");
			rdbtnOk.setBounds(6, 6, 88, 23);
			rdbtnOk.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return rdbtnOk;
	}

	private JRadioButton getRdbtnDelayed() {
		if (rdbtnDelayed == null) {
			rdbtnDelayed = new JRadioButton("Delayed");
			rdbtnDelayed.setBounds(6, 33, 88, 23);
			rdbtnDelayed.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return rdbtnDelayed;
	}

	private JRadioButton getRdbtnProblem() {
		if (rdbtnProblem == null) {
			rdbtnProblem = new JRadioButton("Problem");
			rdbtnProblem.setBounds(6, 60, 88, 23);
			rdbtnProblem.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return rdbtnProblem;
	}

	private JPanel getButton_panel() {
		if (button_panel == null) {
			button_panel = new JPanel();
			button_panel.setBounds(387, 32, 106, 255);
			button_panel.setLayout(null);
			button_panel.add(getRdbtnOk());
			button_panel.add(getRdbtnDelayed());
			button_panel.add(getRdbtnProblem());
		}
		return button_panel;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Orders");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(6, 6, 116, 16);
			lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return lblNewLabel;
	}

	private JLabel getOrderInfoTitle() {
		if (orderInfoTitle == null) {
			orderInfoTitle = new JLabel("Select an order");
			orderInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
			orderInfoTitle.setBounds(134, 6, 249, 16);
			orderInfoTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return orderInfoTitle;
	}

	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("Status");
			lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
			lblStatus.setBounds(387, 6, 106, 16);
			lblStatus.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return lblStatus;
	}

	private JList getOrderList() {
		if (orderList == null) {
			orderList = new JList();
			orderList.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return orderList;
	}

	private JTextPane getOrderInfoTextPane() {
		if (orderInfoTextPane == null) {
			orderInfoTextPane = new JTextPane();
			orderInfoTextPane
					.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		}
		return orderInfoTextPane;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnRefresh());
			menuBar.add(getMnSettings());
		}
		return menuBar;
	}

	private JMenu getMnSettings() {
		if (mnSettings == null) {
			mnSettings = new JMenu("Settings");
			mnSettings.add(getMntmSetServerAddress());
		}
		return mnSettings;
	}

	private JMenu getMnRefresh() {
		if (mnRefresh == null) {
			mnRefresh = new JMenu("Refresh");
			mnRefresh.add(getMntmRefreshOrders());
			mnRefresh.add(getMntmRefreshMenu());
		}
		return mnRefresh;
	}

	private JMenuItem getMntmRefreshOrders() {
		if (mntmRefreshOrders == null) {
			mntmRefreshOrders = new JMenuItem("Refresh Orders");
			mntmRefreshOrders.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return mntmRefreshOrders;
	}

	private JMenuItem getMntmRefreshMenu() {
		if (mntmRefreshMenu == null) {
			mntmRefreshMenu = new JMenuItem("Refresh Menu");
			mntmRefreshMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return mntmRefreshMenu;
	}

	private JMenuItem getMntmSetServerAddress() {
		if (mntmSetServerAddress == null) {
			mntmSetServerAddress = new JMenuItem("Set Server Address");
			mntmSetServerAddress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return mntmSetServerAddress;
	}
}
