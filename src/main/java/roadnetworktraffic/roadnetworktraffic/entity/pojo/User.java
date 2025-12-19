package roadnetworktraffic.roadnetworktraffic.entity.pojo;

import cn.hutool.core.date.DateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    // 账号
    private String account;
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
    // 密码
    private String password;
    // 创建时间
    private DateTime createTime;
//    最近登录
    private DateTime updateTime;
    // 缩放
    private Integer zoom;
}
