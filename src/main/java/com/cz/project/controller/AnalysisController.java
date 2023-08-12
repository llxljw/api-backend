package com.cz.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cz.czapicommon.model.entity.InterfaceInfo;
import com.cz.czapicommon.model.entity.UserInterfaceInfo;
import com.cz.project.common.*;
import com.cz.project.exception.BusinessException;
import com.cz.project.mapper.InterfaceInfoMapper;
import com.cz.project.mapper.UserInterfaceInfoMapper;
import com.cz.project.model.dto.interfaceInfo.InterfaceInfoInvokeRequest;
import com.cz.project.model.vo.InterfaceInfoVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 接口调用情况分析接口
 *
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    private final static Gson GSON = new Gson();

    // region 增删改查

    /**
     * 接口调用分析
     *
     * @return
     */
    @GetMapping("/top/interface/invoke")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<UserInterfaceInfo> userInterfaceInfos = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap  = userInterfaceInfos
                .stream().
                collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> list = interfaceInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        List<InterfaceInfoVO> interfaceInfoVOList  = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceVo = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceVo);
            Integer totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceVo.setTotalNum(totalNum);
            return interfaceVo;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList );
    }
}
