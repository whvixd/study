package com.github.whvixd.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wangzhx on 2019/8/23.
 */
@Data
public class Bean {
    @Length(max = 3,message = "最多3个字")
    @NotBlank(message = "id 不能为空")
    private String id;
    @NotNull
    private Type type;
}
