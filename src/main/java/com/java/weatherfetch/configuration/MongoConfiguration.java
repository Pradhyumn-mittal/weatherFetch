package com.java.weatherfetch.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = {
    "com.java.weatherfetch.repository",
    "com.java.weatherfetch.service",
    "com.java.weatherfetch.outbound"
})
@EnableMongoRepositories(basePackages = "com.java.weatherfetch.repository")
public class MongoConfiguration {

  private final MongoProperties mongoProperties;

  @Autowired
  public MongoConfiguration(MongoProperties mongoProperties) {
    this.mongoProperties = mongoProperties;
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MongoConverter converter) {
    return new MongoTemplate(mongoDbFactory, converter);
  }
}
