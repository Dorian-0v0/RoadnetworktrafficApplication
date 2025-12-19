package roadnetworktraffic.roadnetworktraffic.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVo implements Serializable {
    // ID
    private Integer id;
    // 账号
    private String account;
    // token
    private String token;
    // 昵称
    private String nickName;
    // 头像
    private String avatar;
    // 性别
    private String sex;
//    底图
    private String baseMap;
    // 中心点
    private String center;
    // 缩放比例
    private Integer zoom;
}
