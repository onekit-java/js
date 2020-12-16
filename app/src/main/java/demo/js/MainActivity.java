package demo.js;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.onekit.js.Console;
import cn.onekit.js.JsNumber;
import cn.onekit.js.JsString;
import cn.onekit.js.core.JsAny;
import cn.onekit.js.core.function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Console().log(new JsString("xxx"));
        new function(){
            @Override
            public JsAny invoke(JsAny... arguments) {
                JsAny a = arguments[0];
                new Console().log(a);
                return null;
            }
        }.invoke(new JsNumber(1));
    }
}
