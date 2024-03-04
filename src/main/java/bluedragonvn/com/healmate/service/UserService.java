package bluedragonvn.com.healmate.service;

import bluedragonvn.com.healmate.dto.*;

import java.util.List;

public interface UserService {

  String createUser(CreateUserRequest createUserRequest);

  GetUserResponse getUserByPhone(String Phone);

  GetUserResponse getUserByUserId(String userId);

  GetUserInfoResponse getUserInfo();

  void updateUserInfo(UpdateUserRequest updateUserRequest);

  void deleteUserById(String userId);

  List<GetUserResponse> getAllUsers();

  void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin);
}

