package com.ruowei.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Zhjcxt Java.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final BlockChain blockChain = new BlockChain();

    private String reportPath;

    private String host;

    //集团IP地址
    private String plateHost;

    //平台IP地址
    public String getPlateHost() {
        return plateHost;
    }

    public void setPlateHost(String plateHost) {
        this.plateHost = plateHost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public BlockChain getBlockChain() {
        return blockChain;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    // 区块链业务系统配置信息
    public static class BlockChain {
        private String appId;
        private String channelId;
        private String chaincodeId;
        private String baseUrl;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getChaincodeId() {
            return chaincodeId;
        }

        public void setChaincodeId(String chaincodeId) {
            this.chaincodeId = chaincodeId;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }
}
