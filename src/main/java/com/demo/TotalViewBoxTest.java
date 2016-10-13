package com.demo;

import java.util.Map;

/**
 * Created by PC0353 on 04/10/2016.
 */
public class TotalViewBoxTest {
    private String date;
    private int sourceId;
    private Map<String,Long> viewHour;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public Map<String, Long> getViewHour() {
        return viewHour;
    }

    public void setViewHour(Map<String, Long> viewHour) {
        this.viewHour = viewHour;
    }

}
