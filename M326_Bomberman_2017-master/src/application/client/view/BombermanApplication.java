package application.client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import application.client.control.BombermanController;
import application.client.model.BombermanModel;
import application.server.Server;
import application.server.exceptions.ServerInitializationFailureException;
import global.models.Tile;
import network.communication.Channel;
import network.messaging.client.StartGameMessage;
import network.messaging.server.JoinGameMessage;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class BombermanApplication {

	private JFrame frmBomberman;
	private JTextField txtIP;
	private JTextField txtUserName;
	private JTextField txtPort;
	private JPanel playgroundPanel;
	private JPanel bottomPanel;
	private JLabel lblUsername;
	private JLabel lblIpadresse;
	private JLabel lblPort;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private BombermanController controller;
	private BombermanModel model;
	private Channel channel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BombermanApplication window = new BombermanApplication();
					window.frmBomberman.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BombermanApplication() {
		initialize();
	}

	public BombermanApplication(BombermanModel model) {
		this.model = model;
		initialize();
		this.frmBomberman.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBomberman = new JFrame();
		frmBomberman.setTitle("Bomberman");
		frmBomberman.setBounds(100, 100, 993, 735);
		frmBomberman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmBomberman.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		lblUsername = new JLabel("Username:");
		topPanel.add(lblUsername);
		
		lblIpadresse = new JLabel("IP-Adresse:");
		topPanel.add(lblIpadresse);
		
		lblPort = new JLabel("Port:");
		topPanel.add(lblPort);
		
		JButton btnCreateServer = new JButton("Server");
		btnCreateServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Server(txtIP.getText(), Integer.parseInt(txtPort.getText()));
				} catch (ServerInitializationFailureException e1) {
					e1.printStackTrace();
				}

				// TODO was soll geschehen falls der User mehrmalls auf Verbinden klickt?
				
				try {
					channel = new Channel(txtIP.getText(), Integer.parseInt(txtPort.getText()), controller);
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				channel.send(new JoinGameMessage(txtUserName.getText()));
			}
			
		});
		
		topPanel.add(btnCreateServer);
		
		txtUserName = new JTextField();
		txtUserName.setText("ich");
		txtUserName.setToolTipText("Choose your username");
		topPanel.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		txtIP.setToolTipText("Add your IP address");
		topPanel.add(txtIP);
		txtIP.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setToolTipText("Add your port");
		txtPort.setText("5000");
		topPanel.add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//System.out.println("hallo");
				String s = txtPort.getText();
				int p = Integer.parseInt(s);
				try {
					// TODO was soll geschehen falls der User mehrmalls auf Verbinden klickt?
					
					channel = new Channel(txtIP.getText(), p, controller);
					
					channel.send(new JoinGameMessage(txtUserName.getText()));
					
				} catch (InstantiationException | IllegalAccessException | IOException e1) {
					e1.printStackTrace();
				} 
			}
		});
		topPanel.add(btnLogin);
		
		playgroundPanel = new JPanel();
		playgroundPanel.setBackground(Color.ORANGE);
		frmBomberman.getContentPane().add(playgroundPanel, BorderLayout.CENTER);
		playgroundPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		bottomPanel = new JPanel();
		frmBomberman.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setRows(7);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		bottomPanel.add(scrollPane, BorderLayout.SOUTH);
		
	}

	public void setController(BombermanController controller) {
		this.controller = controller;
		
	}

	public void setMessageText(String string) {
		textArea.append(string + System.lineSeparator());
	}

	public void StartGameMessage(StartGameMessage message) {
		Tile[] tiles = message.getTiles();
	
		int tileHeight = playgroundPanel.getHeight() / message.getPlaygroundHeight();
		int tileWidth = playgroundPanel.getWidth() / message.getPlaygroundWidth();
		
		Color tilesColor;
		Graphics g = null;

		for (Tile tile : tiles) {
			int posX = tile.getPosition().horizontal * tileWidth;
			int posY = tile.getPosition().vertical * tileHeight;
			
			g.fillRect(posX, posY, tileWidth, tileHeight);				
			g.setColor(Color.BLACK);
		}
	}
}
