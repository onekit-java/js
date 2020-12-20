package cn.onekit.js;

import cn.onekit.js.JsAny;

public class WeakMap implements JsAny {
///////////////////////
    public boolean delete(String key){
        return true;
    }
    public boolean has(String key){
        return true;
    }
    public JsObject set(JsAny key, JsObject value){
        return null;
    }



    @Override
    public JsAny get(JsAny key) {
        return null;
    }





    @Override
    public JsAny set(JsAny key, JsAny value) {
        return this;
    }

    @Override
    public JsAny get(String key) {
        return null;
    }

    @Override
    public JsAny set(String key, JsAny value) {
        return null;
    }

    @Override
    public JsString ToString() {
        return new JsString("WeakMap");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
