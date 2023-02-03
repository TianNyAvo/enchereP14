package s5.cloud.enchere;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;



@Configuration
@EnableMongoRepositories(basePackages = "s5.cloud.enchere.mongorepo")
public class MongoDbConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.uri}")
    private String databaseuri;

    @Bean
    public MongoClient mongoClient() {
        //return MongoClients.create(String.format("mongodb://%s:%d", host, port));
        return MongoClients.create(databaseuri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoclient) {
        return new MongoTemplate(mongoclient, database);
    }
}