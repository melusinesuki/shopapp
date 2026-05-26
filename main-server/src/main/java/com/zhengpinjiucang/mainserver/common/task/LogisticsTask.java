package com.zhengpinjiucang.mainserver.common.task;

import cn.hutool.core.util.IdUtil;
import com.zhengpinjiucang.mainserver.domain.bean.MemberAccountBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopLogisticsBean;
import com.zhengpinjiucang.mainserver.domain.bean.ShopOrderBean;
import com.zhengpinjiucang.mainserver.domain.bean.SystemMessageBean;
import com.zhengpinjiucang.mainserver.domain.mapper.MemberAccountMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopLogisticsMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.ShopOrderMapper;
import com.zhengpinjiucang.mainserver.domain.mapper.SystemMessageMapper;
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
    @Autowired
    private SystemMessageMapper systemMessageMapper;
    @Autowired
    private MemberAccountMapper memberAccountMapper;

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
                    // 先查询订单获取完整信息
                    ShopOrderBean shopOrderBeanQ = new ShopOrderBean();
                    shopOrderBeanQ.setLongId(logisticsBean.getLongShopOrderId());
                    ShopOrderBean existingOrder = shopOrderMapper.selectOne(shopOrderBeanQ);

                    ShopOrderBean shopOrderBean = new ShopOrderBean();
                    shopOrderBean.setLongId(logisticsBean.getLongShopOrderId());
                    shopOrderBean.setIntStatus(3);
                    shopOrderBean.setLongUpdatedTime(System.currentTimeMillis());
                    shopOrderMapper.update(shopOrderBean);

                    // 消息通知
                    if (existingOrder != null) {
                        MemberAccountBean memberAccountBean = new MemberAccountBean();
                        memberAccountBean.setLongId(existingOrder.getLongMemberAccountId());
                        MemberAccountBean memberAccountBeanResult = memberAccountMapper.selectOne(memberAccountBean);

                        if (memberAccountBeanResult != null) {
                            // 邮箱通知
                            SystemMessageBean systemMessageBean = new SystemMessageBean();
                            systemMessageBean.setLongId(IdUtil.getSnowflakeNextId());
                            systemMessageBean.setIntTargetType(0);
                            systemMessageBean.setIntBizType(1);
                            systemMessageBean.setLongMemberAccountId(memberAccountBeanResult.getLongId());
                            systemMessageBean.setIntStatus(0);
                            systemMessageBean.setIntChannel(1);
                            systemMessageBean.setStrEmail(memberAccountBeanResult.getStrUsername());
                            systemMessageBean.setStrPath("");
                            systemMessageBean.setStrTitle("包裹送达通知");
                            systemMessageBean.setStrContent("您的订单编号:" + existingOrder.getStrOrderCode() + "的订单已经送达,请及时查收");
                            systemMessageBean.setLongCreatedTime(System.currentTimeMillis());
                            systemMessageBean.setLongUpdatedTime(System.currentTimeMillis());
                            systemMessageMapper.insert(systemMessageBean);

                            // 站内信通知
                            SystemMessageBean systemMessageBean2 = new SystemMessageBean();
                            systemMessageBean2.setLongId(IdUtil.getSnowflakeNextId());
                            systemMessageBean2.setIntTargetType(0);
                            systemMessageBean2.setIntBizType(1);
                            systemMessageBean2.setLongMemberAccountId(memberAccountBeanResult.getLongId());
                            systemMessageBean2.setIntStatus(0);
                            systemMessageBean2.setIntChannel(0);
                            systemMessageBean2.setStrPath("/pages/mine/order-list/index");
                            systemMessageBean2.setStrTitle("包裹送达通知");
                            systemMessageBean2.setStrContent("您的订单编号:" + existingOrder.getStrOrderCode() + "的订单已经送达,请及时查收");
                            systemMessageBean2.setLongCreatedTime(System.currentTimeMillis());
                            systemMessageBean2.setLongUpdatedTime(System.currentTimeMillis());
                            systemMessageMapper.insert(systemMessageBean2);
                        }
                    }
                }
            }
        }
    }
}
