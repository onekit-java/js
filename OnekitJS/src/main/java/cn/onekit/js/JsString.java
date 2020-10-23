package cn.onekit.js;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import cn.onekit.js.core.Onekit_JS;
import cn.onekit.js.core.function;

public  class JsString implements JsObject_ {
	public String THIS;

	public JsString(String THIS) {
		this.THIS = THIS;
	}

/*
	public  Iterator iterator() {
		ArrayList result = new ArrayList<>();
		for (char chr : this.toString().toCharArray()){
			result.add(String.valueOf(chr));
		}
		return new STRING(result.iterator();
	}

 int _hashCode() {
	int arraySize = 11113; // 数组大小一般取质数
	int hashCode = 0;
	for (int i = 0; i < this.toString().length(); i++) { // 从字符串的左边开始计算
		int letterValue = this.toString().charAt(i) - 96;// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
		// 就代表a的值，同理b=2；
		hashCode = ((hashCode << 5) + letterValue) % arraySize;// 防止编码溢出，对每步结果都进行取模运算
	}
	return new STRING(hashCode;
}*/

	public JsObject_ _index(String aString, int index) {
		if (index >= 0) {
			return new JsNumber(index);
		}
		return new JsNumber(aString.length() + index);
	}

	public JsObject_ get(JsObject_ i) {
		int index = Onekit_JS.number(i, 0, 0).intValue();
		return new JsString(String.valueOf(this.toString().charAt(index)));
	}

	@Override
	public void set(String key, JsObject_ value) {

	}

	@Override
	public void set(JsObject_ key, JsObject_ value) {

	}

	///////////////////////////////
	public JsString fromCharCode(JsObject_... nums) {
		try {
			StringBuilder result = new StringBuilder();
			for (JsObject_ num : nums) {
				int number = new JsNumber(num).THIS.intValue();
				if (number > 0xFFFF) {
					number = number % 0xFFFF;
				}
				result.append((char) number);
			}
			return new JsString(result.toString());
		} catch (Exception e) {
			return new JsString("");
		}
	}

	public JsString fromCodePoint(JsObject_... nums) {
		JsArray codeUnits = new JsArray();
		JsNumber codeLen;
		StringBuilder result = new StringBuilder();
		for (int index = 0, len = nums.length; index != len; ++index) {
			int codePoint = Onekit_JS.number(nums[index], 0, 0).intValue();
			// correctly handles all cases including `NaN`, `-Infinity`, `+Infinity`
			// The surrounding `!(...)` is required to correctly handle `NaN` cases
			// The (codePoint>>>0) === codePoint clause handles decimals and negatives
			if (!(codePoint < 0x10FFFF))
				throw new RangeError(new JsString("Invalid code point: " + codePoint));
			if (codePoint <= 0xFFFF) { // BMP code point
				codeLen = codeUnits.push(new JsNumber(codePoint));
			} else { // Astral code point; split in surrogate halves
				// https://mathiasbynens.be/notes/javascript-encoding#surrogate-formulae
				codePoint -= 0x10000;
				codeLen = codeUnits.push(new JsNumber(
								(codePoint >> 10) + 0xD800),  // highSurrogate
						new JsNumber((codePoint % 0x400) + 0xDC00) // lowSurrogate
				);
			}
			if (codeLen.THIS.intValue() >= 0x3fff) {
				result.append(fromCharCode(codeUnits.toArray(new JsObject_[0])));
				//codeUnits.length = 0;
			}
		}
		return new JsString(result + fromCharCode(codeUnits.toArray(new JsObject_[0])).toString());

	}

	public JsString anchor(JsObject_ name) {
		return new JsString(String.format("<a name=\"%s\">%s</a>", name, this));
	}

	public JsString big() {
		return new JsString(String.format("<big>%s</big>", this));
	}

	public JsString blink() {
		return new JsString(String.format("<blink>%s</blink>", this));
	}

