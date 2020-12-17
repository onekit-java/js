package cn.onekit.js;

public class Undefined implements JsAny {

    @Override
    public JsString ToString() {
        return new JsString("undefined");
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
