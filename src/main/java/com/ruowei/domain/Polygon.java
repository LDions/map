package com.ruowei.domain;


import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.Serializable;

public class Polygon extends org.locationtech.jts.geom.Polygon implements Serializable {
    public Polygon(LinearRing shell, PrecisionModel precisionModel, int SRID) {
        super(shell, precisionModel, SRID);
    }

    public Polygon(LinearRing shell, LinearRing[] holes, PrecisionModel precisionModel, int SRID) {
        super(shell, holes, precisionModel, SRID);
    }

    public Polygon(LinearRing shell, LinearRing[] holes, GeometryFactory factory) {
        super(shell, holes, factory);
    }
}
