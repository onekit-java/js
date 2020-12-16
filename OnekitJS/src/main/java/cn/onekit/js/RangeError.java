package cn.onekit.js;

import cn.onekit.js.core.JsAny;

public class RangeError extends Error {
    public RangeError(JsAny message) {
        super(message);
    }
    public RangeError() {
        super();
    }
}