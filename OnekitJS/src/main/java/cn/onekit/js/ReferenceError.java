package cn.onekit.js;

public class ReferenceError extends Error {
    public ReferenceError(JsAny message) {
        super(message);
    }
    public ReferenceError() {
        super();
    }
}
