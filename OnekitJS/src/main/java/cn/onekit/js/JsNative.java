package cn.onekit.js;


public class JsNative implements JsAny{

    public JsNative(Object obj){
        this.THIS=obj;
    }
    public Object THIS;
    @Override
    public JsString ToString() {
        return new JsString(THIS.getClass().getSimpleName());
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
