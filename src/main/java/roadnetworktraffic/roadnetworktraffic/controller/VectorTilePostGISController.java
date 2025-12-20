package roadnetworktraffic.roadnetworktraffic.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import roadnetworktraffic.roadnetworktraffic.entity.pojo.VectorTile;
import roadnetworktraffic.roadnetworktraffic.service.VectorTileService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/vectortile")
public class VectorTilePostGISController {
    @Autowired
    private VectorTileService vectorTileService;


    @GetMapping("{dataSourceName}/{z}/{x}/{y}.pbf")
    public void getTile(@PathVariable String dataSourceName, @PathVariable Integer z,
                        @PathVariable Integer x,
                        @PathVariable Integer y,
                        HttpServletResponse response) {
        log.info("请求瓦片：{}/{}/{}.pbf", z, x, y);
        if (dataSourceName == null || dataSourceName.isEmpty()) {
            throw new RuntimeException("数据源名称不能为空");
        }
//        dataSourceName = "传感器数据";
        try {

            VectorTile vectorTile = vectorTileService.getTile(dataSourceName, z, x, y);
            log.info("{}\t，瓦片：{}/{}/{}.pbf，length：{}", dataSourceName, z, x, y, vectorTile.getMvt().length);

            // 设置响应数据
            response.setContentType("application/x-protobuf");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码
            String encodedFileName = URLEncoder.encode(x.toString(), StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=utf-8''" + encodedFileName + ".pbf");
            ServletOutputStream outputStream = response.getOutputStream();
            try {
                outputStream.write(vectorTile.getMvt());
            } catch (IOException e) {
                log.debug("请求取消：" + e.getMessage());
                // e.printStackTrace();
            }

        } catch (Exception e) {
            // 重置response
            log.error("获取矢量瓦片失败：" + e.getMessage());
            throw new RuntimeException("获取矢量瓦片失败", e);
        }
    }


}

