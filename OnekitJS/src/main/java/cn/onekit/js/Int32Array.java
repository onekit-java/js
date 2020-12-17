package cn.onekit.js;

public class Int32Array extends TypedArray{
    public final static int BYTES_PER_ELEMENT=4;
    public final static String name="Int32Array";
    public Int32Array(JsAny length) {
        super(Int32Array.class,length);
    }

    public Int32Array(TypedArray typedArray) {
        super(typedArray);
    }

    public Int32Array(JsAny buffer, JsAny byteOffset, JsAny length) {
        super(Int32Array.class,buffer, byteOffset, length);
    }

    public Int32Array(JsAny buffer, JsAny byteOffset) {
        super(Int32Array.class,buffer, byteOffset);
    }


    public Int32Array(JsArray array) {
        super(Int32Array.class,array);
    }
    //////////////////////////////////
    public static  Int32Array from(Set source, function mapFn, JsAny thisArg) {
        return _from(Int32Array.class, source, mapFn, thisArg);
    }
    public static Int32Array of(JsAny... elements) {
        return _of(Int32Array.class, elements);
    }
}
