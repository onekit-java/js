package cn.onekit.js;

public class RangeError extends Error {
    public RangeError(JsAny message) {
        super(message);
    }
    public RangeError() {
        super();
    }
}