package roadnetworktraffic.roadnetworktraffic.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.VectorTile;

@Mapper
public interface VectorTileMapper {




    @Select({
            "WITH tile_bbox AS (",
            "  SELECT ST_TileEnvelope(#{z}, #{x}, #{y}) AS bbox_3857",
            ")",
            "SELECT ST_AsMVT(tile, 'layer_name', 4096, 'mvt_geom') AS mvt ",
            "FROM (",
            "  SELECT *,",
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
            @Param("dataSourceName") String dataSourceName
    );
}

