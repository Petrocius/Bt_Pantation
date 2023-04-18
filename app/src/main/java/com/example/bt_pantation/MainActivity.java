package com.example.bt_pantation;
// 00:22:03:01:B5:B3

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final int REQUEST_ENABLE_BT = 1;
    Handler handler;
    EditText waterTo;
    EditText lampOn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waterTo = (EditText) findViewById(R.id.WatertoText);
        lampOn = (EditText) findViewById(R.id.lampOnText);


    }

    //method for upload, when pressed.
    public void uploadConfig(View view) {

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        System.out.println(btAdapter.getBondedDevices());

        BluetoothDevice hc05 = btAdapter.getRemoteDevice("00:22:03:01:B5:B3");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        System.out.println(hc05.getName());
        BluetoothSocket btSocket = null;

        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);


            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            btSocket.connect();
            System.out.println(btSocket.isConnected());

            OutputStream outputStream = btSocket.getOutputStream();

            String string = "?lampOn=" + lampOn.getText() + "&waterTo=" + waterTo.getText() + ";";
            outputStream.write(string.getBytes(Charset.forName("UTF-8")));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            btSocket.close();
            System.out.println(btSocket.isConnected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(MainActivity.this,"Adatok elküldve.",Toast.LENGTH_SHORT).show();

    }

    public void startFan(View view) {

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        System.out.println(btAdapter.getBondedDevices());

        BluetoothDevice hc05 = btAdapter.getRemoteDevice("00:22:03:01:B5:B3");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        System.out.println(hc05.getName());
        BluetoothSocket btSocket = null;

        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);


            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            btSocket.connect();
            System.out.println(btSocket.isConnected());

            OutputStream outputStream = btSocket.getOutputStream();

            String string = "f";
            outputStream.write(string.getBytes(Charset.forName("UTF-8")));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            btSocket.close();
            System.out.println(btSocket.isConnected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(MainActivity.this,"Ventillátor elindítva.",Toast.LENGTH_SHORT).show();

    }
}
