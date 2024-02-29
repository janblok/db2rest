package com.homihq.db2rest.jdbc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homihq.db2rest.dialect.Dialect;
import com.homihq.db2rest.dialect.MySQLDialect;
import com.homihq.db2rest.dialect.PostGreSQLDialect;
import com.homihq.db2rest.jdbc.JdbcOperationService;
import com.homihq.db2rest.jdbc.JdbcSchemaManager;
import com.homihq.db2rest.schema.AliasGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = "db2rest.datasource", name = "type", havingValue = "jdbc")
public class JdbcComponentConfiguration {

    @Bean
    @ConditionalOnBean(DataSource.class)
    public JdbcSchemaManager schemaManager(DataSource dataSource, AliasGenerator aliasGenerator, List<Dialect> dialects) {
        return new JdbcSchemaManager(dataSource, aliasGenerator, dialects);
    }

    @Bean
    public JdbcOperationService operationService(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new JdbcOperationService(namedParameterJdbcTemplate);
    }

    /*
    @Bean
    @ConditionalOnExpression("#{'${spring.datasource.url}'.contains('mysql')}")
    public MySQLDialect mySQLDialect(ObjectMapper objectMapper) {
        System.out.println("*** MYSQL DIALECT **");
        return new MySQLDialect(objectMapper);
    }

    @Bean
    @ConditionalOnExpression("#{'${spring.datasource.url}'.contains('postgresql')}")
    public PostGreSQLDialect postGreSQLDialect(ObjectMapper objectMapper) {
        System.out.println("*** PGSQL DIALECT **");
        return new PostGreSQLDialect(objectMapper);
    }

     */
}