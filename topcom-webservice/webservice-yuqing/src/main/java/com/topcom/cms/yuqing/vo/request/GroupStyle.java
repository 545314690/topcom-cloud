package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.domain.Group;
import com.topcom.cms.yuqing.domain.UserStyle;

import java.io.Serializable;

/**
 * Created by topcom on 2017/9/11 0011.
 */
public class GroupStyle implements Serializable {
    private Group group;
    private UserStyle style;

    public GroupStyle() {
    }
    public GroupStyle(Group group) {
        this.group = group;
    }

    public GroupStyle(Group group, UserStyle style) {
        this.group = group;
        this.style = style;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UserStyle getStyle() {
        return style;
    }

    public void setStyle(UserStyle style) {
        this.style = style;
    }
}
