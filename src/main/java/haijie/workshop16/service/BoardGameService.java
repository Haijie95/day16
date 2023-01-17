package haijie.workshop16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import haijie.workshop16.models.Mastermind;

@Service
public class BoardGameService {
    @Autowired
    RedisTemplate<String, Mastermind> redisTemplate;

    public int saveGame(final Mastermind mds){
        redisTemplate.opsForValue().set(mds.getId(), mds);
        Mastermind result = redisTemplate.opsForValue().get(mds.getId());
        System.out.println("  RESULT >>> " +result);
        if(result != null)
            return 1;
        return 0;
    }

    public Mastermind findById(final String msid){
        return redisTemplate.opsForValue().get(msid);
    }
    

}