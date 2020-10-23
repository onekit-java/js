package cn.onekit.js;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.onekit.js.core.Onekit_JS;

public class RegExp implements JsObject_ {

    public String getPattern() {
        return THIS.pattern();
    }

    @Override
    public JsObject_ get(String key) {
        return null;
    }

    @Override
    public JsObject_ get(JsObject_ key) {
        return null;
    }

    @Override
    public void set(String key, JsObject_ value) {

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

    public static class Match implements JsObject_ {
        JsArray _finds;

        @Override
        public JsObject_ get(String key) {
            return null;
        }

        public JsObject_ get(JsObject_ index) {
            return _finds.get(index);
        }

        @Override
        public void set(String key, JsObject_ value) {

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

        JsArray _groups;

        public JsArray getGroups() {
            return _groups;
        }

        int _index;

        public int getIndex() {
            return _index;
        }

        String _input;

        public String getInput() {
            return _input;
        }

        int _length;

        public int getLength() {
            return _length;
        }

        public Match(JsArray finds, JsArray groups, int index, String input, int length) {
            _finds = finds;
            _groups = groups;
            _index = index;
            _input = input;
            _length = length;
        }

        @NonNull
        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("[");
            for(int i=0;i<getLength();i++) {
                if(i>0){
                    result.append(",");

                }
                result.append(String.format("%d:\"%s\"", i,get(new JsNumber(i))));
            }
            result.append("]");
            result.append(String.format("groups:%s,", Onekit_JS.toString(getGroups())));
            result.append(String.format("index:%d,", getIndex()));
            result.append(String.format("input:\"%s\",", getInput()));
            result.append(String.format("length:%d", getLength()));
            return result.toString();
        }
    }
    ////////////////////////////////////////
    public static JsArray $s=new JsArray();
    static JsObject $=new JsObject();
    public Pattern THIS;
    String _flags;
    int _init(String flags){
        int flag = 0;
        if (flags == null) {
            flags = "";
        }
        for (char f : flags.toCharArray()) {
            switch (f) {
                case 'i':
                    flag += Pattern.CASE_INSENSITIVE;
                    break;
                case 'm':
                    flag += Pattern.MULTILINE;
                    break;
                case 'u':
                    flag += Pattern.UNICODE_CASE;
                    break;
                case 's':
                    flag += Pattern.DOTALL;
                    break;
                default:
                    break;
            }
        }
        char[] array = flags.toCharArray();
        Arrays.sort(array);
        _flags = new String(array);
        return flag;
    }
    public RegExp(String pattern,String flags) {
        int flag = _init(flags);
        THIS = Pattern.compile(pattern, flag);
    }
    public RegExp(String pattern){
        this(pattern,null);
    }
    public static JsObject_ _get(String key) {
        return $.get(key);
    }
    /////////////////
    public static JsObject_ get$1() {
        return $s.get(new JsNumber(0));
    }
    public static JsObject_ get$2() {
        return $s.get(new JsNumber(1));
    }
    public static JsObject_ get$3() {
        return $s.get(new JsNumber(2));
    }
    public static JsObject_ get$4() {
        return $s.get(new JsNumber(3));
    }
    public static JsObject_ get$5() {
        return $s.get(new JsNumber(4));
    }
    public static JsObject_ get$6() {
        return $s.get(new JsNumber(5));
    }
    public static JsObject_ get$7() {
        return $s.get(new JsNumber(6));
    }
    public static JsObject_ get$8() {
        return $s.get(new JsNumber(7));
    }
    public static JsObject_ get$9() {
        return $s.get(new JsNumber(8));
    }
    public static JsObject_ getInput() {
        return _get("$_");
    }
    public static JsObject_ getLastMatch() {
        return $.get("$&");
    }
    public static JsObject_ getLastParen() {
        return $.get("$+");
    }
    public static JsObject_ getLeftContext() {
        return $.get("$`");
    }
    public  String getFlags() {
        return _flags;
    }
    public  final boolean getGlobal(){
        return getFlags().contains("g");
    }
    public  final boolean getIgnoreCase(){
     return getFlags().contains("i");
    }
    public  final boolean getMultiline(){
     return getFlags().contains("m");
    }
   /* public  String getSource(){
        return THIS.pattern();
    }*/
    public  final boolean getSticky(){
        return getFlags().contains("y");
    }
    public  final boolean getUnicode(){
        return getFlags().contains("u");
    }
    public static final JsObject_ getRightContext(){
        return $.get("$'");
    }
    int _lastIndex=0;
    public int getLastIndex(){
        return _lastIndex;
    }
    //////////////////////////////////////////
    public void compile(String pattern,String flags){
        int flag = _init(flags);
        THIS = Pattern.compile(pattern, flag);
    }
    public JsArray exec(String str){
        JsArray result = null;
        Matcher matcher = THIS.matcher(str);
        if (matcher.find(_lastIndex)) {
            if(result==null){
                result=new JsArray();
            }
            _lastIndex = matcher.end();
            for (int i = 0; i <= matcher.groupCount(); i++) {
                String group = matcher.group(i);
                result.add(new JsString(group));
            }
        }
        return result;
    }
    public  boolean test(String str) {

        Matcher matcher = THIS.matcher(str);
        boolean result = false;
        while (matcher.find()) {
            result = true;
            $.put("$_", new JsString(str));
            $.put("$`", new JsString(str.substring(0, matcher.start())));
            $.put("$'", new JsString(str.substring(matcher.end())));
            for (int i = 0; i <= matcher.groupCount(); i++) {
                String group = matcher.group(i);
                $.put("$&",  new JsString(group));
                $.put("$+",  new JsString(group));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("/%s/%s",THIS,getFlags());
    }

}
