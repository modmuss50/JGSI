# Java Game State Integration (CSGO)

Java Game State Integration is a library that can be used with CSGO's gamestate integration.

The API needs documenting, and expanding on (Im not sure ive gotten all the values that the game provides).

## Using with gradle

```groovy
repositories {
    mavenCentral()
	maven {
		name "modmuss50"
		url 'http://maven.modmuss50.me'
	}
}

dependencies {
    compile 'me.modmuss50:jgsi:+'
}
```


## How to use

### Basic example

```java
import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.models.GameState;

import java.io.IOException;

public class JavaGameStateIntegrationTest {

	public static void main(String[] args) {
		GameStateIntegration
			.create()
			.onUpdate(JavaGameStateIntegrationTest::handle)
			.start();
	}

	private static void handle(GameState gameState){
		if(gameState.getMap() != null){
			System.out.println(gameState.getMap().getName());
		}
	}

}
```

This is a basic example, that calls `handle` everytime the game updates the state. This test app is also included in `src/test/java`.

### Round Tracker

```java

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.RoundTracker;

import java.io.IOException;

public class JavaGameStateIntegrationRoundTrackerExample {

	public static void main(String[] args) {
		GameStateIntegration integration = GameStateIntegration.create().start();
		RoundTracker tracker = RoundTracker.create(integration);
	
		tracker.streamRounds().forEach(gameState -> System.out.println("Round " + gameState.getMap().getRound()));
		System.out.println("Current round: " + tracker.getCurrentRound());
	}

}
```

The round tracker can be used to access the GameSate of any past rounds, as well as the current round, this is useful when you want to create stats based of the history of the player stats.

## Setting up the game

The following needs to be placed into a file called `gamestate_integration_java.cfg` located in `steamapps\common\Counter-Strike Global Offensive\csgo\cfg`

```properties 
"GameSate Integration"
{
 "uri" "http://localhost:8181"
 "timeout" "5.0"
 "buffer"  "1"
 "throttle" "0.1"
 "heartbeat" "30.0"
 "auth"
 {
   "token" "TestPassword"
 }
 "data"
 {
   "provider"            	"1"
   "map"                 	"1"
   "round"               	"1"
   "player_id"           	"1"
   "allplayers_id"       	"1"     
   "player_state"        	"1"      
   "allplayers_state"    	"1"      
   "allplayers_match_stats" "1"  
   "allplayers_weapons"  	"1"      
   "allplayers_position" 	"1"      
   "phase_countdowns"    	"1"      
   "allgrenades"    		"1" 
   "bomb" 					"1"  
 }
}
```

The auth token can be validated by using `validateAuth`

