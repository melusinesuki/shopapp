package com.zhengpinjiucang.mainserver.domain.controller;

import com.zhengpinjiucang.mainserver.common.bean.ResultBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopCartBean;
import com.zhengpinjiucang.mainserver.domain.service.ShopCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @RequestMapping("/shop-cart/list-all")
    public ResultBean listAll() {
        log.info("/shop-cart/list-all,购物车完整列表,请求参数:null");
        List<ShopCartBean> list = shopCartService.listAll();
        log.info("/shop-cart/list-all,购物车完整列表,返回结果:{}", list);
        return new ResultBean(200, "查询成功", list);
    }

    @RequestMapping("/shop-cart/save")
    public ResultBean save(@RequestBody ShopCartBean bean) {
        log.info("/shop-cart/save,购物车保存,请求参数:{}", bean);
        shopCartService.save(bean);
        log.info("/shop-cart/save,购物车保存,返回结果:null");
        return new ResultBean(200, "保存成功", null);
    }

    @RequestMapping("/shop-cart/edit")
    public ResultBean edit(@RequestBody ShopCartBean bean) {
        log.info("/shop-cart/edit,购物车修改,请求参数:{}", bean);
        shopCartService.edit(bean);
        log.info("/shop-cart/edit,购物车修改,返回结果:null");
        return new ResultBean(200, "修改成功", null);
    }

    @RequestMapping("/shop-cart/remove")
    public ResultBean remove(@RequestBody ShopCartBean bean) {
        log.info("/shop-cart/remove,购物车删除,请求参数:{}", bean);
        shopCartService.remove(bean);
        log.info("/shop-cart/remove,购物车删除,返回结果:null");
        return new ResultBean(200, "删除成功", null);
    }
}
