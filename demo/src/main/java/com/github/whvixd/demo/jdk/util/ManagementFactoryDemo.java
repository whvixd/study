package com.github.whvixd.demo.jdk.util;

import javax.management.*;
import javax.management.openmbean.CompositeDataSupport;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 打印jvm参数
 */
public class ManagementFactoryDemo {
    private static MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
    private static String[] memoryKeys = {"committed", "init", "max", "used"};

    public static void main(String[] args) throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {
        Set<ObjectInstance> memoryInstanceSet = mbeanServer.queryMBeans(new ObjectName("java.lang:type=MemoryPool,*"),null);
        Map<String, Object> resMap = new HashMap<>();

        for (ObjectInstance memory : memoryInstanceSet) {
            CompositeDataSupport cds1 = (CompositeDataSupport) mbeanServer.getAttribute(memory.getObjectName(),"Usage");
            for (String key : memoryKeys) {
                resMap.put(memory.getObjectName() + "." + key, cds1.get(key));
            }
        }
        resMap.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }
}
