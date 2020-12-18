create table IF NOT EXISTS scores
(
    playerId integer not null AUTO_INCREMENT,
    playerName varchar(255) not null,
    score integer,
    ISACTIVE boolean,
    primary key(playerId)
);

create table IF NOT EXISTS break
(
    breakscore integer,
    current boolean
);
