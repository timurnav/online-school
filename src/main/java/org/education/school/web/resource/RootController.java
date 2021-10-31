package org.education.school.web.resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("message", "Hello from Root Controller");
        return "landing";
    }
}
