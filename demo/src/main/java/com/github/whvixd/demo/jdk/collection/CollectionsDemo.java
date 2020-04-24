package com.github.whvixd.demo.jdk.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by wangzhx on 2018/11/13.
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        // create two lists
        List<String> srclst = new ArrayList<String>(5);
        List<String> destlst = new ArrayList<String>(10);

        // populate two lists
        srclst.add("Java");
        srclst.add("is");
        srclst.add("best");

        destlst.add("C++");
        destlst.add("is not");
        destlst.add("older");

        // check elements in both collections
        boolean iscommon = Collections.disjoint(srclst, destlst);



        System.out.println("No commom elements: "+iscommon);


        List left  = Lists.newArrayList("a","b","c");
        List right  = Lists.newArrayList("1");
//        System.out.println(left.removeAll(right));
//        System.out.println(left);
        System.out.println("disjoint"+!Collections.disjoint(left, right));

        Set set1 = Sets.newHashSet("1");
        Set set2 = Sets.newHashSet("1");
        set1.addAll(set2);
        System.out.println(set1);

    }
}
