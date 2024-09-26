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
@TableName("t_prod_attribute_value")
public class ProdAttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性值id
     */
    @TableId("attribute_value_id")
    private Integer attributeValueId;

    /**
     * 属性key id
     */
    private Integer attributeKeyId;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改时间
     */
    private Integer modifyTime;
}
