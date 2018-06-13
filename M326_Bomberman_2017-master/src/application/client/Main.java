package application.client;

import application.client.control.BombermanController;
import application.client.model.BombermanModel;
import application.client.view.BombermanApplication;
import application.server.Server;

/**
 * 
 * @author c_key
 * This is the Main Class of the Bomberman project
 */
public class Main {

	static BombermanController controller;
	static BombermanModel model;
	static BombermanApplication view;
	
	public static void main(String[] args) {
		
		new Main();
	}
	
	public Main() {
		model = new BombermanModel();
		view = new BombermanApplication(model);
		controller = new BombermanController(model, view);
		
		view.setController(controller);
	}

}
