package com.canbro.coffee.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("t_wallet")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包id
     */
    @TableId(value = "wallet_id", type = IdType.AUTO)
    private Integer walletId;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 钱包币种
     */
    private String walletCurrency;

    /**
     * 钱包地址
     */
    private String walletAddress;

    /**
     * 钱包地址备注
     */
    private String walletMemo;

    /**
     * 钱包网络
     */
    private String walletNetwork;

    /**
     * 钱包状态
     */
    private Integer walletState;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改者
     */
    private String modifyBy;

    /**
     * 修改时间
     */
    private Integer modifyTime;

    /**
     * 备注
     */
    private String remark;
}
