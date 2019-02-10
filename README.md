# Java Game State Integration (CSGO)

Java Game State Integration is a library that can be used with CSGO's gamestate integration.

The API needs documenting, and expanding on (Im not sure ive gotten all the values that the game provides).

## How to use

```java
package me.modmuss50.jgsi.test;

import me.modmuss50.jgsi.api.GameStateIntegration;
import me.modmuss50.jgsi.api.models.GameState;

import java.io.IOException;

public class JavaGameStateIntegrationTest {

	public static void main(String[] args) throws IOException {
		GameStateIntegration
			.create()
			.onUpdate(JavaGameStateIntegrationTest::handle)
			.start();
	}

	private static void handle(GameState gameState){
		System.out.println(gameState.map.name);
	}

}
```

This is a basic example, that calls `handle` everytime the game updates the state. This test app is also included in `src/test/java`.
## Setting up the game

The following needs to be placed into a file called `gamestate_integration_java.cfg` located in `steamapps\common\Counter-Strike Global Offensive\csgo\cfg`

```properties 
"GameSate Integration"
{
 "uri" "http://localhost:8181"
 "timeout" "5.0"
 "buffer"  "0.1"
 "throttle" "0.1"
 "heartbeat" "30.0"
 "auth"
 {
   "token" "TestPassword"
 }
 "data"
 {
   "provider"            "1"
   "map"                 "1"
   "round"               "1"
   "player_id"           "1"
   "allplayers_id"       "1"     
   "player_state"        "1"      
   "allplayers_state"    "1"      
   "allplayers_match_stats"  "1"  
   "allplayers_weapons"  "1"      
   "allplayers_position" "1"      
   "phase_countdowns"    "1"      
   "allgrenades"    "1" 
 }
}
```

The auth token is currently ignored, this will be implemented in the api soon.

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

Add my maven to your repositories and then depend on jgsi. It will pull in the extra deps as required