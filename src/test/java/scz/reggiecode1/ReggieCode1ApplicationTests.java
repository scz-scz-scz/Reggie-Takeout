package scz.reggiecode1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import redis.clients.jedis.Jedis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import scz.reggiecode1.utils.SMSUtils;
import scz.reggiecode1.utils.ValidateCodeUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ReggieCode1ApplicationTests {
    //jedis测试
    /*@Test
    public void testJedis(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.hset("1","name","sb");
        jedis.hset("1","age","12");
        Set<String> hkeys = jedis.hkeys("1");
        List<String> hvals = jedis.hvals("1");
        Map<String, String> hMap = jedis.hgetAll("1");
        for (String hkey : hkeys) {
            System.out.println(hkey);
        }
        for (String hval : hvals) {
            System.out.println(hval);
        }
        hMap.forEach((i,j)-> System.out.println(i+":"+j));
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            jedis.del(key);
        }
        jedis.close();
    }*/

    //spring-data-redis测试
    /*@Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void delete(){
        redisTemplate.keys("*").stream().forEach(key->redisTemplate.delete(key));
    }*/
    /*@Test
    public void testspringdataredis() throws InterruptedException {
        redisTemplate.opsForValue().set("1","sb",3L, TimeUnit.SECONDS);

        redisTemplate.opsForHash().put("2","age","12");
        System.out.println(redisTemplate.opsForHash().entries("2"));
        System.out.println(redisTemplate.opsForHash().get("2","age"));

        //redisTemplate.opsForList().leftPush("3","sb");
        //redisTemplate.opsForList().rightPush("3","sbr");
        //System.out.println(redisTemplate.opsForList().rightPop("3"));
        System.out.println(redisTemplate.opsForList().size("3"));
        System.out.println(redisTemplate.opsForList().range("3",0L,-1L));

        redisTemplate.opsForSet().add("4","a","b","c","a");
        System.out.println(redisTemplate.opsForSet().members("4"));
        System.out.println(redisTemplate.opsForSet().size("4"));
        redisTemplate.opsForSet().add("5","sb","a","b");
        System.out.println(redisTemplate.opsForSet().intersect("4","5"));
        System.out.println(redisTemplate.opsForSet().union("4","5"));
        System.out.println(redisTemplate.opsForSet().difference("4","5"));

        redisTemplate.opsForZSet().add("6","sb1",0.1);
        redisTemplate.opsForZSet().add("6","sb2",0.2);
        redisTemplate.opsForZSet().add("6","sb2",0.2);
        redisTemplate.opsForZSet().add("6","sb3",0.2);
        System.out.println(redisTemplate.opsForZSet().rangeWithScores("6", 0, -1));
        redisTemplate.opsForZSet().remove("6","sb2");
        redisTemplate.opsForZSet().incrementScore("6","sb1",0.2);
        redisTemplate.opsForZSet().add("6","sb3",0.1);
        System.out.println(redisTemplate.opsForZSet().rangeWithScores("6", 0, -1));

        Set<String> keys = redisTemplate.keys("*");
        System.out.println(redisTemplate.hasKey("1"));
        Thread.sleep(3000);
        System.out.println(redisTemplate.hasKey("1"));
        for (String key : keys) {
            System.out.println(key+":"+redisTemplate.type(key));
            redisTemplate.delete(key);
        }
    }*/
}
