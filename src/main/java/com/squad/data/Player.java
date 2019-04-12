package com.squad.data;

import com.google.common.base.Objects;
import com.squad.data.enums.Position;

import java.util.UUID;

public class Player {
    private final UUID name;
    private final Position position;
    private final String nation;
    private final String league;
    private final String club;
    private final int rating;
    private final int price;

    private Player(UUID name, Position position, String nation, String league, String club, int rating, int price) {
        this.name = name;
        this.position = position;
        this.nation = nation;
        this.league = league;
        this.club = club;
        this.rating = rating;
        this.price = price;
    }

    public UUID getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public String getNation() {
        return nation;
    }

    public String getLeague() {
        return league;
    }

    public String getClub() {
        return club;
    }

    public int getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return rating == player.rating &&
                price == player.price &&
                Objects.equal(name, player.name) &&
                position == player.position &&
                Objects.equal(nation, player.nation) &&
                Objects.equal(league, player.league) &&
                Objects.equal(club, player.club);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, position, nation, league, club, rating, price);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", position=" + position +
                ", nation='" + nation + '\'' +
                ", league='" + league + '\'' +
                ", club='" + club + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }

    public static class Builder {
        private UUID name;
        private Position position;
        private String nation;
        private String league;
        private String club;
        private int rating;
        private int price;
        
        public Builder withName(UUID name) {
            this.name = name;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }
        
        public Builder withNation(String nation) {
            this.nation = nation;
            return this;
        }
        
        public Builder withLeague(String league) {
            this.league = league;
            return this;
        }

        public Builder withClub(String club) {
            this.club = club;
            return this;
        }

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Player build() {
            return new Player(name, position, nation, league, club, rating, price);
        }
    }
}
