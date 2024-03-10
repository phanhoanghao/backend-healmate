package bluedragonvn.com.healmate.repository;

import bluedragonvn.com.healmate.repository.dao.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByPhone(String phone);

    Optional<User> findByUserId(String userId);

    void deleteByUserId(String userId);
    void deleteByPhone(String phone);
    Boolean existsByPhone(String phone);

}