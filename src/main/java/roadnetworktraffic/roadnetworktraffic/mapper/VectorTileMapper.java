package roadnetworktraffic.roadnetworktraffic.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.VectorTile;
import roadnetworktraffic.roadnetworktraffic.entity.vo.Field;

import java.util.List;

@Mapper
public interface VectorTileMapper {




    @Select({
            "WITH tile_bbox AS (",
            "  SELECT ST_TileEnvelope(#{z}, #{x}, #{y}) AS bbox_3857",
            ")",
            "SELECT ST_AsMVT(tile, '${dataSourceName}', 4096, 'mvt_geom') AS mvt ",
            "FROM (",
            "  SELECT ${fieldListStr},",
            "    ST_AsMVTGeom(",
            "      ST_Transform(geometry, 3857), ",
            "      (SELECT bbox_3857 FROM tile_bbox), ",
            "      4096, 256, true",
            "    ) AS mvt_geom ",
            "  FROM ${dataSourceName}, tile_bbox ",
            "  WHERE geometry && ST_Transform((SELECT bbox_3857 FROM tile_bbox), 4326)",
            ") AS tile"
    })
    VectorTile getMvtTile(
            @Param("z") int z,
            @Param("x") int x,
            @Param("y") int y,
            @Param("dataSourceName") String dataSourceName,
            @Param("fieldListStr") String fieldListStr
    );



    @Select("""
        SELECT
            a.attname AS value,
            format_type(a.atttypid, a.atttypmod) AS type
        FROM pg_attribute a
        JOIN pg_class c ON a.attrelid = c.oid
        WHERE c.relname = #{tableName}
          AND a.attnum > 0
          AND NOT a.attisdropped
        ORDER BY a.attnum
        """)
    List<Field> getFieldListByTableName(String tableName);

}

