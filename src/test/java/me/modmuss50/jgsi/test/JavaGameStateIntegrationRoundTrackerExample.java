package me.modmuss50.jgsi.test;

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.RoundTracker;

import java.io.IOException;

//Super simple round tracker example
public class JavaGameStateIntegrationRoundTrackerExample {

	public static void main(String[] args) throws IOException {
		GameStateIntegration integration = GameStateIntegration.create().start();
		RoundTracker tracker = RoundTracker.create(integration);

		//Some time later in another part of your application once some data has come in

		//Prints out all the round numbers
		tracker.streamRounds().forEach(gameState -> System.out.println("Round " + gameState.getMap().getRound()));

		//Gets the current round
		System.out.println("Current round: " + tracker.getCurrentRound());
	}

}
