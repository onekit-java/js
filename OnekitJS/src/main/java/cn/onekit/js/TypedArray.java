package cn.onekit.js;

import java.util.Collections;
import java.util.Comparator;

import cn.onekit.js.JsAny;
import cn.onekit.js.core.Iterator;

import cn.onekit.js.core.Onekit_JS;

public abstract   class TypedArray<T extends Number> implements Iterable, JsAny {

    private int _byteLength;
    private int _byteOffset;
    public ArrayBuffer _buffer;

    protected static <TA extends TypedArray> int _BYTES_PER_ELEMENT(Class<TA> clazz) {
        try {
            return (int) clazz.getField("BYTES_PER_ELEMENT").get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    protected static <TA extends TypedArray> String _name(Class<TA> clazz) {
        try {
            return (String) clazz.getField("name").get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < getLength().THIS.intValue(); i++) {
            if (i > 0) {
                result.append(",");
            }
            JsAny v = get(i);
            result.append(v);
        }
        result.append("]");
        return result.toString();
    }
    @Override
    public java.util.Iterator iterator() {
        return new java.util.Iterator() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < getLength().THIS.intValue();
            }

            @Override
            public JsAny next() {
                return get(index++);
            }
        };
    }
    ///////////////////////////////////////////////////

    public JsAny get(Integer index) {
        int size=_BYTES_PER_ELEMENT(getClass());
        return new JsNumber( Onekit_JS.bytes2number(_buffer._data,_name(getClass()).replace("Array",""), size,size*index));
    }

    public JsAny set(JsAny index, JsAny value) {
        _set(Onekit_JS.number(index,0,0).intValue(),this,value);
        return this;
    }

    public static int _index(TypedArray array, int index) {
        if (index >= 0) {
            return index;
        }
        return array.getLength().THIS.intValue() + index;
    }
    ////////////////////////////////////////////////////////


    public <TA extends TypedArray> TypedArray(Class<TA> clazz, JsAny arg) {
        /*if(arg instanceof NUMBER){
            int len=OnekitJS.number(arg,0,0).intValue()* _BYTES_PER_ELEMENT(clazz);
            this(clazz, new ArrayBuffer(new NUMBER(len)),new NUMBER(arg));
        }else if(arg instanceof ArrayBuffer){
            this(clazz, arg, new NUMBER(0));
        }*/
    }

    public <TA extends TypedArray>  TypedArray(TA typedArray) {
        this(typedArray.getClass(),new ArrayBuffer(typedArray.getBuffer()._data.clone()));
    }

    public <TA extends TypedArray> TypedArray(Class<TA> clazz, JsAny buffer, JsAny byteOffset, JsAny length) {
        this._byteOffset = Onekit_JS.number(byteOffset,0,0).intValue();
        this._buffer = (ArrayBuffer) buffer;
        this._byteLength = Onekit_JS.number(length,0,0).intValue();
    }
    public <TA extends TypedArray> TypedArray(Class<TA> clazz, JsAny buffer, JsAny byteOffset) {
        this(clazz, buffer, byteOffset, new JsNumber(((JsNumber)((ArrayBuffer)buffer).getByteLength()).THIS.intValue() - ((JsNumber)byteOffset).THIS.intValue()));
    }

    public <TA extends TypedArray> TypedArray(Class<TA> clazz, JsArray array) {
        this(clazz, _array2buffer(clazz,array));
    }
    public <TA extends TypedArray> TypedArray(Class<TA> clazz, ArrayBuffer buffer) {
        this(clazz, buffer,new JsNumber(0));
    }
    private static < TA extends TypedArray> ArrayBuffer _array2buffer(Class<TA> clazz, JsArray array){
        ArrayBuffer result=new ArrayBuffer(new JsNumber(array.size()*_BYTES_PER_ELEMENT(clazz)));

        for(int i = 0; i< array.size(); i++){
            Onekit_JS.number2bytes(result._data,clazz.getSimpleName().replace("Array",""),_BYTES_PER_ELEMENT(clazz),_BYTES_PER_ELEMENT(clazz)*i, _fix(clazz,array.get(i)));

        }
        return result;
    }
    ////////////////////////////////////////////////////