	public JsString bold() {
		return new JsString(String.format("<bold>%s</bold>", this));
	}

	public JsString charAt(JsObject_ index) {
		int i = Onekit_JS.number(index, 0, 0).intValue();
		return new JsString(String.valueOf(this.toString().charAt(i)));
	}

	public JsString charCodeAt(JsObject_ index) {
		int i = Onekit_JS.number(index, 0, 0).intValue();
		return new JsString(String.valueOf(this.toString().charAt(i)));
	}

	public JsString charCodeAt() {
		return charCodeAt(new JsNumber(0));
	}

	public Number codePointAt(int index) {

		if (index < 0 || index >= this.toString().length()) {
			return null;
		}
		return (this.toString().codePointAt(index));

	}

	public JsString concat(JsObject_... strings) {
		StringBuilder result = new StringBuilder(this.toString());
		for (JsObject_ THIS : strings) {
			result.append(THIS);
		}
		return new JsString(result.toString());
	}

	public JsBoolean endsWith(JsObject_ searchString, JsObject_ length) {
		int len = Onekit_JS.number(length, 0, 0).intValue();
		return new JsBoolean(this.toString().substring(len - searchString.toString().length(), len).equals(searchString.toString()));
	}

	public JsBoolean endsWith(JsObject_ searchString) {
		return endsWith(searchString, new JsNumber(this.toString().length()));
	}

	public JsString fixed() {
		return new JsString(String.format("<tt>%s</tt>", this));
	}

	public JsString fontcolor(JsObject_ color) {
		return new JsString(String.format("<font color=\"%s\">%s</font>", color, this));
	}

	public JsString fontsize(JsObject_ size) {
		return new JsString(String.format("<font size=\"%s\">%s</font>", size, this));
	}

	public JsBoolean includes(JsObject_ searchString, JsObject_ position) {
		if (position == null) {
			position = new JsNumber(0);
		}
		int pos = Onekit_JS.number(position, 0, 0).intValue();
		return new JsBoolean(this.toString().substring(pos).contains(searchString.toString()));
	}

	public JsNumber indexOf(JsObject_ searchValue, JsObject_ from) {
		int f = Onekit_JS.number(from, 0, 0).intValue();
		return new JsNumber(this.toString().indexOf(searchValue.toString(), f));
	}

	public JsNumber indexOf(JsObject_ searchValue) {
		return indexOf(searchValue, new JsNumber(0));
	}

	public JsString italics() {
		return new JsString(String.format("<i>%s</i>", this));
	}

	public JsNumber lastIndexOf(JsObject_ searchValue, JsObject_ fromIndex) {
		int from = Onekit_JS.number(fromIndex, 0, 0).intValue();
		return new JsNumber(this.toString().lastIndexOf(searchValue.toString(), from));
	}

	public JsNumber lastIndexOf(JsObject_ searchValue) {
		return lastIndexOf(searchValue, new JsNumber(Double.POSITIVE_INFINITY));
	}

	public JsString link(JsObject_ url) {
		return new JsString(String.format("<a href=\"%s\">%s</i>", url, this));
	}

	public JsNumber localeCompare(JsObject_ compareString, String locales, JsObject options) {
		return new JsNumber(0);
	}

