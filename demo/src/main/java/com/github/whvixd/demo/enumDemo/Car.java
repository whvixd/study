package com.github.whvixd.demo.enumDemo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangzhx on 2018/6/28.
 */
public enum Car {
    LAMBORGHINI("兰博基尼", 3E8), KOENIGSEGG("科尼赛克", 1E9);

    @Setter
    @Getter
    private String carBrand;

    @Setter
    @Getter
    private double price;

    Car(String carBrand, double price) {
        this.carBrand = carBrand;
        this.price = price;
    }

}


class ICar{
    public static final ICar LAMBORGHINI = new ICar("兰博基尼",3E8);
    public static final ICar KOENIGSEGG = new ICar("科尼赛克",1E9);

    @Setter
    @Getter
    private String carBrand;

    @Setter
    @Getter
    private double price;

    ICar(String carBrand, double price) {
        this.carBrand = carBrand;
        this.price = price;
    }

}
/*

$ javap Car.class
Compiled from "Car.java"
public final class Car extends java.lang.Enum<Car> {
  public static final Car LAMBORGHINI;
  public static final Car KOENIGSEGG;
  public static Car[] values();
  public static Car valueOf(java.lang.String);
  static {};
}

*/