package cn.onekit.js;

import cn.onekit.js.core.JsAny;

public class WebAssembly implements JsAny {
    public static JsObject compile(JsArray bufferSource){
        return null;
    }
    public static JsObject compileStreaming(JsObject source){
        return null;
    }
    public static JsObject instantiate(JsArray bufferSource, JsObject importObject ){
        return null;
    }
    public static JsObject instantiateStreaming(JsObject Parameters, JsObject importObject){
        return null;
    }
    public static boolean validate(JsObject bufferSource){
        return true;
    }

    @Override
    public JsAny get(String key) {
        return null;
    }

    @Override
    public void set(String key, JsAny value) {

    }

    @Override
    public void set(JsAny key, JsAny value) {

    }

    @Override
    public JsString ToString() {
        return new JsString("WebAssembly");
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
