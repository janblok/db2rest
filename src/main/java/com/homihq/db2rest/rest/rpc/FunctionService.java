package com.homihq.db2rest.rest.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ConditionalOnProperty(prefix = "db2rest.datasource", name = "type", havingValue = "jdbc")
public class FunctionService extends SubRoutine {

    public FunctionService(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    SimpleJdbcCall getSimpleJdbcCall(String subRoutineName) {
        return new SimpleJdbcCall(jdbcTemplate).withFunctionName(subRoutineName);
    }
}
