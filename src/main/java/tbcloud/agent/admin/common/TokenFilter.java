package tbcloud.agent.admin.common;

import org.springframework.beans.factory.annotation.Autowired;
import tbcloud.admin.api.ApiConstCollection;
import tbcloud.lib.api.util.IDUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: Dmm
 * @date: 2019/4/3 10:16
 */
public class TokenFilter implements Filter {

    @Autowired
    private RedisClient redisClient;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //不过滤的uri 定义
        String[] notFilter = new String[]{"/login"};

        System.out.println("------------test token filter doFilter --------------");
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;

            //请求的uri
            String uri = req.getRequestURI();

            System.out.println("filter>>>uri>>>"+uri);


            //是否过滤
            boolean doFilter = true;
            for(String s : notFilter){
                //在给定符串中查找另一个字符串
                if(uri.indexOf(s) != -1){
                    //uri中包含不过滤uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }

            if(doFilter){

                String token = req.getHeader("api-token");
                //判断有无token, 非法请求
                if(token==null || "".equals(token)){
                    servletRequest.getRequestDispatcher("/api/invalid").forward(servletRequest,servletResponse);
                    return;

                }

                //转换异常
                Long agentAccountId= null;
                try {
                    agentAccountId = IDUtil.decodeUserIDFromToken(token);
                } catch (Exception e) {
                    e.printStackTrace();
                    servletRequest.getRequestDispatcher("/api/invalid").forward(servletRequest,servletResponse);
                    return;
                }


                System.out.println(agentAccountId+"--------------------------");
                //判断有无id, 非法请求
                if(agentAccountId==null || agentAccountId <=0){
                    servletRequest.getRequestDispatcher("/api/invalid").forward(servletRequest,servletResponse);
                    return;
                }
                String tokenQuery=redisClient.get(ApiConstCollection.REDIS_KEY_AGENT_TOKEN+agentAccountId);

                if(tokenQuery==null){
                    Long l=redisClient.ttl(ApiConstCollection.REDIS_KEY_AGENT_TOKEN+agentAccountId);
                    if(l==-2){
                        //过期
                        servletRequest.getRequestDispatcher("/api/expires").forward(servletRequest,servletResponse);
                        return;
                    }
                    //非法
                    servletRequest.getRequestDispatcher("/api/invalid").forward(servletRequest,servletResponse);
                    return;
                }

                if(token.equals(tokenQuery)){
                    System.out.println("通过了");
                }else{
                    System.out.println("没有通过");
                    servletRequest.getRequestDispatcher("/api/invalid").forward(servletRequest,servletResponse);
                    return;
                }

            }else{
                System.out.println("no Filter>>>执行调用接口");
                //不执行过滤操作
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

        // TODO Auto-generated method stub
        System.out.println("------------test token filter destory--------------");

    }
}
