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
        return new JsString("Error");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
