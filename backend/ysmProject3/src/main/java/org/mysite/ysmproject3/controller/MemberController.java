package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // 인증된 사용자의 경우, 이름을 모델에 추가
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("email", principal.getAttribute("email"));
            model.addAttribute("picture", principal.getAttribute("picture"));
        } else {
            model.addAttribute("name", "Guest");
            model.addAttribute("email", "Not Available");
            model.addAttribute("picture", "default.jpg");
        }
        return "index"; // index.html을 렌더링
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {
        if (authentication != null && authentication.isAuthenticated()) {
            // 이미 인증된 사용자는 홈 페이지로 리다이렉션
            return "redirect:/";
        }

        if (oAuth2User != null) {
            // 첫 로그인인 경우, 사용자 정보를 DB에 저장
            String email = oAuth2User.getAttribute("email");
            String name = oAuth2User.getAttribute("name");
            String picture = oAuth2User.getAttribute("picture");
            memberService.processUserRegistration(email, name, picture);
        }

        // 로그인 페이지에 메시지 전달
        model.addAttribute("message", "Please log in to continue.");
        return "login"; // 로그인 페이지 뷰 이름
    }

}