package org.education.school.config;

import org.education.school.repository.TeacherRepository;
import org.education.school.repository.UserRepository;
import org.education.school.service.TeacherService;
import org.education.school.service.UserAdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public TeacherService teacherService(TeacherRepository repository) {
        return new TeacherService(repository);
    }

    @Bean
    public UserAdminService userAdminService(UserRepository userRepository) {
        return new UserAdminService(userRepository);
    }
}
