package com.namyxc.locations;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute(
                "create table locations (id bigint auto_increment, name varchar(255), lat double precision, lon double precision, primary key(id))"
        );

        jdbcTemplate.execute(
                "insert into locations (name, lat, lon) values ('Loc 1', 1, 1)"
        );

        jdbcTemplate.execute(
                "insert into locations (name, lat, lon) values ('Loc 2', 2, 2)"
        );
    }
}
