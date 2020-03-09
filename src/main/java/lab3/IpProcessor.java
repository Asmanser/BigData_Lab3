package lab3;

import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpProcessor implements Serializable {

    private static String _ipPattern = "ip(\\d+)";
    private static String _contentLengthPattern = "\\d{3}\\s(\\d+)";

    public void process(JavaRDD<String> logs, String fileName) {
        logs.mapToPair(word -> {
            Matcher matcher = Pattern.compile(_ipPattern).matcher(word);
            matcher.find();

            String ip = matcher.group(0);
            LogInfo logInfo = new LogInfo();

            matcher.usePattern(Pattern.compile(_contentLengthPattern));

            if (matcher.find()) {
                logInfo.length = Integer.parseInt(matcher.group(1));
            }
            return new Tuple2<>(ip, logInfo);
        }).reduceByKey((current, next) -> {
            current.count++;
            current.length += next.length;
            return current;
        }).map(tuple -> new Tuple2(tuple._1(), new LogInfo(tuple._2().length / tuple._2.count, tuple._2().length))).saveAsTextFile(fileName);
    }
}
