package com.topcom.bi.domain;

import com.topcom.cms.base.model.nosql.BaseEntityModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by lsm on 2018/3/5 0005.
 */
@Document(collection = "bi_item_type")
public class ItemType extends BaseEntityModel {
    private String name;
    private String type;
    private String description;

    private List<SampleItem> sampleItems;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SampleItem> getSampleItems() {
        return sampleItems;
    }

    public void setSampleItems(List<SampleItem> sampleItems) {
        this.sampleItems = sampleItems;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
