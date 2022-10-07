package com.lq.flink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author LQ
 * @date 2020/11/26 23:37
 */
public class SourceTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String filePath = "D:\\myself-study\\flink\\src\\main\\java\\com\\lq\\flink\\微信_820200728000001_20201026.csv";
        DataStreamSource<String> dataStreamSource = env.readTextFile(filePath);
        dataStreamSource.print();

        env.execute("source test");

    }
}
