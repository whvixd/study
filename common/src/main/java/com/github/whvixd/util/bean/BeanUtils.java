package com.github.whvixd.util.bean;


import com.google.common.collect.Maps;
import net.sf.cglib.core.Converter;

import java.util.Map;
import java.util.Objects;

public abstract class BeanUtils {

    private static final Map<ClassPair, BeanCopier> copiers = Maps.newConcurrentMap();

    public static void copy(Object src, Object dst) {

        Class srcClass = src.getClass();
        Class dstClass = dst.getClass();
        ClassPair classPair = new ClassPair(srcClass, dstClass);

        BeanCopier copier = copiers.get(classPair);

        if (copier == null) {
            copier = BeanCopierFactory.create(srcClass, dstClass, false);
            copiers.putIfAbsent(classPair, copier);
        }
        copier.copy(src, dst, null);
    }

    public static void copy(Object src, Object dst, Converter converter) {

        Class srcClass = src.getClass();
        Class dstClass = dst.getClass();
        ClassPair classPair = new ClassPair(srcClass, dstClass);

        BeanCopier copier = copiers.get(classPair);

        if (copier == null) {
            copier = BeanCopierFactory.create(srcClass, dstClass, true);
            copiers.putIfAbsent(classPair, copier);
        }
        copier.copy(src, dst, converter);
    }

    @FunctionalInterface
    public interface PostProcessor<Src, Dst> {
        void doPostProcessor(Src src, Dst dst);
    }

    public static <Src, Dst> Dst transfer(Src src, Class<Dst> clazz, PostProcessor<Src, Dst> processor) {
        if (src == null) {
            return null;
        }
        try {
            Dst dst = clazz.getConstructor().newInstance();
            BeanUtils.copy(src, dst);

            if (processor != null) {
                processor.doPostProcessor(src, dst);
            }
            return dst;
        } catch (Exception e) {
            throw new RuntimeException("Bean transfer error", e);
        }
    }

    public static <Src, Dst> Dst transfer(Src src, Class<Dst> clazz) {
        return transfer(src, clazz, null);
    }

    private static class ClassPair {
        private Class<?> src;
        private Class<?> dst;

        public ClassPair(Class<?> src, Class<?> dst) {
            this.src = src;
            this.dst = dst;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClassPair classPair = (ClassPair) o;
            return Objects.equals(src, classPair.src) &&
                    Objects.equals(dst, classPair.dst);
        }

        @Override
        public int hashCode() {
            return Objects.hash(src, dst);
        }
    }


}
