package org.education.school.service;

import org.education.school.repository.UserRepository;
import org.education.school.repository.entity.UserEntity;
import org.education.school.service.dto.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserAdminService {

    private final UserRepository userRepository;

    public UserAdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void banUser(int userId, boolean banned) {
        UserEntity userEntity = userRepository.get(userId);
        userEntity.setBanned(banned);
        userRepository.save(userEntity);
    }

    public List<User> getAll() {
        return userRepository.getAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private User toDto(UserEntity entity) {
        return new User(entity.getId(), entity.getFullName().toString(),
                entity.getContacts().getEmail(), entity.getPassword(), entity.isBanned(), entity.type());
    }
}
