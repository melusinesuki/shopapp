package com.zhengpinjiucang.mainserver.domain.service;

import cn.hutool.core.util.IdUtil;
import com.zhengpinjiucang.mainserver.common.exception.NormalException;
import com.zhengpinjiucang.mainserver.common.util.SecurityUtils;
import com.zhengpinjiucang.mainserver.domain.bean.MemberAddressBean;
import com.zhengpinjiucang.mainserver.domain.mapper.MemberAddressMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MemberAddressService {

    @Autowired
    private MemberAddressMapper memberAddressMapper;

    public List<MemberAddressBean> listAll() {
        log.debug("查询收货地址完整列表,补充参数");
        MemberAddressBean bean = new MemberAddressBean();
        bean.setLongMemberAccountId(SecurityUtils.getId());
        log.debug("查询收货地址完整列表,执行操作");
        return memberAddressMapper.select(bean);
    }

    public MemberAddressBean detail(MemberAddressBean bean) {
        log.debug("查询收货地址详情,检查参数");
        if (bean == null || bean.getLongId() == null) {
            throw new NormalException("收货地址id不能为空");
        }
        log.debug("查询收货地址详情,补充参数");
        bean.setLongMemberAccountId(SecurityUtils.getId());
        log.debug("查询收货地址详情,执行操作");
        return memberAddressMapper.selectOne(bean);
    }

    public MemberAddressBean save(MemberAddressBean bean) {
        log.debug("保存收货地址,检查参数");
        checkSaveParam(bean);
        log.debug("保存收货地址,补充参数");
        Long memberAccountId = SecurityUtils.getId();
        long now = System.currentTimeMillis();
        bean.setLongMemberAccountId(memberAccountId);
        bean.setLongUpdateTime(now);
        if (bean.getIntIsDefault() == null) {
            bean.setIntIsDefault(0);
        }

        if (bean.getLongId() == null) {
            bean.setLongId(IdUtil.getSnowflakeNextId());
            bean.setLongCreateTime(now);
            log.debug("保存收货地址,执行新增");
            int inserted = memberAddressMapper.insert(bean);
            if (inserted != 1) {
                throw new NormalException("保存失败");
            }
            return bean;
        }

        MemberAddressBean query = new MemberAddressBean();
        query.setLongId(bean.getLongId());
        query.setLongMemberAccountId(memberAccountId);
        MemberAddressBean exist = memberAddressMapper.selectOne(query);
        if (exist == null) {
            throw new NormalException("收货地址不存在");
        }

        log.debug("保存收货地址,执行修改");
        int updated = memberAddressMapper.update(bean);
        if (updated != 1) {
            throw new NormalException("保存失败");
        }
        return bean;
    }

    public void remove(MemberAddressBean bean) {
        log.debug("删除收货地址,检查参数");
        if (bean.getLongId() == null) {
            throw new NormalException("收货地址id不能为空");
        }
        log.debug("删除收货地址,补充参数");
        bean.setLongMemberAccountId(SecurityUtils.getId());
        log.debug("删除收货地址,执行操作");
        int deleted = memberAddressMapper.delete(bean);
        if (deleted != 1) {
            throw new NormalException("删除失败");
        }
    }

    private void checkSaveParam(MemberAddressBean bean) {
        if (bean == null) {
            throw new NormalException("收货地址不能为空");
        }
        if (bean.getStrNickname() == null || bean.getStrNickname().isBlank()) {
            throw new NormalException("收货人不能为空");
        }
        if (bean.getStrCellphone() == null || bean.getStrCellphone().isBlank()) {
            throw new NormalException("手机号码不能为空");
        }
        if (bean.getStrProvinces() == null || bean.getStrProvinces().isBlank()) {
            throw new NormalException("省份不能为空");
        }
        if (bean.getStrCity() == null || bean.getStrCity().isBlank()) {
            throw new NormalException("城市不能为空");
        }
        if (bean.getStrDistrict() == null || bean.getStrDistrict().isBlank()) {
            throw new NormalException("区县不能为空");
        }
        if (bean.getStrDetailAddress() == null || bean.getStrDetailAddress().isBlank()) {
            throw new NormalException("详细地址不能为空");
        }
    }

    @Transactional
    public void changeStatus(MemberAddressBean bean) {
        log.debug("收货地址状态更新,检查参数");
        if (bean.getLongId() == null) {
            throw new NormalException("id不能为空");
        }
        log.debug("收货地址状态更新,处理页面");
        if (bean.getIntIsDefault() != null) {
            log.debug("收货地址状态更新,处理默认收获地址逻辑");
            MemberAddressBean memberAddressBean = new MemberAddressBean();
            memberAddressBean.setLongMemberAccountId(SecurityUtils.getId());
            memberAddressBean.setIntIsDefault(1);
            MemberAddressBean memberAddressBeanResult = memberAddressMapper.selectOne(memberAddressBean);
            if (memberAddressBeanResult != null) {
                log.debug("收货地址状态更新,取消默认收货地址");
                memberAddressBeanResult.setIntIsDefault(0);
                int updated = memberAddressMapper.update(memberAddressBeanResult);
                if (updated != 1) {
                    throw new NormalException("设置失败");
                }
            }
            memberAddressBean.setLongId(bean.getLongId());
            memberAddressBean.setIntIsDefault(bean.getIntIsDefault());
            int updated = memberAddressMapper.update(memberAddressBean);
            if (updated != 1) {
                throw new NormalException("设置失败");
            }
        }
    }
}
