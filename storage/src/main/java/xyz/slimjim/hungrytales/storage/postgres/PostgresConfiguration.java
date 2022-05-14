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
        dataSource.setUsername("htalesjdbc");
        dataSource.setPassword("Pjv2QkQxHGbvxD");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/hungrytales");
        return dataSource;
    }
}
