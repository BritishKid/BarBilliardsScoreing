package com.rowney.billards.service;

import com.rowney.billards.dao.SetupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SetupService {

@Autowired
private SetupDao setupDao;

    public void startUp() throws SQLException {

        setupDao.setup();
    }
}
