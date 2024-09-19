package com.infodevelopers.ocsm;

import jakarta.servlet.MultipartConfigElement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class OcsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcsmApplication.class, args);
    }

}
