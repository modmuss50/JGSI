package me.modmuss50.jgsi.api;

import me.modmuss50.jgsi.GameStateIntegrationImpl;
import me.modmuss50.jgsi.api.models.GameState;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.util.function.Consumer;

public interface GameStateIntegration {

	static GameStateIntegration create() {
		return GameStateIntegrationImpl.create();
	}

	GameStateIntegration setPort(int port);

	GameStateIntegration start() throws IOException;

	GameStateIntegration onUpdate(Consumer<GameState> state);

	@Nullable GameState getSate();

}
