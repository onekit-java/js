package cn.onekit.js;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.onekit.js.JsAny;
import cn.onekit.js.core.Onekit_JS;

public class Intl  {
    static List<String> allLocale;

    static {
        allLocale = new ArrayList<>();
        for (java.util.Locale local : java.util.Locale.getAvailableLocales()) {
            allLocale.add(local.toString().replace("_", "-").toUpperCase());
        }
    }

    public static JsArray getCanonicalLocales(JsArray locales) {
        JsArray result = new JsArray();
        for (JsAny temp : locales) {
            String locale = ((JsString)temp).THIS;
            if (!allLocale.contains(locale.toUpperCase())) {
                throw new RangeError(new JsString(String.format("invalid language tag: %s", locale)));
            }
            String str;
            if (locale.contains("-")) {
                String[] language_nation = locale.split("-");
                String language = language_nation[0].toLowerCase();
                String nation = language_nation[1].toUpperCase();
                str = String.format("%s-%s", language, nation);
            } else {
                str = locale.toLowerCase();
            }
            result.add(new JsString(str));
        }
        return result;
    }

    public static JsArray getCanonicalLocales(String locale) {
        return getCanonicalLocales(new JsArray() {{
            add(new JsString(locale));
        }});
    }
    //////////////////////////////

    public static class Locale extends JsObject {







        @Override
        public JsAny invoke(JsAny... arguments) {
            return null;
        }

        public static class Options extends JsObject {

            public Options(String locale, JsObject options) {
                _locale=locale;
                for (Map.Entry<String, JsAny> entry:options.entrySet()){
                    JsAny value = entry.getValue();
                    switch (entry.getKey()){
                        case "localeMatcher":_localeMatcher=   ((JsString)value).THIS;break;
                        case "usage":_usage=  ((JsString)value).THIS;break;
                        case "sensitivity":_sensitivity=  ((JsString)value).THIS;break;
                        case "ignorePunctuation":_ignorePunctuation= ((JsBoolean)value).THIS;break;
                        case "numeric":_numeric=   ((JsString)value).THIS;break;
                        case "caseFirst":_caseFirst=  ((JsString)value).THIS;break;
                        default:break;
                    }
                }
            }

            private String _localeMatcher = "best fit";

            public String getLocaleMatcher() {
                return _localeMatcher;
            }

            private String _usage = "sort";

            public String getUsage() {
                return _usage;
            }

            private String _sensitivity = "variant";

            public String getSensitivity() {
                return _sensitivity;
            }

            private boolean _ignorePunctuation;

            public boolean getIgnorePunctuation() {
                return _ignorePunctuation;
            }

            private String _numeric = "false";

            public String getNumeric() {
                return _numeric;
            }

            private String _caseFirst = "false";

            public String getCaseFirst() {
                return _caseFirst;
            }
            private String _locale;
            public String getLocale() {
                return _locale;
            }
            private String _collation="default";
            public String getCollation() {
                return _collation;
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
            public JsAny invoke(JsAny... arguments) {
                return null;
            }
        }
        //////////////////////////
        public Locale(String tag, JsObject options) {
            String[] data = tag.split("-");
            for(int i=0;i<data.length;i++) {
                String dat = data[i];
                switch (i) {
                    case 0:
                        _language = dat;
                        break;
                    case 1:
                        _script = dat;
                        break;
                    case 2:
                        _region = dat;
                        break;
                    case 3:
                        _calendar = dat;
                        break;
                    case 4:
                        break;
                    case 5:
                        _country = dat;
                        break;
                    case 6:
                      //  _collation=dat;
                        break;
                    case 7:
                        _hourCycle = dat;
                        break;
                    default:
                        throw new RangeError(new JsString(dat));
                }
            }
            for (Map.Entry<String, JsAny> entry : options.entrySet()) {
                JsAny value = entry.getValue();
                switch (entry.getKey().trim()) {
                    case "script":
                        _script = value.toString();break;
                    case "region":
                        _region = value.toString();break;
                    case "hourCycle":
                        _hourCycle = value.toString();break;
                    case "calendar":
                        _calendar = value.toString();break;
                    default:
                        throw new RangeError(new JsString(entry.getKey()));
                }
            }
            _THIS = new java.util.Locale(String.format("%s-%s",getLanguage(),getRegion()));
        }

