package application.client.control;

import application.client.model.BombermanModel;
import application.client.view.BombermanApplication;
import network.messaging.client.EndGameMessage;
import network.messaging.client.ErrorMessage;
import network.messaging.client.PlayerJoinedMessage;
import network.messaging.client.StartGameMessage;
import network.messaging.client.UpdateTilesMessage;
import network.messaging.handlers.IClientMessageHandler;
import network.messaging.handlers.IMessageHandler;

public class BombermanController implements IClientMessageHandler {

	private BombermanModel model;
	private BombermanApplication view;

	public BombermanController(BombermanModel model, BombermanApplication view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void PlayerJoinedMessageHandler(PlayerJoinedMessage message) {
		view.setMessageText(message.toString());		
	}

	@Override
	public void StartGameMessageHandler(StartGameMessage message) {
		view.setMessageText(message.toString());	
		view.StartGameMessage(message);
	}

	@Override
	public void UpdateTilesMessageHandler(UpdateTilesMessage message) {
		view.setMessageText(message.toString());		
	}

	@Override
	public void EndGameMessageHandler(EndGameMessage message) {
		view.setMessageText(message.toString());		
	}

	@Override
	public void ErrorMessageHandler(ErrorMessage message) {
		view.setMessageText(message.toString());		
	}
}
