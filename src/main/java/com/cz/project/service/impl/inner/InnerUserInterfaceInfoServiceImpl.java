package com.cz.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cz.czapicommon.model.entity.UserInterfaceInfo;
import com.cz.czapicommon.service.InnerUserInterfaceInfoService;;
import com.cz.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: ljw
 * @Date: 2023/08/07/0:21
 * @Description:
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Override
    public Boolean invokeCount(Long interfaceInfoId, Long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId,userId);
    }

    @Override
    public UserInterfaceInfo getById(long interfaceInfoId, long userId) {
        QueryWrapper<UserInterfaceInfo> qw = new QueryWrapper<>();
        qw.eq("interfaceInfoId",interfaceInfoId);
        qw.eq("userId",userId);
        return userInterfaceInfoService.getOne(qw);
    }
}