	public RegExp.Match match(RegExp regexp) {
		Matcher matcher = regexp.THIS.matcher(this.toString());
		JsArray finds = new JsArray();
		JsArray groups = null;
		if (matcher.find()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				String group = matcher.group(i);
				finds.add(new JsString(group));
			}
		}
		int index = matcher.start();
		String input = this.THIS;
		int length = matcher.groupCount() + 1;
		return new RegExp.Match(finds, groups, index, input, length);
	}

	public JsObject matchAll(RegExp regexp) {
		return null;
	}

	public JsString normalize(JsObject form) {
		return null;
	}

	public JsString padEnd(JsObject_ targetLength, JsObject_ padString) {
		int len = Onekit_JS.number(targetLength, 0, 0).intValue();
		if (len < this.toString().length()) {
			return this;
		}
		//	String format = "%" + (len - this.toString().length()) + "s";
		StringBuilder sb = new StringBuilder(THIS);
		while (sb.length() < len) {
			sb.append(padString.toString());
		}
		return new JsString(sb.toString().substring(0, len));
	}

	public JsString padEnd(JsObject_ targetLength) {
		return padEnd(targetLength, new JsString(" "));
	}

	public JsString padStart(JsObject_ targetLength, String padString) {
		int len = Onekit_JS.number(targetLength, 0, 0).intValue();
		if (len < this.toString().length()) {
			return this;
		}
		//	String format = "%" + (len - this.toString().length()) + "s";
		StringBuilder sb = new StringBuilder(THIS);
		while (this.toString().length() < len) {
			sb.insert(0, padString);
		}
		return new JsString(sb.toString().substring(0, len));
	}

	public JsString padStart(JsObject_ targetLength) {
		return padStart(targetLength, " ");
	}

	public JsString repeat(JsObject_ count) {
		int c = Onekit_JS.number(count, 0, 0).intValue();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < c; i++) {
			result.append(this.toString());
		}
		return new JsString(result.toString());
	}

	public JsString replace(RegExp regexp, String newSubStr) {

		Matcher matcher = regexp.THIS.matcher(this.toString());
		RegExp.$s.clear();

		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				String group = matcher.group(i);
				RegExp.$s.add(new JsString(group));
				assert group != null;
				newSubStr = newSubStr.replace("$" + (i + 1), group);
			}
			this.THIS = this.toString().replaceFirst(regexp.getPattern(), newSubStr);
			if (!regexp.getFlags().contains("g")) {
				break;
			}
		}
		return this;
	}

	public JsString replace(RegExp regexp, function function) {
		Matcher matcher = regexp.THIS.matcher(this.toString());
		RegExp.$s.clear();
		while (matcher.find()) {
			List<JsObject_> arguments = new ArrayList<>();
			arguments.add(new JsString(matcher.group()));
			for (int i = 1; i <= matcher.groupCount(); i++) {
				String group = matcher.group(i);
				RegExp.$s.add(new JsString(group));
				arguments.add(new JsString(group));
			}
			arguments.add(new JsNumber(matcher.start()));
			arguments.add(this);
			this.THIS = this.toString().replaceFirst(
					regexp.getPattern(),
					function.invoke(arguments.toArray(new JsObject_[0])).toString()
			);
			if (!regexp.getFlags().contains("g")) {
				break;
			}
		}
		return this;
	}

	public JsString replace(JsObject_ substr, String newSubStr) {
		return new JsString(this.toString().replaceFirst(substr.toString(), newSubStr));
	}

	public JsString replace(JsObject_ substr, function function) {
		int p = this.toString().indexOf(substr.toString());
		if (p < 0) {
			return this;
		}
		return new JsString(this.toString().replaceFirst(substr.toString(), function.invoke(substr, new JsNumber(p), this).toString()));
	}

	public JsNumber search(RegExp regexp) {
		Matcher matcher = regexp.THIS.matcher(this.toString());
		if (matcher.find()) {
			return new JsNumber(matcher.start());
		}
		return new JsNumber(-1);
	}

	public JsString slice(JsObject_ start, JsObject_ end) {
		int s = Onekit_JS.number(start, 0, 0).intValue();
		int e = Onekit_JS.number(end, 0, 0).intValue();
		return new JsString(this.toString().substring(s, e));

	}

	public JsString slice(JsObject_ start) {
		return slice(start, new JsNumber(this.toString().length()));
	}

	public JsString small() {
		return new JsString(String.format("<small>%s</small>", this));
	}

	public JsArray split(JsObject_ separator, JsObject_ limit) {
		int l = Onekit_JS.number(limit, 0, 0).intValue();
		String[] array = this.toString().split(separator.toString(), l);
		JsArray result = new JsArray();
		int i = 0;
		for (String item : array) {
			if (i++ == 0 && item.equals("") && !this.toString().startsWith(" ")) {
				continue;
			}
			result.add(new JsString(item));
		}
		return result;
	}

	public JsArray split(JsObject_ separator) {
		return split(separator, new JsNumber(0));
	}

	public JsArray split() {
		return JsArray.from(this,null,null);
	}

	public JsBoolean startsWith(JsObject_ searchString, JsObject_ position) {
		if (position == null) {
			position = new JsNumber(0);
		}
		int p = Onekit_JS.number(position, 0, 0).intValue();
		return new JsBoolean(this.toString().substring(p).startsWith(searchString.toString()));

	}

	public JsString strike() {
		return new JsString(String.format("<strike>%s</strike>", this));
	}

	public JsString sub() {
		return new JsString(String.format("<sub>%s</sub>", this));
	}

	public JsString substr(JsObject_ start, JsObject_ length) {
		int s = Onekit_JS.number(start, 0, 0).intValue();
		int l = Onekit_JS.number(length, 0, 0).intValue();
		return new JsString(this.toString().substring(s, s + l));
	}

	public JsString substr(JsObject_ start) {
		int s = Onekit_JS.number(start, 0, 0).intValue();
		return substr(start, new JsNumber(this.toString().length() - s));
	}

	private String _substring(int indexStart, int indexEnd) {
		indexStart = java.lang.Math.max(indexStart, 0);
		indexEnd = java.lang.Math.max(indexEnd, 0);
		if (indexStart >= this.toString().length()) {
			indexStart = this.toString().length() - 1;
		}
		if (indexEnd >= this.toString().length()) {
			indexEnd = this.toString().length() - 1;
		}
		if (indexStart > indexEnd) {
			int temp = indexStart;
			indexStart = indexEnd;
			indexEnd = temp;
		}
		return this.toString().substring(indexStart, indexEnd);
	}

	public JsString substring(JsObject_ indexStart, JsObject_ indexEnd) {
		return new JsString(_substring(Onekit_JS.number(indexStart, 0, 0).intValue(), Onekit_JS.number(indexEnd, 0, 0).intValue()));
	}

	public JsString substring(JsObject_ indexStart) {
		return substring(indexStart, new JsNumber(this.toString().length()));
	}

	public JsString sup() {
		return new JsString(String.format("<sup>%s</sup>", this));
	}

	public JsString toLocaleLowerCase(JsObject_... locale) {
		return null;
	}

	public JsString toLocaleUpperCase(JsObject_... locale) {
		return null;
	}

	public JsString toLowerCase() {
		return new JsString(this.toString().toLowerCase());
	}

	public JsObject toSource() {
		return null;
	}

	public JsString toUpperCase() {
		return new JsString(this.toString().toUpperCase());
	}

	public JsString trim() {
		return new JsString(this.toString().trim());
	}

	public JsString trimRight() {
		if (this.toString().equals("")) {
			return this;
		}

		return new JsString(this.toString().replaceAll("[　 ]+$", ""));

	}

	public JsString trimLeft() {
		if (this.toString().equals("")) {
			return this;
		}
		return new JsString(this.toString().replaceAll("^[　 ]+", ""));

	}

	public JsString valueOf() {
		return new JsString(String.valueOf(this));
	}

	public JsString raw(JsObject callSite, Object... substitutions) {
		return null;
	}

	public JsNumber getLength() {
		return new JsNumber(this.THIS.length());
	}

	/*
		public  STRING ToString( int format) {
			switch (format){
				case 10:return null;
				case 16:return new STRING(String.format("%02x",this.THIS));
				default:
					return null;
			}
		}*/
	public JsString ToString() {
		return new JsString(THIS);
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
}
