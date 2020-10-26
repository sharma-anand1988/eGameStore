package com.egame.store.redis;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RedisRateLimiter {

	@Autowired
	private StringRedisTemplate stringTemplate;

	@Value("${store.allowed-request-per-hour}")
	private int REQUESTS_PER_HOUR;

	@Autowired
	private HttpServletRequest request;

	public boolean isAllowed(String username) {
		final int hour = LocalDateTime.now().getHour();
		String key = request.getRemoteAddr() + ":" + hour;
		ValueOperations<String, String> operations = stringTemplate.opsForValue();
		String requests = operations.get(key);
		if (requests != null && !requests.isEmpty() && Integer.parseInt(requests) >= REQUESTS_PER_HOUR) {
			return false;
		}
		@SuppressWarnings("unchecked")
		List<Object> txResults = (List<Object>) stringTemplate.execute(new SessionCallback<Object>() {
			@Override
			public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
				final StringRedisTemplate redisTemplate = (StringRedisTemplate) operations;
				final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
				operations.multi();
				valueOperations.increment(key);
				redisTemplate.expire(key, 2, TimeUnit.HOURS);
				return operations.exec();
			}
		});
		log.info("Current request count: " + txResults.get(0));
		return true;
	}

}