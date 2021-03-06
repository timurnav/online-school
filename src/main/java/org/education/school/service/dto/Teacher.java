package org.education.school.service.dto;

import org.education.school.repository.entity.TeacherEntity;

public class Teacher {
    public final int id;
    public final String fullName;
    public final String title;
    public final String image;
    public final String email;
    public final String password;
    public final boolean banned;

    public Teacher(int id, String fullName, String title, String image, String email, String password, boolean banned) {
        this.id = id;
        this.fullName = fullName;
        this.title = title;
        this.image = image;
        this.email = email;
        this.password = password;
        this.banned = banned;
    }

    public static Teacher of(TeacherEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Teacher(
                entity.getId(),
                entity.getFullName().toString(),
                entity.getTitle(),
                entity.getImage(),
                entity.getContacts().getEmail(),
                entity.getPassword(),
                entity.isBanned()
        );
    }
}
