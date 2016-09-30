package android.iotlabs.com.rpicontroller;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    TextView message;
    Button btn_connect, btn_on, btn_off, btn_close;
    Button c4, d4, e4, f4, g4, a4, b4, c5;
    LinearLayout now_connecting;
    Client client;

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.message);
        now_connecting = (LinearLayout) findViewById(R.id.now_connecting);

        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_on = (Button) findViewById(R.id.btn_on);
        btn_off = (Button) findViewById(R.id.btn_off);
        btn_close = (Button) findViewById(R.id.btn_close);

        c4 = (Button) findViewById(R.id.btn_c);
        d4 = (Button) findViewById(R.id.btn_d);
        e4 = (Button) findViewById(R.id.btn_e);
        f4 = (Button) findViewById(R.id.btn_f);
        g4 = (Button) findViewById(R.id.btn_g);
        a4 = (Button) findViewById(R.id.btn_a);
        b4 = (Button) findViewById(R.id.btn_b);
        c5 = (Button) findViewById(R.id.btn_C);

        btn_connect.setOnClickListener(this);
        btn_on.setOnClickListener(this);
        btn_off.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        c4.setOnClickListener(this);
        d4.setOnClickListener(this);
        e4.setOnClickListener(this);
        f4.setOnClickListener(this);
        g4.setOnClickListener(this);
        a4.setOnClickListener(this);
        b4.setOnClickListener(this);
        c5.setOnClickListener(this);


        mHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        message.setText((CharSequence) msg.obj);
                        break;
                    case 1:
                        connecting(true);
                        break;
                }
            }
        };

        client = new Client("client", mHandler);
        client.start();
    }

    public void connecting(boolean c) {
        if (c) {
            btn_connect.setVisibility(View.GONE);
            now_connecting.setVisibility(View.VISIBLE);
        } else {
            btn_connect.setVisibility(View.VISIBLE);
            now_connecting.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect:
                client.connect();
                break;
            case R.id.btn_close:
                client.quit();
                break;
            default:
                String scale = (String) ((Button) v).getText();
                client.buzz_scale(scale);
                break;
        }
    }


}

