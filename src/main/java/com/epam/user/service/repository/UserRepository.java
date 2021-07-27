package com.epam.user.service.repository;

import com.epam.user.service.model.User;

public interface UserRepository {

  User getUser(String email);

  User createUser(User user);

  User updateUser(String email, User user);

  void deleteUser(String email);
}
