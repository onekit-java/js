package cn.onekit.js;

import cn.onekit.js.JsAny;

public class InteralError extends Error {
    public InteralError(JsAny message){
        super(message);
    }
    public InteralError(){
        super();
    }
}
