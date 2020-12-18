package cn.onekit.js.core;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.onekit.JsAny;
import cn.onekit.js.JSON;
import cn.onekit.js.JsArray;
import cn.onekit.js.console;
import cn.onekit.js.JsObject;
import cn.onekit.js.Error;
import cn.onekit.js.JsBoolean;
import cn.onekit.js.JsNumber;
import cn.onekit.js.JsString;
import cn.onekit.js.Map;
import cn.onekit.js.Null;
import cn.onekit.js.Symbol;
import cn.onekit.js.URIError;
import cn.onekit.js.Undefined;
import cn.onekit.js.function;

public interface JsFile {

    HashMap<Integer,HashMap<String,function>> allOverrides = new HashMap();
    default void setOverride(String clazz, String method, function function){
        if(!allOverrides.containsKey(this.hashCode())){
            allOverrides.put(hashCode(), new HashMap());
        }
        HashMap<String,function> overrides = allOverrides.get(hashCode());
        overrides.put(String.format("%s.%s",clazz,method),function);
    }
    default function getOverride(String clazz, String method){
        return allOverrides.get(hashCode()).get(String.format("%s.%s",clazz,method));
    }
    //////////////////////////////////////
    HashMap<Integer,HashMap<String,function>> allPrototypes = new HashMap();
    default void setPrototype(String clazz, String method, function function){
        if(!allPrototypes.containsKey(this.hashCode())){
            allPrototypes.put(hashCode(), new HashMap());
        }
        HashMap<String,function> prototypes = allOverrides.get(hashCode());
        prototypes.put(String.format("%s.%s",clazz,method),function);
    }
    default function getPrototype(String clazz, String method, JsAny THIS){
        function function =  allPrototypes.get(hashCode()).get(String.format("%s.%s",clazz,method));
        function.thisArg = THIS;
        return function;
    }
    /////////////////////////////////////
    default String $(String format, JsObject args) {
        for (JsObject.Entry<String,JsAny> entry : args.entrySet()) {
            String str = String.format("${%s}", entry.getKey());
            format = format.replace(str, JSON.stringify(entry.getValue()).THIS);
        }
        return format;
    }

    ////////////////////////////////////

    default String typeof(JsAny obj) {
        if (obj == null) {
            return ((JsString)new Undefined().ToString()).THIS;
        }
        if (obj instanceof Null) {
            return "obj";
        }
        return obj.getClass().getSimpleName();
    }

    JsAny NaN = JsNumber.NaN;
    JsAny undefined = new Undefined();
    JsAny Infinity = JsNumber.POSITIVE_INFINITY;
    JsAny Null = new Null();

    /////////////////////////////////////
    default JsAny Number(JsAny value) {
        return JsNumber.Number(value);
    }

    default JsAny Boolean(JsAny value) {
        return new JsBoolean(value);
    }

    default Error Error(JsAny message) {
        return new Error(message);
    }

    default JsArray Array(JsAny length) {
        return new JsArray(length);
    }

    default  Map Map(JsArray map) {
        Map result = new Map();
        for (JsAny temp : map) {
            JsArray item  = (JsArray) temp;
            result.set(item.get(0), item.get(1));
        }
        return result;
    }

    ///////////////////////////////////////////
    default String decodeURI(String url) {
        return URLDecoder.decode(url);
    }

    default String decodeURIComponent(String url) {
        return URLDecoder.decode(url);
    }

