package me.modmuss50.jgsi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Context;
import io.javalin.Javalin;
import me.modmuss50.jgsi.api.models.GameState;

import java.io.IOException;

class HTTPServer {

	private static final Gson GSON = new GsonBuilder().create();
	private GameStateIntegrationImpl gameStateIntegration;
	private Javalin javalin;

	HTTPServer(GameStateIntegrationImpl integration) throws IOException {
		this.gameStateIntegration = integration;
		this.javalin = Javalin.create().start(integration.getPort());
		javalin.post("/", this::handlePost);
		System.out.println("Http server running @ http://localhost:" + integration.getPort());
	}

	private void handlePost(Context context) {
		String body = context.body();
		GameState gameState = GSON.fromJson(body, GameState.class);
		if (gameState != null) {
			if(!gameStateIntegration.isValidAuth(gameState.auth)){
				System.out.println("Forbidden connection from " + context.ip());
				context.status(403);
				return;
			}
			gameStateIntegration.handle(gameState, body);
		}
		context.status(200);

	}
}
