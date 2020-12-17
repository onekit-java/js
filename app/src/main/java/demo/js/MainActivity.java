package demo.js;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import cn.onekit.js.*;
import cn.onekit.js.core.JsFile;

public class MainActivity extends AppCompatActivity implements JsFile {

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
