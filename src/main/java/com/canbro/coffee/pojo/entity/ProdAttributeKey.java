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
@TableName("t_prod_attribute_key")
public class ProdAttributeKey implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性key id
     */
    @TableId("attribute_key_id")
    private Integer attributeKeyId;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 属性key名
     */
    private String attributeName;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long modifyTime;
}
