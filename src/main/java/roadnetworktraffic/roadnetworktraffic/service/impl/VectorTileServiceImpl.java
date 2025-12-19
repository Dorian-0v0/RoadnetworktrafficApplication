package roadnetworktraffic.roadnetworktraffic.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.VectorTile;
import roadnetworktraffic.roadnetworktraffic.mapper.VectorTileMapper;
import roadnetworktraffic.roadnetworktraffic.service.VectorTileService;
import roadnetworktraffic.roadnetworktraffic.utils.Tile4326Util;

import java.util.Arrays;

@Service
@Slf4j
public class VectorTileServiceImpl implements VectorTileService {
    @Autowired
    private VectorTileMapper vectorTileMapper;

    /**
     * 获取指定行列号的矢量瓦片
     *
     * @param z 缩放等级
     * @param x 瓦片行号
     * @param y 瓦片列号
     * @return 矢量瓦片
     */
    public VectorTile getTile(String dataSourceName, Integer z, Integer x, Integer y) {
//        double[] bbox = Tile4326Util.xyzToBBox3857(x, y, z);
//        log.info("请求瓦片：{}/{}/{}.pbf", z, x, y);
//        System.out.println(Arrays.toString(bbox));
//        System.out.println(Tile4326Util.xyz2prjBound(, y, z));
        Double[] doubles = Tile4326Util.xyz2prjBound(z, x, y);
        return vectorTileMapper.getMvtTile(z, x, y, dataSourceName);
    }





}
