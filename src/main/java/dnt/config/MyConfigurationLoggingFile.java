package dnt.config;

import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Configuration
public class MyConfigurationLoggingFile extends TimeBasedRollingPolicy {

    @Override
    public void setFileNamePattern(String fnp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        super.setFileNamePattern(sdf.format(new Timestamp(System.currentTimeMillis())) + fnp);
    }


}
