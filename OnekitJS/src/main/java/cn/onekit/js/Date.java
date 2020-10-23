package cn.onekit.js;

import android.icu.text.SimpleDateFormat;
import java.text.ParsePosition;

public class Date implements JsObject_ {

    private java.util.Date THIS;

    public JsNumber reduce(Date date) {
        return new JsNumber(THIS.getTime() - date.THIS.getTime());
    }

    private Date(java.util.Date THIS) {
        this.THIS = THIS;
    }

    ///////////////////////////////////////////////////////////
    public Date() {
        this(new java.util.Date());
    }
    public Date(JsObject_ value) {
        if(value instanceof JsNumber) {
            _Date(((JsNumber)value).THIS.longValue());
        }else if(value instanceof JsString){
            _Date(((JsString)value).THIS);
        }
    }
    private void _Date(long value) {
        THIS = new java.util.Date(value);
    }

    private void _Date(String dateString) {
        THIS =new java.util.Date(parse(dateString).THIS.longValue());
    }

    public Date(JsNumber year, JsNumber month, JsNumber day, JsNumber hours, JsNumber minutes, JsNumber seconds, JsNumber milliseconds) {
        _Date(new java.util.Date(year.THIS.intValue() >= 100 ? year.THIS.intValue() - 1900 : year.THIS.intValue(), month.THIS.intValue(), day.THIS.intValue(), hours.THIS.intValue(), minutes.THIS.intValue(), seconds.THIS.intValue()).getTime() + milliseconds.THIS.intValue());
    }

    public Date(JsNumber year, JsNumber monthIndex, JsNumber day, JsNumber hour, JsNumber minutes, JsNumber seconds) {
        this(year, monthIndex, day, hour, minutes, seconds, new JsNumber(0));
    }

    public Date(JsNumber year, JsNumber monthIndex, JsNumber day, JsNumber hour, JsNumber minutes) {
        this(year, monthIndex, day, hour, minutes, new JsNumber(0));
    }

    public Date(JsNumber year, JsNumber monthIndex, JsNumber day, JsNumber hour) {
        this(year, monthIndex, day, hour, new JsNumber(0));
    }

    public Date(JsNumber year, JsNumber monthIndex, JsNumber day) {
        this(year, monthIndex, day, new JsNumber(0));
    }

    public Date(JsNumber year, JsNumber monthIndex) {
        this(year, monthIndex, new JsNumber(1));
    }

    public Date(JsNumber year) {
        this(year, new JsNumber(0));
    }

    public static JsNumber now() {

        return new JsNumber(new Date().getTime());
    }

    public static JsNumber UTC(JsNumber year, JsNumber month, JsNumber date, JsNumber hrs, JsNumber min, JsNumber sec, JsNumber ms) {
        if (year.THIS.intValue() >= 100) {
            year.THIS = year.THIS.intValue()-1900;
        }
        java.util.Date localDate = new Date(year, month, date, hrs, min, sec, ms).THIS;
        return _date2utc(localDate);
    }

    private static JsNumber _date2utc(java.util.Date date) {
        long localTimeInMillis = date.getTime();
        return new JsNumber(localTimeInMillis + date.getTimezoneOffset() * 60 * 1000);
    }

    private static JsNumber _utc2date(java.util.Date date) {
        long localTimeInMillis = date.getTime();
        return new JsNumber(localTimeInMillis - date.getTimezoneOffset() * 60 * 1000);
    }

    public static JsNumber UTC(JsNumber year, JsNumber month, JsNumber date, JsNumber hrs, JsNumber min, JsNumber sec) {
        return UTC(year, month, date, hrs, min, sec, new JsNumber(0));
    }

    public static JsNumber UTC(JsNumber year, JsNumber month, JsNumber date, JsNumber hrs, JsNumber min) {
        return UTC(year, month, date, hrs, min, new JsNumber(0));
    }

    public static JsNumber UTC(JsNumber year, JsNumber month, JsNumber date, JsNumber hrs) {
        return UTC(year, month, date, hrs, new JsNumber(0));
    }

    public static JsNumber UTC(JsNumber year, JsNumber month, JsNumber date) {
        return UTC(year, month, date, new JsNumber(0));
    }

    public static JsNumber UTC(JsNumber year, JsNumber month) {
        return UTC(year, month, new JsNumber(1));
    }

    public static JsNumber UTC(JsNumber year) {
        return UTC(year, new JsNumber(0));
    }

