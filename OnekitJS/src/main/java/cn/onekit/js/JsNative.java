package cn.onekit.js;


import cn.onekit.JsAny;

public class JsNative implements JsAny {

    public JsNative(Object obj){
        this.THIS=obj;
    }
    public Object THIS;

    @Override
    public JsAny get(JsAny key) {
        return null;
    }

    @Override
    public JsAny set(JsAny key, JsAny value) {
        return null;
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
    public JsAny ToString() {
        return new JsString(THIS.getClass().getSimpleName());
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
