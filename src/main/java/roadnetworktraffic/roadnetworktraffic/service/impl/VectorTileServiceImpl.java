package roadnetworktraffic.roadnetworktraffic.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.VectorTile;
import roadnetworktraffic.roadnetworktraffic.entity.vo.Field;
import roadnetworktraffic.roadnetworktraffic.mapper.VectorTileMapper;
import roadnetworktraffic.roadnetworktraffic.service.VectorTileService;
import roadnetworktraffic.roadnetworktraffic.utils.Tile4326Util;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class VectorTileServiceImpl implements VectorTileService {
    @Autowired
    private VectorTileMapper vectorTileMapper;

    private final Cache<String, String> vectorTileFieldCache;



    public VectorTileServiceImpl(Cache<String, String> vectorTileFieldCache) {
        this.vectorTileFieldCache = vectorTileFieldCache;
    }


    /**
     * 获取指定行列号的矢量瓦片
     *
     * @param z 缩放等级
     * @param x 瓦片行号
     * @param y 瓦片列号
     * @return 矢量瓦片
     */
    public VectorTile getTile(String dataSourceName, Integer z, Integer x, Integer y) {
        // 使用 Caffeine 的原子加载方法
        String fieldListStr = vectorTileFieldCache.get(dataSourceName, key -> {
            List<Field> fieldList = vectorTileMapper.getFieldListByTableName(key);
            String joined = String.join(",",
                    fieldList.stream()
                            .filter(field -> {
                                String type = field.getType();
                                // 如果要求严格区分大小写，用：
                                 return type == null || !type.startsWith("geometry");
                            })
                            .map(Field::getValue)
                            .toArray(String[]::new)
            );
            log.info("从数据库获取字段信息: {}", joined);
            return joined;
        });

        return vectorTileMapper.getMvtTile(z, x, y, dataSourceName, fieldListStr);
    }


//    通过数据库获取字段
    public List<Field> getFieldListByTableName(String tableName) {
        return vectorTileMapper.getFieldListByTableName(tableName);
    }







}
