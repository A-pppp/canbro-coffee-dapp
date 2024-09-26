package com.canbro.coffee.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author A-pppp
 * @since 2024-09-24
 */
@Getter
@Setter
@TableName("t_mch")
public class Mch implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户id
     */
    @TableId("mch_id")
    private String mchId;

    /**
     * 商户名称
     */
    private String mchName;

    /**
     * 商户省份
     */
    private String mchProvince;

    /**
     * 商户城市
     */
    private String mchCity;

    /**
     * 商户地址
     */
    private String mchAddress;

    /**
     * 商户状态
     */
    private Integer mchState;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改者
     */
    private String modifyBy;

    /**
     * 修改时间
     */
    private Long modifyTime;

    /**
     * 备注
     */
    private String remark;
}
