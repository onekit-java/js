package cn.onekit.js;

import java.util.HashMap;
import java.util.Random;
import cn.onekit.js.core.Iterator;
import cn.onekit.js.core.Onekit_JS;

public class JsObject extends HashMap<String, JsObject_> implements JsObject_ {
    ///////////////////////////////////////
    int _hashCode =  new Random().nextInt();
    @Override
    public int hashCode() {
        return _hashCode;
    }

    ////////////////////////////


    public static JsObject assign(JsObject target, JsObject... source) {
        for(JsObject dict : source) {
            for (Entry<String, JsObject_> entry : dict.entrySet()) {
                target.put(entry.getKey(),  entry.getValue());
            }
        }
        return target;
    }
    public static JsObject create(JsObject target, JsObject propertiesObject) {
        JsObject result = new JsObject();
        for (Entry<String, JsObject_> entry : target.entrySet()) {
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
                Entry<String, JsObject_> entry= (Entry) value;
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
            JsObject_ item = array.get(i);
            if(item==null){
                continue;
            }
            result.add(new JsString(String.valueOf(i)));
        }
        return result;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        String[] keys = this.keySet().toArray(new String[]{});
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (i > 0) {
                result.append(",");
            }
            result.append(String.format("\"%s\":%s", key, Onekit_JS.toString(this.get(key))));
        }
        result.append("}");
        return result.toString();
    }
    public JsString toLocaleString(JsObject_ locales, JsObject_ options){
        return new JsString("");
    }
    @Override
    public JsObject_ get(String key){
       return super.get(key);
    }
    @Override
    public JsObject_ get(JsObject_ key) {
        return get(((JsString)key).THIS);
    }

    @Override
    public void set(String key, JsObject_ value) {
        put(key, value);
    }

    @Override
    public void set(JsObject_ key, JsObject_ value) {
        put(key.toString(), value);
    }

    @Override
    public JsString ToString() {
        return null;
    }

    @Override
    public String toLocaleString(JsString locales, JsObject_ options) {
        return null;
    }

    @Override
    public JsObject_ invoke(JsObject_... params) {
        return null;
    }

/*
    int _hashCode =  new Random().nextInt();
    @Override
    public int hashCode() {
        return _hashCode;
    }
    ////////////////////////////


    public static  Dict assign(Dict target, Dict... source) {
        for(Dict dict : source) {
            for (Entry<String, JsObject> entry : dict.entrySet()) {
                target.put(entry.getKey(), (T1) entry.getValue());
            }
        }
        return target;
    }
    public static  Dict create(Dict target, Dict propertiesObject) {
        Dict result = new Dict();
        for (Entry<String, JsObject> entry : target.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    public static  Dict create(Dict target) {
        return create(target,null);
    }

    public static void defineProperties(Dict obj, Dict props) {
    }
    public static Iterator entries(Dict obj) {
        return new Iterator(obj.entrySet().iterator()){

            @Override
            public Array getValue(JsObject value) {
                Entry entry= (Entry) value;
                return new Array(){{add(entry.getKey());add(entry.getValue());}};
            }
        };
    }

    public static  Array keys(Dict dict) {
        Array result = new Array();
        for (JsObject key: dict.keySet() ) {
            result.add((String)key);
        }
        return result;
    }
    public static  Array keys(Array array) {
        Array result = new Array();
        for (int i=0;i<array.size();i++) {
            JsObject item = array.get(i);
            if(item==null){
                continue;
            }
            result.add(String.valueOf(i));
        }
        return result;
    }

    public JsObject get(String key) {
        return super.get(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        String[] keys = this.keySet().toArray(new String[]{});
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (i > 0) {
                result.append(",");
            }
            result.append(String.format("\"%s\":%s", key, OnekitJS.toString(this.get(key))));
        }
        result.append("}");
        return result.toString();
    }
    public String toLocaleString(String locales, Dict options){
        return "";
    }*/
}