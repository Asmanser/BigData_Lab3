package lab3;

import java.io.Serializable;

public class LogInfo implements Serializable {
    public int count = 1;

    public double average;

    public int length;

    public LogInfo() {
    }

    public LogInfo(double average, int length) {
        this.average = average;
        this.length = length;
    }

    @Override
    public String toString() {
        return "average =" + average +
                ", Length=" + length;
    }
}
