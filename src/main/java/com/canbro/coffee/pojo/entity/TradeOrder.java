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
@TableName("t_trade_order")
public class TradeOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易单号
     */
    @TableId("trade_no")
    private String tradeNo;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 订单商品详情(json array str)
     */
    private String tradeProds;

    /**
     * 交易币种
     */
    private String tradeCurrency;

    /**
     * 交易金额
     */
    private Integer tradeAmount;

    /**
     * 支付币种
     */
    private String payCurrency;

    /**
     * 支付金额
     */
    private Integer payAmount;

    /**
     * 支付时交易对价格
     */
    private Integer payExchangeRate;

    /**
     * 支付gas费
     */
    private Integer payGas;

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
     * 订单超时时间
     */
    private Integer timeExpire;

    /**
     * 商户钱包币种
     */
    private String mchWalletCurrency;

    /**
     * 商户钱包地址
     */
    private String mchWalletAddress;

    /**
     * 商户钱包地址备注
     */
    private String mchWalletMemo;

    /**
     * 交易描述
     */
    private String tradeDesc;

    /**
     * 客户端系统
     */
    private String clientOs;

    /**
     * 客户端ip
     */
    private String clientIp;

    /**
     * 客户端app
     */
    private String clientApp;

    /**
     * 订单状态
     */
    private Integer tradeState;

    /**
     * 支付状态
     */
    private Integer payState;

    /**
     * 退款状态
     */
    private Integer refundState;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long modifyTime;

    /**
     * 支付时间
     */
    private Long payTime;

    /**
     * 下单时间
     */
    private Long placingTime;

    /**
     * 退款时间
     */
    private Long refundTime;

    /**
     * 关闭时间
     */
    private Long closeTime;

    /**
     * 完成时间
     */
    private Long finishTime;

    /**
     * 备注
     */
    private String remark;
}
