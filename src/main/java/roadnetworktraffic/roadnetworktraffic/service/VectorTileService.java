package roadnetworktraffic.roadnetworktraffic.service;

import roadnetworktraffic.roadnetworktraffic.entity.pojo.VectorTile;

public interface VectorTileService {

    VectorTile getTile(String dataSourceName, Integer z, Integer x, Integer y);
}
