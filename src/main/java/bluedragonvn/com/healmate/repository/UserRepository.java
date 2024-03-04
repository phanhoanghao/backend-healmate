package bluedragonvn.com.healmate.repository;

import bluedragonvn.com.healmate.repository.dao.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPhone(String username);

    Optional<User> findByUserId(String userId);

    void deleteByUserId(String userId);
    void deleteByPhone(String phone);
    Boolean existsByPhone(String phone);

}