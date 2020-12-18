package com.rowney.billards.service;

import com.rowney.billards.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;

    public void addNewPlayer(String playerName) throws SQLException {

        playerDao.addNewPlayer(playerName);

    }

    public void clearPlayers() {

        //remove all players from database
        playerDao.clearPlayers();
    }

    public List<String> getAllPLayers() {

        //get all player used to start game state

        return playerDao.getAllPlayers();
    }
}