    public static JsNumber parse(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat();

        String[] patterns = new String[]{
                "EEE, d MMM yyyy HH:mm:ss 'Z'",
                "yyyy-MM-dd'JsObject'HH:mm:ss.SSS'Z'",
                "yyyy-MM-dd'JsObject'HH:mm:ss",
                "yyyy-MM-dd'JsObject'HH:mm:ss.SSSXXX",
                "MMM dd, yy hh:mm:ss"};
        for (String pattern : patterns) {
            sdf.applyPattern(pattern);
            sdf.setLenient(false);//设置解析日期格式是否严格解析日期
            ParsePosition pos = new ParsePosition(0);
            java.util.Date date = sdf.parse(dateString, pos);
            if (date != null) {
                return new JsNumber(date.getTime());
            }
        }
        long tick = java.util.Date.parse(dateString);
        if (tick != 0) {
            return new JsNumber(tick);
        }
        throw new java.lang.Error(dateString);
    }

    public JsNumber getDate() {
        return new JsNumber(THIS.getDate());
    }

    public JsNumber getDay() {
        return new JsNumber(THIS.getDay());
    }

    public JsNumber getFullYear() {
        int year = THIS.getYear();
        if (year < 1900) {
            year += 1900;
        }
        return new JsNumber(year);
    }

    public JsNumber getHours() {
        return new JsNumber(THIS.getHours());
    }

    public JsNumber getMilliseconds() {
        int result = (int) (THIS.getTime() % 1000);
        if (result < 0) {
            result += 1000;
        }
        return new JsNumber(result);
    }

    public JsNumber getMinutes() {
        return new JsNumber(THIS.getMinutes());
    }

    public JsNumber getMonth() {
        return new JsNumber(THIS.getMonth());
    }

    public JsNumber getSeconds() {
        return new JsNumber(THIS.getSeconds());
    }

    public JsNumber getTime() {
        return new JsNumber(THIS.getTime());
    }

    public JsNumber getTimezoneOffset() {
        return new JsNumber(THIS.getTimezoneOffset());
    }

    public JsNumber getUTCDate() {
        return new Date(_date2utc(THIS)).getDate();
    }

    public JsNumber getUTCDay() {
        return new Date(_date2utc(THIS)).getDay();
    }

    public JsNumber getUTCFullYear() {
        return new Date(_date2utc(THIS)).getFullYear();
    }

    public JsNumber getUTCHours() {
        return new Date(_date2utc(THIS)).getHours();
    }

    public JsNumber getUTCMilliseconds() {
        return new Date(_date2utc(THIS)).getMilliseconds();
    }

    public JsNumber getUTCMinutes() {
        return new Date(_date2utc(THIS)).getMinutes();
    }

    public JsNumber getUTCMonth() {
        return new Date(_date2utc(THIS)).getMonth();
    }

    public JsNumber getUTCSeconds() {
        return new Date(_date2utc(THIS)).getSeconds();
    }

    //被重写为void
    public JsNumber setDate(JsNumber dayValue) {
        THIS.setDate(dayValue.THIS.intValue());
        return this.getTime();
    }

    public JsNumber setFullYear(JsNumber yearValue, JsNumber monthValue, JsNumber dayValue) {
        setYear(yearValue);
        setMonth(monthValue);
        setDate(dayValue);
        return this.getTime();
    }

    public JsNumber setFullYear(JsNumber yearValue, JsNumber monthValue) {
        return setFullYear(yearValue, monthValue, new JsNumber(1));
    }

    public JsNumber setFullYear(JsNumber yearValue) {
        return setFullYear(yearValue, new JsNumber(0));
    }

    public JsNumber setHours(JsNumber hoursValue, JsNumber minutesValue, JsNumber secondsValue, JsNumber msValue) {
        THIS.setHours(hoursValue.THIS.intValue());
        THIS.setMinutes(minutesValue.THIS.intValue());
        THIS.setSeconds(secondsValue.THIS.intValue());
        return setMilliseconds(msValue);
    }

    public JsNumber setHours(JsNumber hoursValue, JsNumber minutesValue, JsNumber secondsValue) {
        return setHours(hoursValue, minutesValue, secondsValue, getMilliseconds());
    }

    public JsNumber setHours(JsNumber hoursValue, JsNumber minutesValue) {
        return setHours(hoursValue, minutesValue, getSeconds());
    }

