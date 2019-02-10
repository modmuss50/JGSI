package me.modmuss50.jgsi;

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.models.Auth;
import me.modmuss50.jgsi.api.models.GameState;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class GameStateIntegrationImpl implements GameStateIntegration {

	private int port = 8181;
	private List<Consumer<GameState>> updateListeners = new ArrayList<>();
	private List<Consumer<String>> rawListeners = new ArrayList<>();
	private List<Predicate<Auth>> authPredicates = new ArrayList<>();

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
	public GameStateIntegration onUpdateRaw(Consumer<String> rawPayload) {
		rawListeners.add(rawPayload);
		return this;
	}

	@Override
	public GameState getSate() {
		return gameState;
	}

	void handle(GameState gameState, String raw) {
		this.gameState = gameState;
		updateListeners.forEach(gameStateConsumer -> gameStateConsumer.accept(gameState));
		rawListeners.forEach(stringConsumer -> stringConsumer.accept(raw));
	}

	@Override
	public GameStateIntegrationImpl setPort(int port) {
		this.port = port;
		return this;
	}

	@Override
	public GameStateIntegration validateAuth(Predicate<Auth> authPredicate) {
		authPredicates.add(authPredicate);
		return this;
	}

	boolean isValidAuth(Auth auth) {
		if (authPredicates.isEmpty()) {
			return true;
		}
		return authPredicates.stream().anyMatch(authPredicate -> authPredicate.test(auth));
	}

	public int getPort() {
		return port;
	}

	@Nullable
	public HTTPServer getHttpServer() {
		return httpServer;
	}
}
