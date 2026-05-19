package com.zhengpinjiucang.mainserver.domain.controller;

import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.domain.bean.MemberAccountBean;
import com.zhengpinjiucang.mainserver.domain.service.MemberAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class MemberAccountController {

    @Autowired
    private MemberAccountService memberAccountService;

    @PostMapping("/member-account/register")
    public ResultBean register(@RequestBody MemberAccountBean bean) {
        log.info("/member-account/register, 请求参数:{}", bean);
        memberAccountService.register(bean);
        return new ResultBean(200, "注册成功", null);
    }

    @PostMapping("/member-account/send-email")
    public ResultBean sendEmailCode(@RequestBody MemberAccountBean bean) {
        log.info("/member-account/send-email, 请求参数:{}", bean);
        memberAccountService.sendEmailCode(bean);
        return new ResultBean(200, "验证码已发送", null);
    }

    @PostMapping("/member-account/login")
    public ResultBean login(@RequestBody MemberAccountBean bean) {
        log.info("/member-account/login, 请求参数:{}", bean);
        String token = memberAccountService.login(bean);
        return new ResultBean(200, "登录成功", token);
    }

    @PostMapping("/member-account/detail")
    public ResultBean detail(@RequestBody MemberAccountBean bean) {
        log.info("/member-account/detail, 请求参数:{}", bean);
        MemberAccountBean result = memberAccountService.detail(bean);
        return new ResultBean(200, "查询成功", result);
    }

    @PostMapping("/member-account/me")
    public ResultBean me(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        log.info("/member-account/me, userId:{}", userId);
        MemberAccountBean bean = new MemberAccountBean();
        bean.setLongId(userId);
        MemberAccountBean result = memberAccountService.detail(bean);
        return new ResultBean(200, "查询成功", result);
    }
}