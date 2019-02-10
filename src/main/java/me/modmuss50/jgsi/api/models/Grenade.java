package me.modmuss50.jgsi.api.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Grenade {

	String lifetime;
	String effecttime;
	String position;
	String velocity;

}
