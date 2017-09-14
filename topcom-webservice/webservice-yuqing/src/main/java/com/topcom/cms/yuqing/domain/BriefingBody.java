package com.topcom.cms.yuqing.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lism on 2017/5/23.
 */
public class BriefingBody implements Serializable {

//    @Transient
    private List<BriefingCell> briefingCells;

    public List<BriefingCell> getBriefingCells() {
        return briefingCells;
    }

    public void setBriefingCells(List<BriefingCell> briefingCells) {
        this.briefingCells = briefingCells;
    }
}