    //被重写为void
    public JsNumber setHours(JsNumber hoursValue) {
        return setHours(hoursValue, getMinutes());
    }

    private static java.util.Date _setMilliseconds(java.util.Date date, JsNumber millisecondsValue) {
        long ms = date.getTime();
        ms = ms - ms % 1000;
        ms += millisecondsValue.THIS.intValue();
        date.setTime(ms);
        return date;
    }

    public JsNumber setMilliseconds(JsNumber millisecondsValue) {
        THIS = _setMilliseconds(THIS, millisecondsValue);
        return this.getTime();
    }

    public JsNumber setMinutes(JsNumber minutesValue, JsNumber secondsValue, JsNumber msValue) {
        THIS.setMinutes(minutesValue.THIS.intValue());
        THIS.setSeconds(secondsValue.THIS.intValue());
        return setMilliseconds(msValue);
    }

    public JsNumber setMinutes(JsNumber minutesValue, JsNumber secondsValue) {
        return setMinutes(minutesValue, secondsValue, getMilliseconds());
    }

    //被重写方法为void
    public JsNumber setMinutes(JsNumber minutesValue) {
        return setMinutes(minutesValue, getSeconds());
    }

    public JsNumber setMonth(JsNumber monthValue, JsNumber dayValue) {
        THIS.setMonth(monthValue.THIS.intValue());
        THIS.setDate(dayValue.THIS.intValue());
        return this.getTime();
    }

    public JsNumber setMonth(JsNumber monthValue) {
        return setMonth(monthValue, getDate());
    }


    public JsNumber setSeconds(JsNumber secondsValue, JsNumber msValue) {
        THIS.setSeconds(secondsValue.THIS.intValue());
        return setMilliseconds(msValue);
    }

    public JsNumber setSeconds(JsNumber secondsValue) {
        return setSeconds(secondsValue, getMilliseconds());
    }

    public JsNumber setTime(long timeValue) {
        THIS.setTime(timeValue);
        return this.getTime();
    }

    public JsNumber setUTCDate(JsNumber dayValue) {
        java.util.Date utc = new java.util.Date(_date2utc(THIS).THIS.longValue());
        utc.setDate(dayValue.THIS.intValue());
        THIS = new java.util.Date(_utc2date(utc).THIS.longValue());
        return this.getTime();
    }

    public JsNumber setUTCFullYear(JsNumber yearValue, JsNumber monthValue, JsNumber dayValue) {
        if (yearValue.THIS.intValue() >= 99) {
            yearValue.THIS = yearValue.THIS.intValue()-1900;
        }
        java.util.Date utc = new java.util.Date(_date2utc(THIS).THIS.longValue());
        utc.setYear(yearValue.THIS.intValue());
        utc.setMonth(monthValue.THIS.intValue());
        utc.setDate(dayValue.THIS.intValue());
        THIS = new java.util.Date(_utc2date(utc).THIS.longValue());
        return this.getTime();
    }

    public JsNumber setUTCFullYear(JsNumber yearValue, JsNumber monthValue) {
        return setUTCFullYear(yearValue, monthValue, getUTCDay());
    }

    public JsNumber setUTCFullYear(JsNumber yearValue) {
        return setUTCFullYear(yearValue, getUTCMonth());
    }

    public JsNumber setUTCHours(JsNumber hoursValue, JsNumber minutesValue, JsNumber secondsValue, JsNumber msValue) {
        java.util.Date utc = new java.util.Date(_date2utc(THIS).THIS.longValue());
        utc.setHours(hoursValue.THIS.intValue());
        utc.setMinutes(minutesValue.THIS.intValue());
        utc.setSeconds(secondsValue.THIS.intValue());
        utc = _setMilliseconds(utc, msValue);
        THIS = new java.util.Date(_utc2date(utc).THIS.longValue());
        return new JsNumber(THIS.getTime());
    }

    public JsNumber setUTCHours(JsNumber hoursValue, JsNumber minutesValue, JsNumber secondsValue) {
        return setUTCHours(hoursValue, minutesValue, secondsValue, getUTCMilliseconds());
    }

    public JsNumber setUTCHours(JsNumber hoursValue, JsNumber minutesValue) {
        return setUTCHours(hoursValue, minutesValue, getUTCSeconds());
    }

    public JsNumber setUTCHours(JsNumber hoursValue) {
        return setUTCHours(hoursValue, getUTCMinutes());
    }

