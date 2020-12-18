package com.rowney.billards.controller;

import com.rowney.billards.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class IndexController {

    @Autowired
    private SetupService setupService;

    @RequestMapping("/")
    public String index() throws SQLException {

        setupService.startUp();

        return "index";
    }
}
