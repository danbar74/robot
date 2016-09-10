package rearobot.robot;

/**
 * Defines abstraction of destination/output for reports produced by {@link Robot}.
 */
public interface ReportWriter {
    void write(String report);
}
