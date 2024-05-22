package com.codecool.cookpad.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private DataSource dataSource;

    public void truncateTables(Connection connection, String... tableNames) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (String tableName : tableNames) {
                statement.executeUpdate("TRUNCATE TABLE " + tableName + " CASCADE");
            }
        }
    }
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try (Connection connection = dataSource.getConnection()) {
            truncateTables(connection, "ingredient_type", "ingredient_for_recipe, recipe");
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("import.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

