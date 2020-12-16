package cn.onekit.js;

import cn.onekit.js.core.JsAny;

public class Int8Array extends TypedArray{
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(1);
    public final static String name="Int8Array";
    public Int8Array(JsAny length) {
        super(Int8Array.class,length);
    }


    public Int8Array(JsAny buffer, JsAny byteOffset, JsAny length) {
        super(Int8Array.class,buffer, byteOffset, length);
    }

    public Int8Array(JsAny buffer, JsAny byteOffset) {
        super(Int8Array.class,buffer, byteOffset);
    }


    public Int8Array(JsArray array) {
        super(Int8Array.class,array);
    }
    //////////////////////////////////
    public static  Int8Array from(JsAny source, JsAny mapFn, JsAny thisArg) {
        return _from(Int8Array.class, source, mapFn, thisArg);
    }


    public static Int8Array of(JsAny... elements) {
        return _of(Int8Array.class, elements);
    }

}
