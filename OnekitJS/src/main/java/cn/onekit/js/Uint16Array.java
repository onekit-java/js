package cn.onekit.js;
import cn.onekit.js.core.function;

public class Uint16Array extends TypedArray{
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(2);
    public final static String name = "Uint16Array";

    public <TA extends TypedArray> Uint16Array(JsObject_ length) {
        super(Uint16Array.class, length);
    }

    public <TA extends TypedArray> Uint16Array(TA typedArray) {
        super(typedArray);
    }

    public <TA extends TypedArray> Uint16Array(JsObject_ buffer, JsObject_ byteOffset, JsObject_ length) {
        super(Uint16Array.class, buffer, byteOffset, length);
    }

    public <TA extends TypedArray> Uint16Array(JsObject_ buffer, JsObject_ byteOffset) {
        super(Uint16Array.class, buffer, byteOffset);
    }


    public <TA extends TypedArray> Uint16Array(JsArray array) {
        super(Uint16Array.class, array);
    }

    //////////////////////////////////
    public static  Uint16Array from(Set source, function mapFn, JsObject_ thisArg) {
        return _from(Uint16Array.class, source, mapFn, thisArg);
    }

    public static Uint16Array from(Set source, function mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint16Array from(Set source) {
        return from(source, null);
    }

    //
    public static  Uint16Array from(JsArray source, function mapFn, JsObject_ thisArg) {
        return _from(Uint16Array.class, source, mapFn, thisArg);
    }

    public static Uint16Array from(JsArray source, function mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint16Array from(JsArray source) {
        return from(source, null);
    }

    //
    public static  Uint16Array from(JsObject_ source, JsObject_ mapFn, JsObject_ thisArg) {
        return _from(Uint16Array.class, source, mapFn, thisArg);
    }

    public static Uint16Array from(JsObject_ source, JsObject_ mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint16Array from(JsObject_ source) {
        return from(source, null);
    }

    public static Uint16Array of(JsObject_... elements) {
        return _of(Uint16Array.class, elements);
    }
}
