package roadnetworktraffic.roadnetworktraffic.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Tile4326Util
 * @Description 4326坐标系xyz行列号转换工具类
 * @Author xuyizhuo
 * @Date 2024/9/1 21:13
 */
@Slf4j
public class Tile4326Util {
    private static final double HALF_WORLD = 20037508.3427892;
    public static Double[] xyz2prjBound(int z, int x, int y) {
        double xTileCount = Math.pow(2, z + 1); // 0级别2张瓦片，1级别4张瓦片
        double yTileCount = Math.pow(2, z);

        double xMin = (x / xTileCount) * 360 - 180;
        double yMin = 90 - ((y + 1) / yTileCount) * 180;
        double xMax = ((x + 1) / xTileCount) * 360 - 180;
        double yMax = 90 - (y / yTileCount) * 180;
        log.info("{},{},{},{}", xMin, yMin, xMax, yMax);
        return new Double[]{xMin, yMin, xMax, yMax};
    }

}
