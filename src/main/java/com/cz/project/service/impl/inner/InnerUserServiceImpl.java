package com.cz.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cz.czapicommon.model.entity.InterfaceInfo;
import com.cz.czapicommon.model.entity.User;
import com.cz.czapicommon.service.InnerUserService;
import com.cz.project.common.ErrorCode;
import com.cz.project.exception.BusinessException;
import com.cz.project.mapper.UserInterfaceInfoMapper;
import com.cz.project.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: ljw
 * @Date: 2023/08/07/0:21
 * @Description:
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口不存在");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey",accessKey);
        return userMapper.selectOne(queryWrapper);
    }
}
