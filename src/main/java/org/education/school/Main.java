package org.education.school;

import org.education.school.repository.StudentRepository;
import org.education.school.repository.TeacherRepository;
import org.education.school.repository.entity.StudentEntity;
import org.education.school.repository.entity.TeacherEntity;
import org.education.school.repository.entity.UserContactsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.education.school.config");
        logger.info("Context loaded");

        StudentRepository studentRepository = context.getBean(StudentRepository.class);
        TeacherRepository teacherRepository = context.getBean(TeacherRepository.class);

        TeacherEntity teacher = new TeacherEntity();
        teacher.setFirstName("AAA");
        teacher.setLastName("BBB");
        teacherRepository.save(teacher);

        StudentEntity entity = new StudentEntity();
        entity.setFirstName("Timur");
        entity.setLastName("M");
        entity.setContacts(new UserContactsEntity());
        entity.getContacts().setEmail("timurnav@gmail.com");
        entity.getContacts().setPhoneNumber("+79269549901");
        entity.getContacts().setGithubLink("https://github.com/timurnav");
        entity.getContacts().setTelegramLink("https://t.me/timurnav");

        StudentEntity saved = studentRepository.save(entity);

        StudentEntity another = new StudentEntity();
        another.setFirstName("Timur");
        another.setLastName("M");

        studentRepository.save(another);

        logger.info("saved");

        List<StudentEntity> all = studentRepository.getAll();
        logger.info("all");

        StudentEntity one = studentRepository.get(saved.getId());
        logger.info("one");

        studentRepository.delete(one.getId());
        logger.info("deleted");
    }
}
