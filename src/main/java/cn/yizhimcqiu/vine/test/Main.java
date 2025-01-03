package cn.yizhimcqiu.vine.test;

import cn.yizhimcqiu.vine.vpa.VinePerformanceAnalyzer;

public class Main {
    public static void main(String[] args) {
        VinePerformanceAnalyzer.start("js");
        // 耗时2秒的代码
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VinePerformanceAnalyzer.end();

        VinePerformanceAnalyzer.start("java");
        // 耗时5秒的代码
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VinePerformanceAnalyzer.end();

        VinePerformanceAnalyzer.start("js");
        // 耗时3秒的代码
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VinePerformanceAnalyzer.end();

        VinePerformanceAnalyzer.print();
    }
}
