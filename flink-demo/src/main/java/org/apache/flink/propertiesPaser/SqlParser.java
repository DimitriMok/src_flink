package org.apache.flink.propertiesPaser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SqlParser {
    private static final Logger LOG = LoggerFactory.getLogger(SqlParser.class);
    protected static List<String> loadFlinkSqlFromResource(String sqlPath){
        List<String> sqls = new ArrayList<>();
        LOG.info("开始读取配置文件: {}!", sqlPath);
        InputStream inputStream = SqlParser.class.getClassLoader().getResourceAsStream(sqlPath);

        return sqls;
    }
    protected static List<String> loadFlinkSqlFromFile(String sqlPath){
        List<String> sqls = new ArrayList<>();
        LOG.info("开始读取配置文件: {}！", sqlPath);
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try{
            inputStream = new FileInputStream(new File(sqlPath));
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuffer sql = new StringBuffer();
            while((line = bufferedReader.readLine()) != null){
                if(line.trim().equals("#####")){
                    if(sql.length() != 0) sqls.add(sql.toString());
                    sql.setLength(0);
                }
                sql.append(line);
            }
        } catch (FileNotFoundException e) {
            LOG.error("文件: {} 不存在！", sqlPath);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOG.error("文件: {} 读取异常！", sqlPath);
            throw new RuntimeException(e);
        }


        return sqls;
    }
}
