package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.SystemMessageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemMessageMapper {

    List<SystemMessageBean> select(SystemMessageBean bean);
    SystemMessageBean selectOne(SystemMessageBean bean);
    int insert(SystemMessageBean bean);
    int update(SystemMessageBean bean);
    int delete(SystemMessageBean bean);
}
