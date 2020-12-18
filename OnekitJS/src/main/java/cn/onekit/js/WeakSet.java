package cn.onekit.js;

public class WeakSet implements JsAny {
    ////////////
    public JsObject add(JsAny value){
        return null;
    }
    public boolean delete(JsAny value){
        return true;
    }
    public boolean has(JsAny value){
        return true;
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
    public JsAny ToString() {
        return new JsString("WeakSet");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
