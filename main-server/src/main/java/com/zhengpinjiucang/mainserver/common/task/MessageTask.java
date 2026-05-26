package com.zhengpinjiucang.mainserver.common.task;

import com.zhengpinjiucang.mainserver.domain.bean.SystemMessageBean;
import com.zhengpinjiucang.mainserver.domain.mapper.SystemMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageTask {

    @Autowired
    SystemMessageMapper systemMessageMapper;
    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "*/45 * * * * ?")
    public void sendEmail() {
        SystemMessageBean systemMessageBean = new SystemMessageBean();
        systemMessageBean.setIntStatus(0);
        systemMessageBean.setIntChannel(1);
        List<SystemMessageBean> list = systemMessageMapper.select(systemMessageBean);
        for (SystemMessageBean messageBean : list) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1194087088@qq.com");
            message.setTo(messageBean.getStrEmail());
            message.setSubject(messageBean.getStrTitle());
            message.setText(messageBean.getStrContent());
            mailSender.send(message);
            messageBean.setIntStatus(1);
            messageBean.setLongUpdatedTime(System.currentTimeMillis());
            systemMessageMapper.update(messageBean);
        }
    }
}
