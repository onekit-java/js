package cn.onekit.js;

import androidx.annotation.Nullable;

import cn.onekit.JsAny;

public class Undefined implements JsAny {

    @Override
    public JsAny get(JsAny key) {
        return null;
    }

    @Override
    public JsAny set(JsAny key, JsAny value) {
        return null;
    }

    @Override
    public JsAny get(String key) {
        return null;
    }

    @Override
    public JsAny set(String key, JsAny value) {
        return null;
    }

    @Override
    public JsAny ToString() {
        return new JsString("undefined");
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Undefined;
    }
}
