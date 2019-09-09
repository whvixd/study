package com.github.whvixd.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wangzhx on 2019/8/23.
 */
@Data
public class Bean {
    private String id;
    @NotNull
    private Type type;
}
