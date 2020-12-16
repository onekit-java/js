package demo.js;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.onekit.js.Console;
import cn.onekit.js.JsString;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Console().log(new JsString("xxx"));
    }
}
