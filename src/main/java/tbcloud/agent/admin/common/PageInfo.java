package tbcloud.agent.admin.common;

/**
 * @author: Dmm
 * @date: 2019/4/1 16:12
 */
public class PageInfo {

    private int page;
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getOffset() {
        return (page-1)*limit;
    }
}
