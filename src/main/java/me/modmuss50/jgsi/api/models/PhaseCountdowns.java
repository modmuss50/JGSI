package me.modmuss50.jgsi.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class PhaseCountdowns {

	String phase;
	@SerializedName("phase_ends_in")
	String phaseEndsIn;

}
