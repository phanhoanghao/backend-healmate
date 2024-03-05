package bluedragonvn.com.healmate.service.impl;

import bluedragonvn.com.healmate.service.OTPCacheService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: phanh, Date : 3/4/2024
 */
@Service
public class OTPCacheServiceImpl implements OTPCacheService {
    @Override
    public void put(String key, Integer value) {

    }

    @Override
    public Optional<String> get(String key) {
        return Optional.empty();
    }

    @Override
    public void remove(String key) {

    }
}