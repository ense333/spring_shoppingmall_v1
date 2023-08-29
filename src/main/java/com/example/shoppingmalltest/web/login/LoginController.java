package com.example.shoppingmalltest.web.login;

import com.example.shoppingmalltest.SessionConst;
import com.example.shoppingmalltest.domain.login.LoginService;
import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    //private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "/login/loginForm";
        }

        //로그인이 성공

        //세션이 있으면 존재하는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;

    }

    @PostMapping("/logout")
    public String logoutV1(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
