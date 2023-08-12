package com.cz.project.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: ljw
 * @Date: 2023/08/12/0:30
 * @Description:
 */
@Data
public class SelfInterfaceDateVo implements Serializable {
    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 已调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    private static final long serialVersionUID = 1L;
}
