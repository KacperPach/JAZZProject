package com.jazzkp.bookshop;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeihnConfig {
    public class FeignConfig {

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }
    }
}
