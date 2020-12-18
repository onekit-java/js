package cn.onekit.js;

import cn.onekit.JsAny;

public class Promise implements JsAny {
    function _callback;

    @Override
    public String toString() {
        return "[obj Promise]";
    }

    ///////////////////////////////
    public Promise(function callback) {
        _callback = callback;
    }

    //////////////////////////////////
    public static Promise all(JsArray iterable) {
        return null;
        /*return new Promise((resolve, reject) -> {
            Array result = new Array();
            for (JsObject item : iterable) {
                if (item instanceof Promise) {
                    Promise promise = (Promise) item;
                    //promise.then(new cn.onekit.js.core.function() {
                   //     @Override
                   //     public JsObject invoke(JsObject... arguments) {
                  //          JsObject arg=arguments[0];
                  //           result.add(arg);
                 //            return null;
                 //       }
                //    });
                } else {
                    result.add(item);
                }
            }
      // reject).invoke(result);
            return null;
        });*/
    }

    public static Promise all(String iterable) {
        return null;
    }

    public static Promise allSettled(JsArray iterable) {
        return null;
    }

    public static Promise allSettled(String iterable) {
        return null;
    }

    public Promise Catch(function onRejected) {
        return null;
    }

    public Promise Finally(function onFinally) {
        return null;
    }

    public Promise then(function onFulfilled, function onRejected) {
        _callback.invoke(onFulfilled, onRejected);
        return this;
    }

    public Promise then(function onFulfilled) {
        return then(onFulfilled, null);
    }

    public static Promise race(JsArray iterable) {
        return null;
    }

    public static Promise race(String iterable) {
        return null;
    }

    public static Promise reject(Error reason) {
        return null;//new Promise((resolve, reject) -> (()invoke(reason));
    }

    public static Promise resolve(JsAny value) {
        return new Promise(new function(){

            @Override
            public JsAny invoke(JsAny... arguments) {
                try {
                    function resolve = (function) arguments[0];
                    resolve.invoke(value);
                }catch (Exception e){
                    e.printStackTrace();
                    function reject = (function) arguments[1];
                    reject.invoke(value);
                }
                return null;
            }
        });
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
        return new JsString("Promise");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
