package org.education.school.web.resource;

import org.education.school.service.UserProfileService;
import org.education.school.service.dto.UserCredentials;
import org.education.school.service.dto.UserProfile;
import org.education.school.web.dto.ProfileView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("profile")
public class ProfileResource {

    private final UserProfileService profileService;

    public ProfileResource(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String profile(Model model, @AuthenticationPrincipal UserCredentials loggedUser) {
        UserProfile profile = profileService.getProfile(loggedUser.id);
        ProfileView view = ProfileView.of(profile);
        model.addAttribute("user", view);
        return "profile";
    }
}
