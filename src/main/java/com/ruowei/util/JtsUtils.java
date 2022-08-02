//package com.ruowei.util;
//
//import org.locationtech.jts.geom.Coordinate;
//import org.locationtech.jts.geom.Geometry;
//
//public class JtsUtils {
//    // 将几何类型转换为wkt字符串
//    public static String toEwkt(Geometry geom) {
//        if (geom == null) {
//            return null;
//        }
//        String type = geom.getGeometryType().toUpperCase();
//        switch (type) {
//            case "POINT": {
//                Coordinate coor = geom.getCoordinate();
//                return "POINT(" + formatCoordinate(coor) + ")";
//            }
//            case "MULTIPOINT": {
//                return coordinateToEwkt("MULTIPOINT(", geom.getCoordinates(), ")");
//            }
//            case "LINESTRING": {
//                return coordinateToEwkt("LINESTRING(", geom.getCoordinates(), ")");
//            }
//            case "POLYGON": {
//                return coordinateToEwkt("POLYGON((", geom.getCoordinates(), "))");
//            }
////            case "MULTILINESTRING": {
////                StringBuffer sb = new StringBuffer();
////                sb.append("MULTILINESTRING(");
////                sb.append(multiGeometryFormat(geom));
////                sb.append(")");
////                return sb.toString();
////            }
////            case "MULTIPOLYGON": {
////                StringBuffer sb = new StringBuffer();
////                sb.append("MULTIPOLYGON(");
////                sb.append(multiGeometryFormat(geom));
////                sb.append(")");
////                return sb.toString();
////            }
//            case "GEOMETRYCOLLECTION": {
//                StringBuffer sb = new StringBuffer();
//                int num = geom.getNumGeometries();
//                sb.append("GEOMETRYCOLLECTION(");
//                for (int j = 0; j < num; j++) {
//                    Geometry element = geom.getGeometryN(j);
//                    String subType = element.getGeometryType().toUpperCase();
//                    if ("POINT".equals(subType)) {
//                        sb.append("POINT(" + formatCoordinate(element.getCoordinate()) + ")");
//                    } else if ("LINESTRING".equals(subType)) {
//                        sb.append(coordinateToEwkt("LINESTRING(", element.getCoordinates(), ")"));
//                    } else if ("POLYGON".equals(subType)) {
//                        sb.append(coordinateToEwkt("POLYGON((", element.getCoordinates(), "))"));
//                    }
//                    if (j < num - 1) {
//                        sb.append(",");
//                    }
//                }
//                sb.append(")");
//                return sb.toString();
//            }
//        }
//        return null;
//    }
//
//    private static String formatCoordinate(Coordinate coordinate) {
//        if (Double.isNaN(coordinate.z)) {
//            coordinate.z = 0;
//        }
//        return df.format(coordinate.x) + " " + df.format(coordinate.y) + " " + (coordinate.z != 0 ? df.format(coordinate.z) : "0");
//    }
//
//    public static String coordinateToEwkt(String prefix, Coordinate[] coordinates, String suffix) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(prefix);
//        boolean append = false;
//        for (Coordinate coordinate : coordinates) {
//            if (append) {
//                sb.append(",");
//            }
//            sb.append(formatCoordinate(coordinate));
//            append = true;
//        }
//        sb.append(suffix);
//        return sb.toString();
//    }
//}
