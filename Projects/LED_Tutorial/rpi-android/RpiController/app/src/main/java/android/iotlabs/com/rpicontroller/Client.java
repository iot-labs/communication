package android.iotlabs.com.rpicontroller;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client extends HandlerThread {
    public final String serverAddress = "127.0.0.1"; //raspberry pi's IP address
    public final int serverPort = 10000; // port number
    
    Handler mHandler, mainHandler;
    
    Socket socket;
    BufferedWriter out;
    BufferedReader in;
    
    public Client(String name, Handler handler) {
        super(name);
        mainHandler = handler;
    }
    
    @Override
    public synchronized void start() {
        super.start();
        if (mHandler == null) mHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                try {
                    switch (msg.what) {
                        case 0:
                            socket = new Socket(serverAddress, serverPort);
                            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            
                            sendMessageToMain(0, in.readLine());
                            sendMessageToMain(1, null);
                            break;
                        case 1:
                            out.write("on");
                            out.flush();
                            break;
                        case 2:
                            out.write("off");
                            out.flush();
                            break;
                        case 3:
                            socket.close();
                            sendMessageToMain(0, "Disconneted!");
                            sendMessageToMain(2, null);
                            break;
                        case 4:
                            String scale = (String) msg.obj;
                            out.write(scale);
                            out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        sendMessageToMain(2, null);
    }
    
    public void connect() {
        if (socket == null || socket.isClosed()) mHandler.sendEmptyMessage(0);
    }
    
    public void on() {
        if (socket != null && socket.isConnected()) mHandler.sendEmptyMessage(1);
    }
    
    public void off() {
        if (socket != null && socket.isConnected()) mHandler.sendEmptyMessage(2);
    }
    
    public void close() {
        if (socket != null && socket.isConnected()) mHandler.sendEmptyMessage(3);
    }
    
    public void buzzScale(String scale) {
        if (socket != null && socket.isConnected()) {
            Message msg = new Message();
            msg.obj = scale;
            msg.what = 4;
            mHandler.sendMessage(msg);
        }
    }
    
    private void sendMessageToMain(int what, String str) {
        Message msg = new Message();
        msg.what = what;
        if (what == 0) msg.obj = str;
        mainHandler.sendMessage(msg);
    }
    
    @Override
    public boolean quit() {
        if (socket != null && socket.isConnected()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.quit();
    }
}

