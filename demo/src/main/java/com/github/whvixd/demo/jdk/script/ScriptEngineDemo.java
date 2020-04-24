package com.github.whvixd.demo.jdk.script;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;

import static javax.script.ScriptContext.ENGINE_SCOPE;

/**
 * Created by wangzhx on 2018/11/13.
 */
public class ScriptEngineDemo {

    public static void main(String[] args) throws ScriptException {
        String expression = "(AccountObj$name.contains(\"demo\") && true) && (AccountObj$number >=1 || false)";

        String expression1 = "AccountObj$name.contains(\"a\")";

        ScriptEngine engine = new NashornScriptEngineFactory().getScriptEngine();
        Bindings bindings = new SimpleScriptContext().getBindings(ENGINE_SCOPE);

        bindings.put("AccountObj$name", "[a,b,c]");

        bindings.put("AccountObj$number", 1);

        System.out.println(engine.eval(expression, bindings));
        System.out.println(engine.eval(expression1, bindings));



    }
}
