package cn.onekit.js;

public class SyntaxError extends Error {
    public String columnNumber;
    public String fileName;
    public Integer lineNumber;
    public String message;
    public String name;
    public JsObject stack;
    ////////////////////
public String toSource(){
    return null;
}
public String toString(){
    return null;
}


}
