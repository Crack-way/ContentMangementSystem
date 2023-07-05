package com.example.contentmanagementsystem.configuration;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinoConfig {


    @Value("http://localhost:9000")
    private String minioUrl;

    @Value("miniorupesh")
    private String accessKey;

    @Value("minorupesh")
    private String secretKey;

    @Bean
    public MinioClient minioClient(){

        return MinioClient.builder().
                endpoint(minioUrl).
                credentials(accessKey,secretKey).build();
    }


}
