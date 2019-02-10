package me.modmuss50.jgsi.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Map {

	String mode;
	String name;
	String phase;
	int round;
	@SerializedName("team_ct")
	Team teamCounterTerrorist;
	@SerializedName("team_t")
	Team teamTerrorist;
	int num_matches_to_win_series;
	int current_spectators;
	int souvenirs_total;

	@Getter
	@ToString
	@EqualsAndHashCode
	public class Team {

		int score;
		@SerializedName("timeouts_remaining")
		int timeoutsRemaining;
		@SerializedName("matches_won_this_series")
		int seriesMatchesWon;
	}

}
