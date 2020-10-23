package cn.onekit.js.core;


import cn.onekit.js.JsObject;

public abstract class JsModule extends JsObject implements JsFile {
   public JsModule module = this;
   public JsObject exports;

   protected abstract void onekit_js();

}
