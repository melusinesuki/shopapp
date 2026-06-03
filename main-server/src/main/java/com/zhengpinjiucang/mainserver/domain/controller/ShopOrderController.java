package com.zhengpinjiucang.mainserver.domain.controller;

import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderItemBean;
import com.zhengpinjiucang.mainserver.domain.service.ShopOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ShopOrderController {

    @Autowired
    private ShopOrderService shopOrderService;

    @RequestMapping("/shop-order/list-all")
    public ResultBean listAll() {
        log.info("/shop-order/list-all,订单完整列表,请求参数:null");
        List<ShopOrderBean> list = shopOrderService.listAll();
        log.info("/shop-order/list-all,订单完整列表,返回值:{}", list);
        return new ResultBean(200, "查询成功", list);
    }

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

    @RequestMapping("/shop-order/cancel")
    public ResultBean cancel(@RequestBody ShopOrderBean bean) {
        log.info("/shop-order/cancel,取消订单,请求参数:{}", bean);
        shopOrderService.cancel(bean);
        log.info("/shop-order/cancel,取消订单,返回值:null");
        return new ResultBean(200, "取消成功", null);
    }

    @RequestMapping("/shop-order/delete")
    public ResultBean delete(@RequestBody ShopOrderBean bean) {
        log.info("/shop-order/delete,删除订单,请求参数:{}", bean);
        shopOrderService.delete(bean);
        log.info("/shop-order/delete,删除订单,返回值:null");
        return new ResultBean(200, "删除成功", null);
    }

    @RequestMapping("/shop-order/items")
    public ResultBean items(@RequestBody ShopOrderBean bean) {
        log.info("/shop-order/items,订单明细,请求参数:{}", bean);
        List<ShopOrderItemBean> items = shopOrderService.listItems(bean.getLongId());
        log.info("/shop-order/items,订单明细,返回值:{}", items);
        return new ResultBean(200, "查询成功", items);
    }
}