    public ArrayBuffer getBuffer() {
        return _buffer;
    }

    public JsNumber getByteLength() {
        return new JsNumber(_byteLength);
    }

    public JsNumber getByteOffset() {
        return new JsNumber(_byteOffset);
    }

    public JsNumber getLength() {
        int size = _BYTES_PER_ELEMENT(this.getClass());
        return new JsNumber(getByteLength().THIS.intValue() / size);
    }
    private static JsAny _fix(Class clazz, JsAny flag){
        if(flag.equals(Double.NaN)) {
            return new JsNumber(0.0);
        }
        String str = flag.toString();
        String name = clazz.getSimpleName().replace("Array", "");
        Number value;
        switch (name) {
            case "Int8":
            case "Uint8Clamped":
            case "Uint8":
            case "Int16":
            case "Uint16":
            case "Int32":
                value= Double.valueOf(str).intValue();break;
            case "Uint32":
                value= Long.valueOf(str);break;
            case "Float32":
                value= Float.valueOf(str);break;
            case "Float64":
                value= Double.valueOf(str);break;
            default:
                throw new Error(new JsString(name));
        }
        return new JsNumber(value);
    }
    public static <TA extends TypedArray> void _set(int index, TA typedArray, JsAny flag) {
        Class clazz = typedArray.getClass();


        int size=_BYTES_PER_ELEMENT(clazz);
        Onekit_JS.number2bytes(typedArray._buffer._data,_name(clazz).replace("Array",""),size ,index*size, _fix(clazz,flag));
    }

