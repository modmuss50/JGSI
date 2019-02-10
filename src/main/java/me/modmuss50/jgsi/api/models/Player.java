package me.modmuss50.jgsi.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public class Player {

	long steamid;
	String name;
	@SerializedName("observer_slot")
	int observerSlot;
	String team;
	String activity;
	Sate state;
	MatchStats match_stats;
	Map<String, Weapon> weapons;
	String position;
	String forward;

	@Getter
	@ToString
	@EqualsAndHashCode
	public class Sate {

		int health;
		int armor;
		boolean helmet;
		int flashed;
		int smoked;
		int burning;
		int money;
		@SerializedName("round_kills")
		int roundKills;
		@SerializedName("round_killhs")
		int roundHeadshots;
		@SerializedName("round_totaldmg")
		int roundTotalDamage;
		int equip_value;
	}

	@Getter
	@ToString
	@EqualsAndHashCode
	public class MatchStats {

		int kills;
		int assists;
		int deaths;
		int mvps;
		int score;
	}

	@Getter
	@ToString
	@EqualsAndHashCode
	public class Weapon {

		String name;
		String paintkit;
		String type;
		@SerializedName("ammo_clip")
		int ammoClip;
		@SerializedName("ammo_clip_max")
		int ammoClipMax;
		@SerializedName("ammo_reserve")
		int ammoReserve;
		String state;
	}

}
