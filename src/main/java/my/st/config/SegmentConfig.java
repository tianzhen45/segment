package my.st.config;

import my.st.util.SegmentService;
import my.st.util.StandardMap;
import my.st.util.TranslateHelper;
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
