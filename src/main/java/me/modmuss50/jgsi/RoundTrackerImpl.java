package me.modmuss50.jgsi;

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.RoundTracker;
import me.modmuss50.jgsi.api.models.GameState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoundTrackerImpl implements RoundTracker {

	Map<Integer, GameState> roundMap = new HashMap<>();
	GameStateIntegration gameStateIntegration;

	public RoundTrackerImpl(GameStateIntegration gameStateIntegration) {
		this.gameStateIntegration = gameStateIntegration;
		gameStateIntegration.onUpdate(this::handle);
	}

	public void handle(GameState gameState){
		if(gameState == null || gameState.getMap() == null){
			return;
		}
		int currentRound = gameState.getMap().getRound();
		if(currentRound < getCurrentRound()){
			getRounds().forEach(round -> {
				if(round > currentRound){
					roundMap.remove(round);
				}
			});
		}
		//Sets the current round to
		roundMap.put(currentRound, gameState);
	}

	@Override
	public GameState getRound(int round) throws IndexOutOfBoundsException {
		if(!roundMap.containsKey(round)){
			throw new IndexOutOfBoundsException(String.format("Round %d has not been tracked"));
		}
		return roundMap.get(round);
	}

	@Override
	public GameState getCurrentRoundState() {
		return getRound(getCurrentRound());
	}

	@Override
	public List<Integer> getRounds() {
		return roundMap.values().stream()
			.filter(Objects::nonNull)
			.filter(gameState -> gameState.getMap() != null)
			.mapToInt(value -> value.getMap().getRound())
			.boxed()
			.collect(Collectors.toList());
	}

	@Override
	public Stream<GameState> streamRounds() {
		return roundMap.values().stream();
	}

	@Override
	public int getCurrentRound() {
		return roundMap.values().stream()
			.filter(Objects::nonNull)
			.filter(gameState -> gameState.getMap() != null)
			.mapToInt(value -> value.getMap().getRound())
			.max().orElse(-1);
	}

	@Override
	public void reset() {
		roundMap.clear();
	}
}
