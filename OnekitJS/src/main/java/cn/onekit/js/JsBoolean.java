package cn.onekit.js;

public class JsBoolean implements JsObject_ {
    public boolean THIS;
    public JsBoolean(boolean value) {
       THIS = value;
    }
    public JsBoolean(JsObject_ value) {
        if (value == null) {
            THIS= false;
        }
        if (value instanceof JsString) {
            String s = value.toString();
            THIS= s.length() > 0;
        } else if (value instanceof JsBoolean) {
            THIS= ((JsBoolean) value).THIS;
        } else if (value instanceof JsNumber) {
            THIS= ((JsNumber)value).THIS.doubleValue() != 0;
        } else {
            THIS= true;
        }
    }
    public static String toString(Boolean THIS) {
        return THIS.toString();
    }
    public static JsBoolean valueof(JsObject_ value) {
        return new JsBoolean(value);
    }
    public JsString ToString() {
        return new JsString(String.valueOf(THIS));
    }

    @Override
    public String toLocaleString(JsString locales, JsObject_ options) {
        return null;
    }

    @Override
    public JsObject_ invoke(JsObject_... params) {
        return null;
    }

    @Override
    public JsObject_ get(String key) {
        return null;
    }

    @Override
    public JsObject_ get(JsObject_ key) {
        return null;
    }

    @Override
    public void set(String key, JsObject_ value) {

    }

    @Override
    public void set(JsObject_ key, JsObject_ value) {

    }
}