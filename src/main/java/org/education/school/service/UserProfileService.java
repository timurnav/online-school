package org.education.school.service;

import org.education.school.repository.UserRepository;
import org.education.school.repository.entity.UserCredentialsEntity;
import org.education.school.service.dto.UserCredentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements UserDetailsService {

    private final UserRepository repository;

    public UserProfileService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredentialsEntity entity = repository.findCredentials(email);
        if (entity == null) {
            throw new UsernameNotFoundException("Unable to find user with email " + email);
        }
        return new UserCredentials(entity.getId(), entity.getEmail(), entity.getPassword(),
                entity.getRoles(), entity.isBanned());
    }
}
