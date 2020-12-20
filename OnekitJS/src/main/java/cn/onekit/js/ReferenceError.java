package cn.onekit.js;

import cn.onekit.js.JsAny;

public class ReferenceError extends Error {
    public ReferenceError(JsAny message) {
        super(message);
    }
    public ReferenceError() {
        super();
    }
}
