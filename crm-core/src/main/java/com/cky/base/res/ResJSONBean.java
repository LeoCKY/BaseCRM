package com.cky.base.res;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ResJSONBean {

    //默认成功
    private boolean flag = true;

    private String msg;

    private JSONObject jsonObj;

    private Integer status;

    private Object data;

    public ResJSONBean() {
    }

    public ResJSONBean(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public ResJSONBean(boolean flag, String msg, Integer status) {
        this.flag = flag;
        this.msg = msg;
        this.status = status;
    }

    /**
     * restful 返回
     */
    public static ResJSONBean error(String msg) {
        return new ResJSONBean(false, msg);
    }

    public static ResJSONBean Success(String msg) {
        return new ResJSONBean(true, msg);
    }
}
