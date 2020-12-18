package cn.onekit.js;

import java.util.HashMap;
import java.util.Random;

import cn.onekit.JsAny;
import cn.onekit.js.core.Iterator;

public class JsObject extends HashMap<String, JsAny> implements JsAny {
    ///////////////////////////////////////
    int _hashCode =  new Random().nextInt();
    @Override
    public int hashCode() {
        return _hashCode;
    }

    ////////////////////////////


    public static JsObject assign(JsObject target, JsObject... source) {
        for(JsObject dict : source) {
            for (Entry<String, JsAny> entry : dict.entrySet()) {
                target.put(entry.getKey(),  entry.getValue());
            }
        }
        return target;
    }
    public static JsObject create(JsObject target, JsObject propertiesObject) {
        JsObject result = new JsObject();
        for (Entry<String, JsAny> entry : target.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    public static JsObject create(JsObject target) {
        return create(target,null);
    }

    public static void defineProperties(JsObject obj, JsObject props) {
    }
    public static Iterator entries(JsObject obj) {
        return new Iterator(obj.entrySet().iterator()){

            @Override
            public JsArray getValue(Object value) {
                Entry<String, JsAny> entry= (Entry) value;
                return new JsArray(){{add(new JsString(entry.getKey()));add(entry.getValue());}};
            }
        };
    }

    public static JsArray keys(JsObject dict) {
        JsArray result = new JsArray();
        for (String key: dict.keySet() ) {
            result.add(new JsString(key));
        }
        return result;
    }
    public static JsArray keys(JsArray array) {
        JsArray result = new JsArray();
        for (int i=0;i<array.size();i++) {
            JsAny item = array.get(i);
            if(item==null){
                continue;
            }
            result.add(new JsString(String.valueOf(i)));
        }
        return result;
    }

    public JsString toLocaleString(JsAny locales, JsAny options){
        return new JsString("");
    }

    @Override
    public JsAny get(JsAny key) {
        return get(((JsString)key).THIS);
    }



    @Override
    public JsAny set(JsAny key, JsAny value) {
        put(key.toString(), value);return this;
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
        return JSON.stringify(this);
    }



    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }

}