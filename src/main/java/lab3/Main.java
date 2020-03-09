package lab3;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.File;
import java.io.IOException;

public class Main {
    // TODO Весь процесс запуска должен быть автоматизирован, по запуску одной команды должны пройти тесты, запуститься джоба и открыть выходной файл.
    public static void main(String[] args) throws IOException {
        Initial();

        File output = new File("output");

        if (output.exists()) {
            FileUtils.deleteDirectory(output);
        }

        JavaSparkContext sparkContext = GetContextSpark();
        JavaRDD<String> requestsInfo = sparkContext.textFile("E:\\Programming\\Sptn\\input.txt");

        ShowBrowserStatistic(requestsInfo);

        IpProcessor proc = new IpProcessor();
        proc.process(requestsInfo, "output");
    }

    public static void Initial() {
        System.setProperty("hadoop.home.dir", "C:\\winutils");
        System.setProperty("spark.local.dir", "C:\\winutils\\tmp");
    }

    public static JavaSparkContext GetContextSpark() {
        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("lab3");
        return new JavaSparkContext(conf);
    }

    public static void ShowBrowserStatistic(JavaRDD<String> logs) {
        logs.mapToPair(str -> new Tuple2(UserAgent.parseUserAgentString(str).getBrowser().getName(), 0))
                .countByKey().forEach((key, value) -> System.out.println(String.format("%-15s : %d", key, value)));
    }
}
