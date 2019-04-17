//package tbcloud.agent;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import tbcloud.agent.admin.entity.AgentInfo;
//import tbcloud.agent.admin.entity.query.AgentInfoQuery;
//import tbcloud.agent.admin.entity.vo.AgentInfoVo;
//import tbcloud.agent.admin.mapper.AgentInfoMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AgentApplicationTests {
//
//	@Autowired
//	AgentInfoMapper agentInfoMapper;
//
//	@Test
//	public void contextLoads() {
//		//AgentInfo agentInfo=agentInfoMapper.selectById(1L);
//
//        AgentInfoQuery agentInfoQuery=new AgentInfoQuery();
//        agentInfoQuery.setAgentId("123456");
//        agentInfoQuery.setParentAgentId("123456");
//        agentInfoQuery.setPage(1);
//        agentInfoQuery.setLimit(10);
//
//		//Long L=agentInfoMapper.countAgentInfo(agentInfoQuery);
//
//		List<AgentInfoVo> agentInfoVoList=agentInfoMapper.selectByPage(agentInfoQuery);
//
//		for(AgentInfoVo agentInfoVo:agentInfoVoList){
//            System.out.println(agentInfoVo.getAgentId());
//        }
//
//       // System.out.println(L);
//		//System.out.println(agentInfo.);
//
//	}
//
//	public static void main(String[] args) {
////		final List<String> list = new ArrayList<>();
////		list.add("1");
////		list.add("2");
////		System.out.println(list);
//
//
//	}
//
//
//
//}