        public Locale(String tag) {
            this(tag, new JsObject());
        }
        /////////////////////
        private String _country;
        public String getCountry() {
            return _country;
        }
        private String _language;
        public String getLanguage() {
            return _language;
        }
        public String getBaseName() {
            return String.format("%s-%s-%s",getLanguage(),getScript(),getRegion());
        }

        private String _script;
        public String getScript() {
            return _script;
        }
private String _region;
        public String getRegion() {
            return _region;
        }
        private String _hourCycle;
        public String getHourCycle() {
            return _hourCycle;
        }
        private String _calendar;
        public String getCalendar() {
            return _calendar;
        }
        ///////////
        private java.util.Locale _THIS;


    }

    //////////////////////////////
    public static class Collator extends JsObject {

        //////////////////////////
        private Locale _locale;
        private Locale.Options _options;

        public Collator(String locales, JsObject options) {
            _locale = new Locale(locales);
            _options = new Locale.Options (locales,options);
        }

        public Collator(String locales) {
            this(locales, new JsObject());
        }

        public Collator() {
            this(java.util.Locale.getDefault().toString());
        }

        ////////////////////////////////////////
        public int compare(String string1, String string2) {
            java.text.Collator myCollator = java.text.Collator.getInstance(_locale._THIS);
            return myCollator.compare(string1, string2);
        }

        public Locale.Options resolvedOptions() {
            return _options;
        }
        public static JsArray supportedLocalesOf(JsArray locales, JsObject options) {
            JsArray result=new JsArray();
            for (JsAny locale : locales) {
                if (allLocale.contains(locale.toString().toUpperCase())) {
                    result.add(locale);
                }
            }
            return result;
        }

        public static JsArray supportedLocalesOf(JsArray locales) {
            return supportedLocalesOf(locales, new JsObject());
        }
        public static JsArray supportedLocalesOf(JsAny locales, JsObject options) {
            return supportedLocalesOf(new JsArray(){{add(locales);}},options);
        }

        public static JsArray supportedLocalesOf(JsAny locales) {
            return supportedLocalesOf(locales, new JsObject());
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
        public JsAny invoke(JsAny... arguments) {
            return null;
        }
    }

    public static class DateTimeFormat extends JsObject {



        @Override
        public JsAny invoke(JsAny... arguments) {
            return null;
        }

        public static class Options extends JsObject {

            public Options(JsObject options) {
                for (Map.Entry<String, JsAny> entry:options.entrySet()) {
                    JsAny value = entry.getValue();
                    switch (entry.getKey()) {
                        case "dateStyle":
                            _dateStyle = ((JsString) value).THIS;
                            break;
                        case "timeStyle":
                            _timeStyle = ((JsString) value).THIS;
                            break;
                        case "fractionalSecondDigits":
                            _fractionalSecondDigits = ((JsNumber) value).THIS.floatValue();
                            break;
                        case "calendar":
                            _calendar = ((JsString) value).THIS;
                            break;
                        case "dayPeriod":
                            _dayPeriod = ((JsString) value).THIS;
                            break;
                        case "numberingSystem":
                            _numberingSystem = ((JsString) value).THIS;
                            break;
                        case "localeMatcher":
                            _localeMatcher = ((JsString) value).THIS;
                            break;
                        case "timeZone":
                            _timeZone = ((JsString) value).THIS;
                            break;
                        case "hour12":
                            _hour12 = ((JsBoolean) value).THIS;
                            break;
                        case "hourCycle":
                            _hourCycle = ((JsString) value).THIS;
                            break;
                        case "formatMatcher":
                            _formatMatcher = ((JsString) value).THIS;
                            break;
                        case "weekday":
                            _weekday = ((JsString) value).THIS;
                            break;
                        case "era":
                            _era = ((JsString) value).THIS;
                            break;
                        case "year":
                            _year = ((JsString) value).THIS;
                            break;
                        case "hour":
                            _hour = ((JsString) value).THIS;
                            break;
                        case "minute":
                            _minute = ((JsString) value).THIS;
                            break;
                        case "second":
                            _second = ((JsString) value).THIS;
                            break;
                        case "timeZoneName":
                            _timeZoneName = ((JsString) value).THIS;
                            break;
                        default:
                            break;
                    }
                }
            }
            private String _dateStyle ;

