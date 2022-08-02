package com.ruowei.util;


import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;

/*
 * wkt和geoJson互相转的工具类
 */
public class WKTUtils {

    /**
     * geojson 转 geometry
     *
     * @param geojson
     * @return
     * @throws Exception
     */
    public static Geometry geojson2Geometry(String geojson) {
        GeoJSONReader reader = new GeoJSONReader();
        Geometry geometry = reader.read(geojson);
        return geometry;
    }

    /**
     * wkt 转 geometry
     *
     * @param wkt
     * @return
     * @throws Exception
     */
    public static Geometry wkt2Geometry(String wkt) throws Exception {
        WKTReader reader = new WKTReader();
        return reader.read(wkt);
    }

    /**
     * geometry 转 wkt
     *
     * @param geometry
     * @return
     * @throws Exception
     */
    public static String geometry2Wkt(Geometry geometry) throws Exception {
        WKTWriter writer = new WKTWriter();
        return writer.write(geometry);
    }

    /**
     * geojson 转 wkt
     *
     * @param geojson
     * @return
     * @throws Exception
     */
    public static String geojson2Wkt(String geojson) throws Exception {
        Geometry geometry = geojson2Geometry(geojson);
        return geometry2Wkt(geometry);
    }

    /**
     * geometry 转 geojson
     *
     * @param geometry
     * @return
     * @throws Exception
     */
    public static String geometry2Geojson(Geometry geometry) throws Exception {
        GeoJSONWriter writer = new GeoJSONWriter();
        GeoJSON json = writer.write(geometry);
        return json.toString();
    }

    /**
     * wkt 转 geojson
     *
     * @param wkt
     * @return
     * @throws Exception
     */
    public static String wkt2Geojson(String wkt) throws Exception {
        Geometry geometry = wkt2Geometry(wkt);
        return geometry2Geojson(geometry);
    }

//    public static void main(String[] args) throws Exception {
//        String geojson =
//            "{\"content\":\"测试1\",\"id\":\"33EB12E9-75D9-4358-B80E-B6F06FAFB364\",\"type\":\"Polygon\",\"coordinates\":[[[116.2078857421875,39.928694653732364],[116.20925903320312,39.91078961774283],[116.20651245117188,39.89393354266699],[116.23397827148436,39.86547951378614],[116.24496459960938,39.82752244475985],[116.29852294921876,39.78954439311165],[116.3397216796875,39.78532331459258],[116.3836669921875,39.78848914776114],[116.41799926757811,39.79904087286648],[116.444091796875,39.80748108746673],[116.45919799804688,39.818029898770206],[116.48117065429686,39.83490462943255],[116.50314331054688,39.86231722624386],[116.50588989257812,39.88023492849342],[116.5045166015625,39.90973623453719],[116.4935302734375,39.925535281697286],[116.5045166015625,39.94975340768179],[116.47979736328125,39.98132938627215],[116.47567749023438,39.99395569397331],[116.45507812500001,40.000267972646796],[116.43859863281249,40.000267972646796],[116.4166259765625,39.998163944585805],[116.36581420898438,40.00868343656941],[116.35208129882812,40.00447583427404],[116.30264282226562,40.01078714046552],[116.27792358398436,39.999215966720165],[116.24771118164061,39.99500778093748],[116.23260498046874,39.990799335838034],[116.21200561523438,39.95606977009003],[116.2078857421875,39.928694653732364]]]}";
//        String pointjson = "{\"type\":\"Point\",\"coordinates\":[34.0,45.0]}";
//        String point=geojson2Wkt(pointjson);
//        System.out.println(point);
//        System.out.println(wkt2Geometry(point));
////        String wkt = geojson2Wkt(geojson);
////        System.out.println(wkt);
////        String newGeojson = wkt2Geojson(wkt);
////        System.out.println(newGeojson);
//    }

}
