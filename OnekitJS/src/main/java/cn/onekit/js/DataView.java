package cn.onekit.js;

import cn.onekit.js.JsAny;
import cn.onekit.js.core.Onekit_JS;

/**
 * Created by zhangjin on 2017/10/30.
 */

public class DataView implements JsAny {
    private final int _byteLength;
    private ArrayBuffer _buffer;
    private int _byteOffset;

    ////////////////////////////////////////////////////
    public DataView(JsAny buffer, JsAny byteOffset, JsAny byteLength) {
        _byteOffset = Onekit_JS.number(byteOffset,0,0).intValue();
        _byteLength = Onekit_JS.number(byteLength,0,0).intValue();
        _buffer = (ArrayBuffer)buffer;
    }

    public DataView(JsAny buffer, JsAny byteOffset) {
        this(buffer, byteOffset, new JsNumber(((JsNumber)((ArrayBuffer)buffer).getByteLength()).THIS.intValue() - ((JsNumber)byteOffset).THIS.intValue()));
    }

    public DataView(JsAny buffer) {
        this(buffer, new JsNumber(0));
    }

    //////////////////////////////////////////////

    public ArrayBuffer getBuffer() {
        return _buffer;
    }

    public JsAny getByteLength() {
        return new JsNumber(_byteLength);
    }

    public JsAny getByteOffset() {
        return new JsNumber(_byteOffset);
    }
    ////////////////////////////////////////////////


    public JsAny getFloat32(JsAny byteOffset, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber((float) _get(byteOffset, "Float32", 4, ((JsBoolean)littleEndian).THIS));
    }
 

    public JsAny getFloat64(JsAny byteOffset, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber((double)_get(byteOffset, "Float64", 8, ((JsBoolean)littleEndian).THIS));
    }

    public JsAny getInt16(JsAny byteOffset, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber( (short)_get(byteOffset, "Int16", 2, ((JsBoolean)littleEndian).THIS));
    }
    

    public JsAny getInt32(JsAny byteOffset, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return (JsAny) _get(byteOffset, "Int32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public JsAny getInt8(JsAny byteOffset) {
        return new JsNumber( (byte)_get(byteOffset, "Int8", 1, false));
    }

    public JsAny getUint16(JsAny byteOffset, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber( (short)_get(byteOffset, "Uint16", 2, ((JsBoolean)littleEndian).THIS));
    }
    public JsAny getUint32(JsAny byteOffset, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber((long) _get(byteOffset, "Uint32", 4, ((JsBoolean)littleEndian).THIS));
    }

    public JsAny getUint8(JsAny byteOffset) {
        return new JsNumber((short) _get(byteOffset, "Uint8", 1, false));
    }

    public void setFloat32(JsAny byteOffset, JsAny value, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Float32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public void setFloat64(JsAny byteOffset, JsAny value, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Float64", 8, ((JsBoolean)littleEndian).THIS);
    }


    public void setInt16(JsAny byteOffset, JsAny value, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Int16", 2, ((JsBoolean)littleEndian).THIS);
    }

    public void setInt32(JsAny byteOffset, JsAny value, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Int32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public void setInt8(JsAny byteOffset, JsAny value) {
        
        _set(byteOffset, value, "Int8", 1, false);
    }

    

    public void setUint16(JsAny byteOffset, JsAny value, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Uint16", 2, ((JsBoolean)littleEndian).THIS);
    }
    public void setUint32(JsAny byteOffset, JsAny value, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Uint32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public void setUint8(JsAny byteOffset, JsAny value, JsAny littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Uint8", 1, ((JsBoolean)littleEndian).THIS);
    }

    private Object _get(JsAny byteOffset, String type, int BYTES_PER_ELEMENT, boolean littleEndian) {
        
        return Onekit_JS.bytes2number(_buffer._data, type, BYTES_PER_ELEMENT,((JsNumber)getByteOffset()).THIS.intValue()+ ((JsNumber)byteOffset).THIS.intValue());
    }

    private <T extends Number>  void _set(JsAny byteOffset, JsAny value, String type, int BYTES_PER_ELEMENT, boolean littleEndian) {
        Onekit_JS.number2bytes(_buffer._data, type, BYTES_PER_ELEMENT,_byteOffset+ ((JsNumber)byteOffset).THIS.intValue(), value);
    }

    @Override
    public JsAny get(JsAny key) {
        return null;
    }

    @Override
        public JsAny set(JsAny key, JsAny value) {
return this;
        }

    @Override
    public JsAny get(String key) {
        return null;
    }

    @Override
    public JsAny set(String key, JsAny value) {
        return null;
    }

    @Override
    public JsString ToString() {
        return new JsString("DataView");
    }




    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}


















































