package my.config;

import my.segment.SegmentService;
import my.segment.StandardMap;
import my.segment.TranslateHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SegmentConfig {


    @Bean
    public SegmentService segmentService() {
        return SegmentService.getInstance();
    }

    @Bean
    public TranslateHelper translateHelper() {
        return TranslateHelper.getInstance();
    }

    @Bean
    public StandardMap standardMap(){
        return StandardMap.getInstance();
    }
}
