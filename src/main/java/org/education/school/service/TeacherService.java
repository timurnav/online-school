package org.education.school.service;

import org.education.school.repository.TeacherRepository;
import org.education.school.repository.entity.TeacherEntity;
import org.education.school.service.dto.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> getAll() {
        return repository.getAll().stream()
                .map(Teacher::of)
                .collect(Collectors.toList());
    }
}
