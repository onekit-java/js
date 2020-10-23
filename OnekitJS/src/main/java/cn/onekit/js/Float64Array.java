package cn.onekit.js;

import cn.onekit.js.core.function;

public class Float64Array extends TypedArray<Double> {
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(8);
    public final static String name="Float64Array";
    public Float64Array(JsObject_ length) {
        super(Float64Array.class,length);
    }

    public Float64Array(TypedArray<Double> typedArray) {
        super(typedArray);
    }

    public Float64Array(JsObject_ buffer, JsObject_ byteOffset, JsObject_ length) {
        super(Float64Array.class,buffer, byteOffset, length);
    }

    public Float64Array(JsObject_ buffer, JsObject_ byteOffset) {
        super(Float64Array.class,buffer, byteOffset);
    }


    public Float64Array(JsArray array) {
        super(Float64Array.class,array);
    }
    //////////////////////////////////
    public static  Float64Array from(Set source, function mapFn, JsObject_ thisArg) {
        return _from(Float64Array.class, source, mapFn, thisArg);
    }
    public static Float64Array of(JsObject_... elements) {
        return _of(Float64Array.class, elements);
    }
}
