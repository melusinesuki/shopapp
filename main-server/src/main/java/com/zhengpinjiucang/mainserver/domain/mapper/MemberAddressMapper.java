package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.MemberAddressBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberAddressMapper {

    List<MemberAddressBean> select(MemberAddressBean bean);

    MemberAddressBean selectOne(MemberAddressBean bean);

    int insert(MemberAddressBean bean);

    int update(MemberAddressBean bean);

    int delete(MemberAddressBean bean);
}
