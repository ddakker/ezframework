package org.ezdevgroup.ezframework.security.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EzResourceServiceImpl implements EzResourceService {

	private JdbcTemplate jdbcTemplate;

	private String authResourceUrlQuery;

	public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }




	public void setAuthResourceUrlQuery(String authResourceUrlQuery) {
		this.authResourceUrlQuery = authResourceUrlQuery;
	}


	@Override
	public List<String> getAuthResourceUrlQuery(String authCd) {
		return this.jdbcTemplate.query(authResourceUrlQuery,
				new Object[] {authCd}, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
	}
}
