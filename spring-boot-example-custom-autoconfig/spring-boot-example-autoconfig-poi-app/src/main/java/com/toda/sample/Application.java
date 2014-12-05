package com.toda.sample;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.toda.boot.autoconfigure.poi.writer.PoiExcelWriter;

@Configuration
@EnableAutoConfiguration()
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
//        args = new String[]{"--toda.poi.flushSize=100"};

        logger.debug("Input args: " + Arrays.toString(args));

//        System.setProperty("toda.poi.flushSize", "50"); //could be also toda.poi.flush_size=40 -> relaxed binding

        runAppAndLogResult(args);

        System.clearProperty("toda.poi.flushSize");
    }

    private static void runAppAndLogResult(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        logger.info("Bean is: {}", run.getBean(PoiExcelWriter.class));
        logger.info("Property is: {}", run.getBean(PoiExcelWriter.class).getFlushSize());
    }

    @Bean
    @Profile("beanManualRegister")
    PoiExcelWriter poiExcelWriter() {
        return new PoiExcelWriter(10);
    }

}
