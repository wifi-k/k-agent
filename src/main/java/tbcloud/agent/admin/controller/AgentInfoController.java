package tbcloud.agent.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentInfo;
import tbcloud.agent.admin.entity.baby.AgentInfoQuery;
import tbcloud.agent.admin.entity.query.AgentInfoUpdate;
import tbcloud.agent.admin.entity.vo.AgentInfoVo;
import tbcloud.agent.admin.service.IAgentInfoService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hello
 * @since 2019-04-15
 */
@RestController
@RequestMapping("/api/agent/info")
public class AgentInfoController {

    @Autowired
    IAgentInfoService iAgentInfoService;

    /**
     * todo ok
     * @param request
     * @param agentInfoQuery
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Gather<Result<AgentInfoVo>> selectByPage(HttpServletRequest request, @RequestBody AgentInfoQuery agentInfoQuery) {

        System.out.println(request.getHeader("api-token") + "TOKEN--agent--list");

        agentInfoQuery.setToken(request.getHeader("api-token"));

        return iAgentInfoService.findByPage(agentInfoQuery);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Gather<Void> updateAgentInfo(HttpServletRequest request, @RequestBody AgentInfo agentInfo) {

        System.out.println(request.getHeader("api-token") + "TOKEN--agent--update");

        //agentInfoUpdate.setToken(request.getHeader("api-token"));

        return iAgentInfoService.updateAgentInfo(agentInfo);
    }

    /**
     *
     * @param request
     * @param agentInfoQuery
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Gather<AgentInfoVo> selectAgentInfo(HttpServletRequest request, @RequestBody AgentInfoQuery agentInfoQuery) {

        System.out.println(request.getHeader("api-token") + "TOKEN--agent--info");

        agentInfoQuery.setToken(request.getHeader("api-token"));

        return iAgentInfoService.selectAgentInfo(agentInfoQuery);
    }
}
