package tbcloud.agent.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.common.api.version.ApiVersion;
import tbcloud.agent.admin.entity.AgentNode;
import tbcloud.agent.admin.entity.baby.AgentNodeQuery;
import tbcloud.agent.admin.entity.baby.AgentNodeVo;
import tbcloud.agent.admin.entity.query.AgentNodeQueryIs;
import tbcloud.agent.admin.entity.query.ShiftNodeQuery;
import tbcloud.agent.admin.entity.vo.AgentNodeVo2;
import tbcloud.agent.admin.service.IAgentNodeService;

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
@RequestMapping("/api/agent/node")
public class AgentNodeController {

    @Autowired
    private IAgentNodeService iAgentNodeService;

//    /**
//     * 分表了
//     * @param request
//     * @param agentNodeQuery
//     * @return
//     */
//    @RequestMapping(value = "/list2", method = RequestMethod.POST)
//    @ResponseBody
//    public Gather<Result<AgentNodeVo>> selectByPage(HttpServletRequest request, @RequestBody AgentNodeQuery agentNodeQuery) {
//
//        agentNodeQuery.setToken(request.getHeader("api-token"));
//        System.out.println(request.getHeader("api-token") + "TOKEN--node--list");
//
//        return iAgentNodeService.findByPage(agentNodeQuery);
//    }
//
//    @RequestMapping(value = "/listjoin2", method = RequestMethod.POST)
//    @ResponseBody
//    public Gather<Result<AgentNodeVo>> getListJoin(HttpServletRequest request, @RequestBody AgentNodeQuery agentNodeQuery) {
//
//
//        System.out.println("+++++++");
//        System.out.println(request.getHeader("api-token"));
//        agentNodeQuery.setToken(request.getHeader("api-token"));
//
//        return iAgentNodeService.findByPageJoin(agentNodeQuery);
//    }
//

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiVersion
    public Gather<Void> updateNode(@RequestBody ShiftNodeQuery shiftNodeQuery) {

        return iAgentNodeService.shiftNodeJoin(shiftNodeQuery);
    }
//
//    /**
//     * todo 测试
//     * @param request
//     * @param agentNodeQueryIs
//     * @return
//     */
//    @RequestMapping(value = "/list3", method = RequestMethod.POST)
//    @ResponseBody
//    public Gather<Result<AgentNode>> selectByPageIs(HttpServletRequest request, @RequestBody AgentNodeQueryIs agentNodeQueryIs) {
//
//        agentNodeQueryIs.setToken(request.getHeader("api-token"));
//        System.out.println(request.getHeader("api-token") + "TOKEN--node--list");
//
//        return iAgentNodeService.findByPageIs(agentNodeQueryIs);
//    }
//
//    /**
//     * todo 测试
//     * @param request
//     * @param agentNodeQueryIs
//     * @return
//     */
//    @RequestMapping(value = "/listjoin3", method = RequestMethod.POST)
//    @ResponseBody
//    public Gather<Result<AgentNode>> getListJoinIs(HttpServletRequest request, @RequestBody AgentNodeQueryIs agentNodeQueryIs) {
//
//
//        System.out.println("+++++++");
//        System.out.println(request.getHeader("api-token"));
//        agentNodeQueryIs.setToken(request.getHeader("api-token"));
//
//        return iAgentNodeService.findByPageIsJoinIs(agentNodeQueryIs);
//    }

    /**
     * todo 测试 联查
     * @param request
     * @param agentNodeQuery
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiVersion
    public Gather<Result<AgentNodeVo>> selectByPageIs3(HttpServletRequest request, @RequestBody AgentNodeQuery agentNodeQuery) {

        agentNodeQuery.setToken(request.getHeader("api-token"));

        return iAgentNodeService.findByPage(agentNodeQuery);
    }

    /**
     * todo 测试 联查
     * @param request
     * @param agentNodeQuery
     * @return
     */
    @RequestMapping(value = "/listjoin", method = RequestMethod.POST)
    @ResponseBody
    @ApiVersion
    public Gather<Result<AgentNodeVo>> getListJoinIs3(HttpServletRequest request, @RequestBody AgentNodeQuery agentNodeQuery) {

        agentNodeQuery.setToken(request.getHeader("api-token"));

        return iAgentNodeService.findByPageIsJoin(agentNodeQuery);
    }
}
