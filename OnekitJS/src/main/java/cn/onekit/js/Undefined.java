package cn.onekit.js;

public class Undefined implements JsAny {

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
        return new JsString("undefined");
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
