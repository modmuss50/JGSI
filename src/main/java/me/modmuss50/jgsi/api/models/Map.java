package me.modmuss50.jgsi.api.models;

public class Map {

	public String mode;
	public String name;
	public String phase;
	public int round;
	public Team team_ct;
	public Team team_t;
	public int num_matches_to_win_series;
	public int current_spectators;
	public int souvenirs_total;

	public class Team {

		public int score;
		public int timeouts_remaining;
		public int matches_won_this_series;
	}

}
