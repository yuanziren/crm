package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

public class CustomerOrderQuery extends BaseQuery {
    private Integer cusId;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}