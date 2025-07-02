package scz.reggiecode1.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import scz.reggiecode1.common.JacksonObjectMapper;

import java.time.Duration;

@Configuration
public class RedisConfig{
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    private final StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();

    //当用到RedisTemplate时需要以下配置类
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer(new JacksonObjectMapper());
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    //当用到RedisCacheManager时需要以下配置类
    @Bean
    public RedisCacheManager redisCacheManager(){
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer(
                //缓存时，将类名也缓存进去
                new JacksonObjectMapper().activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
        );
        //RedisCacheConfiguration相当于是RedisCacheManager的配置类，里面记录了缓存策略
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))   //设置缓存过期时间
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))   //设置键的序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer));   //设置值的序列化器
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
        return redisCacheManager;
    }
}