package tbcloud.agent.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.AgentAccount;
import tbcloud.agent.admin.entity.baby.AgentAccountQuery;
import tbcloud.agent.admin.entity.baby.AgentAccountUpdate;
import tbcloud.agent.admin.entity.vo.LoginToken;
import tbcloud.agent.admin.service.IAgentAccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hello
 * @since 2019-04-02
 */
@RestController
@RequestMapping("/api/agent/account")
public class AgentAccountController {

    @Autowired
    private IAgentAccountService iAgentAccountService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Gather<Void> addAgentAccount(HttpServletRequest request, @RequestBody AgentAccountUpdate agentAccountUpdate){

        System.out.println(request.getHeader("api-token")+"TOKEN--ACCOUNT--add");
        agentAccountUpdate.setToken(request.getHeader("api-token"));

        return iAgentAccountService.addAgentAccount(agentAccountUpdate);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Gather<Void> updateAgentAccount(HttpServletRequest request, @RequestBody AgentAccountUpdate agentAccountUpdate){

        System.out.println(request.getHeader("api-token")+"TOKEN--ACCOUNT--update");
        agentAccountUpdate.setToken(request.getHeader("api-token"));

        return iAgentAccountService.updateAgentAccount(agentAccountUpdate);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    //OK
    public Gather<Void> deleteAgentAccount(HttpServletRequest request, @RequestBody AgentAccountUpdate agentAccountUpdate){

        System.out.println(request.getHeader("api-token")+"TOKEN--ACCOUNT--delete");
        agentAccountUpdate.setToken(request.getHeader("api-token"));

        return iAgentAccountService.deleteAgentAccount(agentAccountUpdate);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    //OK
    public Gather<Result<AgentAccount>> selectByPage(HttpServletRequest request, @RequestBody AgentAccountQuery agentAccountQuery){

        System.out.println(request.getHeader("api-token")+"TOKEN--ACCOUNT--list");
        //TOKEN 存入
        agentAccountQuery.setToken(request.getHeader("api-token"));

        return iAgentAccountService.findByPage(agentAccountQuery);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    //ok
    public Gather<LoginToken> loginAgentAccount(@RequestBody AgentAccount agentAccount){

        return iAgentAccountService.agentAccountLogin(agentAccount);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    //ok
    public Gather<Void> logoutAgentAccount(HttpServletRequest request){

        String token=request.getHeader("api-token");

        return iAgentAccountService.agentAccountLogout(token);
    }


}
