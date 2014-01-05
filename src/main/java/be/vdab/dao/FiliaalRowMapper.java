package be.vdab.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;

public class FiliaalRowMapper implements RowMapper <Filiaal>{

	@Override
	public Filiaal mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	  return new Filiaal(resultSet.getLong("id"), resultSet.getString("naam"), resultSet.getBoolean("hoofdFiliaal"),
			resultSet.getBigDecimal("waardeGebouw"), resultSet.getDate("inGebruikName"),
			new Adres(resultSet.getString("straat"), 
			resultSet.getString("huisNr"), 
			resultSet.getInt("postcode"),
			resultSet.getString("gemeente")));
	}

}
