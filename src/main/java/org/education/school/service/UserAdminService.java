package org.education.school.service;

import org.education.school.repository.CourseRepository;
import org.education.school.repository.StudentRepository;
import org.education.school.repository.TeacherRepository;
import org.education.school.repository.UserRepository;
import org.education.school.repository.entity.*;
import org.education.school.service.dto.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAdminService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAdminService(UserRepository userRepository,
                            TeacherRepository teacherRepository,
                            StudentRepository studentRepository,
                            CourseRepository courseRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(User toDto, Set<Integer> courses) {
        switch (toDto.type) {
            case STUDENT:
                saveStudent(toDto, courses);
                break;
            case TEACHER:
                saveTeacher(toDto, courses);
                break;
            case ADMIN:
                saveAdmin(toDto);
                break;
        }
    }

    private void saveAdmin(User dto) {
        UserEntity admin;
        if (dto.id == null) {
            admin = new AdminEntity();
            admin.setPassword(passwordEncoder.encode("0000"));
        } else {
            admin = teacherRepository.get(dto.id);
        }
        enrich(dto, admin);
        userRepository.save(admin);
    }

    private void saveTeacher(User dto, Set<Integer> coursesSet) {
        TeacherEntity entity;
        if (dto.id == null) {
            entity = new TeacherEntity();
            entity.setPassword(passwordEncoder.encode("0000"));
        } else {
            entity = teacherRepository.get(dto.id);
        }
        enrich(dto, entity);
        teacherRepository.save(entity);

        courseRepository.getAllWithUsers().forEach(course -> {
            if (coursesSet.contains(course.getId())) {
                course.setTeacher(entity);
            } else if (course.getTeacher() != null && Objects.equals(course.getTeacher().getId(), entity.getId())) {
                course.setTeacher(null);
            }
        });
    }

    private void saveStudent(User dto, Set<Integer> coursesToSet) {
        if (dto.id == null) {
            throw new IllegalStateException("Can't create student via admin service");
        }
        StudentEntity entity = studentRepository.get(dto.id);
        enrich(dto, entity);

        Set<Integer> learningCourseIds = entity.getLearningCourseIds();
        Set<Integer> toRemove = learningCourseIds.stream()
                .filter(cId -> !coursesToSet.contains(cId))
                .collect(Collectors.toSet());
        learningCourseIds.removeAll(toRemove);
        learningCourseIds.addAll(coursesToSet);
    }

    private void enrich(User dto, UserEntity admin) {
        String[] parts = dto.fullName.split(" ");
        admin.setFullName(new FullName());
        admin.getFullName().setFirstName(parts[0]);
        admin.getFullName().setLastName(parts[1]);
        admin.getContacts().setEmail(dto.email);
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

    public User getUser(int userId) {
        UserEntity entity = userRepository.get(userId);
        return toDto(entity);
    }

    private User toDto(UserEntity entity) {
        return new User(entity.getId(), entity.getFullName().toString(),
                entity.getContacts().getEmail(), entity.getPassword(), entity.isBanned(), entity.type());
    }
}