            public String getDateStyle() {
                return _dateStyle;
            }

            private String _timeStyle;

            public String getTimeStyle() {
                return _timeStyle;
            }

            private double _fractionalSecondDigits = 0;

            public double getFractionalSecondDigits() {
                return _fractionalSecondDigits;
            }

            private String _calendar;

            public String getCalendar() {
                return _calendar;
            }


            private String _dayPeriod = "best fit";

            public String getDayPeriod() {
                return _dayPeriod;
            }

            private String _numberingSystem;

            public String getNumberingSystem() {
                return _numberingSystem;
            }
            private String _localeMatcher = "best fit";

            public String getLocaleMatcher() {
                return _localeMatcher;
            }

            private String _timeZone;

            public String getTimeZone() {
                return _timeZone;
            }

            private boolean _hour12;

            public boolean getHour12() {
                return _hour12;
            }


            private String _hourCycle;

            public String getHourCycle() {
                return _hourCycle;
            }

            private String _formatMatcher="best fit";

            public String getFormatMatcher() {
                return _formatMatcher;
            }

            private String _weekday;

            public String getWeekday() {
                return _weekday;
            }

            private String _era;

            public String getEra() {
                return _era;
            }

            private String _year;

            public String getYear() {
                return _year;
            }

            private String _month;

            public String getMonth() {
                return _month;
            }

            private String _day;

            public String getDay() {
                return _day;
            }

            private String _hour;

            public String getHour() {
                return _hour;
            }

            private String _minute;

            public String getMinute() {
                return _minute;
            }

            private String _second;

            public String getSecond() {
                return _second;
            }

            private String _timeZoneName;

            public String getTimeZoneName() {
                return _timeZoneName;
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
            public JsAny invoke(JsAny... arguments) {
                return null;
            }
        }
        ///////////////////////
        private JsArray _locales;
        private Options _options;

        public DateTimeFormat(JsArray locales, JsObject options) {
            _locales = locales;
            _options = new Options(options);
        }

        public DateTimeFormat(JsArray locales) {
            this(locales, new JsObject());
        }

        public DateTimeFormat() {
            this(java.util.Locale.getDefault().toString());
        }

        public DateTimeFormat(String locales, JsObject options) {
            this(new JsArray() {{
                add(new JsString(locales));
            }}, new JsObject());
        }

        private DateTimeFormat(String locales) {
            this(locales, new JsObject());
        }

        ////////////////////////////////////////
        public String format(Date date) {
            return date.toString();
        }

        public String formatRange(Date startDate, Date endDate) {
            return String.format("%s - %s",startDate.toString(),endDate.toString());
        }

        public JsArray formatToParts(Date date) {
            return new JsArray();
        }

        public Options resolvedOptions() {
            return _options;
        }

        public JsArray supportedLocalesOf(JsArray locales, JsObject options) {
            return new JsArray();
        }

        public JsArray supportedLocalesOf(JsArray locales) {
            return supportedLocalesOf(locales, new JsObject());
        }
        public JsArray supportedLocalesOf(JsAny locales, JsObject options) {
            return supportedLocalesOf(new JsArray(){{add(locales);}},options);
        }

        public JsArray supportedLocalesOf(String locales) {
            return supportedLocalesOf(locales);
        }
    }

    public static class ListFormat extends JsObject {
        @Override
        public JsAny get(JsAny key) {
            return null;
        }

        @Override
        public JsAny set(JsAny key, JsAny value) {
return this;
        }





        @Override
        public JsAny invoke(JsAny... arguments) {
            return null;
        }

        public static class Options extends JsObject {
    private String _localeMatcher;

    public String getLocaleMatcher() {
        return _localeMatcher;
    }

    private String _type;

    public String getType() {
        return _type;
    }

    private String _style;

    public String getStyle() {
        return _style;
    }

