package cn.onekit.js;

public class WeakMap implements JsObject_ {
///////////////////////
    public boolean delete(String key){
        return true;
    }
    public boolean has(String key){
        return true;
    }
    public JsObject set(JsObject_ key, JsObject value){
        return null;
    }

    @Override
    public JsObject_ get(String key) {
        return null;
    }

    @Override
    public JsObject_ get(JsObject_ key) {
        return null;
    }

    @Override
    public void set(String key, JsObject_ value) {

    }

    @Override
    public void set(JsObject_ key, JsObject_ value) {

    }

    @Override
    public JsString ToString() {
        return null;
    }

    @Override
    public String toLocaleString(JsString locales, JsObject_ options) {
        return null;
    }

    @Override
    public JsObject_ invoke(JsObject_... params) {
        return null;
    }
}
