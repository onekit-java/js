package cn.onekit.js;

import java.lang.reflect.Method;

import cn.onekit.js.JsAny;

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
          return body(JsArray.of(arguments));
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
     public JsString ToString() {
          return new JsString("function");
     }



}
