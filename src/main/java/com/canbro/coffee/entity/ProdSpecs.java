package com.canbro.coffee.entity;

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
@TableName("t_prod_specs")
public class ProdSpecs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品规格id
     */
    @TableId("prod_specs_id")
    private Integer prodSpecsId;

    /**
     * 商品id
     */
    private Integer prodId;

    /**
     * 商户id
     */
    private Integer mchId;

    /**
     * 商品库存
     */
    private Integer prodStock;

    /**
     * 商品价格币种
     */
    private String prodCurrency;

    /**
     * 商品价格
     */
    private Integer prodPrice;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改时间
     */
    private Integer modifyTime;
}
