package com.jk.client;


import com.jk.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("coloud-provider")
public interface LoginCilen {

    @RequestMapping("login")
    User login(User user);
}
