package com.rowney.billards.controller;

import com.rowney.billards.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping("/player/new")
    public String addNewPlayer(@RequestParam(name = "player") String playerName) throws SQLException {

        playerService.addNewPlayer(playerName);

        return "newplayer";
    }

    @RequestMapping("/player/create")
    public String createNewPlayer() throws SQLException {

        return "newplayer";
    }




}
