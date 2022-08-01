package com.msc.easybiz.easybizservice.dbaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DBService {

    private static final Logger logger = LoggerFactory.getLogger(DBService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<?> queryForList(String sql, Object... arg){
        logger.info("Executing query '{}' with params {}", sql, arg);
        return jdbcTemplate.queryForList(sql, arg);
    }

    public Object queryForObject(String sql, Object... arg){
        logger.info("Executing query '{}' with params {}", sql, arg);
        return jdbcTemplate.queryForObject(sql, arg, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                int columnCount = rs.getMetaData().getColumnCount();
                Map<String,Object> item = new HashMap<>();
                for(int column = 1; column <= columnCount ; column++){
                    item.put(rs.getMetaData().getColumnName(column), rs.getObject(column));
                }
                return item;
            }
        });
    }

    public int update(String sql, Object... arg){
        return jdbcTemplate.update(sql, arg);
    }
}
