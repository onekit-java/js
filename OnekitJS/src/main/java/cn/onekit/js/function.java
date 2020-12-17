package cn.onekit.js;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.onekit.js.JsAny;
import cn.onekit.js.JsString;

public class function implements JsAny {
     JsAny obj;
     Method method;
     public JsAny thisArg;
     public function(){

     }
     public function(JsAny obj,Method method) {
          this.obj=obj;
          this.method=method;
     }
/*
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
     }*/
     public JsAny body(JsArray arguments) {
          try {
               if(method.getParameterTypes().length==1 && method.getParameterTypes()[0].isArray()){
                    return (JsAny) method.invoke(obj, new Object[]{arguments.toArray(new JsAny[arguments.size()])});
               }else {
                    return (JsAny) method.invoke(obj, arguments.toArray());
               }
          } catch (Exception e) {
               e.printStackTrace();
               return null;
          }
     }
     public JsAny invoke(JsAny... arguments) {
          try{
               final JsArray array = JsArray.of(arguments);
               return body(array);
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
          return new JsString("function");
     }


     @Override
     public String toLocaleString(JsString locales, JsAny options) {
          return null;
     }
}
