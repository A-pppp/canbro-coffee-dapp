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
@TableName("t_prod")
public class Prod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品编码
     */
    @TableId("prod_code")
    private String prodCode;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 商品小图
     */
    private String prodPic;

    /**
     * 商品轮播图地址
     */
    private String prodMediaUrls;

    /**
     * 商品分类id
     */
    private Integer categoryId;

    /**
     * 商品规格列表
     */
    private String attributeList;

    /**
     * 商品状态
     */
    private Integer prodState;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改时间
     */
    private Integer modifyTime;

    /**
     * 备注
     */
    private String remark;
}
