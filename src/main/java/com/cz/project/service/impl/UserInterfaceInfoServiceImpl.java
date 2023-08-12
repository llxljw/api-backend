package com.cz.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.czapicommon.model.entity.UserInterfaceInfo;
import com.cz.project.common.ErrorCode;
import com.cz.project.mapper.UserInterfaceInfoMapper;
import com.cz.project.service.UserInterfaceInfoService;
import com.cz.project.exception.BusinessException;
import com.cz.project.exception.ThrowUtils;
import com.cz.project.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 李建伟llx
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2023-08-03 19:18:57
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (add){
            ThrowUtils.throwIf(userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0,
                    ErrorCode.PARAMS_ERROR,"接口或用户不存在");
        }

        ThrowUtils.throwIf(userInterfaceInfo.getLeftNum() < 0,ErrorCode.PARAMS_ERROR,"接口调用次数不足");
    }

    @Override
    public Boolean invokeCount(Long interfaceInfoId, Long userId) {
        if (interfaceInfoId < 0 || userId < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或用户不存在");
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId",interfaceInfoId);
        updateWrapper.eq("userId",userId);
        updateWrapper.setSql("totalNum = totalNum + 1,leftNum = leftNum - 1");
        return this.update(updateWrapper);
    }

}




