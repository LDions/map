package com.ruowei.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.QSewPot;
import com.ruowei.domain.QSewProcess;
import com.ruowei.domain.QSewSlu;
import com.ruowei.service.SewEmiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SelectUtil {


    private final Logger log = LoggerFactory.getLogger(SelectUtil.class);
    private final ObjectMapper objectMapper;
    private final SewEmiService sewEmiService;
    private final JPAQueryFactory jpaQueryFactory;
    private final QSewProcess qSewProcess = QSewProcess.sewProcess;
    private final QSewPot qSewPot = QSewPot.sewPot;
    private final QSewSlu qSewSlu = QSewSlu.sewSlu;

    public SelectUtil(ObjectMapper objectMapper, SewEmiService sewEmiService, JPAQueryFactory jpaQueryFactory) {
        this.objectMapper = objectMapper;
        this.sewEmiService = sewEmiService;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<BigDecimal> getSome(String source, String target, String beginTime, String endTime,String subsection) {
        JPAQuery<BigDecimal> bigDecimalJPAQuery = null;
        if (source.equals("meter")) {
            OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
                .notEmptyAnd(qSewProcess.dayTime::goe, sewEmiService.getInstant(beginTime))
                .notEmptyAnd(qSewProcess.dayTime::loe, sewEmiService.getInstant(endTime));
            switch (target) {
                case "inFlow":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inFlow)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inAmmonia":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inAmmonia)
                        .from(qSewProcess)
                        .where(predicate.build());

                    break;

                case "inCod":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inCod)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inTp":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inTp)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inTn":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inTn)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inSs":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inSs)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "outFlow":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.outFlow)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "outCod":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.outCod)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "outTp":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.outTp)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "outTn":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.outTn)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "outSs":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.outSs)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "anoxicPoolDo":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.anoxicPoolDo)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "aerobicPoolDo":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.aerobicPoolDo)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;


                case "anoxicPoolDoOutNit":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.anoxicPoolDoOutNit)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;


                case "aerobicPoolNit":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.aerobicPoolNit)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;
            }
        }
        if (source.equals("assay")) {
            OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
                .notEmptyAnd(qSewSlu.dayTime::goe, sewEmiService.getInstant(beginTime))
                .notEmptyAnd(qSewSlu.dayTime::loe, sewEmiService.getInstant(endTime));
            switch (target) {
                case "assInAmmonia":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assInAmmonia)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;

                case "assInCod":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assInCod)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assInTn":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assInTn)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assInTp":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assInTp)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assAnoxicPoolDoOutNit":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assAnoxicPoolDoOutNit)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assAerobicPoolDoOutNit":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assAerobicPoolDoOutNit)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assOutAmmonia":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assOutAmmonia)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assOutCod":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assOutCod)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assOutTn":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assOutTn)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;
                case "assOutTp":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewSlu.assOutTp)
                        .from(qSewSlu)
                        .where(predicate.build());
                    break;


            }
            if (source.equals("daily")) {
                return null;
            }
        }
        return bigDecimalJPAQuery.fetch();
    }
}
