package com.rowney.billards.model;

public class Score {

    private int playerId;
    private String playerName;
    private int score;
    private boolean activePlayer;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setActivePlayer(boolean activePlayer) {
        this.activePlayer = activePlayer;
    }

    public boolean isActivePlayer() {
        return activePlayer;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
