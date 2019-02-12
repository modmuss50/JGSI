package me.modmuss50.jgsi.api.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Grenade {

	long owner;
	String position;
	String velocity;
	String lifetime;
	String type;
	String effecttime;

}
