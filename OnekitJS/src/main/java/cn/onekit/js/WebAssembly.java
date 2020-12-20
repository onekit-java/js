package cn.onekit.js;

import cn.onekit.js.JsAny;

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
        return new JsString("WebAssembly");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
