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
    Button btnConnect;
    Button btnOn;
    Button btnOff;
    Button btnClose;
    Button btnC4;
    Button btnD4;
    Button btnE4;
    Button btnF4;
    Button btnG4;
    Button btnA4;
    Button btnB4;
    Button btnC5;
    LinearLayout nowConnecting;
    Client client;

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.message);
        nowConnecting = (LinearLayout) findViewById(R.id.now_connecting);

        // Connection Control
        btnConnect = (Button) findViewById(R.id.btn_connect);
        btnOn = (Button) findViewById(R.id.btn_on);
        btnOff = (Button) findViewById(R.id.btn_off);
        btnClose = (Button) findViewById(R.id.btn_close);


        // Buzzer Control
        btnC4 = (Button) findViewById(R.id.btn_c);
        btnD4 = (Button) findViewById(R.id.btn_d);
        btnE4 = (Button) findViewById(R.id.btn_e);
        btnF4 = (Button) findViewById(R.id.btn_f);
        btnG4 = (Button) findViewById(R.id.btn_g);
        btnA4 = (Button) findViewById(R.id.btn_a);
        btnB4 = (Button) findViewById(R.id.btn_b);
        btnC5 = (Button) findViewById(R.id.btn_C);

        btnConnect.setOnClickListener(this);
        btnOn.setOnClickListener(this);
        btnOff.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        btnC4.setOnClickListener(this);
        btnD4.setOnClickListener(this);
        btnE4.setOnClickListener(this);
        btnF4.setOnClickListener(this);
        btnG4.setOnClickListener(this);
        btnA4.setOnClickListener(this);
        btnB4.setOnClickListener(this);
        btnC5.setOnClickListener(this);


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
            btnConnect.setVisibility(View.GONE);
            nowConnecting.setVisibility(View.VISIBLE);
        } else {
            btnConnect.setVisibility(View.VISIBLE);
            nowConnecting.setVisibility(View.GONE);
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
                client.buzzScale(scale);
                break;
        }
    }


}
