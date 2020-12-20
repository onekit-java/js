package cn.onekit.js;

public interface JsAny {
    JsAny get(JsAny key);
    JsAny get(String key);
    JsAny set(JsAny key,JsAny value);
    JsAny set(String key,JsAny value);

    JsAny invoke(JsAny... arguments);
    JsString ToString();
}
