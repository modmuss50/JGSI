package me.modmuss50.jgsi.test;

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.models.GameState;
import me.modmuss50.jgsi.api.models.Player;

import java.io.IOException;
import java.util.function.BiConsumer;

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
		if(gameState.getMap() != null){
			System.out.println(gameState.getMap().getName());
		}




		gameState.getAllplayers().forEach(new BiConsumer<Long, Player>() {
			@Override
			public void accept(Long id, Player player) {
				System.out.println(player);
			}
		});
	}

}
