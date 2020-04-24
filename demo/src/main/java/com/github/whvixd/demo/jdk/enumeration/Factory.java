package com.github.whvixd.demo.jdk.enumeration;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangzhx on 2018/6/28.
 */
public interface Factory {
    enum Car{
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

    interface ICar{
        String printCatName(Car car);
    }

    //实现ICar接口
    enum ICarImpl implements ICar{
        LAMBORGHINI{
            @Override
            public String printCatName(Car car) {
                //根据入参不同去写对应的逻辑
                if("兰博基尼".equals(car.getCarBrand())){
                    return "王志祥的兰博基尼";
                }
                return "";
            }
        },KOENIGSEGG {
            @Override
            public String printCatName(Car car) {
                //根据入参不同去写对应的逻辑
                if(1E9 == car.getPrice()){
                    return car.getCarBrand()+"价格："+car.getPrice();
                }
                return null;
            }
        }
    }

}
