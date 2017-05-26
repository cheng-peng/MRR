package com.cxp.mrr.model;

/**
 * 文 件 名: ResponseModel
 * 创 建 人: CXP
 * 创建日期: 2017-02-05 9:02
 * 描    述: 数据返回统一解析
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class ResponseModel {

    //状态码
    private String ret;
    //状态信息
    private String desc;
    //返回参数
    private Object result;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }
}
