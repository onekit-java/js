package cn.onekit.js;

import cn.onekit.js.JsAny;

public class Float32Array extends TypedArray<Double> {
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(4);
    public final static String name="Float32Array";
    public Float32Array(JsAny length) {
        super(Float32Array.class,length);
    }

    public Float32Array(TypedArray<Double> typedArray) {
        super(typedArray);
    }

    public Float32Array(JsAny buffer, JsAny byteOffset, JsAny length) {
        super(Float32Array.class,buffer, byteOffset, length);
    }

    public Float32Array(JsAny buffer, JsAny byteOffset) {
        super(Float32Array.class,buffer, byteOffset);
    }


    public Float32Array(JsArray array) {
        super(Float32Array.class,array);
    }
    //////////////////////////////////
    public static  Float32Array from(Set source, function mapFn, JsAny thisArg) {
        return _from(Float32Array.class, source, mapFn, thisArg);
    }
    public static Float32Array of(JsAny... elements) {
        return _of(Float32Array.class, elements);
    }
}
