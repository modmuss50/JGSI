package me.modmuss50.jgsi.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.modmuss50.jgsi.api.GameStateIntegration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//This is used to find missing data points
public class JavaGameStateIntegrationValidation {

	private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private static GameStateIntegration integration;

	public static void main(String[] args) throws IOException {
		integration =
			GameStateIntegration.create()
				.validateAuth(auth -> auth.getToken().equals("TestPassword"))
				.onUpdateRaw(JavaGameStateIntegrationValidation::raw) //This line can be enabled to print out the raw json payload, this can be useful for testing
				.start();
	}

	private static void raw(String raw) {
		JsonObject rawJsonObject = GSON.fromJson(raw, JsonObject.class);
		String rawJson = GSON.toJson(rawJsonObject);
		String stateJson = GSON.toJson(integration.getSate());
		if (!rawJson.equals(stateJson)) {
			try {
				FileUtils.writeStringToFile(new File("raw.json"), rawJson, StandardCharsets.UTF_8);
				FileUtils.writeStringToFile(new File("state.json"), stateJson, StandardCharsets.UTF_8);
				System.out.println("Diff found");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		try {
			FileUtils.writeStringToFile(new File("live.json"), raw, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
