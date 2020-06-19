package com.github.whvixd.util.datastructure;

import java.util.Arrays;

/**
 * 数组集合
 * Created by wangzhixiang on 2020/6/19.
 */
public class ArrayList<E> {

    private Object[] elements;

    private int size;

    private static final int DEFAULT_CAPACITY=10;

    private static final Object[] DEFAULT_EMPTY_ELEMENTS={};

    public ArrayList(){
        elements=DEFAULT_EMPTY_ELEMENTS;
    }

    public ArrayList(int capacity){
        if(capacity>0){
            elements=new Object[capacity];
        }else if(capacity==0){
            elements=DEFAULT_EMPTY_ELEMENTS;
        }else {
            throw new IllegalArgumentException("capacity is illegal!");
        }
    }

    public int size(){
        return size;
    }

    // 查

    /**
     * 根据下标定位
     * @param index 下标
     * @return 元素
     */
    @SuppressWarnings("unchecked")
    public E get(int index){
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * 查询下标
     * @param o 元素
     * @return 下标
     */
    public int indexOf(Object o){
        if(o==null){
            for(int i=0;i<size;i++){
                if(elements[i]==null){
                    return i;
                }
            }
        }else {
            for(int i=0;i<size;i++){
                if(o.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    // 插

    /**
     * 添加新元素到数组尾部
     * @param o 新加入的元素
     * @return true
     */
    public boolean add(Object o){
        ensureCapacityInternal(size+1);
        elements[size++]=o;
        return true;
    }


    /**
     *
     * @param index 添加到哪个下标
     * @param o 新元素
     * @return true
     */
    public boolean add(int index,Object o){
        checkIndex(index);
        ensureCapacityInternal(size+1);
        // 从原来数组的index下标复制到elements中，从index+1开始，到size-index结束
        System.arraycopy(elements,index,elements,index+1,size-index);
        elements[index]=o;
        size++;
        return true;
    }


    // 删

    // 改

    private void checkIndex(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index is illegal!");
        }
    }


    private void ensureCapacityInternal(int capacity) {
        // 若是初始化的数组，则使用默认的大小
        if(DEFAULT_EMPTY_ELEMENTS==elements){
            capacity=Math.max(capacity,DEFAULT_CAPACITY);
        }
        // 是否超过真实的长度
        if(capacity>elements.length){
            int oldCapacity=elements.length;
            // 扩容1.5倍
            int newCapacity=oldCapacity+(oldCapacity>>1);
            // 初始化时数组长度是0，则需要与默认长度对比大小
            if(oldCapacity-capacity<0){
                newCapacity=capacity;
            }
            // 数组复制是native方法
            elements=Arrays.copyOf(elements,newCapacity);
        }
    }
}
