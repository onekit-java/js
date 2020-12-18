package cn.onekit.js;

public class JsBoolean implements JsAny {
    public boolean THIS;
    public JsBoolean(boolean value) {
       THIS = value;
    }
    public JsBoolean(JsAny value) {
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
    public static JsBoolean valueof(JsAny value) {
        return new JsBoolean(value);
    }
    public JsAny ToString() {
        return new JsString(String.valueOf(THIS));
    }



    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }



    @Override
    public JsAny get(JsAny key) {
        return null;
    }





    @Override
    public JsAny set(JsAny key, JsAny value) {
        return this;
    }

    @Override
    public JsAny get(String key) {
        return null;
    }

    @Override
    public JsAny set(String key, JsAny value) {
        return null;
    }
}