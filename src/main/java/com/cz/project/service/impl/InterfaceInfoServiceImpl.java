package com.cz.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cz.czapicommon.model.entity.InterfaceInfo;
import com.cz.czapicommon.model.entity.User;
import com.cz.czapicommon.model.entity.UserInterfaceInfo;
import com.cz.project.common.ErrorCode;
import com.cz.project.mapper.InterfaceInfoMapper;
import com.cz.project.exception.BusinessException;
import com.cz.project.exception.ThrowUtils;
import com.cz.project.service.InterfaceInfoService;
import com.cz.project.service.UserInterfaceInfoService;
import com.cz.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author 李建伟llx
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-06-25 21:53:42
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, description), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称过长");
        }
        if (StringUtils.isNotBlank(description) && description.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述内容过长");
        }
    }


}




