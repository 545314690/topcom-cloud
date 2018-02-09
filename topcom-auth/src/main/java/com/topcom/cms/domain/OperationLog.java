//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(
    name = "t_operation_log"
)
public class OperationLog extends BaseEntityModel {
    @Column(
        name = "username"
    )
    String username;
    @Column(
        name = "userId"
    )
    Long userId;
    @Column(
        name = "host"
    )
    String host;
    @Column(
        name = "method"
    )
    String method;
    @Column(
        name = "entity"
    )
    String entity;
    @Column(
        name = "operation"
    )
    @Enumerated(EnumType.STRING)
    OperationLog.Operation operation;

    public OperationLog() {
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OperationLog.Operation getOperation() {
        return this.operation;
    }

    public void setOperation(OperationLog.Operation operation) {
        this.operation = operation;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public static enum Operation {
        CREATE,
        MODIFY,
        DELETE;

        private Operation() {
        }
    }
}
