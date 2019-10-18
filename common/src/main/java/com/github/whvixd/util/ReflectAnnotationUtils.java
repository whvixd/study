package com.github.whvixd.util;

import com.github.whvixd.util.bean.ValidateDocument;
import com.github.whvixd.util.bean.ValidateDocumentModel;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by wangzhx on 2019/10/17.
 */
@UtilityClass
public class ReflectAnnotationUtils {
    List<ValidateDocumentModel> getAllAnnotations(final String packageNamePrefix, final Class<ValidateDocument> annotation) {
        ConfigurationBuilder configBuilder =
                new ConfigurationBuilder()
                        .filterInputsBy(new FilterBuilder().includePackage(packageNamePrefix))
                        .setUrls(ClasspathHelper.forPackage(packageNamePrefix))
                        .setScanners(
                                new SubTypesScanner(),
                                new TypeAnnotationsScanner(),
                                new MethodParameterScanner(),
                                new FieldAnnotationsScanner(),
                                new MethodAnnotationsScanner());
        Reflections reflections = new Reflections(configBuilder);
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(annotation);
        Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(annotation);
        Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith(annotation);

        List<ValidateDocumentModel> annotations = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(typesAnnotatedWith)) {
            typesAnnotatedWith.forEach(type ->
                    annotations.add(ValidateDocumentModel.create(type.getDeclaredAnnotation(annotation))));
        }
        if (CollectionUtils.isNotEmpty(methodsAnnotatedWith)) {
            methodsAnnotatedWith.forEach(method ->
                    annotations.add(ValidateDocumentModel.create(method.getDeclaredAnnotation(annotation))));
        }
        if (CollectionUtils.isNotEmpty(fieldsAnnotatedWith)) {
            fieldsAnnotatedWith.forEach(field ->
                    annotations.add(ValidateDocumentModel.create(field.getDeclaredAnnotation(annotation))));
        }
        return annotations;
    }
}
