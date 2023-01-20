package s5.cloud.enchere;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.JpaVendorAdapter;

@Primary
@Configuration
@EnableJpaRepositories(basePackages ="s5.cloud.enchere.repo")
public class DataSourceConfig {

     @Value("${spring.datasource.url}")
     private String url;

     @Value("${spring.datasource.username}")
     private String username;

     @Value("${spring.datasource.password}")
     private String password;

     @Value("${spring.datasource.driver-class-name}")
     private String driver;

     @Bean
     public DataSource dataSource() {
          DriverManagerDataSource dataSource = new DriverManagerDataSource();
          dataSource.setUrl(url);
          dataSource.setUsername(username);
          dataSource.setPassword(password);
          dataSource.setDriverClassName(driver);
          
          return dataSource;
     }

     @Bean
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
          LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
          em.setDataSource(dataSource());
          em.setPackagesToScan("s5.cloud.enchere");
          JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
          em.setJpaVendorAdapter(vendorAdapter);
          em.setJpaProperties(additionalProperties());
          return em;
     }
     private Properties additionalProperties() {
          Properties properties = new Properties();
          properties.setProperty("spring.jpa.show-sql", "true");
          properties.setProperty("spring.jpa.hibernate.ddl-auto", "none");
          return properties;
      }
}

