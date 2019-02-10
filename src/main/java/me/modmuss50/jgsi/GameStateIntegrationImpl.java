package me.modmuss50.jgsi;

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.models.GameState;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameStateIntegrationImpl implements GameStateIntegration {

	private int port = 8181;
	private List<Consumer<GameState>> updateListeners = new ArrayList<>();

	@Nullable
	private HTTPServer httpServer;

	private GameState gameState;

	private GameStateIntegrationImpl() {

	}

	public static GameStateIntegration create() {
		return new GameStateIntegrationImpl();
	}

	public GameStateIntegrationImpl start() throws IOException {
		if (httpServer != null) {
			throw new RuntimeException("server already started");
		}
		httpServer = new HTTPServer(this);
		return this;
	}

	@Override
	public GameStateIntegration onUpdate(Consumer<GameState> state) {
		updateListeners.add(state);
		return this;
	}

	@Override
	public GameState getSate() {
		return gameState;
	}

	void handle(GameState gameState) {
		this.gameState = gameState;
		updateListeners.forEach(gameStateConsumer -> gameStateConsumer.accept(gameState));
	}

	@Override
	public GameStateIntegrationImpl setPort(int port) {
		this.port = port;
		return this;
	}

	public int getPort() {
		return port;
	}

	@Nullable
	public HTTPServer getHttpServer() {
		return httpServer;
	}
}
