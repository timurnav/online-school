package org.education.school.web.resource;

import org.education.school.web.dto.CourseView;
import org.education.school.web.dto.SchoolStatView;
import org.education.school.web.dto.TeacherLinkView;
import org.education.school.web.dto.TeacherView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(Model model) throws ParseException {
        int alumni = 25000;
        SchoolStatView schoolStat = new SchoolStatView(
                alumni, 8, 4800, 300, alumni / 5
        );
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TeacherLinkView teacherLink = new TeacherLinkView("Тимур Мухитдинов");
        List<CourseView> courses = List.of(
                new CourseView("Основы Java", "ENTRY",
                        "Курс Introduction Java предназначен для тех, кто только начинает свой путь в IT-индустрии и не имеет представления об основах программирования. Поcле окончания курса Выпускники обладают достаточной базой для выбора пути дальнейшего развития в IT-сфере.",
                        dateFormat.parse("2021-09-03"), teacherLink
                ),
                new CourseView("Java Elementary", "BASIC",
                        "На курсе Студенты научатся создавать Java-приложения, углубят свои знания в Java Core и на практике приобретут понимание принципов ООП. Этот курс рассчитан на слушателей с базовыми знаниями в любом С-подобном языке программирования.",
                        dateFormat.parse("2021-09-03"), teacherLink
                ),
                new CourseView("Java Enterprise", "ADVANCED",
                        "В процессе обучения Студенты курса Java Enterprise освоят стек EE-технологий, используемый для создания приложений и сервисов бизнес-уровня, что позволит им стать более востребованными на современном IT-рынке.",
                        dateFormat.parse("2021-09-03"), teacherLink
                )
        );
        List<TeacherView> teachers = List.of(
                new TeacherView(1, "Тимур Мухитдинов", "aaa.jpg", "Lead Developer в SberDevices",
                        List.of("Основы Java", "Java Elementary", "Java Enterprise"))
        );

        model.addAttribute("stat", schoolStat);
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        return "landing";
    }
}
