package cn.onekit.js;

public class Error extends java.lang.Error implements JsObject_ {

    public Error(JsObject_ message) {
        super(message.toString());
    }
    public Error() {
        super();
    }

    public String toSource(){
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