    public Options(JsObject options) {
        for (Map.Entry<String, JsAny> entry : options.entrySet()) {
            JsAny value = entry.getValue();
            switch (entry.getKey()) {
                case "localeMatcher":
                    _localeMatcher = ((JsString)value).THIS;
                    break;
                case "type":
                    _type = ((JsString)value).THIS;
                    break;
                case "style":
                    _style = ((JsString)value).THIS;
                    break;
                default:
                    break;
            }
        }
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
            public JsAny invoke(JsAny... arguments) {
                return null;
            }
        }
        ///////////////////
        JsArray _locales;
        private Options _options;
        public ListFormat(JsArray locales, JsObject options) {
            _locales = locales;
            _options = new Options(options);
        }

        public ListFormat(JsArray locales) {
this(locales,new JsObject());
        }
        private ListFormat(String locale, JsObject options) {
            this(new JsArray() {{
                add(new JsString(locale));
            }}, options);
        }

        public ListFormat(String locales) {
            this(locales,new JsObject());
        }
        public ListFormat() {
            this(java.util.Locale.getDefault().toString());
        }
        ////////////////////////////////////////
        public JsArray supportedLocalesOf(String locales, JsObject options) {
            return new JsArray();
        }

        public JsArray supportedLocalesOf(String locales) {
            return supportedLocalesOf(locales, new JsObject());
        }
        public JsArray supportedLocalesOf() {
            return supportedLocalesOf(java.util.Locale.getDefault().toString());
        }
        public  String format(JsArray list) {
            return "";
        }

        public String format() {
            return format(new JsArray());
        }
        public String format(String list) {
            return format(Onekit_JS.string2Array(list));
        }
        public JsArray formatToParts(JsArray list) {
            return new JsArray();
        }

        public Options resolvedOptions() {
            return _options;
        }

    }

    public static class NumberFormat extends JsObject {
        @Override
        public JsAny get(JsAny key) {
            return null;
        }

        @Override
        public JsAny set(JsAny key, JsAny value) {
return this;
        }





        @Override
        public JsAny invoke(JsAny... arguments) {
            return null;
        }

        public static class Options extends JsObject {

        public Options(JsObject options) {
            for (Map.Entry<String, JsAny> entry : options.entrySet()) {
                JsAny value = entry.getValue();
                switch (entry.getKey().toString()) {
                    case "localeMatcher":
                        _localeMatcher = ((JsString)value).THIS;
                        break;
                    case "style":
                        _style = ((JsString)value).THIS;
                        break;

                    case "numberingSystem":
                        _numberingSystem =  ((JsString)value).THIS;
                        break;

                    case "unit":
                        _unit = ((JsString)value).THIS;
                        break;

                    case "unitDisplay":
                        _unitDisplay =  ((JsString)value).THIS;
                        break;

                    case "currency":
                        _currency =  ((JsString)value).THIS;
                        break;

                    case "currencyDisplay":
                        _currencyDisplay = ((JsString)value).THIS;
                        break;
                    case "useGrouping":
                        _useGrouping = ((JsBoolean)value).THIS;
                        break;
                    case "minimumIntegerDigits":
                        _minimumIntegerDigits =  ((JsNumber)value).THIS.longValue();
                        break;
                    case "minimumFractionDigits":
                        _minimumFractionDigits =((JsNumber)value).THIS.longValue();
                    case "maximumFractionDigits":
                        _maximumFractionDigits = ((JsNumber)value).THIS.longValue();
                        break;
                    case "minimumSignificantDigits":
                        _minimumSignificantDigits = ((JsNumber)value).THIS.longValue();
                        break;
                    case "maximumSignificantDigits":
                        _maximumSignificantDigits = ((JsNumber)value).THIS.longValue();
                        break;
                    case "notation":
                        _notation =  ((JsString)value).THIS;
                        break;
                    default:
                        break;
                }
            }
        }
            private  String _localeMatcher="best fit";
            public String getLocaleMatcher(){
                return _localeMatcher;
            }
        private  String _style="decimal";
        public String getStyle(){
            return _style;
        }
        private  String _numberingSystem;
        public String getNumberingSystem(){
            return _numberingSystem;
        }
        private  String _unit;
        public String getUnit(){
            return _unit;
        }
        private  String _unitDisplay="symbol";
        public String getUnitDisplay(){
            return _unitDisplay;
        }
        private  String _currency;
        public String getCurrency(){
            return _currency;
        }
        private  String _currencyDisplay;
        public String getCurrencyDisplay(){
            return _currencyDisplay;
        }
        private  boolean _useGrouping=true;
        public boolean getUseGrouping(){
            return _useGrouping;
        }
        private  Long _minimumIntegerDigits=1l;
        public Long getMinimumIntegerDigits(){
            return _minimumIntegerDigits;
        }
        private  Long _minimumFractionDigits=0l;
        public Long getMinimumFractionDigits(){
            return _minimumFractionDigits;
        }
        private  Long _maximumFractionDigits=0l;
        public Long getMaximumFractionDigits(){
            return _maximumFractionDigits;
        }
        private  Long _minimumSignificantDigits=1l;
        public Long getMinimumSignificantDigits(){
            return _minimumSignificantDigits;
        }
        private  Long _maximumSignificantDigits=21l;
        public Long getMaximumSignificantDigits(){
            return _maximumSignificantDigits;
        }
        private  String _notation="standard";
        public String getNotation(){
            return _notation;
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
            public JsAny invoke(JsAny... arguments) {
                return null;
            }
        }
            //////////////////////////
        private JsArray _locales;
    private Options _options;
        private NumberFormat(JsAny locales, JsAny options) {
            _locales = (JsArray) locales;
            _options=new Options((JsObject) options);
        }

