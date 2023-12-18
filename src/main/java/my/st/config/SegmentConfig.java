package my.st.config;

import my.st.segment.SegmentService;
import my.st.segment.StandardMap;
import my.st.segment.TranslateHelper;
import my.st.segment.StandardMap;
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
