package bluedragonvn.com.healmate.service;

import java.util.Optional;

/**
 * @author: phanh, Date : 3/5/2024
 */
public interface OTPCacheService {
    /**
     * Store the key value pair in cache
     *
     * @param key   String key
     * @param value Integer value
     */
    void put(String key, Integer value);

    /**
     * Retrieve value from cache when key is provided
     *
     * @param key String key
     * @return Optional String
     */
    Optional<String> get(String key);

    /**
     * Remove value from cache
     *
     * @param key String key
     */
    void remove(String key);
}
