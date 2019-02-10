package me.modmuss50.jgsi.api.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Provider {

	String name;
	int appid;
	int version;
	long steamid;
	int timestamp;
}
