package org.education.school.config;

import org.education.school.repository.AdminRepository;
import org.education.school.repository.CourseRepository;
import org.education.school.repository.LessonRepository;
import org.education.school.repository.TeacherRepository;
import org.education.school.repository.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@Component
public class DatabasePopulator {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabasePopulator(AdminRepository adminRepository,
                             TeacherRepository teacherRepository,
                             CourseRepository courseRepository,
                             LessonRepository lessonRepository,
                             PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;

        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!adminRepository.getAll().isEmpty()) {
            return;
        }
        try {
            populateDatabase();
        } catch (Exception e) {
            logger.error("Unable to populate database: {}", e.getMessage(), e);
        }
    }

    private void populateDatabase() throws ParseException {
        AdminEntity adminV = new AdminEntity();
        adminV.setFullName(new FullName("Админ", "Вася"));
        adminV.getContacts().setEmail("adminv@ma.com");
        adminV.setPassword(passwordEncoder.encode("password"));
        adminV.setRoles(Set.of(UserRole.ADMIN));
        adminRepository.save(adminV);

        AdminEntity adminP = new AdminEntity();
        adminP.setFullName(new FullName("Админ", "Петя"));
        adminP.getContacts().setEmail("adminp@ma.com");
        adminP.setPassword(passwordEncoder.encode("password"));
        adminP.setRoles(Set.of(UserRole.ADMIN));
        adminRepository.save(adminP);

        TeacherEntity teacher = new TeacherEntity();
        teacher.setFullName(new FullName("Тимур", "Мухитдинов"));
        teacher.setTitle("Lead developer в SberDevices");
        teacher.getContacts().setEmail("timurnav@gmail.com");
        teacher.setPassword(passwordEncoder.encode("password"));
        teacher.setImage("aaa.jpg");
        teacher.setRoles(Set.of(UserRole.USER));
        teacherRepository.save(teacher);

        CourseEntity javaEntry = new CourseEntity();
        javaEntry.setTeacher(teacher);
        javaEntry.setTitle("Основы Java");
        javaEntry.setLevel("ENTRY");
        javaEntry.setDescription("Курс Introduction Java предназначен для тех, кто только начинает свой путь в IT-индустрии и не имеет представления об основах программирования. Поcле окончания курса Выпускники обладают достаточной базой для выбора пути дальнейшего развития в IT-сфере.");
        CourseEntity javaBasic = new CourseEntity();
        javaBasic.setTeacher(teacher);
        javaBasic.setTitle("Java Elementary");
        javaBasic.setLevel("BASIC");
        javaBasic.setDescription("На курсе Студенты научатся создавать Java-приложения, углубят свои знания в Java Core и на практике приобретут понимание принципов ООП. Этот курс рассчитан на слушателей с базовыми знаниями в любом С-подобном языке программирования.");
        CourseEntity javaEnterprise = new CourseEntity();
        javaEnterprise.setTeacher(teacher);
        javaEnterprise.setTitle("Java Enterprise");
        javaEnterprise.setLevel("ADVANCED");
        javaEnterprise.setDescription("В процессе обучения Студенты курса Java Enterprise освоят стек EE-технологий, используемый для создания приложений и сервисов бизнес-уровня, что позволит им стать более востребованными на современном IT-рынке.");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CourseEntity> courses = List.of(javaEntry, javaBasic, javaEnterprise);
        for (int i = 0; i < courses.size(); i++) {
            CourseEntity course = courses.get(i);
            courseRepository.save(course);
            for (int j = 1; j < 4; j++) {
                LessonEntity lesson = new LessonEntity();
                lesson.setCourse(course);
                lesson.setTitle("Урок #" + j);
                lesson.setDescription("Описание урока #" + j);
                lesson.setDate(dateFormat.parse("2021-09-" + i + j));
                lessonRepository.save(lesson);
            }
        }
    }
}
