package org.education.school.web.resource;

import org.education.school.service.CourseService;
import org.education.school.service.TeacherService;
import org.education.school.web.dto.CourseView;
import org.education.school.web.dto.SchoolStatView;
import org.education.school.web.dto.TeacherLinkView;
import org.education.school.web.dto.TeacherView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class RootResource {

    private final TeacherService teacherService;
    private final CourseService courseService;

    public RootResource(TeacherService teacherService,
                        CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String root(Model model) {
        Map<Integer, List<String>> coursesByTeacher = new HashMap<>();
        List<CourseView> courses = new ArrayList<>();
        courseService.getAll()
                .forEach(c -> {
                    TeacherLinkView teacherLink = null;
                    if (c.teacher != null) {
                        coursesByTeacher.computeIfAbsent(c.teacher.id, id -> new ArrayList<>())
                                .add(c.title);
                        teacherLink = new TeacherLinkView();
                        teacherLink.setId(c.teacher.id);
                        teacherLink.setName(c.teacher.fullName);
                    }
                    CourseView courseView = new CourseView(c.id, c.title, c.level, c.description, c.startDate, teacherLink);
                    courses.add(courseView);
                });
        List<TeacherView> teacherViews = teacherService.getAll().stream()
                .map(t -> new TeacherView(t.id, t.fullName, t.image, t.title, coursesByTeacher.getOrDefault(t.id, Collections.emptyList())))
                .collect(Collectors.toList());

        int alumni = 25000;
        SchoolStatView schoolStat = new SchoolStatView(
                alumni, 8, 4800, teacherViews.size(), alumni / 5
        );

        model.addAttribute("stat", schoolStat);
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teacherViews);
        return "landing";
    }
}
