package cn.onekit.js;

import java.util.HashSet;
import java.util.Random;

import cn.onekit.JsAny;
import cn.onekit.js.core.Iterator;
import cn.onekit.js.core.Onekit_JS;

public class Set implements Iterable<JsAny> , JsAny {

    int _hashCode =  new Random().nextInt();
    @Override
    public int hashCode() {
        return _hashCode;
    }
    java.util.Set<JsAny> _THIS;

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

    public Set add(JsAny value) {
        _THIS.add(value);
        return this;
    }

    public void clear() {
        _THIS.clear();
    }

    public boolean delete(JsAny value) {
        return _THIS.remove(value);
    }
    public  void forEach(function callback, JsAny THIS) {
        callback.thisArg = THIS;
        for(JsAny item : _THIS){
            callback.invoke(item,item,this);
        }
    }
    public void forEach(function callback) {
        forEach(callback,null);
    }
    public boolean has(JsAny value) {
        return _THIS.contains(value);
    }


    @Override
    public String toString() {
        StringBuilder result=new StringBuilder();
        result.append("Set [");
        int i=0;
        for (JsAny item : _THIS) {
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
            public JsAny getValue(Object value) {
                return (JsAny) value;
            }

        };
    }

    @Override
    public java.util.Iterator iterator() {
        return _THIS.iterator();
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

    @Override
    public JsAny ToString() {
        return new JsString("Set");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
