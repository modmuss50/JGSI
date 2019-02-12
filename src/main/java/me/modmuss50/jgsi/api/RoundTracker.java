package me.modmuss50.jgsi.api;

import me.modmuss50.jgsi.RoundTrackerImpl;
import me.modmuss50.jgsi.api.models.GameState;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.stream.Stream;

/**
 * This can be used to store all of the previous rounds, as well as the current round.
 * When the map resets all of the previous rounds will be removed, if the game is rolled back it will remove the extra rounds.
 */
public interface RoundTracker {

	/**
	 * This is used to create a new RoundTracker
	 *
	 * @param gameStateIntegration pass your instance of GameStateIntegration, this will register the tracker with GSI
	 * @return a new RoundTracker
	 */
	static RoundTracker create(GameStateIntegration gameStateIntegration) {
		return new RoundTrackerImpl(gameStateIntegration);
	}

	/**
	 * This is used to get a specific round
	 *
	 * @param round the round number
	 * @return the lastest GameState for that round
	 * @throws IndexOutOfBoundsException when the round is not stored
	 */
	GameState getRound(int round) throws IndexOutOfBoundsException;

	/**
	 * @return the current round, can be null
	 */
	@Nullable
	GameState getCurrentRoundState();

	/**
	 * @return returns a list of all the rounds that are stored
	 */
	List<Integer> getRounds();

	/**
	 * @return a Stream with all the GameStates that are saved in the tracker.
	 */
	Stream<GameState> streamRounds();

	/**
	 * @return gets the current round number, if there isnt a round saved this will be -1
	 */
	int getCurrentRound();

	/**
	 * Clears out all of the saved rounds.
	 */
	void reset();

}
