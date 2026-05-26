package com.zhengpinjiucang.mainserver.domain.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.zhengpinjiucang.mainserver.common.exception.NormalException;
import com.zhengpinjiucang.mainserver.common.util.SecurityUtils;
import com.zhengpinjiucang.mainserver.domain.bean.MemberAddressBean;
import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopCartBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopLogisticsBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderBean;
import com.zhengpinjiucang.mainserver.domain.mapper.MemberAddressMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ProductMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopCartMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopLogisticsMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class ShopOrderService {

    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Autowired
    private ShopCartMapper shopCartMapper;

    @Autowired
    private MemberAddressMapper memberAddressMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ShopLogisticsMapper shopLogisticsMapper;

    public List<ShopOrderBean> listAll() {
        log.debug("订单完整列表,补充参数");
        ShopOrderBean bean = new ShopOrderBean();
        bean.setLongMemberAccountId(SecurityUtils.getId());
        log.debug("订单完整列表,执行查询");
        return shopOrderMapper.select(bean);
    }

    public ShopOrderBean save(ShopOrderBean bean) {
        log.debug("订单保存,检查参数,检查地址");
        if (bean.getLongAddressId() == null) {
            throw new NormalException("参数错误");
        }
        if ((bean.getShopCartIdList() == null || bean.getShopCartIdList().isEmpty())
                && (bean.getProductIdList() == null || bean.getProductIdList().isEmpty())) {
            throw new NormalException("参数错误");
        }
        log.debug("订单保存,查询必要信息");
        MemberAddressBean memberAddressBean = new MemberAddressBean();
        memberAddressBean.setLongId(bean.getLongAddressId());
        memberAddressBean.setLongMemberAccountId(SecurityUtils.getId());
        MemberAddressBean memberAddressBeanResult = memberAddressMapper.selectOne(memberAddressBean);
        if (memberAddressBeanResult == null) {
            throw new NormalException("参数错误");
        }

        int totalPrice = 0;
        List<ShopCartBean> shopCartBeanList = new ArrayList<>();

        if (bean.getShopCartIdList() != null && !bean.getShopCartIdList().isEmpty()) {
            ShopCartBean shopCartBean = new ShopCartBean();
            shopCartBean.setLongMemberAccountId(SecurityUtils.getId());
            shopCartBean.setIdList(bean.getShopCartIdList());
            shopCartBeanList = shopCartMapper.select(shopCartBean);
            if (shopCartBeanList == null || shopCartBeanList.isEmpty()) {
                throw new NormalException("参数错误");
            }
            for (ShopCartBean cartBean : shopCartBeanList) {
                ProductBean productBean = new ProductBean();
                productBean.setLongId(cartBean.getLongProductId());
                ProductBean productBeanResult = productMapper.selectOne(productBean);
                if (productBeanResult == null) {
                    throw new NormalException("商品已经下架");
                }
                cartBean.setProductBean(productBeanResult);
                totalPrice += productBeanResult.getIntPrice() * cartBean.getIntNum();
            }
        }

        if (bean.getProductIdList() != null && !bean.getProductIdList().isEmpty()) {
            for (Long productId : bean.getProductIdList()) {
                ProductBean productBean = new ProductBean();
                productBean.setLongId(productId);
                ProductBean productBeanResult = productMapper.selectOne(productBean);
                if (productBeanResult == null) {
                    throw new NormalException("商品已经下架");
                }
                totalPrice += productBeanResult.getIntPrice();
                ShopCartBean cartBean = new ShopCartBean();
                cartBean.setProductBean(productBeanResult);
                cartBean.setLongProductId(productId);
                cartBean.setIntNum(1);
                shopCartBeanList.add(cartBean);
            }
        }

        log.debug("保存订单,补充订单信息");
        ShopOrderBean shopOrderBean = new ShopOrderBean();
        shopOrderBean.setLongId(IdUtil.getSnowflakeNextId());
        shopOrderBean.setLongMemberAccountId(SecurityUtils.getId());
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int random = ThreadLocalRandom.current().nextInt(10000000, 100000000);
        shopOrderBean.setStrOrderCode("P" + date + random);
        shopOrderBean.setStrProductListJson(JSONUtil.toJsonStr(shopCartBeanList));
        shopOrderBean.setIntTotalPrice(totalPrice);
        shopOrderBean.setIntPayPrice(totalPrice);
        shopOrderBean.setStrAddressJson(JSONUtil.toJsonStr(memberAddressBeanResult));
        shopOrderBean.setIntStatus(0);
        shopOrderBean.setLongCreatedTime(System.currentTimeMillis());
        shopOrderBean.setLongUpdatedTime(System.currentTimeMillis());
        shopOrderBean.setIntIsDeleted(0);
        int inserted = shopOrderMapper.insert(shopOrderBean);
        if (inserted <= 0) {
            throw new NormalException("下单失败");
        }

        log.info("订单保存,删除购物车数据");
        for (ShopCartBean cartBean : shopCartBeanList) {
            if (cartBean.getLongId() != null) {
                shopCartMapper.delete(cartBean);
            }
        }

        return shopOrderBean;
    }

    public void checkPayStatus(ShopOrderBean bean) {
        bean.setLongMemberAccountId(SecurityUtils.getId());
        ShopOrderBean shopOrderBeanResult = shopOrderMapper.selectOne(bean);
        if (shopOrderBeanResult == null) {
            throw new NormalException("订单不存在");
        }

        if (shopOrderBeanResult.getIntStatus() == 0) {
            shopOrderBeanResult.setIntStatus(2);
            shopOrderMapper.update(shopOrderBeanResult);
            // 模拟支付成功, 自动发货, 增加一条物流信息数据
            ShopLogisticsBean shopLogisticsBean = new ShopLogisticsBean();
            shopLogisticsBean.setLongId(IdUtil.getSnowflakeNextId());
            shopLogisticsBean.setLongShopOrderId(shopOrderBeanResult.getLongId());
            shopLogisticsBean.setStrCode("YT8871701153792");
            shopLogisticsBean.setIntStatus(0);
            shopLogisticsBean.setStrContent("");
            shopLogisticsBean.setLongCreatedTime(System.currentTimeMillis());
            shopLogisticsBean.setLongUpdatedTime(System.currentTimeMillis());
            shopLogisticsMapper.insert(shopLogisticsBean);
        }
    }
}
