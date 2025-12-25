package roadnetworktraffic.roadnetworktraffic.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roadnetworktraffic.roadnetworktraffic.entity.vo.Field;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {


    @Bean("vectorTileFieldCache")
    public Cache<String, String> vectorTileFieldCache() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES) // 写入后10分钟过期
                .recordStats()
                .build();
    }

}