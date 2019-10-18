package com.github.whvixd.util.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * Created by wangzhx on 2019/10/17.
 */
@Data
@AllArgsConstructor
public class ValidateDocumentModel {
    private String module;
    private String message;

    public static ValidateDocumentModel create(ValidateDocument validateDocument) {
        if (Objects.nonNull(validateDocument)) {
            return new ValidateDocumentModel(validateDocument.module(), validateDocument.message());
        }
        return null;
    }
}