        public NumberFormat(JsAny locales) {
this(locales,new JsObject());
        }
        public NumberFormat() {
            this(java.util.Locale.getDefault().toString());
        }
        public NumberFormat(String locales, JsObject options) {
this(new JsArray(){{add(new JsString(locales));}},options);
        }

        public NumberFormat(String locales) {
            this(locales,new JsObject());
        }
        ////////////////////////////////////////
        public String format(JsAny number) {
            return "";
        }

        public JsArray formatToParts(JsAny number) {
            return new JsArray();
        }

        public Options resolvedOptions() {
            return _options;
        }

        public JsArray supportedLocalesOf(String locales, JsObject options) {
            return new JsArray();
        }

        public JsArray supportedLocalesOf(String locales) {
            return supportedLocalesOf(locales, new JsObject());
        }
    }

    public static class PluraRules extends JsObject {
        @Override
        public JsAny get(JsAny key) {
            return null;
        }

        @Override
        public JsAny set(JsAny key, JsAny value) {
return this;
        }





        @Override
        public JsAny invoke(JsAny... arguments) {
            return null;
        }

        public static class Options{

        }
        private String _locale;
        private Options _options;
        public PluraRules(String locale) {
            _locale = locale;
            _options=new Options();
        }
        public PluraRules() {
            this(java.util.Locale.getDefault().toString());
        }
        public Options resolvedOptions() {
            return _options;
        }

        public String select(JsAny number) {
            return "";
        }

        public JsArray supportedLocalesOf(String locales, JsObject options) {
            return new JsArray();
        }

        public JsArray supportedLocalesOf(String locales) {
            return supportedLocalesOf(locales, new JsObject());
        }


    }

    public static class RelvativeTimeFormat extends JsObject {
        @Override
        public JsAny get(JsAny key) {
            return null;
        }

        @Override
        public JsAny set(JsAny key, JsAny value) {
return this;
        }





        @Override
        public JsAny invoke(JsAny... arguments) {
            return null;
        }

        public static class Options implements JsAny {



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
                return new JsString("Intl");
            }




            @Override
            public JsAny invoke(JsAny... arguments) {
                return null;
            }
        }
     /////////////////
       private String _locales;
     private Options _options;
        public RelvativeTimeFormat(String locales, JsObject options) {
_locales=locales;
            _options=new Options();
        }
        public RelvativeTimeFormat(String locales) {
            this(locales, new JsObject());
        }
        public String format(int value, String unit) {
            return "";
        }


        public Options resolvedOptions() {
            return _options;
        }

        public JsArray supportedLocalesOf(String locales, JsObject options) {
            return new JsArray();
        }

        public JsArray supportedLocalesOf(String locales) {
            return supportedLocalesOf(locales, new JsObject());
        }

    }
}
