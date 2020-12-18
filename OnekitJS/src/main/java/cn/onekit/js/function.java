package cn.onekit.js;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.onekit.JsAny;

public class function implements JsAny {
     Object obj;
     Method method;
     public JsAny thisArg;
     public function(){

     }
     public function(Object obj,Method method) {
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
     public JsAny invoke(JsAny... arguments) {
          /*
          try {
               if(method.getParameterTypes().length==1 && method.getParameterTypes()[0].isArray()){
                    return (JsAny) method.invoke(obj, new Object[]{arguments.toArray(new JsAny[arguments.size()])});
               }else {
                    return (JsAny) method.invoke(obj, arguments.toArray());
               }
          } catch (Exception e) {
               e.printStackTrace();
               return null;
          }*/
          try {
               return (JsAny) method.invoke(obj, arguments);
          } catch (Exception e) {
               e.printStackTrace();
               return null;
          }
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
          return new JsString("function");
     }



}
