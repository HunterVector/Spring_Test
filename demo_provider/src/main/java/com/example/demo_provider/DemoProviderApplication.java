package com.example.demo_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo_provider.service.DownFileService;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement(proxyTargetClass = true)
public class DemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProviderApplication.class, args);
        
    }
    @Bean
    public DownFileService getDownFileService() {
        return new DownFileService();
    }

}
