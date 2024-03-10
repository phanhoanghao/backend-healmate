package bluedragonvn.com.healmate.service;

import java.util.List;
import java.util.Optional;

import bluedragonvn.com.healmate.dto.GetUserResponse;
import bluedragonvn.com.healmate.dto.SignUpHospitalRequest;
import bluedragonvn.com.healmate.dto.UpdateUserRequest;
import bluedragonvn.com.healmate.repository.dao.Hospital;

public interface HospitalService {
	Hospital createHospital(SignUpHospitalRequest signUpHospitalRequest);

	Optional<Hospital> findById(String id);

	Optional<Hospital> findByUserId(String userId);

	void updateUserInfo(UpdateUserRequest updateUserRequest);

	void deleteUserById(String userId);

	List<GetUserResponse> getAllUsers();

}
