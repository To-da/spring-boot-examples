package org.toda.boot.autoconfigure.poi;

import org.apache.poi.Version;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.toda.boot.autoconfigure.poi.writer.PoiExcelWriter;

@Configuration
@ConditionalOnClass({Version.class, Workbook.class})
@EnableConfigurationProperties(PoiProperties.class)
public class PoiAutoconfigure {

    Logger logger = LoggerFactory.getLogger(PoiAutoconfigure.class);

    @Autowired
    PoiProperties poiProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = "org.apache.poi.xssf.streaming.SXSSFWorkbook")
    public PoiExcelWriter poiExcelWriter() {
        logger.info("Trying to init {} by auto-configuration.", PoiExcelWriter.class.getSimpleName());
        return new PoiExcelWriter(poiProperties.getFlushSize());
    }

}
