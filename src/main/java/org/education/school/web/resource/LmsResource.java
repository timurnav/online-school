package org.education.school.web.resource;

import org.education.school.repository.entity.UserRole;
import org.education.school.service.CourseService;
import org.education.school.service.dto.Lesson;
import org.education.school.service.dto.LmsCourse;
import org.education.school.service.dto.UserCredentials;
import org.education.school.web.dto.LmsCourseLessonView;
import org.education.school.web.dto.LmsCourseView;
import org.education.school.web.dto.LmsHomeworkView;
import org.education.school.web.dto.LmsLessonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("lms")
public class LmsResource {

    private final CourseService courseService;

    public LmsResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String lms(Model model,
                      @AuthenticationPrincipal UserCredentials loggedUser) {
        return lms(model, null, loggedUser);
    }

    @GetMapping("/{lessonId}")
    public String lms(Model model,
                      @PathVariable(required = false) Integer lessonId,
                      @AuthenticationPrincipal UserCredentials loggedUser) {
        Set<UserRole> roles = loggedUser.getAuthorities();
        List<LmsCourse> courses = roles.contains(UserRole.TEACHER)
                ? courseService.getByTeacher(loggedUser.id)
                : courseService.getByStudent(loggedUser.id);
        List<LmsCourseView> views = courses.stream()
                .map(course -> {
                    LmsCourseView view = new LmsCourseView();
                    view.setId(course.id);
                    view.setTitle(course.title);
                    view.setDescription(course.description);
                    List<LmsCourseLessonView> lessons = course.lessons.stream()
                            .map(lesson -> {
                                LmsCourseLessonView lessonView = new LmsCourseLessonView();
                                lessonView.setId(lesson.id);
                                lessonView.setTitle(lesson.title);
                                lessonView.setDescription(lesson.description);
                                return lessonView;
                            })
                            .collect(Collectors.toList());
                    view.setLessons(lessons);
                    return view;
                })
                .collect(Collectors.toList());
        if (lessonId != null) {
            Lesson lesson = courseService.getLesson(lessonId);
            LmsLessonView lessonView = new LmsLessonView();
            lessonView.setId(lesson.id);
            lessonView.setTitle(lesson.title);
            lessonView.setDescription(lesson.description);
            List<LmsHomeworkView> homeworks = lesson.homeworks.stream()
                    .map(hw -> {
                        LmsHomeworkView hwView = new LmsHomeworkView();
                        hwView.setId(hw.id);
                        hwView.setTitle(hw.title);
                        hwView.setDescription(hw.description);
                        hwView.setAttempts(hw.attempts);
                        hwView.setTill(hw.till);
                        return hwView;
                    })
                    .collect(Collectors.toList());
            lessonView.setHomeWorks(homeworks);
            model.addAttribute("lesson", lessonView);
        }

        model.addAttribute("courses", views);
        return "lms";
    }
}
