package com.example.window_close;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.SystemClock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        //임시 개체를 사용하여 입력 및 출력 스트림을 가져옵니다
        
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int bytes; // bytes returned from read()
        // 예외가 발생할 때까지 InputStream을 계속 듣고 있음
        while (true) {
            try {
                // InputStream을 읽고
                bytes = mmInStream.available();
                if (bytes != 0) {
                    buffer = new byte[1024];
                    SystemClock.sleep(100); //SystemClock.sleep으로 일시 중지하며 나머지 데이터를 기다림
                    bytes = mmInStream.available();
                    bytes = mmInStream.read(buffer, 0, bytes); // bytes에 실제로 읽은 바이트 수 기록
                }
            } catch (IOException e) {
                e.printStackTrace();

                break;
            }
        }
    }

    // 데이터를 보내기 위해 메인에서 이를 호출함
    public void write(String input) {
        byte[] bytes = input.getBytes();           //converts entered String into bytes
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) {
        }
    }

    // 연결 종료시 메인에서 이를 호출함
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
        }
    }
}
