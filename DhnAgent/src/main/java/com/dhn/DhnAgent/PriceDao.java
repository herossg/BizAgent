package com.dhn.DhnAgent;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dhn.DhnAgent.Price.Price;
import com.dhn.DhnAgent.Price.PriceMapper;

@Repository
public class PriceDao {

	@Autowired
	JdbcTemplate template ;//= DhnAgentApplication.jdbcTemplate;

	@Autowired
    DataSource dataSource ;//= DhnAgentApplication.dataSource;
	
	public List<Price>  getMemberPrice(int userid) {
		String query = "select * from cb_wt_member_addon where mad_mem_id = " + userid;
		System.out.println("DS = " + dataSource + " / JT = " + template + " / Query : " + query);
//		Connection conn;
//		try {
//			if(dataSource != null) {
//				conn = dataSource.getConnection();
//			} else {
//				System.out.println("Is Null");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<Price> result = template.query(query, new PriceMapper());
		return result;
		
	}

}
