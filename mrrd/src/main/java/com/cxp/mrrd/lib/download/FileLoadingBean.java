package com.cxp.mrrd.lib.download;

/**
 * 文 件 名: FileLoadingBean
 * 创 建 人: CXP
 * 创建日期: 2017-02-06 10:33
 * 描    述: 下载参数
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class FileLoadingBean {

    /**
     * 文件大小
     */
    long total;
    /**
     * 已下载大小
     */
    long progress;

    public long getProgress() {
        return progress;
    }

    public long getTotal() {
        return total;
    }

    public FileLoadingBean(long total, long progress) {
        this.total = total;
        this.progress = progress;
    }
}
