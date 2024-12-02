package org.example.redisteste;

import org.example.redisteste.configuration.RedisCacheConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
public class RedisTesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisTesteApplication.class, args);
	}

}
