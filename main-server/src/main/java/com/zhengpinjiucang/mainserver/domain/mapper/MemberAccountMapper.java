package com.zhengpinjiucang.mainserver.domain.mapper;

import com.zhengpinjiucang.mainserver.domain.bean.MemberAccountBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberAccountMapper {

    List<MemberAccountBean> select(MemberAccountBean bean);

    MemberAccountBean selectOne(MemberAccountBean bean);

    int insert(MemberAccountBean bean);

    int update(MemberAccountBean bean);

    int delete(MemberAccountBean bean);
}
