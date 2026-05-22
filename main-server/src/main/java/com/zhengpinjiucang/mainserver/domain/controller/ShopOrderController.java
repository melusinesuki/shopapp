package com.zhengpinjiucang.mainserver.domain.controller;

import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderBean;
import com.zhengpinjiucang.mainserver.domain.service.ShopOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ShopOrderController {

    @Autowired
    private ShopOrderService shopOrderService;

    @RequestMapping("/shop-order/save")
    public ResultBean saveShopOrder(@RequestBody ShopOrderBean bean) {
        log.info("/shop-order/save,订单保存,请求参数:{}", bean);
        ShopOrderBean shopOrderBean = shopOrderService.save(bean);
        log.info("/shop-order/save,订单保存,返回值:{}", shopOrderBean);
        return new ResultBean(200, "下单成功", shopOrderBean);
    }

    @RequestMapping("/shop-order/check-pay-status")
    public ResultBean checkPayStatus(@RequestBody ShopOrderBean bean) {
        log.info("/shop-order/check-pay-status,检查支付结果,请求参数:{}", bean);
        shopOrderService.checkPayStatus(bean);
        log.info("/shop-order/check-pay-status,检查支付结果,返回值:null");
        return new ResultBean(200, "支付成功", null);
    }
}
