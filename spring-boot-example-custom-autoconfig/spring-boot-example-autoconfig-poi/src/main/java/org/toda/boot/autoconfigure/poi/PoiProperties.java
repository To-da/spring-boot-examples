package org.toda.boot.autoconfigure.poi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("toda.poi")
public class PoiProperties {

    public static final int DEFAULT_FLUSH_SIZE = 20;

    private Integer flushSize = DEFAULT_FLUSH_SIZE;

    public Integer getFlushSize() {
        return flushSize;
    }

    public void setFlushSize(Integer flushSize) {
        this.flushSize = flushSize;
    }
}
