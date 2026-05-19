package com.zhengpinjiucang.mainserver.domain.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zhengpinjiucang.mainserver.common.exception.NormalException;
import com.zhengpinjiucang.mainserver.domain.bean.MemberAccountBean;
import com.zhengpinjiucang.mainserver.domain.bean.MessageCodeBean;
import com.zhengpinjiucang.mainserver.domain.mapper.MemberAccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import com.zhengpinjiucang.mainserver.common.util.JWTUtils;
import com.zhengpinjiucang.mainserver.domain.mapper.MessageCodeMapper;
import java.util.HashMap;


import java.util.Random;

@Service
@Slf4j
public class MemberAccountService {

    @Autowired
    private MemberAccountMapper memberAccountMapper;
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private MessageCodeMapper messageCodeMapper;


    public void register(MemberAccountBean bean) {
        log.debug("用户注册, 检查参数");
        if (bean == null) {
            throw new NormalException("参数错误");
        }
        if (bean.getStrUsername() == null || bean.getStrUsername().isEmpty()) {
            throw new NormalException("用户名不能为空");
        }
        if (bean.getStrPasswordHash() == null || bean.getStrPasswordHash().isEmpty()) {
            throw new NormalException("密码不能为空");
        }
        if (!bean.getStrPasswordHash().equals(bean.getStrRePasswordHash())) {
            throw new NormalException("两次密码不一致");
        }
        log.debug("用户注册, 检查用户名是否已存在");
        MemberAccountBean beanQ = new MemberAccountBean();
        beanQ.setStrUsername(bean.getStrUsername());
        MemberAccountBean exist = memberAccountMapper.selectOne(beanQ);
        if (exist != null) {
            throw new NormalException("用户名已存在");
        }
        log.debug("用户注册, 补充参数");
        bean.setLongId(IdUtil.getSnowflakeNextId());
        bean.setStrPasswordHash(DigestUtil.md5Hex(bean.getStrPasswordHash()));
        bean.setStrNickname("珍品酒友");
        bean.setStrAvatar("https://ts3.tc.mm.bing.net/th/id/OIP-C.D_0j-989FquhjlnrH_gUbgHaHa?cb=thfc1&rs=1&pid=ImgDetMain&o=7&rm=3");
        bean.setIntGender(1);
        bean.setBirthday(null);
        bean.setStrCellphone("");
        bean.setLongCreatedTime(System.currentTimeMillis());
        bean.setLongUpdateTime(System.currentTimeMillis());
        int inserted = memberAccountMapper.insert(bean);
        if (inserted != 1) {
            throw new NormalException("注册失败");
        }
    }
    public void sendEmailCode(MemberAccountBean bean) {
        log.debug("发送邮件,检查参数");
        String email = bean.getStrUsername();

        if (!email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            throw new NormalException("邮箱格式错误");
        }
        log.debug("发送邮件,构建验证码");

        String code="";

        for(int i=0;i<4;i++){
            int a=new Random().nextInt(10);
            code+=a;
        }
        System.out.println(code);
        log.debug("发送邮件，生成验证码");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1194087088@qq.com");
        message.setTo(email);
        message.setSubject("[pronhub]邮箱验证码");
        message.setText("[pornhub]您的邮箱验证码为"+code);

        mailSender.send(message);
        log.debug("发送邮件，保存状态码");

        MessageCodeBean messageCodeBean = new MessageCodeBean();
        messageCodeBean.setStrEmail(email);
        messageCodeBean.setStrCode(code);
        messageCodeBean.setLongCreatedTime(System.currentTimeMillis());

        // 先删旧的，防止重复发送同一邮箱主键冲突
        MessageCodeBean deleteBean = new MessageCodeBean();
        deleteBean.setStrEmail(email);
        messageCodeMapper.delete(deleteBean);

        messageCodeMapper.insert(messageCodeBean);

    }
    public String login(MemberAccountBean bean) {
        log.debug("用户登录,检查参数");
        if (bean == null) {
            throw new NormalException("参数错误");
        }
        if (!bean.getStrUsername().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            throw new NormalException("邮箱格式错误");
        }
        if (bean.getStrCode() == null || bean.getStrCode().isEmpty()) {
            throw new NormalException("验证码不能为空");
        }

        log.debug("用户登录,检查验证码");
        MessageCodeBean messageCodeBeanQ = new MessageCodeBean();
        messageCodeBeanQ.setStrEmail(bean.getStrUsername());
        MessageCodeBean messageCodeBean = messageCodeMapper.selectOne(messageCodeBeanQ);
        if (messageCodeBean == null) {
            throw new NormalException("验证码不正确");
        }
        if (messageCodeBean.getLongCreatedTime() + 1000 * 60 * 5 < System.currentTimeMillis()) {
            throw new NormalException("验证码已过期");
        }
        if (!messageCodeBean.getStrCode().equals(bean.getStrCode())) {
            throw new NormalException("验证码不正确");
        }
        messageCodeMapper.delete(messageCodeBean);

        log.debug("用户登录,检查用户是否存在");
        MemberAccountBean memberAccountBeanQ = new MemberAccountBean();
        memberAccountBeanQ.setStrUsername(bean.getStrUsername());
        MemberAccountBean memberAccountBeanResult = memberAccountMapper.selectOne(memberAccountBeanQ);
        if (memberAccountBeanResult == null) {
            memberAccountBeanQ.setLongId(IdUtil.getSnowflakeNextId());
            memberAccountBeanQ.setStrUsername(bean.getStrUsername());
            memberAccountBeanQ.setStrPasswordHash("");
            memberAccountBeanQ.setStrNickname("珍品酒友");
            memberAccountBeanQ.setStrAvatar("https://ts3.tc.mm.bing.net/th/id/OIP-C.D_0j-989FquhjlnrH_gUbgHaHa?cb=thfc1&rs =1&pid=ImgDetMain&o=7&rm=3");
            memberAccountBeanQ.setIntGender(1);
            memberAccountBeanQ.setBirthday(null);
            memberAccountBeanQ.setStrCellphone("");
            memberAccountBeanQ.setLongCreatedTime(System.currentTimeMillis());
            memberAccountBeanQ.setLongUpdateTime(System.currentTimeMillis());
            int inserted = memberAccountMapper.insert(memberAccountBeanQ);
            if (inserted != 1) {
                throw new NormalException("注册失败");
            }
            memberAccountBeanResult = memberAccountBeanQ;
        }

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", memberAccountBeanResult.getLongId());

        String token = JWTUtils.createToken(claims);
        return token;
    }
    public MemberAccountBean detail(MemberAccountBean bean) {
        if (bean.getLongId() == null) {
            throw new NormalException("用户ID不能为空");
        }
        MemberAccountBean result = memberAccountMapper.selectOne(bean);
        if (result == null) {
            throw new NormalException("用户不存在");
        }
        return result;
    }

}
