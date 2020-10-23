package cn.onekit.js;

import java.util.HashMap;

import cn.onekit.js.core.Iterator;
import cn.onekit.js.core.function;

public class Map implements JsObject_ {


    private java.util.Map<JsObject_, JsObject_> _THIS = new HashMap<>();

    public int getSize() {
        return _THIS.size();
    }

    /////////////////////////////
    public void clear() {
        _THIS.clear();
    }

    public boolean delete(JsObject_ key) {
        for (JsObject.Entry<JsObject_, JsObject_> entry :_THIS.entrySet()){
            if(entry.getKey().hashCode()==key.hashCode()){
                _THIS.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }

    public Iterator entries() {
        return new Iterator(_THIS.entrySet().iterator()){

            @Override
            public Object getValue(Object value) {
                JsObject.Entry<JsObject_, JsObject_> entry= (JsObject.Entry) value;
                return new JsArray(){{add(entry.getKey());add(entry.getValue());}};
            }
        };
    }

    public   void forEach(function callback, JsObject_ thisArg) {
        callback.thisArg = thisArg;
        for (JsObject.Entry<JsObject_, JsObject_> entry : _THIS.entrySet()) {
            callback.invoke(entry.getValue(), entry.getKey(), this);
        }
    }
    public void forEach(function  callback ) {
        forEach(callback, null);
    }

    @Override
    public JsObject_ get(String key) {
        return null;
    }

    public JsObject_ get(JsObject_ key) {
        return _THIS.get(key);
    }

    @Override
    public void set(String key, JsObject_ value) {

    }

    public JsBoolean has(JsObject_ key) {
         for (JsObject.Entry entry :_THIS.entrySet()){
             if(entry.getKey().hashCode()==key.hashCode()){
                 return new JsBoolean(true);
             }
         }
         return new JsBoolean(false);
    }

    public Iterator keys() {
        return new Iterator<JsObject_>(_THIS.entrySet().iterator()) {

            @Override
            public JsObject_ getValue(Object value) {
                JsObject.Entry<JsObject_, JsObject_> entry = (JsObject.Entry<JsObject_, JsObject_>) value;
                return entry.getKey();
            }
        };
    }

    public void set(JsObject_ key, JsObject_ value) {
        _THIS.put(key ,value);
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

    public Iterator values() {
        return new Iterator(_THIS.entrySet().iterator()) {


            @Override
            public JsObject_ getValue(Object value) {
                JsObject.Entry<JsObject_, JsObject_> entry = (JsObject.Entry<JsObject_, JsObject_> ) value;
                return entry.getValue();
            }
        };
    }

}
