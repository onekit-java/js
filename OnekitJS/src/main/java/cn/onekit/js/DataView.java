package cn.onekit.js;

import cn.onekit.js.core.Onekit_JS;

/**
 * Created by zhangjin on 2017/10/30.
 */

public class DataView implements JsObject_ {
    private final int _byteLength;
    private ArrayBuffer _buffer;
    private int _byteOffset;

    ////////////////////////////////////////////////////
    public DataView(JsObject_ buffer, JsObject_ byteOffset, JsObject_ byteLength) {
        _byteOffset = Onekit_JS.number(byteOffset,0,0).intValue();
        _byteLength = Onekit_JS.number(byteLength,0,0).intValue();
        _buffer = (ArrayBuffer)buffer;
    }

    public DataView(JsObject_ buffer, JsObject_ byteOffset) {
        this(buffer, byteOffset, new JsNumber(((JsNumber)((ArrayBuffer)buffer).getByteLength()).THIS.intValue() - ((JsNumber)byteOffset).THIS.intValue()));
    }

    public DataView(JsObject_ buffer) {
        this(buffer, new JsNumber(0));
    }

    //////////////////////////////////////////////

    public ArrayBuffer getBuffer() {
        return _buffer;
    }

    public JsObject_ getByteLength() {
        return new JsNumber(_byteLength);
    }

    public JsObject_ getByteOffset() {
        return new JsNumber(_byteOffset);
    }
    ////////////////////////////////////////////////


    public JsObject_ getFloat32(JsObject_ byteOffset, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber((float) _get(byteOffset, "Float32", 4, ((JsBoolean)littleEndian).THIS));
    }
 

    public JsObject_ getFloat64(JsObject_ byteOffset, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber((double)_get(byteOffset, "Float64", 8, ((JsBoolean)littleEndian).THIS));
    }

    public JsObject_ getInt16(JsObject_ byteOffset, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber( (short)_get(byteOffset, "Int16", 2, ((JsBoolean)littleEndian).THIS));
    }
    

    public JsObject_ getInt32(JsObject_ byteOffset, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return (JsObject_) _get(byteOffset, "Int32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public JsObject_ getInt8(JsObject_ byteOffset) {
        return new JsNumber( (byte)_get(byteOffset, "Int8", 1, false));
    }

    public JsObject_ getUint16(JsObject_ byteOffset, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber( (short)_get(byteOffset, "Uint16", 2, ((JsBoolean)littleEndian).THIS));
    }
    public JsObject_ getUint32(JsObject_ byteOffset, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        return new JsNumber((long) _get(byteOffset, "Uint32", 4, ((JsBoolean)littleEndian).THIS));
    }

    public JsObject_ getUint8(JsObject_ byteOffset) {
        return new JsNumber((short) _get(byteOffset, "Uint8", 1, false));
    }

    public void setFloat32(JsObject_ byteOffset, JsObject_ value, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Float32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public void setFloat64(JsObject_ byteOffset, JsObject_ value, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Float64", 8, ((JsBoolean)littleEndian).THIS);
    }


    public void setInt16(JsObject_ byteOffset, JsObject_ value, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Int16", 2, ((JsBoolean)littleEndian).THIS);
    }

    public void setInt32(JsObject_ byteOffset, JsObject_ value, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Int32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public void setInt8(JsObject_ byteOffset, JsObject_ value) {
        
        _set(byteOffset, value, "Int8", 1, false);
    }

    

    public void setUint16(JsObject_ byteOffset, JsObject_ value, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Uint16", 2, ((JsBoolean)littleEndian).THIS);
    }
    public void setUint32(JsObject_ byteOffset, JsObject_ value, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Uint32", 4, ((JsBoolean)littleEndian).THIS);
    }

    public void setUint8(JsObject_ byteOffset, JsObject_ value, JsObject_ littleEndian) {
        if(littleEndian==null){
            littleEndian = new JsBoolean(false) ;
        }
        _set(byteOffset, value, "Uint8", 1, ((JsBoolean)littleEndian).THIS);
    }

    private Object _get(JsObject_ byteOffset, String type, int BYTES_PER_ELEMENT, boolean littleEndian) {
        
        return Onekit_JS.bytes2number(_buffer._data, type, BYTES_PER_ELEMENT,((JsNumber)getByteOffset()).THIS.intValue()+ ((JsNumber)byteOffset).THIS.intValue());
    }

    private <T extends Number>  void _set(JsObject_ byteOffset, JsObject_ value, String type, int BYTES_PER_ELEMENT, boolean littleEndian) {
        Onekit_JS.number2bytes(_buffer._data, type, BYTES_PER_ELEMENT,_byteOffset+ ((JsNumber)byteOffset).THIS.intValue(), value);
    }

    @Override
    public JsObject_ get(JsObject_ key) {
        return null;
    }

    @Override
    public void set(JsObject_ key, JsObject_ value) {

    }

    @Override
    public JsString ToString() {
        return null;
    }

    @Override
    public String toLocaleString(JsString locales, JsObject_ options) {
        return null;
    }

    @Override
    public JsObject_ invoke(JsObject_... params) {
        return null;
    }
}


















































