package com.zhengpinjiucang.mainserver.domain.service;

import cn.hutool.core.util.IdUtil;
import com.zhengpinjiucang.mainserver.common.exception.NormalException;
import com.zhengpinjiucang.mainserver.common.util.SecurityUtils;
import com.zhengpinjiucang.mainserver.domain.bean.ProductBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopCartBean;
import com.zhengpinjiucang.mainserver.domain.mapper.ProductMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShopCartService {

    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private ProductMapper productMapper;

    public List<ShopCartBean> listAll() {
        ShopCartBean bean = new ShopCartBean();
        bean.setLongMemberAccountId(SecurityUtils.getId());
        List<ShopCartBean> shopCartBeans = shopCartMapper.select(bean);

        for (ShopCartBean shopCartBean : shopCartBeans) {
            ProductBean productBean = new ProductBean();
            productBean.setLongId(shopCartBean.getLongProductId());
            ProductBean productBeanResult = productMapper.selectOne(productBean);
            shopCartBean.setProductBean(productBeanResult);
        }

        return shopCartBeans;
    }

    public void save(ShopCartBean bean) {
        log.debug("添加到购物车,检查参数");
        if (bean.getLongProductId() == null) {
            throw new NormalException("产品id不能为空");
        }
        log.debug("添加到购物车,检查产品是否存在");
        ProductBean productBeanQ = new ProductBean();
        productBeanQ.setLongId(bean.getLongProductId());
        ProductBean productBeanResult = productMapper.selectOne(productBeanQ);
        if (productBeanResult == null) {
            throw new NormalException("产品不存在");
        }

        log.debug("添加到购物车,检查产品是否已经存在于购物车中");
        ShopCartBean shopCartBeanQ = new ShopCartBean();
        shopCartBeanQ.setLongProductId(bean.getLongProductId());
        shopCartBeanQ.setLongMemberAccountId(SecurityUtils.getId());
        ShopCartBean shopCartBeanResult = shopCartMapper.selectOne(shopCartBeanQ);
        if (shopCartBeanResult == null) {
            log.debug("添加到购物车,补充参数");
            bean.setLongId(IdUtil.getSnowflakeNextId());
            bean.setLongMemberAccountId(SecurityUtils.getId());
            bean.setIntNum(1);
            bean.setLongCreatedTime(System.currentTimeMillis());
            bean.setLongUpdatedTime(System.currentTimeMillis());
            log.debug("添加到购物车,执行操作");
            int inserted = shopCartMapper.insert(bean);
            if (inserted != 1) {
                throw new NormalException("添加失败");
            }
        } else {
            shopCartBeanResult.setIntNum(shopCartBeanResult.getIntNum() + 1);
            shopCartBeanResult.setLongUpdatedTime(System.currentTimeMillis());
            int updated = shopCartMapper.update(shopCartBeanResult);
            if (updated != 1) {
                throw new NormalException("添加失败");
            }
        }
    }

    public void edit(ShopCartBean bean) {
        log.debug("修改购物车,检查参数");
        if (bean.getLongProductId() == null) {
            throw new NormalException("产品id不能为空");
        }
        if (bean.getIntNum() != null && bean.getIntNum() <= 0) {
            this.remove(bean);
            return;
        }
        log.debug("修改购物车,补充参数");
        bean.setLongMemberAccountId(SecurityUtils.getId());
        bean.setLongUpdatedTime(System.currentTimeMillis());

        log.debug("修改购物车,执行操作");
        int updated = shopCartMapper.update(bean);
        if (updated != 1) {
            throw new NormalException("修改失败");
        }
    }

    public void remove(ShopCartBean bean) {
        log.debug("删除购物车,检查参数");
        if (bean.getLongProductId() == null) {
            throw new NormalException("产品id不能为空");
        }
        log.debug("删除购物车,补充参数");
        bean.setLongMemberAccountId(SecurityUtils.getId());
        log.debug("删除购物车,执行操作");
        int deleted = shopCartMapper.delete(bean);
        if (deleted != 1) {
            throw new NormalException("删除失败");
        }
    }
}