    protected static <TA extends TypedArray>  TA _from(Class<TA> clazz, JsAny source, JsAny fn, JsAny thisArg) {
        function mapFn = (function)fn;
        if(source instanceof Set) {
            return _from(clazz,(Set)source, mapFn, thisArg);
        }else  if(source instanceof JsString) {
            return _from(clazz, (JsString)source, mapFn, thisArg);
        }else if(source instanceof JsArray) {
            return _from(clazz, (JsArray)source, mapFn, thisArg);
        }else{
            return null;
        }
    }
    private static <TA extends TypedArray> TA _from(Class<TA> clazz, Set source, function mapFn, JsAny thisArg) {
        try {

            ArrayBuffer buffer = new ArrayBuffer(new JsNumber(source.getSize() * _BYTES_PER_ELEMENT(clazz)));
            TA result = clazz.getConstructor(ArrayBuffer.class).newInstance(buffer);
            int i = 0;
            for (JsAny element : source) {
                JsAny flag;
                if (mapFn != null) {
                    flag = mapFn.invoke(element);
                } else  {
                    flag=element;
                }

               _set(i++,result,flag);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <TA extends TypedArray>  TA _from(Class<TA> clazz, JsArray source, function mapFn, JsAny thisArg) {
        return _from(clazz, new Set(source),mapFn,thisArg);
    }
    private static <TA extends TypedArray>  TA _from(Class<TA> clazz, JsString source, function mapFn, JsAny thisArg) {
  JsArray array = Onekit_JS.string2Array(source.THIS);
    return _from(clazz, new Set(array),mapFn,thisArg);
    }

    public static < TA extends TypedArray> TA _of(Class<TA> clazz, JsAny... elements) {
        try {

            ArrayBuffer buffer = new ArrayBuffer(new JsNumber(elements==null?1:elements.length * _BYTES_PER_ELEMENT(clazz)));
            TA result = clazz.getConstructor(ArrayBuffer.class).newInstance(buffer);
            if (elements == null) {
                result._buffer = new ArrayBuffer(new JsNumber(1 * result._BYTES_PER_ELEMENT(clazz)));
                result.set(new JsNumber(0),new JsNumber(0));
            } else {
                result._buffer = new ArrayBuffer(new JsNumber(elements.length * result._BYTES_PER_ELEMENT(clazz)));
                for (int i = 0; i < elements.length; i++) {
                    JsAny element = elements[i];
                    if(element==null){
                        element=new JsNumber(0);
                    }
                    _set(i,result,element);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    ////////////////////////////////////////////////////
    public <TA extends TypedArray> TA copyWithin(JsAny target, JsAny start, JsAny end) {
        if(end==null){
            end =  this.getLength();
        }
        int t = Onekit_JS.number(target,0,0).intValue();
        int s = Onekit_JS.number(start,0,0).intValue();
        int e = Onekit_JS.number(end,0,0).intValue();
        for (int i = s, j = t; i < e && i<getLength().THIS.intValue() && j<getLength().THIS.intValue(); i++, j++) {
            JsAny element = get(i);
            set(new JsNumber(j), element);
        }
        return (TA) this;
    }

    public Iterator entries() {
        return new Iterator(new java.util.Iterator() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < getLength().THIS.intValue();
            }

            @Override
            public JsAny next() {
                return get(index++);
            }
        }) {
            int i = 0;

            @Override
            public JsArray getValue(Object value) {
                return new JsArray() {{
                    add(new JsNumber(i++));
                    add((JsAny) value);
                }};
            }
        };
    }

        public JsBoolean every(function callback, JsAny thisArg)  {
            callback.thisArg = thisArg;
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                JsAny element = get(i);
                if (element == null) {
                    continue;
                }

                if (!Onekit_JS.is(callback.invoke(element, new JsNumber(i), this))) {
                    return new JsBoolean(false);
                }
            }
            return new JsBoolean(true);
        }

        public JsBoolean every(function callback) {
            return every(callback, null);
        }

        //
        <TA extends TypedArray> TA _fill(JsAny value, int start, int end) {
            start = _index(this, start);
            end = _index(this, end);
            for (int i = start; i >= 0 && i < end && i < getLength().THIS.intValue(); i++) {
                set( new JsNumber(i), value);
            }
            return (TA) this;
        }

        public <TA extends TypedArray> TA fill(JsAny value, JsAny start, JsAny end) {
            int start_ = Onekit_JS.number(start, 0, 0).intValue();
            int end_ = Onekit_JS.number(end, 0, 0).intValue();
            return _fill(value, start_, end_);
        }

        public <TA extends TypedArray> TA fill(JsAny value, JsAny start) {
            return fill(value, start, new JsNumber(getLength()));
        }

        public <TA extends TypedArray> TA fill(JsAny value) {
            return fill(value,  new JsNumber(0));
        }

        public <TA extends TypedArray>  TA filter(function callback, JsAny thisArg) {

            callback.thisArg = thisArg;
            JsArray array = new JsArray();
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                JsAny element = this.get(i);
                if (element == null) {
                    continue;
                }
                if (Onekit_JS.is(callback.invoke(element, new JsNumber( i), this))) {
                    array.add(element);
                }
            }
            Class<TA> clazz = (Class<TA>) getClass();
            return TA._of(clazz, array);
        }

        public <TA extends TypedArray> TA filter(function callback) {
            return filter(callback, null);
        }

        public JsAny find(function callback, JsAny thisArg) {
            callback.thisArg = thisArg;
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                JsAny element = this.get(i);
                if (Onekit_JS.is(callback.invoke(element, new JsNumber(i), this))) {
                    return element;
                }
            }
            return null;
        }

        public JsAny find(function callback) {
            return find(callback, null);
        }

        public JsNumber findIndex(function callback, JsAny thisArg) {
            callback.thisArg = thisArg;
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                JsAny element = this.get(i);
                if (element == null) {
                    continue;
                }
                if (Onekit_JS.is( callback.invoke(element, new JsNumber(i), this)) ){
                    return new JsNumber(i);
                }
            }
            return new JsNumber( -1);
        }

        public JsNumber findIndex(function callback) {
            return findIndex(callback, null);
        }

        public  void forEach(function callback, JsAny thisArg) {
            callback.thisArg = thisArg;
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                JsAny element = this.get(i);
                if (element == null) {
                    continue;
                }
                callback.invoke(element, new JsNumber(i), this);
            }

        }

        public void forEach(function callback) {
            forEach(callback, null);
        }

