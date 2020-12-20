package cn.onekit.js.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import cn.onekit.js.JsAny;
import cn.onekit.js.JsString;
import cn.onekit.js.function;

@SuppressWarnings("unused")
public  class JsClass implements  JsAny {

    private Class clazz = getClass();
    @Override
    public JsAny get(JsAny key) {
        throw new Error();
    }

    @Override
    public JsAny get(String key) {
        try {
            Class clazz = getClass();
            for (Field field : clazz.getFields()) {
                if (field.getName().equals(key)) {
                    return (JsAny) field.get(this);
                }
            }
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(key)) {
                    return new function(this,method);
                }
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public JsAny set(JsAny key, JsAny value) {
        throw new Error();
    }

    @Override
    public JsAny set(String key, JsAny value) {
        try {
            Class clazz = getClass();
            for (Field field : clazz.getFields()) {
                if (field.getName().equals(key)) {
                  field.set(this,value);
                }
            }
            return this;
        }catch (Exception ex){
            ex.printStackTrace();
            return this;
        }
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        throw new Error();
    }

    @Override
    public JsString ToString() {
        return new JsString(clazz.getSimpleName());
    }
}
