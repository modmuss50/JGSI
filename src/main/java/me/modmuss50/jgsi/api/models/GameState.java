package me.modmuss50.jgsi.api.models;

public class GameState {

	public Provider provider;
	public Map map;
	public Round round;
	public Player player;
	public java.util.Map<Long, Player> allplayers;
	public PhaseCountdowns phase_countdowns;
	public java.util.Map<String, Grenade> grenades;
	public Auth auth;

}
