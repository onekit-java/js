package cn.onekit.js;

import cn.onekit.js.core.Onekit_JS;


public class ArrayBuffer implements JsAny {
    public byte[] _data;
    public ArrayBuffer(byte[] data) {
        _data=data;
    }
    public ArrayBuffer(JsAny data) {
        if (data instanceof JsArray) {
            JsArray array = (JsArray) data;
            this._data = new byte[array.size()];
            for (int i=0;i<array.size();i++) {
                JsNumber item = (JsNumber)array.get(i);
                this._data[i] =item.THIS.byteValue();
            }
        } else if (data instanceof JsNumber) {
            _data = new byte[((JsNumber) data).THIS.intValue()];
        }
    }

    public JsString ToString() {
        return new JsString(String.format("ArrayBuffer { byteLength: %d }", getByteLength()));
    }

    @Override
    public String toLocaleString(JsString locales, JsAny options) {
        return null;
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }

    ////////////////////////////////////
    public JsAny getByteLength() {
        return new JsNumber(_data.length);
    }

    public JsNumber get(int index) {
        return new JsNumber(_data[index]);
    }

    public static JsBoolean isView(JsAny arg) {
        if (arg == null) {
            return new JsBoolean(false);
        }
        return new JsBoolean((arg instanceof TypedArray) || (arg instanceof DataView));
    }

    public static boolean isView() {
        return false;
    }

    public ArrayBuffer slice(JsAny begin, JsAny end) {
        int b = Onekit_JS.number(begin, 0, 0).intValue();
        int e = Onekit_JS.number(end, 0, 0).intValue();
        byte[] data = new byte[e - b];
        System.arraycopy(this._data, b, data, 0, e - b);
        ArrayBuffer result = new ArrayBuffer(new JsNumber(e - b));
        result._data = data;
        return result;
    }

    public ArrayBuffer slice(JsNumber begin) {
        return slice(begin, getByteLength());
    }

    public static ArrayBuffer transfer(ArrayBuffer oldBuffer, JsAny newByteLength) {
        ArrayBuffer result = new ArrayBuffer(newByteLength);
        int len = ((JsNumber) newByteLength).THIS.intValue();
        for (int i = 0; i < len && i < oldBuffer._data.length; i++) {
            result._data[i] = oldBuffer._data[i];
        }

        oldBuffer._data = new byte[]{};
        return result;
    }

    public static ArrayBuffer transfer(ArrayBuffer oldBuffer) {
        return transfer(oldBuffer, oldBuffer.getByteLength());
    }

    @Override
    public JsAny get(String key) {
        return null;
    }

    @Override
    public JsAny get(JsAny key) {
        return null;
    }

    @Override
    public void set(String key, JsAny value) {

    }

    @Override
    public void set(JsAny key, JsAny value) {

    }
}
