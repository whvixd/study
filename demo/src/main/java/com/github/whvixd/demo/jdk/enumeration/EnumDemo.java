package com.github.whvixd.demo.jdk.enumeration;

public interface EnumDemo {
    enum Light {
        RED("红灯"), YELLOW("黄灯"), BLUE("蓝灯");

        private String light;

        Light() {
        }

        Light(String light) {
            this.light = light;
        }

        public String getLight() {
            return light;
        }

        public void setLight(String light) {
            this.light = light;
        }

        public static void main(String[] args) {
            Light[] lights = Light.values();
            for (Light l : lights) {
                System.out.println(l.light);
            }

            System.out.println(Light.BLUE.light instanceof String);

            Light light = Light.valueOf(Light.BLUE.name());//就相当于new Light(RED); private String RED = "red";

            System.out.println(light.getLight());
        }
    }

}
