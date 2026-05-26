package com.zhengpinjiucang.mainserver.domain.controller;

import com.zhengpinjiucang.mainserver.common.bean.RequestBean;
import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.domain.bean.SystemMessageBean;
import com.zhengpinjiucang.mainserver.domain.service.SystemMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class SystemMessageController {

    @Autowired
    private SystemMessageService systemMessageService;

    @PostMapping("/system-message/list-all")
    public ResultBean listAll(@RequestBody SystemMessageBean bean) {
        log.info("/system-message/list-all,消息通知,完整列表,请求参数:{}", bean);
        List<SystemMessageBean> list = systemMessageService.listAll(bean);
        log.info("/system-message/list-all,消息通知,完整列表,返回值:{}", list);
        return new ResultBean(200, "success", list);
    }

    @PostMapping("/system-message/list")
    public ResultBean list(@RequestBody RequestBean<SystemMessageBean> bean) {
        log.info("/system-message/list,消息通知,分页列表,请求参数:{}", bean);
        List<SystemMessageBean> list = systemMessageService.list(bean);
        log.info("/system-message/list,消息通知,分页列表,返回值:{}", list);
        return new ResultBean(200, "success", list);
    }

    @PostMapping("/system-message/change-status")
    public ResultBean changeStatus(@RequestBody SystemMessageBean bean) {
        log.info("/system-message/change-status,消息通知,更新状态,请求参数:{}", bean);
        systemMessageService.changeStatus(bean);
        log.info("/system-message/change-status,消息通知,更新状态,返回值:null");
        return new ResultBean(200, "success", null);
    }
}
