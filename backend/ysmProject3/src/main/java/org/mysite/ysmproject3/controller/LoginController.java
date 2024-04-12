package org.mysite.ysmproject3.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // 인증된 사용자의 경우, 이름을 모델에 추가
            model.addAttribute("name", principal.getAttribute("name"));
        }
        return "index"; // index.html을 렌더링
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // 이미 인증된 사용자는 다른 페이지로 리다이렉션
            return "redirect:/home"; // 예시 URL, 실제로는 적절한 대상 URL 사용
        }

        return "login"; // 로그인 페이지 뷰 이름
    }

}
