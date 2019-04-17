package tbcloud.agent.admin.service.impl;

import tbcloud.agent.admin.entity.UserInfo;
import tbcloud.agent.admin.mapper.UserInfoMapper;
import tbcloud.agent.admin.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hello
 * @since 2019-04-01
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
