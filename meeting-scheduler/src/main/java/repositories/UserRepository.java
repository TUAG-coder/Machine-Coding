package repositories;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByName(String name);
    List<User> findUsersByName(List<String> names);
}
