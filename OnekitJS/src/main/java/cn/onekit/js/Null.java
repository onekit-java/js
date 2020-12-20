package cn.onekit.js;

import cn.onekit.js.JsAny;

public class Null implements JsAny {

    @Override
    public String toString() {

        return "null";
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
        return new JsString("Null");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
