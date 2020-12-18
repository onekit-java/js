package cn.onekit.js;

import java.util.HashMap;

import cn.onekit.js.core.Iterator;

public class Map implements JsAny {


    private java.util.Map<JsAny, JsAny> _THIS = new HashMap<>();

    public int getSize() {
        return _THIS.size();
    }

    /////////////////////////////
    public void clear() {
        _THIS.clear();
    }

    public boolean delete(JsAny key) {
        for (JsObject.Entry<JsAny, JsAny> entry :_THIS.entrySet()){
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
                JsObject.Entry<JsAny, JsAny> entry= (JsObject.Entry) value;
                return new JsArray(){{add(entry.getKey());add(entry.getValue());}};
            }
        };
    }

    public   void forEach(function callback, JsAny thisArg) {
        callback.thisArg = thisArg;
        for (JsObject.Entry<JsAny, JsAny> entry : _THIS.entrySet()) {
            callback.invoke(entry.getValue(), entry.getKey(), this);
        }
    }
    public void forEach(function  callback ) {
        forEach(callback, null);
    }



    public JsAny get(JsAny key) {
        return _THIS.get(key);
    }





    public JsBoolean has(JsAny key) {
         for (JsObject.Entry entry :_THIS.entrySet()){
             if(entry.getKey().hashCode()==key.hashCode()){
                 return new JsBoolean(true);
             }
         }
         return new JsBoolean(false);
    }

    public Iterator keys() {
        return new Iterator<JsAny>(_THIS.entrySet().iterator()) {

            @Override
            public JsAny getValue(Object value) {
                JsObject.Entry<JsAny, JsAny> entry = (JsObject.Entry<JsAny, JsAny>) value;
                return entry.getKey();
            }
        };
    }

    public JsAny set(JsAny key, JsAny value) {
        _THIS.put(key ,value);return this;
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
        return new JsString("Map");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }

    public Iterator values() {
        return new Iterator(_THIS.entrySet().iterator()) {


            @Override
            public JsAny getValue(Object value) {
                JsObject.Entry<JsAny, JsAny> entry = (JsObject.Entry<JsAny, JsAny> ) value;
                return entry.getValue();
            }
        };
    }

}
