package org.education.school;

import org.education.school.repository.StudentRepository;
import org.education.school.repository.entity.StudentEntity;
import org.education.school.repository.entity.UserContactsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.education.school.config");
        logger.info("Context loaded");

        StudentRepository repository = context.getBean(StudentRepository.class);

        StudentEntity entity = new StudentEntity();
        entity.setFirstName("Timur");
        entity.setLastName("M");
        entity.setContacts(new UserContactsEntity());
        entity.getContacts().setEmail("timurnav@gmail.com");
        entity.getContacts().setPhoneNumber("+79269549901");
        entity.getContacts().setGithubLink("https://github.com/timurnav");
        entity.getContacts().setTelegramLink("https://t.me/timurnav");

//        JpaTransactionManager transactionManager = context.getBean(JpaTransactionManager.class);
//        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
//        StudentEntity saved = transactionTemplate.execute(status -> {
////            status.setRollbackOnly(); // to rollback manually
//            return repository.saveNonTransactional(entity);
//        });

        StudentEntity saved = repository.save(entity);

        logger.info("saved");

        List<StudentEntity> all = repository.getAll();
        logger.info("all");

        StudentEntity one = repository.get(saved.getId());
        logger.info("one");

        repository.delete(one.getId());
        logger.info("deleted");
    }
}
