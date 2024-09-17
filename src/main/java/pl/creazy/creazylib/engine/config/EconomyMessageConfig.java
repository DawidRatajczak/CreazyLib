package pl.creazy.creazylib.engine.config;

import lombok.Getter;
import pl.creazy.creazylib.data.persistence.config.constraints.ConfigFile;
import pl.creazy.creazylib.data.persistence.config.constraints.ConfigVar;
import pl.creazy.creazylib.part.constraints.Part;

@Part
@Getter
@ConfigFile(name = "economy", path = "message")
public class EconomyMessageConfig {
  @ConfigVar("balance.get")
  private String balanceGet = "&aBalans: &e${AMOUNT}&a.";

  @ConfigVar("balance.set")
  private String balanceSet = "&aNowy balans gracza &e${PLAYER_NAME}&a: &e${AMOUNT}&a.";
}
