package xyz.slimjim.hungrytales.storage.postgres;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("xyz.slimjim")
public class PostgresConfiguration {

    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(System.getProperty("jdbc.username"));
        dataSource.setPassword(System.getProperty("jdbc.password"));
        dataSource.setUrl(System.getProperty("jdbc.url"));
        return dataSource;
    }
}
