package cn.onekit.js;

public class Int8Array extends TypedArray{
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(1);
    public final static String name="Int8Array";
    public Int8Array(JsObject_ length) {
        super(Int8Array.class,length);
    }


    public Int8Array(JsObject_ buffer, JsObject_ byteOffset, JsObject_ length) {
        super(Int8Array.class,buffer, byteOffset, length);
    }

    public Int8Array(JsObject_ buffer, JsObject_ byteOffset) {
        super(Int8Array.class,buffer, byteOffset);
    }


    public Int8Array(JsArray array) {
        super(Int8Array.class,array);
    }
    //////////////////////////////////
    public static  Int8Array from(JsObject_ source, JsObject_ mapFn, JsObject_ thisArg) {
        return _from(Int8Array.class, source, mapFn, thisArg);
    }


    public static Int8Array of(JsObject_... elements) {
        return _of(Int8Array.class, elements);
    }

}
