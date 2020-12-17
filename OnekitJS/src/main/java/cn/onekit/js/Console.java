package cn.onekit.js;

import android.util.Log;

import java.util.Random;

import cn.onekit.js.core.Onekit_JS;

public class Console implements JsAny {
    String _run(JsAny... data) {
        StringBuilder str = new StringBuilder(" \r\n");
        for (int i=0;i<data.length;i++) {
            JsAny item = data[i];
            str.append(String.format("%s\t", JSON.stringify(item)));
        }
        return str.toString();
    }

    public JsAny asset(Object assertion, JsAny... assets) {
        if (!Onekit_JS.is(assertion)) {
            Log.v("[OneKit]===============" + new Random().nextInt(), _run(assets));
        }
        return null;
    }

    public JsAny error(JsAny... errors) {

        Log.e("[OneKit]==============="+ new Random().nextInt(), _run(errors));
        return null;
    }

    public JsAny info(JsAny... infos) {

        Log.i("[OneKit]==============="+new Random().nextInt(), _run(infos));
        return null;
    }

    public JsAny log(JsAny... logs) {

        Log.d("[OneKit]==============="+ new Random().nextInt(), _run(logs));
        return null;
    }

    public JsAny warn(JsAny... warns) {

        Log.w("[OneKit]==============="+ new Random().nextInt(), _run(warns));
        return null;
    }


    @Override
    public JsString ToString() {
        return new JsString("Console");
    }

    @Override
    public JsAny invoke(JsAny... arguments) {
        return null;
    }
}
