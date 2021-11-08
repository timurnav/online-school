package org.education.school.web.resource;

import org.education.school.service.CourseService;
import org.education.school.service.UserAdminService;
import org.education.school.service.dto.Course;
import org.education.school.service.dto.User;
import org.education.school.web.dto.CourseView;
import org.education.school.web.dto.UserAdminView;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/users")
public class AdminUsersResource {

    private final UserAdminService service;
    private final CourseService courseService;

    public AdminUsersResource(UserAdminService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(UserAdminView view) {
        service.save(view.toDto(), view.getCourses());
        return "redirect:/admin/users";
    }

    @GetMapping()
    public String users(Model model) {
        List<User> users = service.getAll();
        List<UserAdminView> views = users.stream()
                .map(UserAdminView::of)
                .collect(Collectors.toList());
        model.addAttribute("users", views);
        return "users";
    }

    @GetMapping("ban/{userId}")
    public String ban(@PathVariable int userId) {
        service.banUser(userId, true);
        return "redirect:/admin/users";
    }

    @GetMapping("unban/{userId}")
    public String unban(@PathVariable int userId) {
        service.banUser(userId, false);
        return "redirect:/admin/users";
    }

    @GetMapping("new-user")
    public String newUserForm(@RequestParam String type, Model model) {
        List<CourseView> courses = courseService.getAll().stream()
                .map(CourseView::of)
                .collect(Collectors.toList());
        model.addAttribute("user", new UserAdminView());
        model.addAttribute("type", type.toUpperCase());
        model.addAttribute("create", true);
        model.addAttribute("allCourses", courses);
        return "user";
    }

    @GetMapping("edit/{userId}")
    public String newUserForm(@PathVariable int userId, Model model) {
        User user = service.getUser(userId);
        List<Course> all = courseService.getAll();
        UserAdminView adminView = UserAdminView.of(user);
        Set<Integer> userCourses = all.stream()
                .filter(c -> Objects.nonNull(c.teacher) && Objects.equals(user.id, c.teacher.id)
                        || c.hasStudent(user.id))
                .map(c -> c.id)
                .collect(Collectors.toSet());
        adminView.setCourses(userCourses);

        List<CourseView> courses = all.stream()
                .map(CourseView::of)
                .collect(Collectors.toList());
        model.addAttribute("user", adminView);
        model.addAttribute("type", user.type.name());
        model.addAttribute("create", false);
        model.addAttribute("allCourses", courses);
        return "user";
    }

}
