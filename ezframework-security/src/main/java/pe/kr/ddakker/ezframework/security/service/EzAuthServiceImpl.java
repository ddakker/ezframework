package pe.kr.ddakker.ezframework.security.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EzAuthServiceImpl implements EzAuthService {

	private JdbcTemplate jdbcTemplate;

	private String totalAuthsQuery;
	private String authHierarchyQuery;

	public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	public void setTotalAuthsQuery(String totalAuthsQuery) {
		this.totalAuthsQuery = totalAuthsQuery;
	}

	public void setAuthHierarchyQuery(String authHierarchyQuery) {
		this.authHierarchyQuery = authHierarchyQuery;
	}



	@Override
	public List<String> getTotalAuthsQuery() {
		return this.jdbcTemplate.query(totalAuthsQuery,
				new Object[] {}, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
	}

	@Override
	public String getAuthHierarchyQuery() {
		List<String> list = this.jdbcTemplate.query(authHierarchyQuery,
				new Object[] {}, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});

		String authHierarchy = "";
		for (String s : list) {
			authHierarchy += s + "\n";
		}

		return authHierarchy;
	}
}
