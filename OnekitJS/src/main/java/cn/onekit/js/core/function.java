package cn.onekit.js.core;

import java.lang.reflect.Method;

import cn.onekit.js.JsObject_;
import cn.onekit.js.JsString;

public  class function implements JsObject_ {
     JsObject_ obj;
     Method method;
     public JsObject_ thisArg;
     public function(){

     }

     public function(Class clazz, String methodName, Class<JsObject_>... types) {
          try {
               method = clazz.getMethod(methodName,types);
          } catch (NoSuchMethodException e) {
               e.printStackTrace();
          }
     }

     public function(JsObject_ obj, String methodName, Class<JsObject_>... types) {
          this(obj.getClass(), methodName,types);
          this.obj = obj;
     }
     public JsObject_ invoke(Object... arguments) {
          try{
               return (JsObject_) method.invoke(obj, arguments);
          } catch (Exception e) {
               e.printStackTrace();
               return null;
          }
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
}
