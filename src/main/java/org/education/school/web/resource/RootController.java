package org.education.school.web.resource;

import org.education.school.web.SchoolStat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(Model model) {
        int alumni = 25000;
        model.addAttribute("stat", new SchoolStat(
                alumni, 8, 4800, 300, alumni / 5
        ));
        return "landing";
    }
}
