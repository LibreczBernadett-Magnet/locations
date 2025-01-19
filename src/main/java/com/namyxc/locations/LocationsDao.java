package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@AllArgsConstructor
public class LocationsDao {

    private JdbcTemplate jdbcTemplate;

    private static Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        double lat = rs.getDouble("lat");
        double lon = rs.getDouble("lon");
        return new Location(id, name, lat, lon);
    }

    public List<Location> findAll() {
        return jdbcTemplate.query("select id, name, lat, lon from locations",
                LocationsDao::mapRow);
    }

    public Location getLocation(long id) {
        return jdbcTemplate.queryForObject("select id, name, lat, lon from locations where id = ?",
                LocationsDao::mapRow,
                id);
    }

    public Location createLocation(Location location) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement("insert into locations(name, lat, lon) values (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, location.getName());
                    ps.setDouble(2, location.getLat());
                    ps.setDouble(3, location.getLon());
                    return ps;
                }, keyHolder
        );

        location.setId(keyHolder.getKey().longValue());
        return location;
    }

    public Location updateLocation(long id, Location location) {
        jdbcTemplate.update("update locations set name = ?, lat = ?, lon = ? where id = ?",
                location.getName(), location.getLat(), location.getLon(), id);

        return getLocation(id);
    }

    public void delete(long id) {
        jdbcTemplate.update("delete from locations where id = ?", id);
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from locations");
    }
}
