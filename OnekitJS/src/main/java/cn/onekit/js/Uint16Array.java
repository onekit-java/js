package cn.onekit.js;
import cn.onekit.js.core.function;

public class Uint16Array extends TypedArray{
    public final static JsNumber BYTES_PER_ELEMENT=new JsNumber(2);
    public final static String name = "Uint16Array";

    public <TA extends TypedArray> Uint16Array(JsAny length) {
        super(Uint16Array.class, length);
    }

    public <TA extends TypedArray> Uint16Array(TA typedArray) {
        super(typedArray);
    }

    public <TA extends TypedArray> Uint16Array(JsAny buffer, JsAny byteOffset, JsAny length) {
        super(Uint16Array.class, buffer, byteOffset, length);
    }

    public <TA extends TypedArray> Uint16Array(JsAny buffer, JsAny byteOffset) {
        super(Uint16Array.class, buffer, byteOffset);
    }


    public <TA extends TypedArray> Uint16Array(JsArray array) {
        super(Uint16Array.class, array);
    }

    //////////////////////////////////
    public static  Uint16Array from(Set source, function mapFn, JsAny thisArg) {
        return _from(Uint16Array.class, source, mapFn, thisArg);
    }

    public static Uint16Array from(Set source, function mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint16Array from(Set source) {
        return from(source, null);
    }

    //
    public static  Uint16Array from(JsArray source, function mapFn, JsAny thisArg) {
        return _from(Uint16Array.class, source, mapFn, thisArg);
    }

    public static Uint16Array from(JsArray source, function mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint16Array from(JsArray source) {
        return from(source, null);
    }

    //
    public static  Uint16Array from(JsAny source, JsAny mapFn, JsAny thisArg) {
        return _from(Uint16Array.class, source, mapFn, thisArg);
    }

    public static Uint16Array from(JsAny source, JsAny mapFn) {
        return from(source, mapFn, null);
    }

    public static Uint16Array from(JsAny source) {
        return from(source, null);
    }

    public static Uint16Array of(JsAny... elements) {
        return _of(Uint16Array.class, elements);
    }
}
