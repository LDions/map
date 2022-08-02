package com.ruowei.web.rest;

import com.alibaba.fastjson.JSON;
import com.ruowei.domain.Geometries;
import com.ruowei.domain.Location;
import com.ruowei.repository.GeometriesRepository;
import com.ruowei.repository.LocationRepository;
import com.ruowei.util.WKTUtils;
import com.ruowei.web.rest.dto.LocationVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/push")
@Api(tags = "test")
public class TestResource {

    private final GeometriesRepository geometriesRepository;
    private final LocationRepository locationRepository;

    public TestResource(GeometriesRepository geometriesRepository, LocationRepository locationRepository) {
        this.geometriesRepository = geometriesRepository;
        this.locationRepository = locationRepository;
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建")
    public ResponseEntity<Void> create() {
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);
        List<Location> geo = new ArrayList();
        try {
            for (int i = 0; i < 10000; i++) {
                Double x = (Math.random() * 120);
                Double y = (Math.random() * 120);
                String wktPoint = "POINT (" + x + " " + y + ")";
                Geometry point = reader.read(wktPoint);
                Location location = new Location();
                location.setPhone("15044042528");
                location.setSite(point.getEnvelope().toText());
                locationRepository.save(location);
                Geometries geometries1 = new Geometries();
                geometries1.setLocationId(location.getId());
                geometries1.setName("1号多边形");
                geometries1.setShape("POLYGON ((0 0, 10 0, 10 10, 0 10, 0 0))");
                geometries1.setSite(point.getEnvelope().toText());
                geometries1.setType("危险");
                geometriesRepository.save(geometries1);
                Geometries geometries2 = new Geometries();
                geometries2.setLocationId(location.getId());
                geometries2.setName("2号多边形");
                geometries2.setShape("POLYGON ((50 10, 30 40, 20 40, 18 18, 50 10))");
                geometries2.setSite(point.getEnvelope().toText());
                geometries2.setType("危险");
                geometriesRepository.save(geometries2);
                Geometries geometries3 = new Geometries();
                geometries3.setLocationId(location.getId());
                geometries3.setName("3号多边形");
                geometries3.setShape("POLYGON ((120 120, 80 90, 90 80, 120 120))");
                geometries3.setSite(point.getEnvelope().toText());
                geometries3.setType("危险");
                geometriesRepository.save(geometries3);
                Geometries geometries4 = new Geometries();
                geometries4.setLocationId(location.getId());
                geometries4.setName("4号多边形");
                geometries4.setShape("POLYGON ((80 10, 50 50, 72 12, 80 10))");
                geometries4.setSite(point.getEnvelope().toText());
                geometries4.setType("危险");
                geometriesRepository.save(geometries4);
                Geometries geometries5 = new Geometries();
                geometries5.setLocationId(location.getId());
                geometries5.setName("5号多边形");
                geometries5.setShape("POLYGON ((0 50, 30 60, 30 50, 4 80, 0 50))");
                geometries5.setSite(point.getEnvelope().toText());
                geometries5.setType("危险");
                geometriesRepository.save(geometries5);
                Geometries geometries6 = new Geometries();
                geometries6.setLocationId(location.getId());
                geometries6.setName("6号多边形");
                geometries6.setShape("POLYGON ((100 0, 90 0, 90 10, 100 0))");
                geometries6.setSite(point.getEnvelope().toText());
                geometries6.setType("危险");
                geometriesRepository.save(geometries6);
                Geometries geometries7 = new Geometries();
                geometries7.setLocationId(location.getId());
                geometries7.setName("7号多边形");
                geometries7.setShape("POLYGON ((100 50, 90 40, 90 50, 100 50))");
                geometries7.setSite(point.getEnvelope().toText());
                geometries7.setType("危险");
                geometriesRepository.save(geometries7);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新点位置")
    public ResponseEntity<Void> update(@RequestParam Long id, @RequestParam String site) {
        locationRepository.findById(id)
            .ifPresent(loc -> {
                try {
                    loc.setSite(WKTUtils.geojson2Wkt(site));
                    locationRepository.save(loc);

                    for (Geometries geometries : geometriesRepository.findAllByLocationId(loc.getId())) {
//                        geometries.setSite(loc.getSite());
//                        geometriesRepository.save(geometries);
                        boolean isContains = WKTUtils.wkt2Geometry(geometries.getShape()).contains(WKTUtils.wkt2Geometry(loc.getSite()));
                        if (isContains) {
                            System.out.println("发送危险报警....");
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test")
    @ApiOperation(value = "test")
    public ResponseEntity<Void> createRole() {
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

//        locationRepository.findAll()
//            .forEach(location -> {
//                for (Geometries geometries : geometriesRepository.findAllByLocationId(location.getId())) {
//                    try {
//                        Geometry point = reader.read(location.getSite());
//                        boolean b = reader.read(geometries.getShape()).contains(point);
//                        if (b) {
//                            System.out.println(point.getEnvelope() + "此点出现在" + geometries.getName() + "图像内部");
//                            break;
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        final int[] count = {0};
        geometriesRepository.findAll().forEach(geometries -> {
            try {
                Geometry point = reader.read(geometries.getSite());
                boolean b = reader.read(geometries.getShape()).contains(point);
                if (b) {
                    System.out.println(point.getEnvelope() + "此点出现在" + geometries.getName() + "图像内部");
                    count[0] = count[0] + 1;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        System.out.println("个数" + count[0]);
        return ResponseEntity.ok().build();
    }
}
