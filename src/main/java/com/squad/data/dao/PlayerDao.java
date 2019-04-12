package com.squad.data.dao;

import com.squad.data.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class PlayerDao {

    private final static Random RND = new Random();
    private static PlayerDao instance;

    public static PlayerDao getInstance() {
        if(instance == null) {
            instance = new PlayerDao();
        }
        return instance;
    }

    private PlayerDao() {
    }

    private List<Player> playerStorage = new ArrayList<>();

    public void setPlayers(List<Player> players) {
        this.playerStorage = players;
    }

    public void addPlayer(Player player) {
        if (!playerStorage.contains(player))
            this.playerStorage.add(player);
    }

    public List<Player> findPlayers(Predicate<Player> predicate) {
        return playerStorage.stream()
                .filter(predicate)
                .collect(toList());
    }

    public Player getRandomPlayer() {
        return playerStorage.get(RND.nextInt(playerStorage.size()));
    }
}
