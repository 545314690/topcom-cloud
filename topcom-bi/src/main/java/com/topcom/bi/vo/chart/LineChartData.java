package com.topcom.bi.vo.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsm on 2018/3/2 0002.
 */
public class LineChartData implements Serializable {
    private List<ChartData> data = new ArrayList();

    public List<ChartData> getData() {
        return data;
    }

    public void setData(List<ChartData> data) {
        this.data = data;
    }
}
