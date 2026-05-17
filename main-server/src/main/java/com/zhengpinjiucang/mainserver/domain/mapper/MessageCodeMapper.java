package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.MessageCodeBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageCodeMapper {

    MessageCodeBean selectOne(MessageCodeBean bean);

    int insert(MessageCodeBean bean);

    int delete(MessageCodeBean bean);
}