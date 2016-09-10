package rearobot.robot;

import java.util.LinkedList;

class TestReportWriter implements ReportWriter {
    private LinkedList<String> reports = new LinkedList<>();

    @Override
    public void write(String report) {
        if (reports.size() == 20) {
            reports.removeFirst();
        }
        reports.add(report);
    }

    String mostRecentReport() {
        return reports.getLast();
    }
}
