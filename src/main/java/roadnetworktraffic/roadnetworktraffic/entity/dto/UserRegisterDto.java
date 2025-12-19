package roadnetworktraffic.roadnetworktraffic.entity.dto;


import lombok.Data;

@Data
public class UserRegisterDto extends UserLoginDto {
    private String nickName;
    // 性别
    private String sex;
}
