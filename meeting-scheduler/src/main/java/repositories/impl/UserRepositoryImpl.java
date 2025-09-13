package repositories.impl;

import models.User;
import repositories.UserRepository;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {
    private Map<Long, User> userMap = new HashMap<>();
    @Override
    public Optional<User> findUserByName(String name) {
        for (Long userId: userMap.keySet()) {
            if (userMap.get(userId).getName().equals(name)) {
                return Optional.of(userMap.get(userId));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findUsersByName(List<String> names) {
        List<User> availableParticipants = new ArrayList<>();
        for (String name: names) {
            for (Long userId: userMap.keySet()) {
                String userName = userMap.get(userId).getName();
                if (userName.equals(name)) {
                    availableParticipants.add(userMap.get(userId));
                    break;
                }
            }
        }
        return availableParticipants;
    }
}
