package cn.onekit.js;

import cn.onekit.JsAny;

public class ReferenceError extends Error {
    public ReferenceError(JsAny message) {
        super(message);
    }
    public ReferenceError() {
        super();
    }
}
