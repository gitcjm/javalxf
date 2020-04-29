package com.str.rafactoring.duplicate_observed_data;

import java.util.Observable;

/**
 * 观察者模式的主题(处理业务逻辑)
 */
public class Interval extends Observable {
    private String start = "0";
    private String end = "0";
    private String length = "0";

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
        setChanged();
        notifyObservers();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
        setChanged();
        notifyObservers();
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
        setChanged();
        notifyObservers();
    }

    public void calculateLength() {
        int start = Integer.parseInt(getStart());
        int end = Integer.parseInt(getEnd());
        int length = end - start;
        setLength(String.valueOf(length));
    }

    public void calculateEnd() {
        int start = Integer.parseInt(getStart());
        int length = Integer.parseInt(getLength());
        int end = start + length;
        setEnd(String.valueOf(end));
    }
}
