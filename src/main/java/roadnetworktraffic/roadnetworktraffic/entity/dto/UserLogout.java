package roadnetworktraffic.roadnetworktraffic.entity.dto;

import lombok.Data;

@Data
public class UserLogout {
    private Integer id;
//    private String account;
    // 底图
    private String baseMap;
    // 中心点
    private String center;
    // 缩放比例
    private Integer zoom;
}
