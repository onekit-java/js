package cn.onekit.js;

import cn.onekit.js.core.JsAny;

public class InteralError extends Error {
    public InteralError(JsAny message){
        super(message);
    }
    public InteralError(){
        super();
    }
}
