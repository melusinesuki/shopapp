package com.zhengpinjiucang.mainserver.common.task;

import com.zhengpinjiucang.mainserver.domain.bean.ShopLogisticsBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderBean;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopLogisticsMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogisticsTask {

    @Autowired
    private ShopOrderMapper shopOrderMapper;
    @Autowired
    private ShopLogisticsMapper shopLogisticsMapper;

    @Scheduled(cron = "*/30 * * * * ?")
    public void refreshLogistics() {
        int[] status = new int[]{0, 1, 2};
        for (int i : status) {
            ShopLogisticsBean shopLogisticsBean = new ShopLogisticsBean();
            shopLogisticsBean.setIntStatus(i);
            List<ShopLogisticsBean> list = shopLogisticsMapper.select(shopLogisticsBean);
            for (ShopLogisticsBean logisticsBean : list) {
                ShopLogisticsBean shopLogisticsBeanQ = new ShopLogisticsBean();
                shopLogisticsBeanQ.setLongId(logisticsBean.getLongId());
                shopLogisticsBeanQ.setIntStatus(3);
                shopLogisticsBeanQ.setStrContent("快递,已经签收");
                shopLogisticsBeanQ.setLongUpdatedTime(System.currentTimeMillis());
                shopLogisticsMapper.update(shopLogisticsBeanQ);
                // 如果物流状态是已经送达, 更新订单状态
                if (shopLogisticsBeanQ.getIntStatus() == 3) {
                    ShopOrderBean shopOrderBean = new ShopOrderBean();
                    shopOrderBean.setLongId(logisticsBean.getLongShopOrderId());
                    shopOrderBean.setIntStatus(3);
                    shopOrderBean.setLongUpdatedTime(System.currentTimeMillis());
                    shopOrderMapper.update(shopOrderBean);
                }
            }
        }
    }
}