    default String encodeURI(String url) {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new URIError(new JsString("Uncaught URIError: URI malformed"));
        }
    }

    default String encodeURIComponent(String url) {

        try {
            return URLEncoder.encode(url, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e) {
            throw new URIError(new JsString("Uncaught URIError: URI malformed"));
        }
    }
     default boolean is(Object obj) {
        return Onekit_JS.is(obj);
    }
    String ascii = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890";

    default String escape(String src) {
        StringBuilder result = new StringBuilder();
        for (char chr : src.toCharArray()) {
            String str = String.valueOf(chr);
            if (ascii.contains(str)) {
                result.append(str);
                continue;
            }
            if ("@*_+-./".contains(str)) {
                result.append(str);
                continue;
            }
            StringBuilder s16 = new StringBuilder(Long.toHexString(chr).toUpperCase());
            if (s16.length() > 2) {
                while (s16.length() < 4) {
                    s16.insert(0, "0");
                }
                s16.insert(0, "%u");
            } else {
                while (s16.length() < 2) {
                    s16.insert(0, "0");
                }
                s16.insert(0, "%");
            }
            result.append(s16);
        }
        return result.toString();
    }

    default JsAny isFinite(JsAny testValue) {
        return JsNumber.isFinite(testValue);

    }

    default JsAny isNaN(JsAny v) {
        return JsNumber.isNaN(v);
/*
        if (v == null) {
            return true;
        }
        if (v instanceof Null) {
            return true;
        }
        Object value = ((Number)v)._object;
        if (value instanceof Number) {
            Number number = (Number) value;
            return Double.valueOf(number.doubleValue()).equals(NUMBER.NaN);
        }
        if (value instanceof String) {

            String aString = (String) value;
            if (aString.equals("NaN")) {
                return true;
            }
            if (aString.equals("")) {
                return false;
            }
            int count = 0;
            for (char chr : aString.toCharArray()) {
                if (chr == ' ') {
                    count++;
                }
            }
            if (count == aString.length()) {
                return false;
            }
        }

        if (value instanceof java.lang.Boolean) {
            return false;
        }
        return !OnekitJS.isNumber(value);*/
    }

    default Double parseFloat(JsAny aString) {
        try {
            Pattern pattern = Pattern.compile("^[+-]?[\\d]+([.][\\d]*)?([Ee][+-]?[\\d]+)?$");
            Matcher matcher = pattern.matcher(aString.toString().trim());
            StringBuilder str = new StringBuilder();
            boolean flag = false;
            while (matcher.find()) {
                str.append(matcher.group());
                flag = true;
            }
            if (!flag) {
                pattern = Pattern.compile("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])");
                matcher = pattern.matcher(aString.toString().trim());
                while (matcher.find()) {
                    str.append(matcher.group());
                }
            }
            return Double.parseDouble(str.toString());
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    default JsAny parseInt(JsAny aString, JsAny radix) {
        try {
            int flag;
            if (radix == null || !Onekit_JS.isNumber(radix)) {
                flag = 10;
            } else {
                flag = Onekit_JS.number(radix,0,0).intValue();
            }
            String str = aString.toString().trim();
            Long result;
            if (str.startsWith("0x")) {
                result = new BigInteger(str.substring(2), flag).longValue();
            } else if (str.startsWith("0b")) {
                result = new BigInteger(str.substring(2), flag).longValue();
            } else if (str.startsWith("0o")) {
                result = new BigInteger(str.substring(2), flag).longValue();
            } else {
                result = new BigInteger(str, flag).longValue();
            }
            return  new JsNumber(result);
        } catch (Exception e) {
            return new JsString(NaN.toString());
        }
    }


    default String unescape(String src) {
        StringBuilder tmp = new StringBuilder();
        tmp.ensureCapacity(src.length());
        int lastPos = 0;
        char ch;
        while (lastPos < src.length()) {
            int pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /////////////////////////////////////
      console console = new console();
    /////////////////////////////////////


    default Class getClass(JsAny obj) {
        return obj.getClass();
    }


    default Float NUMBER(String aString) {
        try {
            return Float.parseFloat(aString);
        } catch (Exception e) {
            return null;
        }
    }


    default String String(String aString, JsAny... vars) {
        return aString;
    }

    //
   /* default String typeOf(JsObject obj) {
        return obj.getClass().getSimpleName().toLowerCase();
    }*/

    ///////////////////////////

    default Symbol Symbol(JsAny description) {
        return new Symbol(description);
    }
    default Symbol Symbol() {
        return new Symbol();
    }


    ////////////////////
    @SuppressLint("UseSparseArrays")
    HashMap<Long, function> _timeouts = new HashMap();

    default long setTimeout(JsAny function, JsAny delay, JsAny... params) {

        long id = new Random().nextLong();
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (_timeouts.containsKey(id)) {
                    _timeouts.get(id).invoke(params);
                }
            }
        };
        handler.sendEmptyMessageDelayed(0, Onekit_JS.number(delay,0,0).longValue());
        _timeouts.put(id, (cn.onekit.js.function) function);
        return id;

    }

    default long setTimeout(JsAny function) {
        return setTimeout(function, new JsNumber(0));
    }

    default void clearTimeout(long id) {

        if (!_timeouts.containsKey(id)) {
            return;
        }
        _timeouts.remove(id);
    }

 //
    HashMap<Long, Timer> _intervals = new HashMap();
    default long setInterval(JsAny function, JsAny delay, JsAny... params) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                function.invoke(params);
            }
        };

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                handler.sendMessage(message);
            }
        };
        long id = timer.hashCode();
        long delay_ = Onekit_JS.number(delay,0,0).longValue();
        timer.schedule(timerTask, delay_, delay_);
        _intervals.put(id, timer);
        return id;
    }

    default void clearInterval(long id) {

        if (!_intervals.containsKey(id)) {
            return;
        }
        _intervals.get(id).cancel();
        _intervals.remove(id);
    }
    ////////////////////////
    default JsAny or(JsAny a, JsAny b) {
        return a != null ? a : b;
    }
    default JsAny fullequals(JsAny a, JsAny b) {
        if(a==null && b==null){
            return new JsBoolean(true);
        }
        if(a==null && b!=null){
            return new JsBoolean(false);
        }
        if(a!=null && b==null){
            return new JsBoolean(false);
        }
        return new JsBoolean(a.getClass().getName().equals(b.getClass().getName()) && a.equals(b));
    }
    default JsAny quals(JsAny a, JsAny b) {
        if(a==null && b==null){
            return new JsBoolean(true);
        }
        if(a==null && b!=null){
            return new JsBoolean(false);
        }
        if(a!=null && b==null){
            return new JsBoolean(false);
        }
        return new JsBoolean( a.equals(b));
    }
    default JsAny plus(JsAny a, JsAny b) {
        if (Onekit_JS.isNumber(a) && Onekit_JS.isNumber(b)) {
            return new JsNumber(((JsNumber) a).THIS.doubleValue() + ((JsNumber) b).THIS.doubleValue());
        } else {
            return new JsString(a.toString() + b.toString());
        }
    }
    default JsAny minus(JsAny a, JsAny b) {
        return new JsNumber(((JsNumber) a).THIS.doubleValue() - ((JsNumber) b).THIS.doubleValue());

    }
    default JsAny times(JsAny a, JsAny b) {
        return new JsNumber(((JsNumber) a).THIS.doubleValue() * ((JsNumber) b).THIS.doubleValue());

    }
    default JsAny div(JsAny a, JsAny b) {
        return new JsNumber(((JsNumber) a).THIS.doubleValue() / ((JsNumber) b).THIS.doubleValue());

    }
}
