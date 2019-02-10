package me.modmuss50.jgsi.api;

import me.modmuss50.jgsi.GameStateIntegrationImpl;
import me.modmuss50.jgsi.api.models.Auth;
import me.modmuss50.jgsi.api.models.GameState;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface GameStateIntegration {

	/**
	 *
	 * This is used to get a new instance of GameStateIntegration
	 *
	 * @return a new GameStateIntegration
	 */
	static GameStateIntegration create() {
		return GameStateIntegrationImpl.create();
	}

	/**
	 *
	 * This can be used to set the listening port of the http server the default is (8181)
	 *
	 * @param port the port of the server
	 * @return GameStateIntegration
	 */
	GameStateIntegration setPort(int port);

	/**
	 *
	 * This can be used to validate the auth, this should be used in production otherwise anyone could send bogus data
	 *
	 * @param authPredicate a Predicate that should only return true if the token in Auth is valid
	 * @return GameStateIntegration
	 */
	GameStateIntegration validateAuth(Predicate<Auth> authPredicate);

	/**
	 *
	 * This starts the http server
	 *
	 * @return GameStateIntegration
	 * @throws IOException when the server fails to start
	 */
	GameStateIntegration start() throws IOException;

	/**
	 *
	 * This is used to register a consumer to handle the GameState each time the game sends the data
	 *
	 * @param state a consumer that handles GameState
	 * @return GameStateIntegration
	 */
	GameStateIntegration onUpdate(Consumer<GameState> state);

	/**
	 *
	 * This is used to get the raw json string from the game. This can be used for testing, or for passing on this data to another application
	 *
	 * @param rawPayload the raw json string
	 * @return GameStateIntegration
	 */
	GameStateIntegration onUpdateRaw(Consumer<String> rawPayload);

	/**
	 *
	 * You can use this to get the last game state, this can be null
	 *
	 * @return the last sent GameSate
	 */
	@Nullable GameState getSate();

}
