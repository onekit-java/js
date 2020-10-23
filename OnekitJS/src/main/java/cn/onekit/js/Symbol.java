package cn.onekit.js;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Symbol implements JsObject_ {

    static List _Symbols = new ArrayList();
    JsObject_ _description;
    public Symbol(JsObject_ description){
        _description=description;
        String result = String.format("__%s_%d_%d__", description, (int) java.lang.Math.floor(java.lang.Math.random() * 1e9), _Symbols.size() + 1);
        _Symbols.add(result);
    }
    public Symbol(){
        this(new JsNumber(new Random().nextInt()));
    }
    public static boolean asyncIterator;
    public static boolean hasInstance;
    public static JsObject isConcatSpreadable;
    public static JsArray iterator ;
    public static String match;
    public static JsObject matchAll;
    public final String description = null;
    public static String  replace;
    public static JsObject search;
    public static boolean species;
    public static String split;
    public static JsObject toPrimitive;
    public static String toStringTag;
    public static JsObject unscopables;
    //////////////////////////////
    public static JsObject For(String key){
        return null;
    }
    public static String keyFor(JsObject sym){
        return null;
    }
    public String toSource(){
        return  null;
    }
    public String toString(){
        return String.format("Symbol(%s)",_description);
    }
    public JsObject valueOf(){
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
