package com.github.whvixd.junit4.category;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.lang.Character;

/**
 * Created by wangzhx on 2019/10/21.
 */
@RunWith(Categories.class)
/**
 * 执行哪些类型
 */
@Categories.IncludeCategory({Character.class, Animal.class})
/**
 * 哪些类型的测试
 */
@Suite.SuiteClasses({A.class, B.class, Dog.class})
public class CategoriesDemo {
}
