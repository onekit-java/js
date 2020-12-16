package cn.onekit.js;

import android.util.Log;

import java.util.Random;

import cn.onekit.js.core.JsAny;
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

    public void asset(Object assertion, JsAny... assets) {
        if (!Onekit_JS.is(assertion)) {
            Log.v("[OneKit]===============" + new Random().nextInt(), _run(assets));
        }
    }

    public void error(JsAny... errors) {

        Log.e("[OneKit]==============="+ new Random().nextInt(), _run(errors));
    }

    public void info(JsAny... infos) {

        Log.i("[OneKit]==============="+new Random().nextInt(), _run(infos));
    }

    public void log(JsAny... logs) {

        Log.d("[OneKit]==============="+ new Random().nextInt(), _run(logs));
    }

    public void warn(JsAny... warns) {

        Log.w("[OneKit]==============="+ new Random().nextInt(), _run(warns));
    }


    @Override
    public JsString ToString() {
        return new JsString("Console");
    }
}
