package com.ruowei.web.rest;

import com.alibaba.fastjson.JSON;
import com.ruowei.domain.Geometries;
import com.ruowei.util.WKTUtils;
import com.ruowei.web.rest.dto.LocationVM;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.algorithm.PointLocator;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.index.strtree.STRtree;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: ruowei
 * @time: 2022/7/19 17:25
 */
public class Csv2Shape {
    public static void main(String[] args) {
//        //创建一条直线
//        Coordinate[] coordinates4 = new Coordinate[] {
//            new Coordinate(116.664496,40.387171),
//            new Coordinate(116.663463,40.387158),
//        };
//
//        GeometryFactory gf=new GeometryFactory();
//        Geometry gfLineString = gf.createLineString(coordinates4);
//        //将度换算成米 公式为：degree = meter / (2 * Math.PI * 6371004) * 360
//        double degree = 10 / (2*Math.PI*6371004)*360;
//        //缓冲区建立
//        BufferOp bufOp = new BufferOp(gfLineString);
//        bufOp.setEndCapStyle(BufferOp.CAP_BUTT);
//        Geometry bg = bufOp.getResultGeometry(degree);
//        System.out.println("缓冲区---"+bg);
//        //点是否在多边形内判断
//        Coordinate point = new Coordinate(116.663609,40.387187);
//        PointLocator a=new PointLocator();
//        boolean p1=a.intersects(point, bg);
//        if(p1)
//            System.out.println("point1:"+"该点在多边形内"+p1);
//        else
//            System.out.println("point1:"+"该点不在多边形内"+p1);
//        WKTReader wktReader = new WKTReader();
//        try {
//            //读被裁切面
//            Geometry polygon = wktReader.read("POLYGON ((220 350, 400 440, 635 249, 380 80, 174 164, 179 265, 220 350))");
//            //读裁切线
//            Geometry polyline = wktReader.read("LINESTRING (570 400, 392 315, 299 215, 430 140, 530 240, 450 360, 460 480)");
//            //取面的边线
//            Geometry boundary = polygon.getBoundary();
//            //将裁切线与面的边线联合，交点会被打断
//            polyline = polyline.union(boundary);
//            List<Geometry> clipPolygon = new ArrayList<>();
//            List<Geometry> lineList = new ArrayList<>();
//            for (int i = 0; i < polyline.getNumGeometries(); i++) {
//                lineList.add(polyline.getGeometryN(i));
//            }
//            // 构造面生成器
//            Polygonizer p = new Polygonizer();
//            p.add(lineList);
//            //取构面结果
//            Collection<Geometry> polys = p.getPolygons();
//            //取buffer，以免因精度损失，遗漏构面结果
//            Geometry buffer = polygon.buffer(1);
//            for (Geometry geometry : polys) {
//                //如果包含在buffer中，则添加
//                if (buffer.contains(geometry)) {
//                    clipPolygon.add(geometry);
//                }
//            }
//            for (Geometry presult : clipPolygon) {
//                System.out.println(presult);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        String wktPoly = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))"; //请自行搜素了解wkt格式
//        String wktPoint = "POINT (30 10)";
//        GeometryFactory geometryFactory = new GeometryFactory();
//        WKTReader reader = new WKTReader(geometryFactory);
//        try {
//            Geometry point = reader.read(wktPoint);
//            Geometry poly = reader.read(wktPoly);
//            System.out.println(point.getCoordinate());
//            System.out.println(poly.getGeometryType());
//            boolean ex = poly.contains(point);
//            System.out.println("ex = " + ex);
//            LineString geometry = (LineString) reader.read("LINESTRING(0 0, 2 0, 5 0)");
//            LineString geometry2 = (LineString) reader.read("LINESTRING(5 0, 0 0)");
//            boolean b = geometry.equals(geometry2);
//            System.out.println("b = " + b);
//            System.out.println(withinGeo(10, 0, "POLYGON((0 0, 10 0, 10 10, 0 10,0 0))"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        String wktPoly = "POLYGON ((120 10, 40 40, 20 40, 10 20, 120 10))"; //纬度 经度
        String wktPoint = "POINT (20 39)";    //纬度 经度
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);
        try {
            Geometry point = reader.read(wktPoint);
            Geometry poly = reader.read(wktPoly);
            Polygon polygon = (Polygon) reader.read(wktPoly);
//            com.ruowei.domain.Polygon polygon = (com.ruowei.domain.Polygon) reader.read(wktPoly);
            Geometries g1 = new Geometries();
            g1.setId(1L);
            g1.setType("危险");
            g1.setName("tu");
            g1.setShape(wktPoly);
            LocationVM vm=new LocationVM();
            vm.setId(1L);
            vm.setType("Point");
            vm.setCoordinate("[34.0,45.0]");
            System.out.println("JSON:"+JSON.toJSONString(vm));
            System.out.println("jsontowkt:"+WKTUtils.geojson2Wkt(JSON.toJSONString(vm)));
            System.out.println(poly.getEnvelope().toString());
            System.out.println(g1.getShape());
            boolean b = poly.contains(point); //返回true或false
            System.out.println("result = " + b);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static boolean withinGeo(double x, double y, String geometry) throws ParseException {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coord = new Coordinate(x, y);
        Point point = geometryFactory.createPoint(coord);
        WKTReader reader = new WKTReader(geometryFactory);
        Polygon polygon = (Polygon) reader.read(geometry);
        return point.within(polygon);
    }
}
