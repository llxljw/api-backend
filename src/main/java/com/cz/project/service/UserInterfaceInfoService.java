package com.cz.project.service;

import com.cz.czapicommon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 李建伟llx
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-08-03 19:18:57
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 校验
     *
     * @param userInterfaceInfo
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    Boolean invokeCount(Long interfaceInfoId,Long userId);
}
