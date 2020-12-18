package cn.onekit.js;

import cn.onekit.JsAny;

public class Uint8Array extends TypedArray{
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(1);
    public final static String name = "Uint8Array";

    public <TA extends TypedArray> Uint8Array(JsNumber length) {
        super(Uint8Array.class, length);
    }

    public <TA extends TypedArray> Uint8Array(TA typedArray) {
        super(typedArray);
    }

    public <TA extends TypedArray> Uint8Array(JsAny buffer, JsAny byteOffset, JsAny length) {
        super(Uint8Array.class, buffer, byteOffset, length);
    }

    public <TA extends TypedArray> Uint8Array(JsAny buffer, JsAny byteOffset) {
        super(Uint8Array.class, buffer, byteOffset);
    }

    public <TA extends TypedArray> Uint8Array(JsAny buffer) {
        super(Uint8Array.class, buffer);
    }

    public <TA extends TypedArray> Uint8Array(JsArray array) {
        super(Uint8Array.class, array);
    }

    //////////////////////////////////
    public static  Uint8Array from(Set source, function mapFn, JsAny thisArg) {
        return _from(Uint8Array.class, source, mapFn, thisArg);
    }
    public static Uint8Array of(JsAny... elements) {
        return _of(Uint8Array.class, elements);
    }
}
