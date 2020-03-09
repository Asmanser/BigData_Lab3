/*
package lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProcessingTest {

    private static JavaRDD<String> _requestsInfo;
    private static IpProcessor _processor;
    private static UserAgentAnalyzer _analyzer;


    @BeforeClass
    public static void Init() {
        System.setProperty("hadoop.home.dir", "C:\\winutils");
        System.setProperty("spark.local.dir", "C:\\winutils\\tmp");

        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);;
        _requestsInfo = sparkContext.textFile(".\\ipProcessorTestData.txt");
        _processor = new IpProcessor();
        _analyzer = new UserAgentAnalyzer();
    }

    @Test
    public void IpProcessorPositiveTest() {
        TreeMap<String, LogInfo> data = _processor.Process(_requestsInfo);

        Set<String> keys = data.keySet();
        assertEquals(keys.size(), 2);
        assertTrue(keys.contains("ip1") && keys.contains("ip2"));

        LogInfo ip1 = data.get("ip1");
        assertTrue(ip1.Count == 2 && ip1.Length == 56928 + 42011);

        LogInfo ip2 = data.get("ip2");
        assertTrue(ip2.Count == 1 && ip2.Length == 14917);
    }

    @Test
    public void UserAgentAnalyzerPositiveTest() {
        Map<String, Long> data = _analyzer.GetBrowsersStatistic(_requestsInfo);

        Set<String> keys = data.keySet();
        assertEquals(keys.size(), 2);
        assertTrue(keys.contains("Opera 10") && keys.contains("Firefox 3"));

        assertEquals(data.get("Opera 10"), 2);
        assertEquals(data.get("Firefox 3"), 1);
    }
}*/
