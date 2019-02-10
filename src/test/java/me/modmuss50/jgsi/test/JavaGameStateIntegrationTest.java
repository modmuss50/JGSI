package me.modmuss50.jgsi.test;

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.models.GameState;

import java.io.IOException;

public class JavaGameStateIntegrationTest {

	public static void main(String[] args) throws IOException {
		GameStateIntegration
			.create()
			.onUpdate(JavaGameStateIntegrationTest::handle)
			.validateAuth(auth -> auth.getToken().equals("TestPassword"))
			//.onUpdateRaw(System.out::println) //This line can be enabled to print out the raw json payload, this can be useful for testing
			.start();
	}

	private static void handle(GameState gameState){
		System.out.println(gameState.getMap().getName());
	}

}
