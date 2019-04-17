package tbcloud.agent.admin.common;

import java.util.List;

/**
 * @author: Dmm
 * @date: 2019/4/1 17:58
 */
public class Result<T> {

    private long count;

    private List<T> page;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getPage() {
        return page;
    }

    public void setPage(List<T> page) {
        this.page = page;
    }
}
