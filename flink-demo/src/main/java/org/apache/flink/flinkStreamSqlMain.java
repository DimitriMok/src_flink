package org.apache.flink;


import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableConfig;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Flink SQL Main
 *
 */
public class flinkStreamSqlMain
{
    private static final Logger LOGGER = LoggerFactory.getLogger(flinkStreamSqlMain.class);
    public static void main( String[] args )
    {
        LOGGER.info( "create environment!" );
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
        TableConfig tableConfig = tableEnv.getConfig();
        Configuration tableConfigConfiguration =tableConfig.getConfiguration();


        //开启Mini Batch
        tableConfigConfiguration.setString("table.exec.mini-batch.enabled", "true");
        tableConfigConfiguration.setString("table.exec.min-batch.allow-latency", "2 s");
        tableConfigConfiguration.setString("table.exec.mini-batch.size", "5000");

        //开启Local Global

    }
}
