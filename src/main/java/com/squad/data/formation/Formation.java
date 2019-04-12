package com.squad.data.formation;

import com.squad.data.Player;
import com.squad.data.enums.Position;
import com.squad.data.enums.Schema;

import java.util.Map;

public interface Formation {

    Schema getSchema();

    Map<Position, Player> getSquad();
}
