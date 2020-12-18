package cn.onekit.js;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import cn.onekit.JsAny;

public class JSON {
	private static Gson gson = new Gson();

	public static JsAny parse(String aString) {
		try {
			if (aString == null) {
				return null;
			}
			String str = aString.trim();
			if (str.equals("")) {
				return null;
			}
			if (str.equals("null")) {
				return null;
			}
			if (str.equalsIgnoreCase("true")) {
				return new JsBoolean( true);
			}
			if (str.equalsIgnoreCase("false")) {
				return new JsBoolean( false);
			}
			switch (str.substring(0, 1)) {
				case "{":
					return gson.fromJson(str, (Type) JsObject.class);
				case "[":
					return gson.fromJson(str, (Type) JsArray.class);
				default:
					if (aString.startsWith("\"") && aString.endsWith("\"")) {
						aString = aString.substring(1, aString.length() - 1);
					}
					return new JsString( aString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	 static String _entry(int depth, String key, JsAny value, JsAny replacer, String space){
		return String.format("\"%s\":%s",key,_stringify(depth+1,value,replacer,space));
	}
	 static String _stringify(int depth, JsAny json, JsAny replacer, String space) {
		if(json==null){
			return null;
		}
		String tab="";
		for(int d=0;d<depth;d++){
			tab+=space;
		}
		StringBuilder result = new StringBuilder();

		 if(json instanceof JsNumber){
			return ((JsString)json.ToString()).THIS;
		}else  if(json instanceof JsString){
			 return ((JsString)json).THIS;
		 }else if(json instanceof JsAny){
			 return  String.format("%s\"%s\"",tab,((JsString)json.ToString()).THIS);
		 }else if(json instanceof JsArray){
			JsArray array = (JsArray) json;
			result.append(tab+"[\r\n");
			int i=0;
			for(JsAny item: array){
				result.append(_stringify(depth+1,item,replacer,space));
				if(i<array.size()-1) {
					result.append(",");
				}
				result.append("\r\n");
				i++;
			}
			result.append(tab+"]");
		}else if(json instanceof JsObject){
			JsObject dict = (JsObject) json;
			result.append(tab+"{\r\n");
			int i=0;
			for(String key : dict.keySet()){
				JsAny value = dict.get(key);
				if(replacer!=null){
					if(replacer instanceof function) {
						result.append(_entry(depth,key,value, replacer,space));
					}else if(replacer instanceof List){
						if(((List) replacer).contains(key)){
							result.append(_entry(depth,key,value,replacer,space));
						}
					}else {
						throw new java.lang.Error(replacer.toString());
					}
				}else{
					result.append(_entry(depth,key,value,replacer,space));
				}
				if(i<dict.keySet().size()-1) {
					result.append(",");
				}
				result.append("\r\n");
				i++;
			}
			result.append(tab+"}");
		}else{
			String str;
			if(replacer!=null){
				if(replacer instanceof function) {
					str = _stringify( depth+1,((function)replacer).invoke(json),replacer,space);
				}else if(replacer instanceof List){
					str=json.toString();
				}else {
					throw new java.lang.Error(replacer.toString());
				}
			}else{
				str=json.toString();
			}
			result.append(str);
		}
		return result.toString();
	}
	public static JsString stringify(JsAny json, JsAny replacer, JsAny space) {
		if (space == null) {
			space = new JsString();
		}else if (space instanceof JsString ) {
			if(((JsString)space.ToString()).THIS.length() > 10) {
				space = new JsString(((JsString)space.ToString()).THIS.substring(0, 10));
			}
		}else if(space instanceof JsNumber){
			space = new JsString(java.lang.Math.min(10,((JsNumber)space).THIS.intValue()));
		}
		return new JsString(_stringify( 0,json, replacer,((JsString)space).THIS));
	}
	public static JsString stringify(JsAny json, JsAny replacer) {
		return stringify(json, replacer, new JsString());
	}

	public static JsString stringify(JsAny json) {
		return stringify(json, null);
	}
}
