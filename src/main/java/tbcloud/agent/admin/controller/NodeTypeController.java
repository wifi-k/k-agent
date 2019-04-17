package tbcloud.agent.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tbcloud.agent.admin.common.Gather;
import tbcloud.agent.admin.common.Result;
import tbcloud.agent.admin.entity.NodeType;
import tbcloud.agent.admin.service.INodeTypeService;
import tbcloud.lib.api.ApiCode;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hello
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/api/agent/type")
public class NodeTypeController {

    @Autowired
    private INodeTypeService iNodeTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Gather<Result<NodeType>> selectByPage(HttpServletRequest request) {

        return iNodeTypeService.selectByPage();
    }

}
