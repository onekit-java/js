package cn.onekit.js;

public class Error extends java.lang.Error implements JsAny {

    public Error(JsAny message) {
        super(message.toString());
    }
    public Error() {
        super();
    }

    public String toSource(){
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
    public JsString ToString() {
        return new JsString("Error");
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
