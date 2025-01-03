package cn.yizhimcqiu.vine.vpa;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VinePerformanceAnalyzer {
    private static class PerformanceSegment {
        String type;
        long startTime;
        long endTime;

        PerformanceSegment(String type, long startTime) {
            this.type = type;
            this.startTime = startTime;
        }

        void end(long endTime) {
            this.endTime = endTime;
        }
    }

    private static final List<PerformanceSegment> segments = new ArrayList<>();

    public static void start(String type) {
        long startTime = System.nanoTime();
        segments.add(new PerformanceSegment(type, startTime));
    }

    public static void end() {
        if (!segments.isEmpty()) {
            PerformanceSegment segment = segments.remove(segments.size() - 1);
            segment.end(System.nanoTime());
        }
    }

    public static void print() {
        long totalRuntime = 0;
        for (PerformanceSegment segment : segments) {
            totalRuntime += (segment.endTime - segment.startTime);
        }

        StringBuilder output = getStringBuilder(totalRuntime);

        // Limit the output to 10 characters
        if (output.length() > 10) {
            output.setLength(10);
        }

        // Print the performance bar
        System.out.println(output);
    }

    private static @NotNull StringBuilder getStringBuilder(long totalRuntime) {
        StringBuilder output = new StringBuilder();
        for (PerformanceSegment segment : segments) {
            double percentage = (double) (segment.endTime - segment.startTime) / totalRuntime * 100;
            int count = (int) Math.round(percentage * 10);
            for (int i = 0; i < count; i++) {
                if (segment.type.equals("js")) {
                    output.append("-");
                } else if (segment.type.equals("java")) {
                    output.append("#");
                }
            }
        }
        return output;
    }
}