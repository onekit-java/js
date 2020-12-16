package cn.onekit.js;

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
    public JsAny get(String key) {
        return null;
    }

    @Override
    public JsAny get(JsAny key) {
        return null;
    }

    @Override
    public void set(String key, JsAny value) {

    }

    @Override
    public void set(JsAny key, JsAny value) {

    }

    @Override
    public JsString ToString() {
        return null;
    }

    @Override
    public String toLocaleString(JsString locales, JsAny options) {
        return null;
    }

    @Override
    public JsAny invoke(JsAny... params) {
        return null;
    }
}
