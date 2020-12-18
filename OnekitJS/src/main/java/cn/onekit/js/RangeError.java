package cn.onekit.js;

import cn.onekit.JsAny;

public class RangeError extends Error {
    public RangeError(JsAny message) {
        super(message);
    }
    public RangeError() {
        super();
    }
}