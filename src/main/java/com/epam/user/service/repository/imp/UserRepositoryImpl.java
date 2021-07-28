package com.epam.user.service.repository.imp;

import com.epam.user.service.exception.UserNotFoundException;
import com.epam.user.service.model.User;
import com.epam.user.service.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

  private final List<User> list = new ArrayList<>();

  @Override
  public User getUser(String email) {
    return list.stream()
        .filter(user -> user.getEmail().equals(email))
        .findFirst()
        .orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User createUser(User user) {
    list.add(user);
    return user;
  }

  @Override
  public User updateUser(String email, User user) {
    boolean isDeleted = list.removeIf(u -> u.getEmail().equals(email));
    if (isDeleted) {
      list.add(user);
    } else {
      throw new UserNotFoundException();
    }
    return user;
  }

  @Override
  public void deleteUser(String email) {
    list.removeIf(user -> user.getEmail().equals(email));
  }

}