        //
        public JsBoolean includes(JsAny valueToFind, JsAny index) {
        int idx = Onekit_JS.number(index,0,0).intValue();
            double target;
            if (Onekit_JS.isNumber(valueToFind)) {
                target = Onekit_JS.number(valueToFind,0,0).doubleValue();
            } else {
                target = 0;
            }
            for (int i = idx; i < getLength().THIS.intValue(); i++) {
                double element =  Onekit_JS.number(this.get(i),0,0).doubleValue();
                if (target == element) {
                    return new JsBoolean( true);
                }
            }

            return  new JsBoolean( false);
        }

        public JsBoolean includes(JsAny valueToFind) {
            return includes(valueToFind, new JsNumber(0));
        }

        //
        public JsNumber indexOf(JsAny searchElement, int fromIndex) {
            double target;
            if(Onekit_JS.isNumber(searchElement)){
                target = Onekit_JS.number(searchElement,0,0).doubleValue();
            }else{
                target = 0;
            }
            fromIndex = _index(this, fromIndex);
            for (int i = fromIndex; i < getLength().THIS.intValue(); i++) {
                double element = Onekit_JS.number( get(i),0,0).doubleValue();
                if (target==element) {
                    return new JsNumber(i);
                }
            }
            return  new JsNumber( -1);
        }

        public JsNumber indexOf(JsAny searchElement) {
            return indexOf(searchElement, 0);
        }


