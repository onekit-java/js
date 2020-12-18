package demo.js;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import cn.onekit.*;
import cn.onekit.js.*;
import cn.onekit.js.core.JsFile;

public class MainActivity extends AppCompatActivity implements JsFile {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final JsAny test = new function(){ public JsAny body(JsArray arguments){
            JsAny t = arguments.get(0);
            if(is(fullequals(t,undefined))){
                return new JsString("Undefined value!");
            };
            return t;
        }};
        JsAny x = new Undefined();
        console.log(test.invoke(x));



    }
}
