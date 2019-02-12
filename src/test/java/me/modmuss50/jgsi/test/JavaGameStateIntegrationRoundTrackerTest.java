package me.modmuss50.jgsi.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.modmuss50.jgsi.RoundTrackerImpl;
import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.RoundTracker;
import me.modmuss50.jgsi.api.models.GameState;

import java.io.IOException;

//A hacky test for RoundTacker
public class JavaGameStateIntegrationRoundTrackerTest {

	private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	static RoundTracker tracker;

	public static void main(String[] args) throws IOException {
		GameStateIntegration integration = GameStateIntegration
			.create();

		tracker = RoundTracker.create(integration);
		tests((RoundTrackerImpl) tracker);
	}

	private static void tests(RoundTrackerImpl roundTracker){
		roundTracker.handle(createFakeGameState(0));
		roundTracker.handle(createFakeGameState(1));
		roundTracker.handle(createFakeGameState(2));
		roundTracker.handle(createFakeGameState(2));
		roundTracker.handle(createFakeGameState(2));

		System.out.println("Current round:");
		System.out.println(roundTracker.getCurrentRound());
		System.out.println("Rounds:");
		roundTracker.getRounds().forEach(System.out::println);

		roundTracker.handle(createFakeGameState(1));

		System.out.println("Rounds:");
		roundTracker.getRounds().forEach(System.out::println);
		System.out.println("Current round:");
		System.out.println(roundTracker.getCurrentRound());

		roundTracker.handle(createFakeGameState(0));
		System.out.println("Current round:");
		System.out.println(roundTracker.getCurrentRound());
	}


	private static GameState createFakeGameState(int round){
		JsonObject object = new JsonObject();
		JsonObject map = new JsonObject();
		map.addProperty("round", round);
		object.add("map", map);
		return GSON.fromJson(object, GameState.class);
	}

}
