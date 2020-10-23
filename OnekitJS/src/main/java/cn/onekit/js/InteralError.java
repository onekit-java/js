package cn.onekit.js;

public class InteralError extends Error {
    public InteralError(JsObject_ message){
        super(message);
    }
    public InteralError(){
        super();
    }
}
