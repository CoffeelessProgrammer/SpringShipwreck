package dev.koicreek.hellojdbc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/settlements")
public class SettlementsAPI implements InitializingBean {

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Settlements WHERE city_id=?";
    private static final String SQL_INSERT = "INSERT INTO Settlements (city_name, region, slogan) VALUES (?,?,?)";
    private static final String SQL_FIND_CITY_BY_NAME = "SELECT city_id FROM Settlements WHERE city_name=?";

    @Autowired
    DataSource dataSource;

    @Override
    public void afterPropertiesSet() {
        int totalSettlements = 0;

        try (Connection con = dataSource.getConnection(); Statement statement = con.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS SETTLEMENTS(" +
                            "city_id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "city_name TEXT NOT NULL," +
                            "region TEXT NOT NULL," +
                            "slogan TEXT)");

            totalSettlements = statement.executeUpdate("INSERT INTO Settlements (city_name, region, slogan) VALUES" +
                    "('Pallet Town', 'Kanto', 'A Pure White Beginning')," +
                    "('Viridian City', 'Kanto', 'The City of Evergreen')," +
                    "('Pewter City', 'Kanto', 'Between Rugged Mountains')," +
                    "('Cerulean City', 'Kanto', 'The Floral Lagoon City')," +
                    "('Vermilion City', 'Kanto', 'The Port of Exquisite Sunsets')," +
                    "('Lavender Town', 'Kanto', 'The Noble Town')," +
                    "('Celadon City', 'Kanto', 'City of the Rainbow''s Colors')," +
                    "('Fuchsia City', 'Kanto', 'Happening and Passing City')," +
                    "('Saffron City', 'Kanto', 'Shining Big City')," +
                    "('Cinnabar Island', 'Kanto', 'The Ravaged Town of the Past')," +
                    "('Indigo Plateau', 'Kanto', 'The ultimate Goal of Trainers!')," +
                    "('One Island', 'Sevii Islands', 'Friends Gather at Knot Island')," +
                    "('Two Island', 'Sevii Islands', 'Boon Island for Two')," +
                    "('Three Island', 'Sevii Islands', 'Kin Island of Family Bonding')," +
                    "('Four Island', 'Sevii Islands', 'The Warm, Blue, Floe Island')," +
                    "('Five Island', 'Sevii Islands', 'Chrono Island: Where Time Goes')," +
                    "('Six Island', 'Sevii Islands', 'Fortune Island of Aged Wisdom')," +
                    "('Seven Island', 'Sevii Islands', 'Quest Island of Infinity')," +
                    "('New Bark Town', 'Johto', 'Winds of a New Beginning')," +
                    "('Cherrygrove City', 'Johto', 'The City of Fragrant Flowers')," +
                    "('Violet City', 'Johto', 'The City of Nostalgic Scents')," +
                    "('Azalea Town', 'Johto', 'Living Happily with Pokémon')," +
                    "('Goldenrod City', 'Johto', 'A Happening Big City')," +
                    "('Ecruteak City', 'Johto', 'A Historical City')," +
                    "('Olivine City', 'Johto', 'The Port with Sea Breezes')," +
                    "('Frontier Access', 'Johto', NULL)," +
                    "('Cianwood City', 'Johto', 'A Port of Crashing Waves')," +
                    "('Safari Zone Gate', 'Johto', NULL)," +
                    "('Mahogany Town', 'Johto', 'Home of the Ninja')," +
                    "('Blackthorn City', 'Johto', 'A Quiet Mountain Retreat');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/count")
    ResponseEntity<String> countSettlements() {
        int totalSettlements = 0;

        try (Connection con = dataSource.getConnection(); Statement statement = con.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS tally FROM SETTLEMENTS")) {
                rs.next();
                totalSettlements = rs.getInt("tally");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(totalSettlements + " settlements found", HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Settlement>> getSettlements() {
        List<Settlement> result = new ArrayList<>();

        try (Connection con = dataSource.getConnection(); Statement statement = con.createStatement()) {
            try (ResultSet settlements = statement.executeQuery("SELECT * FROM SETTLEMENTS")) {
                while(settlements.next()) {
                    result.add(new Settlement(settlements.getInt("city_id"),
                            settlements.getString("city_name"),
                            settlements.getString("region"),
                            settlements.getString("slogan"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<Settlement>>(result, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    ResponseEntity<Settlement> getSettlementById(@PathVariable int id) {
        Settlement result = null;

        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_SELECT_BY_ID)) {

            statement.setInt(1, id);

            try (ResultSet settlements = statement.executeQuery()) {
                if(settlements.next()) {
                    result = new Settlement(settlements.getInt("city_id"),
                            settlements.getString("city_name"),
                            settlements.getString("region"),
                            settlements.getString("slogan"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result == null)
            return new ResponseEntity<Settlement>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Settlement>(result, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Map<String, Integer>> addSettlement(@RequestBody @Valid Settlement request) {
        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement statement = con.prepareStatement(SQL_INSERT)) {
                statement.setString(1, request.getCityName());
                statement.setString(2, request.getRegion());
                statement.setString(3, request.getSlogan());
                statement.executeUpdate();
            }

            try (PreparedStatement statement = con.prepareStatement(SQL_FIND_CITY_BY_NAME)) {
                statement.setString(1, request.getCityName());

                try (ResultSet rs = statement.executeQuery()) {
                    if(rs.next()) {
                        request.setCityId(rs.getInt("city_id"));
                    }
                }
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Map<String, Integer> response = new HashMap<>();
        response.put("cityId", request.getCityId());

        return new ResponseEntity<Map<String, Integer>>(response, HttpStatus.CREATED);
    }
}
