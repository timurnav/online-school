package org.education.school.web.resource;

import org.education.school.web.dto.CourseView;
import org.education.school.web.dto.SchoolStatView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(Model model) {
        int alumni = 25000;
        model.addAttribute("stat", new SchoolStatView(
                alumni, 8, 4800, 300, alumni / 5
        ));
        model.addAttribute("courses", Arrays.asList(
                new CourseView("Основы Java", "ENTRY", "Курс Introduction Java предназначен для тех, кто только начинает свой путь в IT-индустрии и не имеет представления об основах программирования. Поcле окончания курса Выпускники обладают достаточной базой для выбора пути дальнейшего развития в IT-сфере."),
                new CourseView("Java Elementary", "BASIC", "На курсе Студенты научатся создавать Java-приложения, углубят свои знания в Java Core и на практике приобретут понимание принципов ООП. Этот курс рассчитан на слушателей с базовыми знаниями в любом С-подобном языке программирования."),
                new CourseView("Java Enterprise", "ADVANCED", "В процессе обучения Студенты курса Java Enterprise освоят стек EE-технологий, используемый для создания приложений и сервисов бизнес-уровня, что позволит им стать более востребованными на современном IT-рынке.")
        ));
        return "landing";
    }
}
