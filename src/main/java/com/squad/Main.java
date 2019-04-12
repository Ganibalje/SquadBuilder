package com.squad;

import com.squad.builder.SquadBuilder;
import com.squad.data.Player;
import com.squad.data.dao.PlayerDao;
import com.squad.data.enums.Position;
import com.squad.data.enums.Schema;
import com.squad.data.formation.Formation;
import com.squad.data.formation.FormationImpl;
import com.squad.data.generator.DataGenerator;

import java.util.EnumMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        DataGenerator.generate();

        PlayerDao instance = PlayerDao.getInstance();

        SquadBuilder squadBuilder = new SquadBuilder.Builder()
                .addPredicates(x -> true)
                .addSolver((formation, predicates) -> formation)
                .build();

        Map<Position, Player> playersByPosition = new EnumMap<>(Position.class);
        playersByPosition.put(Position.GK, instance.getRandomPlayer());

        FormationImpl formation = new FormationImpl(Schema.F4_4_2, playersByPosition);

        Map<Class, Formation> solved = squadBuilder.solve(formation);

        System.out.println(solved);
    }
}
