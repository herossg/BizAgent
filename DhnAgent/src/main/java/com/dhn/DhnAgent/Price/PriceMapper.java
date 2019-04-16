package com.dhn.DhnAgent.Price;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PriceMapper implements RowMapper<Price> {

	@Override
	public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
		Price price = new Price(rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                , rs.getFloat("mad_price_ft")
			                );
		return price;
	}
	
}