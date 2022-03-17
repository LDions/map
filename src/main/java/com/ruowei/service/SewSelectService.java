package com.ruowei.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.QSewPot;
import com.ruowei.domain.QSewProcess;
import com.ruowei.domain.QSewSlu;
import com.ruowei.util.OptionalBooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service

public class SewSelectService {

    private final ObjectMapper objectMapper;
    private final SewEmiService sewEmiService;
    private final JPAQueryFactory jpaQueryFactory;
    private final QSewProcess qSewProcess =QSewProcess.sewProcess;
    private final QSewPot qSewPot = QSewPot.sewPot;
    private final QSewSlu qSewSlu = QSewSlu.sewSlu;

    public SewSelectService(ObjectMapper objectMapper, SewEmiService sewEmiService, JPAQueryFactory jpaQueryFactory) {
        this.objectMapper = objectMapper;
        this.sewEmiService = sewEmiService;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<BigDecimal> getSome(String str, String str1, String beginTime, String endTime) {
        JPAQuery<BigDecimal> bigDecimalJPAQuery = null;
        if (str.equals("meter")){
            OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
                .notEmptyAnd(qSewProcess.dayTime::goe, sewEmiService.getInstant(beginTime))
                .notEmptyAnd(qSewProcess.dayTime::loe,sewEmiService.getInstant(endTime));
            switch (str1){
                case "inFlow":
                    bigDecimalJPAQuery= jpaQueryFactory.select(qSewProcess.inFlow)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inAmmonia":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inAmmonia)
                        .from(qSewProcess)
                        .where(predicate.build());

                    break;

                case "inCod;":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inCod)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inTp;":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inTp)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inTn;":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inTn)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "inSs;":
                    bigDecimalJPAQuery = jpaQueryFactory.select(qSewProcess.inSs)
                        .from(qSewProcess)
                        .where(predicate.build());
                    break;

                case "outFlow;":
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
        if (str.equals("assay")){
            return null;
        }
        if (str.equals("daily")){
            return null;
        }
        return bigDecimalJPAQuery.fetch();
    }
}