        public String _join(String separator) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                if (i > 0) {
                    result.append(separator);
                }
                result.append(get(i));
            }
            return result.toString();
        }

        public JsString join(JsAny separator) {
            if (separator == null) {
                separator = new JsString(",");
            }
            return new JsString(_join(separator.toString()));
        }

        public Iterator keys() {
            return new Iterator(new java.util.Iterator() {
                int index = 0;

                @Override
                public boolean hasNext() {
                    return index < getLength().THIS.intValue();
                }

                @Override
                public JsAny next() {
                    return get(index++);
                }
            }) {
                @Override
                public Integer getValue(Object value) {
                    return index++;
                }

                int index = 0;
            };
        }

        public JsNumber lastIndexOf(JsAny searchElement, JsNumber fromIndex) {
            if (fromIndex == null) {
                fromIndex = new JsNumber(getLength().THIS.intValue() - 1);
            }
            double target;
            if (Onekit_JS.isNumber(searchElement)) {
                target = Onekit_JS.number(searchElement, 0, 0).doubleValue();
            } else {
                target = 0;
            }
            int index = _index(this, fromIndex.THIS.intValue());
            for (int i = index; i >= 0; i--) {
                double element = Onekit_JS.number(get(i), 0, 0).doubleValue();
                if (element == target) {
                    return new JsNumber(i);
                }
            }
            return new JsNumber(-1);
        }


        //
        public <TA extends TypedArray>  TA map(function callback, JsAny thisArg) {
            callback.thisArg = thisArg;
            JsArray array = new JsArray();
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                JsAny element = this.get(i);
                if (element == null) {
                    continue;
                }
                Class<TA> clazz = (Class<TA>)getClass();
                JsAny item = callback.invoke(element);
                array.add(_fix(clazz,item));
            }
            Class<TA> clazz = (Class<TA>) getClass();
            return TA._of(clazz, array.toArray(new JsAny[0]));
        }

        public <TA extends TypedArray> TA map(function callback) {
            return map(callback, null);
        }


        public JsAny reduce(function callback, JsAny initialValue) {
            if (initialValue == null) {
                initialValue =  get(0);
            }
            for (int i = 0; i < getLength().THIS.intValue(); i++) {
                JsAny element = this.get(i);
                if (element == null) {
                    continue;
                }
                initialValue = callback.invoke(initialValue, element,new JsNumber(i), this);
            }
            return initialValue;
        }

        public JsAny reduce(function callback) {
            return reduce(callback, null);
        }

        public JsAny reduceRight(function callback, JsAny initialValue) {
            if (initialValue == null) {
                initialValue =  get(0);
            }
            for (int i = getLength().THIS.intValue() - 1; i >= 0; i--) {
                JsAny element = this.get(i);
                if (element == null) {
                    continue;
                }
                initialValue = callback.invoke(initialValue, element, new JsNumber(i), this);
            }
            return initialValue;
        }

        public JsAny reduceRight(function callback) {
            return reduceRight(callback, null);
        }

        public <TA extends TypedArray> TA reverse() {

            JsArray array = new JsArray();
            for (int i = getLength().THIS.intValue() - 1; i >= 0; i--) {
                array.add(get(i));
            }
            for (int i = 0; i < array.size(); i++) {
                set(new JsNumber(i), array.get(i));
            }
            return (TA) this;
        }

    public void set(JsArray array, JsAny offset) {
        int o = Onekit_JS.number(offset,0,0).intValue();
        for (int i = 0, j = o; i < array.size(); i++, j++) {
            set(new JsNumber(j), array.get(i));
        }
    }
    public void set(TypedArray typedArray, JsAny offset) {
        int o = Onekit_JS.number(offset,0,0).intValue();
        for (int i = 0, j = o; i < typedArray.getLength().THIS.intValue(); i++, j++) {
            set(new JsNumber(j), typedArray.get(i));
        }
    }

    public <TA extends TypedArray> TA _slice(int start, int end) {
        start = _index(this,start);
        end = _index(this,end);
        JsArray array = new JsArray();
        for (int i = start; i < end; i++) {
            array.add(get(i));
        }
        Class<TA> clazz = (Class<TA>) getClass();
        return TA._of(clazz, array);
    }

    public <TA extends TypedArray> TA slice(JsAny start, JsAny end) {
        int start_ = Onekit_JS.number(start, 0, 0).intValue();
        int end_ = Onekit_JS.number(end, 0, 0).intValue();
        return _slice(start_, end_);
    }

    public <TA extends TypedArray> TA slice(JsAny start) {
        return slice(start, new JsNumber(getLength()) );
    }

    public <TA extends TypedArray> TA slice() {
        return slice(new JsNumber(0));
    }

    public JsBoolean some(function callback, JsAny thisArg) {
        for (int i = 0; i < getLength().THIS.intValue(); i++) {
            JsAny element = this.get(i);
            if (element == null) {
                continue;
            }
            if (Onekit_JS.is(callback.invoke(element,new JsNumber( i), this))) {
                return new JsBoolean( true);
            }
        }
        return new JsBoolean( false);
    }

    public <TA extends TypedArray> TA sort(function compareFunction) {
        JsArray array = new JsArray();
        for (int i = 0; i < getLength().THIS.intValue(); i++) {
            array.add(get(i));
        }
        Collections.sort(array, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((JsNumber)compareFunction.invoke((JsAny) o1, (JsAny) o2)).THIS.intValue();
            }
        });
        for (int i = 0; i < array.size(); i++) {
            set(new JsNumber(i),array.get(i));
        }
        return (TA) this;
    }

    public  <TA extends TypedArray> TA sort() {
        return sort(new function() {
            @Override
            public JsAny invoke(JsAny... arguments) {
                Double v1 = Onekit_JS.number(arguments[0],0,0).doubleValue();
                Double v2 =  Onekit_JS.number(arguments[1],0,0).doubleValue();
                return new JsNumber(v1.compareTo(v2));
            }
        });
    }
    public  <TA extends TypedArray> TA subarray(int begin, int end){
        JsArray array = new JsArray();
        for (int i = begin; i < end; i++) {
            array.add(get(i));
        }
        Class<TA> clazz = (Class<TA>) getClass();
        return TA._of(clazz, array);
    }
    public  <TA extends TypedArray> TA subarray(int begin){
        return subarray(begin,getLength().THIS.intValue());
    }
    public  <TA extends TypedArray> TA subarray(){
        return subarray(0);
    }


    public JsString toLocaleString(JsString locales, JsObject options) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <getLength().THIS.intValue(); i++) {
            JsAny element = this.get(i);
            if (i > 0) {
                result.append(",");
            }
            if (element == null) {
                result.append("null");
                continue;
            }
            String str = element.toString();
            result.append(str);
        }
        return new JsString( result.toString());
    }


    public  Iterator values() {
        return new Iterator(this.iterator()) {
            @Override
            public JsAny getValue(Object value) {
                return (JsAny) value;
            }

        };
    }

    @Override
    public JsAny get(JsAny key) {
        return null;
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
    public JsAny invoke(JsAny... arguments) {
        return null;
    }




    public JsString ToString(){
        return new JsString("TypedArray");
    }


}
