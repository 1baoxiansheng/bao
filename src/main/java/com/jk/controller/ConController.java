package com.jk.controller;


import com.alibaba.fastjson.JSONObject;
import com.jk.bean.User;
import com.jk.client.LoginCilen;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Controller
@RequestMapping("con")
public class ConController {
    @Resource
    LoginCilen loginclien;


    @RequestMapping("login")
    @ResponseBody
    public Integer login(User user, HttpServletResponse response) throws UnsupportedEncodingException {
        User userfamsr = loginclien.login(user);
        if (userfamsr == null) {
            return 0;
        }
        if (user.getSava() != null) {
            String userText = JSONObject.toJSONString(user);
            String encode = URLEncoder.encode(userText, "utf-8");
            Cookie cookie = new Cookie("pwd", user.getLoginact() + "-" + user.getUserpswd());
            Cookie pwd = new Cookie("json", encode);
            cookie.setMaxAge(9999999);
            cookie.setPath("/");
            response.addCookie(pwd);
            response.addCookie(cookie);
        }
        if (user.getSava() == null) {
            Cookie cookie = new Cookie("pwd", user.getLoginact() + "-" + user.getUserpswd());
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return 1;
    }

    @RequestMapping("tologin")
    public String tologin(HttpServletRequest request, Model model) {
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("pwd")) {
                    String msg = cookie.getValue();
                    String[] userAndPwd = msg.split("-");
                    model.addAttribute("name", userAndPwd[0]);
                    model.addAttribute("value", userAndPwd[1]);

                }
            }

        }
        return "login";
    }
    @RequestMapping("query")

    public String query(){

        return"query";
    }


}
