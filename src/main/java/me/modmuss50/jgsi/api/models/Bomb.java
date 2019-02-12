package me.modmuss50.jgsi.api.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Bomb {

	String state;
	String position;
	long player;

}
