package tbcloud.agent.admin.common;

import tbcloud.admin.api.ApiCodeCollection;

import java.io.Serializable;

/**
 * 结果集 如何data为集合，类为total(数据量),page(数据)
 * @param <T>
 * @author dmm
 */
public class Gather<T> implements Serializable {

    private int code= ApiCodeCollection.SUCCESS;

    private T data;

    private String msg;
    /**
     * if data is array, count is the array's size
     */

    private Integer count;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
