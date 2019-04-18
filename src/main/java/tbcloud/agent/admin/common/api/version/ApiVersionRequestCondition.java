package tbcloud.agent.admin.common.api.version;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理字符串
 * @author: Dmm
 * @date: 2019/4/18 10:58
 */
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    /**
     * 用于匹配request中的版本号  v1 v2
     */
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)");
    /**
     * 保存当前的版本号
     */

    private int version;
    /**
     * 保存所有接口的最大版本号
     */

    private static int maxVersion = 20190412;


    public ApiVersionRequestCondition(int version) {
        this.version = version;
    }


    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition apiVersionRequestCondition) {
        return new ApiVersionRequestCondition(apiVersionRequestCondition.version);
    }

    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {

        String s= httpServletRequest.getHeader("api-version");

        if(s==null || "".equals(s)){
            return null;
        }

        //正则匹配请求的uri，看是否有版本号 v1
        Matcher matcher = VERSION_PATTERN.matcher(s);

        if (matcher.find()) {
            String versionNo = matcher.group(1);
            //String versionNo0 = matcher.group(0);
            int version = Integer.valueOf(versionNo);
            // 超过当前最大版本号或者低于最低的版本号均返回不匹配 修改 todo
            if (version <= maxVersion && version >= this.version) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionRequestCondition apiVersionRequestCondition, HttpServletRequest httpServletRequest) {
        // 以版本号大小判定优先级，越高越优先
        return apiVersionRequestCondition.version - this.version;
    }

    public int getVersion() {
        return version;
    }

    public static void setMaxVersion(int maxVersion) {

        ApiHandlerMapping.setLatestVersion(maxVersion);
        ApiVersionRequestCondition.maxVersion = maxVersion;
    }
}
