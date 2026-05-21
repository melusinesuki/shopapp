package com.zhengpinjiucang.mainserver.domain.controller;

import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.domain.bean.MemberAddressBean;
import com.zhengpinjiucang.mainserver.domain.service.MemberAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MemberAddressController {

    @Autowired
    private MemberAddressService memberAddressService;

    @RequestMapping("/member-address/list-all")
    public ResultBean listAll() {
        log.info("/member-address/list-all,收货地址完整列表,请求参数:null");
        List<MemberAddressBean> list = memberAddressService.listAll();
        log.info("/member-address/list-all,收货地址完整列表,返回结果:{}", list);
        return new ResultBean(200, "查询成功", list);
    }

    @RequestMapping("/member-address/detail")
    public ResultBean detail(@RequestBody MemberAddressBean bean) {
        log.info("/member-address/detail,收货地址详情,请求参数:{}", bean);
        MemberAddressBean memberAddressBean = memberAddressService.detail(bean);
        log.info("/member-address/detail,收货地址详情,返回结果:{}", memberAddressBean);
        return new ResultBean(200, "查询成功", memberAddressBean);
    }

    @RequestMapping("/member-address/save")
    public ResultBean save(@RequestBody MemberAddressBean bean) {
        log.info("/member-address/save,收货地址保存,请求参数:{}", bean);
        MemberAddressBean memberAddressBean = memberAddressService.save(bean);
        log.info("/member-address/save,收货地址保存,返回结果:{}", memberAddressBean);
        return new ResultBean(200, "保存成功", memberAddressBean);
    }

    @RequestMapping("/member-address/remove")
    public ResultBean remove(@RequestBody MemberAddressBean bean) {
        log.info("/member-address/remove,收货地址删除,请求参数:{}", bean);
        memberAddressService.remove(bean);
        log.info("/member-address/remove,收货地址删除,返回结果:null");
        return new ResultBean(200, "删除成功", null);
    }

    @RequestMapping("/member-address/change-status")
    public ResultBean changeStatus(@RequestBody MemberAddressBean bean) {
        log.info("/member-address/change-status,收货地址状态更新,请求参数:{}", bean);
        memberAddressService.changeStatus(bean);
        log.info("/member-address/change-status,收货地址删除,返回结果:null");
        return new ResultBean(200, "收货地址状态更新", null);
    }
}
