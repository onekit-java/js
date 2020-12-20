package cn.onekit.js;

import cn.onekit.js.JsAny;

public class Reflect  {
    ////////////////////////
    public static JsAny apply(JsObject target, JsObject thisArgument, JsObject argumentsList){
        return null;
    }
    public static JsAny construct(JsObject target, JsObject argumentsList, JsObject newTarget ){
        return null;
    }
    public static boolean defineProperty(JsObject target, String  propertyKey, String attributes){
        return true;
    }
    public static boolean deleteProperty(JsObject target, String  propertyKey){
        return true;
    }
    public static JsAny get(JsObject target, String  propertyKey, JsObject receiver){
        return null;
    }
    public static JsAny getOwnPropertyDescriptor(JsObject target, String propertyKey){
        return null;
    }
    public static JsAny getPrototypeOf(JsObject target){
        return null;
    }
    public static boolean has(JsObject target, String propertyKey){
        return true;
    }
    public static boolean isExtensible(JsObject target){
        return true;
    }
    public static JsObject ownKeys(JsObject target){
        return  null;
    }
    public static boolean preventExtensions(JsObject target){
        return true;
    }
    public static boolean set(JsObject target, String propertyKey, String value, JsObject receiver){
        return true;
    }
    public static boolean setPrototypeOf(JsObject target, JsObject prototype){
        return true;
    }

}