    public JsNumber setUTCMilliseconds(JsNumber millisecondsValue) {
        java.util.Date utc = new java.util.Date(_date2utc(THIS).THIS.longValue());
        utc = _setMilliseconds(utc, millisecondsValue);
        THIS = new java.util.Date(_utc2date(utc).THIS.longValue());
        return new JsNumber(THIS.getTime());
    }

    public JsNumber setUTCMinutes(JsNumber minutesValue, JsNumber secondsValue, JsNumber msValue) {
        java.util.Date utc = new java.util.Date(_date2utc(THIS).THIS.longValue());
        utc.setMinutes(minutesValue.THIS.intValue()) ;
        utc.setSeconds(secondsValue.THIS.intValue());
        utc = _setMilliseconds(utc, msValue);
        THIS = new java.util.Date(_utc2date(utc).THIS.longValue());
        return new JsNumber(THIS.getTime());
    }

    public JsNumber setUTCMinutes(JsNumber minutesValue, JsNumber secondsValue) {
        return setUTCMinutes(minutesValue, secondsValue, getUTCMilliseconds());
    }

    public JsNumber setUTCMinutes(JsNumber minutesValue) {
        return setUTCMinutes(minutesValue, getUTCSeconds());
    }

    public JsNumber setUTCMonth(JsNumber monthValue, JsNumber dayValue) {
        java.util.Date utc = new java.util.Date(_date2utc(THIS).THIS.longValue());
        utc.setMonth(monthValue.THIS.intValue());
        utc.setDate(dayValue.THIS.intValue());
        THIS = new java.util.Date(_utc2date(utc).THIS.longValue());
        return new JsNumber(THIS.getTime());
    }

    public JsNumber setUTCMonth(JsNumber monthValue) {
        return setUTCMonth(monthValue, getUTCDay());
    }

    public JsNumber setUTCSeconds(JsNumber secondsValue, JsNumber msValue) {
        java.util.Date utc = new java.util.Date(_date2utc(THIS).THIS.longValue());
        utc.setSeconds(secondsValue.THIS.intValue());
        utc = _setMilliseconds(utc, msValue);
        THIS = new java.util.Date(_utc2date(utc).THIS.longValue());
        return new JsNumber(THIS.getTime());
    }

    public JsNumber setUTCSeconds(JsNumber secondsValue) {
        return setUTCSeconds(secondsValue, getUTCMilliseconds());
    }

    public JsNumber setYear(JsNumber yearValue) {
        if (yearValue.THIS.intValue() >= 99) {
            yearValue.THIS =   yearValue.THIS.intValue() -1900;
        }
        THIS.setYear(yearValue.THIS.intValue());
        return this.getTime();
    }

    public JsString toDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d yyyy");
        return new JsString(sdf.format(THIS));

    }

    //重写方法
    public JsString toGMTString() {
        return new JsString(THIS.toGMTString());
    }

    public JsString toISOString() {
        Date utc = new Date(_date2utc(THIS));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'JsObject'HH:mm:ss.SSSZ");
        return new JsString(sdf.format(utc.THIS));
    }


    public JsString toJSON() {
        return toISOString();
    }

    public JsString toLocaleDateString(String locales, JsObject options) {
        return new JsString("");
    }

    public JsString toLocaleDateString(String locales) {
        return toLocaleDateString(locales, new JsObject());
    }

    public JsString toLocaleDateString() {
        return toLocaleDateString("");
    }

    public JsString toLocaleFormat(JsObject_ formatString) {
        return new JsString("");
    }

    public JsString toLocaleString(JsObject_ locales, JsObject_ options) {
        return new JsString("");
    }

    public JsString toLocaleTimeString(JsObject_ locales, JsObject_ options) {
        return new JsString("");
    }

    public JsString toSource() {
        return null;
    }

    public JsString ToString() {

        return new JsString(new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss 'GMT'Z").format(THIS));
    }

    @Override
    public String toLocaleString(JsString locales, JsObject_ options) {
        return null;
    }

    @Override
    public JsObject_ invoke(JsObject_... params) {
        return null;
    }

    public JsString toTimeString() {
        return null;
    }

    public JsString toUTCString() {
        return new JsString(new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss 'GMT'").format(THIS));
    }

    public JsNumber getYear() {
        return new JsNumber(THIS.getYear());
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
/*
    public NUMBER valueOf(){
        return _THIS.getTime();
    }
*/

}