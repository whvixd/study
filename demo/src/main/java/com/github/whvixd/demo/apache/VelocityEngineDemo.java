package com.github.whvixd.demo.apache;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by wangzhx on 2019/10/16.
 */
public class VelocityEngineDemo {
    private VelocityEngine velocityEngine = new VelocityEngine();
    public VelocityEngineDemo() {
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

     void generateDM() {

         try {
            FileWriter writer = new FileWriter("test.md");
            Template template = velocityEngine.getTemplate("ValidateDocument.vm", "utf-8");
            VelocityContext ctx = new VelocityContext();
            ctx.put("module", "after");
            ctx.put("message", "error");
            template.merge(ctx, writer);
            writer.flush();
        } catch (IOException e) {
        }

    }

    public static void main(String[] args) {
        VelocityEngineDemo demo = new VelocityEngineDemo();
        demo.generateDM();
    }
}
