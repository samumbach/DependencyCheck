package org.owasp.dependencycheck.maven;

import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import org.apache.maven.plugin.logging.Log;

public class CustomLogHandler extends Handler {
    private final Log mavenLog;

    public CustomLogHandler(Log mavenLog) {
        this.mavenLog = mavenLog;
    }

    @Override
    public void publish(LogRecord record) {
        String message = record.getMessage();
        int level = record.getLevel().intValue();

        if (level >= java.util.logging.Level.SEVERE.intValue()) {
            mavenLog.error(message);
        } else if (level >= java.util.logging.Level.WARNING.intValue()) {
            mavenLog.warn(message);
        } else if (level >= java.util.logging.Level.INFO.intValue()) {
            mavenLog.info(message);
        } else {
            mavenLog.debug(message);
        }
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}
}
