package cn.onekit.js;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cn.onekit.js.core.Iterator;
import cn.onekit.js.core.Onekit_JS;
import cn.onekit.js.core.function;

public class JsArray extends ArrayList<JsObject_> implements JsObject_ {

    public JsArray(List<JsObject_> subList) {
        super(subList);
    }

    /////////////////////////////////////////
    public int getLength() {
        return this.size();
    }

    private void _setLength(long length) {
        if (length < 0 || length >= 4294967296L) {
            throw new RangeError(new JsString("Invalid array length"));
        }
        if (length > this.size()) {
            return;
        }
        for (long i = length; i <= this.size(); i++) {
            this.remove(this.size() - 1);
        }
    }

    public void setLength(JsObject_ length) {
        int length_ = Onekit_JS.number(length, 0, 0).intValue();
        _setLength(length_);
    }

    int _hashCode = new Random().nextInt();

    @Override
    public int hashCode() {
        return _hashCode;
    }

    private static int _index(JsArray array, int index) {
        if (index >= 0) {
            return index;
        }
        return array.size() + index;
    }

    public JsString ToString() {

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < size(); i++) {
            JsObject_ element = get(i);
            if (i > 0) {
                result.append(",");
            }
            result.append(element == null ? "empty" : Onekit_JS.toString(element));

        }
        result.append("]");
        return new JsString(result.toString());
    }

    @Override
    public String toLocaleString(JsString locales, JsObject_ options) {
        return null;
    }

    @Override
    public JsObject_ invoke(JsObject_... params) {
        return null;
    }

    @Override
    public JsObject_ get(String key) {
        return null;
    }

    //////////////////////////////////////
    public static JsArray from(JsObject_ arrayLike, JsObject_ mapFn, JsObject_ thisArg) {
        function fn = (function) mapFn;
        if (arrayLike instanceof JsArray) {
            return from((JsArray) arrayLike, fn, thisArg);
        } else if (arrayLike instanceof JsString) {
            return from(((JsString) arrayLike).THIS, fn, thisArg);
        } else {
            return null;
        }
    }

    private static JsArray from(JsArray array, JsObject_ mapFn, JsObject_ thisArg) {
        JsArray result = new JsArray();
        for (JsObject_ element : array) {
            if (mapFn != null) {
                result.add(mapFn.invoke(element));
            } else {
                result.add(element);
            }
        }
        return result;
    }

    private static JsArray from(String arrayLike, JsObject_ mapFn, JsObject_ thisArg) {
        return from(Onekit_JS.string2Array(arrayLike), mapFn, thisArg);
    }


    //
    public static boolean isArray(JsObject_ obj) {
        return obj instanceof JsArray;
    }

    //
    public static JsArray of(JsObject_... elements) {
        JsArray result = new JsArray();
        Collections.addAll(result, elements);
        return result;
    }

    /////////////////////////////////////////
    public JsArray() {
    }

    public JsArray(JsObject_ length) {
        if (length == null) {
            add(null);
            return;
        }
        if (Onekit_JS.isNumber(length)) {
            long length_ = ((JsNumber) length).THIS.longValue();
            if (length_ <= 0 || length_ >= 4294967296L) {
                throw new Error(new JsString("Invalid array length"));
            }
            for (int i = 0; i < length_; i++) {
                this.add(null);
            }
        } else {
            add(length);
        }
    }

    public JsArray(JsObject_... args) {

        Collections.addAll(this, args);

    }
    /////////////////////////////////////////////////

    public JsArray concat(JsObject_... values) {
        JsArray RESULT = (JsArray) this.clone();
        for (JsObject_ value : values) {
            if (value instanceof JsArray) {
                JsArray array = (JsArray) value;
                RESULT.addAll(array);
            } else {
                RESULT.add(value);
            }
        }
        return RESULT;
    }

    //
    private JsArray _copyWithin(int target, int start, int end) {
        target = _index(this, target);
        start = _index(this, start);
        end = _index(this, end);
        JsArray result = new JsArray();
        result.addAll(this);
        for (int i = start, j = 0; i < end && i < result.size() && target + j < result.size(); i++, j++) {
            result.set(target + j, this.get(i));
        }
        return result;
    }

    public JsArray copyWithin(int target, int start, int end) {
        target = _index(this, target);
        start = _index(this, start);
        end = _index(this, end);
        JsArray result = new JsArray();
        result.addAll(this);
        for (int i = start, j = 0; i < end && i < result.size() && target + j < result.size(); i++, j++) {
            result.set(target + j, this.get(i));
        }
        return result;
    }

    public JsArray copyWithin(int target, int start) {
        return copyWithin(target, start, this.size() - 1);
    }

    public JsArray copyWithin(int target) {
        return copyWithin(target, 0);
    }
    //

    public Iterator entries() {
        return new Iterator(this.iterator()) {


            @Override
            public JsObject_ getValue(Object value) {
                return new JsArray() {{
                    add(new JsNumber(index++));
                    add((JsObject_) value);
                }};
            }

            int index = 0;

        };
    }

    //
    public boolean every(function callback, JsObject_ thisArg) {
        callback.thisArg = thisArg;
        for (int i = 0; i < size(); i++) {
            JsObject_ element = get(i);
            if (element == null) {
                continue;
            }

            if (!Onekit_JS.is(callback.invoke(element, new JsNumber(i), this))) {
                return false;
            }
        }
        return true;
    }

    public boolean every(function callback) {
        return every(callback, null);
    }


    //
    private JsArray _fill(JsObject_ value, int start, int end) {
        start = _index(this, start);
        end = _index(this, end);
        for (int i = start; i >= 0 && i < end && i < size(); i++) {
            set(i, value);
        }
        return this;
    }

    public JsArray fill(JsObject_ value, JsObject_ start, JsObject_ end) {
        int start_ = Onekit_JS.number(start, 0, 0).intValue();
        int end_ = Onekit_JS.number(end, 0, 0).intValue();
        return _fill(value, start_, end_);
    }

    public JsArray fill(JsObject_ value, JsObject_ start) {
        return fill(value, start, new JsNumber(this.size()));
    }

    public JsArray fill(JsObject_ value) {
        return fill(value, new JsNumber(0));
    }

    //
    public JsArray filter(function callback, JsObject_ thisArg) {
        callback.thisArg = thisArg;
        JsArray result = new JsArray();
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
            if (element == null) {
                continue;
            }
            if (Onekit_JS.is(callback.invoke(element))) {
                result.add(element);
            }
        }
        return result;
    }

    public JsArray filter(function callback) {
        return filter(callback, null);
    }

    //
    public JsObject_ find(function callback, JsObject_ thisArg) {
        callback.thisArg = thisArg;
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
            if (Onekit_JS.is(callback.invoke(element, new JsNumber(i), this))) {
                return element;
            }
        }
        return null;
    }

    public JsObject_ find(function callback) {
        return find(callback, null);
    }

    //
    public Integer findIndex(function callback, JsObject_ thisArg) {
        callback.thisArg = thisArg;
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
            if (element == null) {
                continue;
            }
            if (Onekit_JS.is(callback.invoke(element, new JsNumber(i), this))) {
                return i;
            }
        }
        return -1;
    }

    public Integer findIndex(function callback) {
        return findIndex(callback, null);
    }

    //
    private JsArray _flat(int depth, int current) {
        JsArray result = new JsArray();
        for (JsObject_ element : this) {
            if (element instanceof JsArray && current < depth) {
                JsArray array = (JsArray) element;
                result = result.concat(array._flat(depth, current + 1));
            } else {
                result.add(element);
            }
        }
        return result;
    }

    public JsArray flat(int depth) {
        return _flat(depth, 0);
    }

    public JsArray flat() {
        return flat(1);
    }

    //
    public JsArray flatMap(function callback, JsObject_ thisArg) {
        callback.thisArg = thisArg;
        JsArray result = new JsArray();
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
            if (element == null) {
                continue;
            }
            result.add(callback.invoke(element, new JsNumber(i), this).get(new JsNumber(0)));
        }
        return result;
    }

    public JsArray flatMap(function callback) {
        return flatMap(callback, null);
    }

    //
    public void forEach(function callback, JsObject_ thisArg) {
        callback.thisArg = thisArg;
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
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
    public JsBoolean includes(JsObject_ valueToFind, JsObject_ fromIndex) {
        int index = Onekit_JS.number(fromIndex, 0, 0).intValue();

        for (int i = index; i < size(); i++) {
            if (i < 0) {
                continue;
            }
            JsObject_ element = this.get(i);
            if (element.equals(valueToFind)) {
                return new JsBoolean(true);
            }
        }
        return new JsBoolean(false);
    }

    //
    public JsNumber indexOf(JsObject_ searchElement, JsObject_ fromIndex) {
        int f = Onekit_JS.number(fromIndex, 0, 0).intValue();
        f = _index(this, f);
        for (int i = f; i < size(); i++) {
            JsObject_ temp = get(i);
            if (searchElement.equals(temp)) {
                return new JsNumber(i);
            }
        }
        return new JsNumber(-1);
    }

    public JsNumber indexOf(JsObject_ searchElement) {
        return indexOf(searchElement, new JsNumber(0));
    }

    //
    private String _join(String separator) {
        return StringUtils.join(this, separator);
    }

    public JsString join(JsObject_ separator) {
        return new JsString(_join(separator.toString()));
    }

    public JsString join() {
        return join(new JsString(","));
    }

    public Iterator keys() {
        return new Iterator(this.iterator()) {

            @Override
            public JsObject_ getValue(Object value) {
                return new JsNumber(index++);
            }

            int index = 0;
        };
    }

    public int lastIndexOf(JsObject_ searchElement, int fromIndex) {
        fromIndex = _index(this, fromIndex);
        for (int i = fromIndex; i >= 0; i--) {
            JsObject_ temp = get(i);
            if (searchElement.equals(temp)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(JsObject_ searchElement) {
        return lastIndexOf(searchElement, size() - 1);
    }

    //
    public JsArray map(JsObject_ callback, JsObject_ thisArg) {
        ((function)callback).thisArg = thisArg;
        JsArray result = new JsArray();
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
            if (element == null) {
                continue;
            }
            result.add(callback.invoke(element));
        }
        return result;
    }

    public JsArray map(JsObject_ callback) {
        return map(callback, null);
    }
    //

    public JsObject_ pop() {
        if (this.size() == 0) {
            return null;
        }
        return remove(this.size() - 1);
    }

    public JsNumber push(JsObject_... elements) {
        if (elements == null) {
            add(null);
        } else {
            this.addAll(Arrays.asList(elements));
        }
        return new JsNumber(this.size());
    }

    public JsObject_ reduce(function callback, JsObject_ initialValue) {
        boolean flag = (initialValue == null);
        if (flag) {
            initialValue = get(0);
        }
        for (int i = (flag ? 1 : 0); i < size(); i++) {
            JsObject_ element = this.get(i);
            if (element == null) {
                continue;
            }
            initialValue = callback.invoke(initialValue, element, new JsNumber(i), this);
        }
        return initialValue;
    }

    public JsObject_ reduce(function callback) {
        return reduce(callback, null);
    }

    public JsObject_ reduceRight(function callback, JsObject_ initialValue) {
        boolean flag = initialValue == null;
        if (flag) {
            initialValue = get(size() - 1);
        }
        for (int i = size() - (flag ? 2 : 1); i >= 0; i--) {
            JsObject_ element = this.get(i);
            if (element == null) {
                continue;
            }
            initialValue = callback.invoke(initialValue, element, new JsNumber(i), this);
        }
        return initialValue;
    }

    public JsObject_ reduceRight(function callback) {
        return reduceRight(callback, null);
    }

    public JsArray reverse() {
        JsArray temp = new JsArray();
        for (int i = size() - 1; i >= 0; i--) {
            temp.add(get(i));
        }
        clear();
        addAll(temp);
        return this;
    }

    public JsObject_ shift() {
        return this.remove(0);
    }

    private JsArray _slice(int start, int end) {
        return new JsArray(this.subList(start, end));
    }

    public JsArray slice(JsObject_ start, JsObject_ end) {
        int start_ = Onekit_JS.number(start, 0, 0).intValue();
        int end_ = Onekit_JS.number(end, 0, 0).intValue();
        return _slice(start_, end_);
    }

    public JsArray slice(JsObject_ start) {
        return slice(start, new JsNumber(this.size() - 1));
    }

    public JsArray slice() {
        return slice(new JsNumber(0));
    }

    public boolean some(function callback, JsObject_ thisArg) {
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
            if (element == null) {
                continue;
            }
            if (Onekit_JS.is(callback.invoke(element, new JsNumber(i), this))) {
                return true;
            }
        }
        return false;
    }

    public boolean some(function callback) {
        return some(callback, null);
    }

    public JsArray sort(function compareFunction) {
        Collections.sort(this, (o1, o2) -> ((JsNumber) compareFunction.invoke(o1, o2)).THIS.intValue());
        return this;
    }

    public JsArray sort() {

        Collections.sort(this, (o1, o2) -> {
            String str1 = o1.toString();
            String str2 = o2.toString();
            str1 = str1.length() > 0 ? str1.substring(0, 1) : "";
            str2 = str2.length() > 0 ? str2.substring(0, 1) : "";
            return str1.compareTo(str2);
        });
        return this;
    }

    public JsArray splice(int start, int deleteCount, JsObject_... items) {
        JsArray result = new JsArray(subList(start, start + deleteCount));
        this.removeRange(start, start + deleteCount);
        for (JsObject_ element : items) {
            this.add(start++, element);
        }
        return result;
    }

    public JsArray splice(int start) {
        return splice(start, size() - start);
    }

    public String toLocaleString(JsObject_ locales, JsObject options) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            JsObject_ element = this.get(i);
            if (i > 0) {
                result.append(",");
            }
            if (element == null) {
                result.append("null");
                continue;
            }
            String str;
            if (element instanceof JsObject) {
                str = ((JsObject) element).toLocaleString(locales, options).THIS;
            } else if (Onekit_JS.isNumber(element)) {
                str = ((JsNumber) element).toLocaleString(locales, options).THIS;
            } else if (element instanceof Date) {
                str = ((Date) element).toLocaleString(locales, options).THIS;
            } else {
                str = element.toString();
            }
            result.append(str);
        }
        return result.toString();
    }


    //
    public int unshift(JsObject_... elements) {
        int i = 0;
        for (JsObject_ element : elements) {
            this.add(i++, element);
        }
        return this.size();
    }

    public Iterator values() {
        return new Iterator(this.iterator()) {

            @Override
            public JsObject_ getValue(Object value) {
                return (JsObject_) value;
            }

        };
    }

    @Override
    public JsObject_ get(JsObject_ key) {
        int index = Onekit_JS.number(key, 0, 0).intValue();
        return super.get(index);
    }

    @Override
    public void set(String key, JsObject_ value) {
    }

    @Override
    public void set(JsObject_ key, JsObject_ value) {
        int index = Onekit_JS.number(key, 0, 0).intValue();
        set(index, value);
    }
}