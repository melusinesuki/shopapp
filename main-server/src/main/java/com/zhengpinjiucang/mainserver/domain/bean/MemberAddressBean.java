package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberAddressBean {

    private Long longId;
    private Long longMemberAccountId;
    private String strNickname;
    private String strCellphone;
    private String strProvinces;
    private String strCity;
    private String strDistrict;
    private Integer intIsDefault;
    private String strDetailAddress;
    private Long longCreateTime;
    private Long longUpdateTime;
}
