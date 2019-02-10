package me.modmuss50.jgsi.api.models;

import java.util.Map;

public class Player {

	public long steamid;
	public String name;
	public int observer_slot;
	public String team;
	public String activity;
	public Sate state;
	public MatchStats match_stats;
	public Map<String, Weapon> weapons;
	public String position;
	public String forward;

	public class Sate {

		public int health;
		public int armor;
		public boolean helmet;
		public int flashed;
		public int smoked;
		public int burning;
		public int money;
		public int round_kills;
		public int round_killhs;
		public int round_totaldmg;
		public int equip_value;
	}

	public class MatchStats {

		public int kills;
		public int assists;
		public int deaths;
		public int mvps;
		public int score;
	}

	public class Weapon {

		public String name;
		public String paintkit;
		public String type;
		public int ammo_clip;
		public int ammo_clip_max;
		public int ammo_reserve;
		public String state;
	}

}
