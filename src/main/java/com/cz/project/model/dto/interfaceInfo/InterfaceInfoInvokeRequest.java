package com.cz.project.model.dto.interfaceInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;


/**
 * 测试调用请求
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String userRequestParams;
    /**
     * 主机号
     */
    private String host;


    private static final long serialVersionUID = 1L;
}
