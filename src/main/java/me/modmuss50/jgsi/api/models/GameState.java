package me.modmuss50.jgsi.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class GameState {

	Provider provider;
	Map map;
	Round round;
	Player player;
	java.util.Map<Long, Player> allplayers;
	@SerializedName("phase_countdowns")
	PhaseCountdowns phaseCountdowns;
	java.util.Map<String, Grenade> grenades;
	Auth auth;

}
