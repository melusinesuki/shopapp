package com.zhengpinjiucang.mainserver.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberAccountBean {
    private Long longId;
    private String strUsername;
    private String strPasswordHash;
    private String strRePasswordHash;
    private String strNickname;
    private String strAvatar;
    private Integer intGender;
    private Long longCreatedTime;
    private Long longUpdateTime;
    private Integer intIsDeleted;
    private Long longDeleteTime;
    private Date birthday;
    private String strCellphone;
    private String strCode;

}
