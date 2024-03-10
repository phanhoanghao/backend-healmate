package bluedragonvn.com.healmate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import bluedragonvn.com.healmate.repository.dao.Hospital;

@Transactional
public interface HospitalRepository extends JpaRepository<Hospital, String> {
	
	@Query(value = "select h.* from user u join hospital h on u.user_id = h.user_id where u.phone = :phone", nativeQuery = true)
	Hospital findByPhoneUser(String phone);
}
