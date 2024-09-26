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
@TableName("t_prod_category")
public class ProdCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品类别id
     */
    @TableId("category_id")
    private Integer categoryId;

    /**
     * 商品类别名称
     */
    private String categoryName;

    /**
     * 商品类别父id
     */
    private Integer categoryParentId;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改时间
     */
    private Integer modifyTime;
}
