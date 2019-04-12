package com.squad.data.generator;

import com.mifmif.common.regex.Generex;
import com.squad.data.Player;
import com.squad.data.dao.PlayerDao;
import com.squad.data.enums.Position;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class DataGenerator {

    private final static int PLAYER_COUNT = 100;
    private final static int PLAYER_MIN_RATING = 50;
    private final static int PLAYER_MAX_RATING = 99;
    private final static int PLAYER_MIN_PRICE = 200;
    private final static int PLAYER_MAX_PRICE = 2_000_000;
    private final static int NATION_COUNT = 20;
    private final static int LEAGUE_COUNT = 5;
    private final static int CLUB_IN_LEAGUE_COUNT = 20;
    private final static String NAMING_PATTERN = "[A-Z]{3}";
    private final static Generex GENEREX = new Generex(NAMING_PATTERN);
    private final static Random RND = new Random();

    public static void generate() {

        List<String> nations = generateNations();
        List<String> leagues = generateLeagues();
        Map<String, List<String>> clubs = generateClubs(leagues);

        PlayerDao playerDao = PlayerDao.getInstance();
        List<Player> generatedPlayers = IntStream.range(0, PLAYER_COUNT)
                .boxed()
                .map(x -> generatePlayer(nations, leagues, clubs))
                .collect(toList());
        playerDao.setPlayers(generatedPlayers);
    }

    private static List<String> generateNations() {
        return IntStream.range(0, NATION_COUNT + 1)
                .boxed()
                .map(x -> GENEREX.random())
                .collect(toList());

    }

    private static List<String> generateLeagues() {
        return IntStream.range(0, LEAGUE_COUNT)
                .boxed()
                .map(x -> GENEREX.random())
                .collect(toList());
    }

    private static Map<String, List<String>> generateClubs(List<String> leagues) {
        return leagues.stream()
                .collect(toMap(league -> league,
                        league -> IntStream.range(0, CLUB_IN_LEAGUE_COUNT)
                                .boxed()
                                .map(x -> GENEREX.random())
                                .collect(toList())));
    }

    private static Player generatePlayer(List<String> nations, List<String> leagues, Map<String, List<String>> clubs) {
        UUID name = UUID.randomUUID();
        Position position = randomEnum(Position.class);
        String nation = nations.get(RND.nextInt(nations.size()));
        String league = leagues.get(RND.nextInt(leagues.size()));
        List<String> clubsInLeague = clubs.get(league);
        String club = clubsInLeague.get(RND.nextInt(clubsInLeague.size()));
        int rating = ThreadLocalRandom.current().nextInt(PLAYER_MIN_RATING, PLAYER_MAX_RATING + 1);
        int price = ThreadLocalRandom.current().nextInt(PLAYER_MIN_PRICE, PLAYER_MAX_PRICE + 1);
        return new Player.Builder()
                .withName(name)
                .withPosition(position)
                .withNation(nation)
                .withLeague(league)
                .withClub(club)
                .withRating(rating)
                .withPrice(price)
                .build();
    }

    private static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = RND.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
