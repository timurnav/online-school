package org.education.school.web.resource;

import org.education.school.service.UserAdminService;
import org.education.school.service.dto.User;
import org.education.school.web.dto.UserAdminView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/users")
public class UsersResource {

    private final UserAdminService service;

    public UsersResource(UserAdminService service) {
        this.service = service;
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

}
