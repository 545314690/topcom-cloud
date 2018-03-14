package com.topcom.bi.vo.chart;



import java.util.List;

/**
 * Created by lsm on 2018/3/2 0002.
 */
public class ChartData {
    private String name;
    private List<NameValue> data;

    public ChartData() {
    }

    public ChartData(String name, List<NameValue> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NameValue> getData() {
        return data;
    }

    public void setData(List<NameValue> data) {
        this.data = data;
    }
}
