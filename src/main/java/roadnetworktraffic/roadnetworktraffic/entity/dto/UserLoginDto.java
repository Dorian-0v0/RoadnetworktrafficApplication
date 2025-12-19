package roadnetworktraffic.roadnetworktraffic.entity.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDto implements Serializable {
    // 账号
    private String account;
    // 密码
    private String password;
}
