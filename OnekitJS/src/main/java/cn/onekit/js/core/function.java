package cn.onekit.js.core;

import java.lang.reflect.Method;

import cn.onekit.js.JsAny;
import cn.onekit.js.JsString;

public  class function implements JsAny {
     JsAny obj;
     Method method;
     public JsAny thisArg;
     public function(){

     }

     public function(Class clazz, String methodName, Class<JsAny>... types) {
          try {
               method = clazz.getMethod(methodName,types);
          } catch (NoSuchMethodException e) {
               e.printStackTrace();
          }
     }

     public function(JsAny obj, String methodName, Class<JsAny>... types) {
          this(obj.getClass(), methodName,types);
          this.obj = obj;
     }
     public JsAny invoke(JsAny... arguments) {
          try{
               return (JsAny) method.invoke(obj, arguments);
          } catch (Exception e) {
               e.printStackTrace();
               return null;
          }
     }

     @Override
     public JsAny get(String key) {
          return null;
     }

     @Override
     public JsAny get(JsAny key) {
          return null;
     }

     @Override
     public void set(String key, JsAny value) {

     }

     @Override
     public void set(JsAny key, JsAny value) {

     }

     @Override
     public JsString ToString() {
          return null;
     }

     @Override
     public String toLocaleString(JsString locales, JsAny options) {
          return null;
     }
}
