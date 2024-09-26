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
@TableName("t_blockchain")
public class Blockchain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区块链id
     */
    @TableId("blockchain_id")
    private String blockchainId;

    /**
     * 区块链编码
     */
    private String blockchainCode;

    /**
     * 区块链名称
     */
    private String blockchainName;

    /**
     * 区块链主网地址
     */
    private String blockchainMainNetwork;

    /**
     * 区块链网络类型
     */
    private String blockchainNetworkType;

    /**
     * 状态
     */
    private Integer blockchainState;

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
