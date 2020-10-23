package cn.onekit.js;
import cn.onekit.js.core.function;

public class Uint32Array extends TypedArray{
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(4);
    public final static String name = "Uint32Array";

    public <TA extends TypedArray> Uint32Array(JsObject_ length) {
        super(Uint32Array.class, length);
    }

    public <TA extends TypedArray> Uint32Array(TA typedArray) {
        super(typedArray);
    }

    public <TA extends TypedArray> Uint32Array(JsObject_ buffer, JsObject_ byteOffset, JsObject_ length) {
        super(Uint32Array.class, buffer, byteOffset, length);
    }

    public <TA extends TypedArray> Uint32Array(JsObject_ buffer, JsObject_ byteOffset) {
        super(Uint32Array.class, buffer, byteOffset);
    }

    public <TA extends TypedArray> Uint32Array(JsArray array) {
        super(Uint32Array.class, array);
    }

    //////////////////////////////////
    public static  Uint32Array from(Set source, function mapFn, JsObject_ thisArg) {
        return _from(Uint32Array.class, source, mapFn, thisArg);
    }

    public static Uint32Array from(Set source, function mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint32Array from(Set source) {
        return from(source, null);
    }

    //
    public static  Uint32Array from(JsArray source, function mapFn, JsObject_ thisArg) {
        return _from(Uint32Array.class, source, mapFn, thisArg);
    }

    public static Uint32Array from(JsArray source, function mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint32Array from(JsArray source) {
        return from(source, null);
    }

    //
    public static  Uint32Array from(JsObject_ source, JsObject_ mapFn, JsObject_ thisArg) {
        return _from(Uint32Array.class, source, mapFn, thisArg);
    }

    public static Uint32Array from(JsObject_ source, JsObject_ mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint32Array from(JsObject_ source) {
        return from(source, null);
    }

    public static Uint32Array of(JsObject_... elements) {
        return _of(Uint32Array.class, elements);
    }


}
