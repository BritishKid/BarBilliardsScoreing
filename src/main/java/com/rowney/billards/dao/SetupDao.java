package com.rowney.billards.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class SetupDao extends CommonDao {

    private Statement statement;

    public void setup() throws SQLException {
        statement = createConnection();

        String sql1 = "create table IF NOT EXISTS scores (playerId integer not null AUTO_INCREMENT, playerName varchar(255) not null, score integer, isactive boolean, primary key(playerId))";
        String sql2 = "create table IF NOT EXISTS break (breakscore integer, current boolean)";

        statement.execute(sql1);
        statement.execute(sql2);

    }
}
