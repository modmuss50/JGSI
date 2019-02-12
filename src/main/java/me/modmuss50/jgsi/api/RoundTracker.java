package me.modmuss50.jgsi.api;

import me.modmuss50.jgsi.RoundTrackerImpl;
import me.modmuss50.jgsi.api.models.GameState;

import java.util.List;
import java.util.stream.Stream;

public interface RoundTracker {

	static RoundTracker create(GameStateIntegration gameStateIntegration){
		return new RoundTrackerImpl(gameStateIntegration);
	}

	GameState getRound(int round) throws IndexOutOfBoundsException;

	GameState getCurrentRoundState();

	List<Integer> getRounds();

	Stream<GameState> streamRounds();

	//Returns -1 if there are no rounds
	int getCurrentRound();

	void reset();

}
