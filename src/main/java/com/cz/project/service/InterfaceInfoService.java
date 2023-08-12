package com.cz.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.czapicommon.model.entity.InterfaceInfo;


/**
* @author 李建伟llx
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-06-25 21:53:42
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);



}

