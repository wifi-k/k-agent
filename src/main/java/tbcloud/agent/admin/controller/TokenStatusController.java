package tbcloud.agent.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tbcloud.agent.admin.common.Gather;
import tbcloud.lib.api.ApiCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Dmm
 * @date: 2019/4/4 15:17
 */
@RestController
public class TokenStatusController {

    @RequestMapping("/api/expires")
    public Gather<String> requestFailedExpires(){

//        Map<String, String> map = new HashMap<>(2);
//        map.put("code", ApiCode.TOKEN_EXPIRED+"");
//        map.put("msg", "TOKEN_EXPIRED");

        Gather<String> gather=new Gather<>();
        gather.setCode(ApiCode.TOKEN_EXPIRED);
        gather.setMsg("TOKEN_EXPIRED");
        return gather;
    }

    @RequestMapping(value = "/api/invalid")
    public Gather<String> requestFailedInvalid(){


        Gather<String> gather=new Gather<>();
        gather.setCode(ApiCode.TOKEN_INVALID);
        gather.setMsg("TOKEN_INVALID");
        return gather;

//        Map<String, String> map = new HashMap<>(2);
//        map.put("code", ApiCode.TOKEN_INVALID+"");
//        map.put("msg", "TOKEN_INVALID");
//        return map;
    }


}
