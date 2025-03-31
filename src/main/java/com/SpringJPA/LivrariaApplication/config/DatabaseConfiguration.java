package com.SpringJPA.LivrariaApplication.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;


// Configuração do banco de dados OPCIONAL
@Configuration
public class DatabaseConfiguration {

    // Anotação value, pegar valores inseridos no application.yml
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    @Bean
    public DataSource hikariDataSource(){

        // Hikari recebendo os parametros de configuração
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        // Configurações personalizadas no banco
        config.setMaximumPoolSize(10); // maximo de conexões liberadas
        config.setMinimumIdle(1); // tamanho inicial do pool
        config.setPoolName("libraria-db-pool");
        config.setMaxLifetime(600000); // 600 mil ms (10 minutos)
        config.setConnectionTimeout(100000); // 100 mil ms timeout para conseguir uma conexão
        config.setConnectionTestQuery("select 1"); // query de teste

        return new HikariDataSource(config);
    }

}
