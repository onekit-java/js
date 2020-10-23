package cn.onekit.js;

import java.util.HashSet;
import java.util.Random;

import cn.onekit.js.core.Iterator;
import cn.onekit.js.core.Onekit_JS;
import cn.onekit.js.core.function;

public class Set implements Iterable<JsObject_> , JsObject_ {

    int _hashCode =  new Random().nextInt();
    @Override
    public int hashCode() {
        return _hashCode;
    }
    java.util.Set<JsObject_> _THIS;

    public Set(JsArray array) {
        _THIS = new HashSet(array);
    }

    public Set(String array) {
        this(Onekit_JS.string2Array(array));
    }

    public Set() {
        _THIS = new HashSet();
    }

    /////////////////////////////////
    public int getSize() {
        return _THIS.size();
    }

    public Set add(JsObject_ value) {
        _THIS.add(value);
        return this;
    }

    public void clear() {
        _THIS.clear();
    }

    public boolean delete(JsObject_ value) {
        return _THIS.remove(value);
    }
    public  void forEach(function callback, JsObject_ THIS) {
        callback.thisArg = THIS;
        for(JsObject_ item : _THIS){
            callback.invoke(item,item,this);
        }
    }
    public void forEach(function callback) {
        forEach(callback,null);
    }
    public boolean has(JsObject_ value) {
        return _THIS.contains(value);
    }


    @Override
    public String toString() {
        StringBuilder result=new StringBuilder();
        result.append("Set [");
        int i=0;
        for (JsObject_ item : _THIS) {
            if (i++ > 0) {
                result.append(",");
            }
            result.append(item);
        }
        result.append("]");
        return result.toString();
    }

    public Iterator values() {
        return  new Iterator(_THIS.iterator()) {
            @Override
            public JsObject_ getValue(Object value) {
                return (JsObject_) value;
            }

        };
    }

    @Override
    public java.util.Iterator iterator() {
        return _THIS.iterator();
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
}
