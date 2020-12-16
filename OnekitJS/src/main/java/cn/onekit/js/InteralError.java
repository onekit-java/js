package cn.onekit.js;

public class InteralError extends Error {
    public InteralError(JsAny message){
        super(message);
    }
    public InteralError(){
        super();
    }
}
