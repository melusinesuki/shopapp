package com.zhengpinjiucang.mainserver.domain.service;

import com.github.pagehelper.PageHelper;
import com.zhengpinjiucang.mainserver.common.bean.RequestBean;
import com.zhengpinjiucang.mainserver.common.exception.NormalException;
import com.zhengpinjiucang.mainserver.common.util.SecurityUtils;
import com.zhengpinjiucang.mainserver.domain.bean.SystemMessageBean;
import com.zhengpinjiucang.mainserver.domain.mapper.SystemMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SystemMessageService {
    @Autowired
    private SystemMessageMapper systemMessageMapper;

    public List<SystemMessageBean> listAll(SystemMessageBean bean) {
        log.debug("通知消息,完整列表,补充参数");
        bean.setLongMemberAccountId(SecurityUtils.getId());
        bean.setIntChannel(0);
        log.debug("通知消息,完整列表,执行操作");
        return systemMessageMapper.select(bean);
    }

    public List<SystemMessageBean> list(RequestBean<SystemMessageBean> requestBean) {
        log.debug("通知消息,分页列表,设置分页");
        PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), requestBean.getOrderBy());
        log.debug("通知消息,分页列表,补充参数");
        SystemMessageBean bean = requestBean.getParams();
        bean.setLongMemberAccountId(SecurityUtils.getId());
        bean.setIntChannel(0);
        log.debug("通知消息,分页列表,执行操作");
        return systemMessageMapper.select(bean);
    }

    public void changeStatus(SystemMessageBean bean) {
        log.debug("通知消息,更新状态,检查参数");
        if (bean.getLongId() == null) {
            throw new NormalException("id不能为空");
        }
        log.debug("通知消息,更新状态,补充参数");
        bean.setIntStatus(1);
        bean.setLongMemberAccountId(SecurityUtils.getId());
        bean.setLongUpdatedTime(System.currentTimeMillis());
        systemMessageMapper.update(bean);
    }

}
