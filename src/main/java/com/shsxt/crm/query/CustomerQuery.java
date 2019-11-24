package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

public class CustomerQuery extends BaseQuery {

    private String khno;
    private String fr;
    private String name;

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
