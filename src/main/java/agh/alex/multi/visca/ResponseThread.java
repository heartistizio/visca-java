package agh.alex.multi.visca;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;

public class ResponseThread implements Runnable {
    private Thread t;
    private SerialPort serialPort;
    private String portName;
    private ArrayList<String> out;

    public ResponseThread(SerialPort serialPort, ArrayList<String> out) {
        this.serialPort = serialPort;
        this.portName = serialPort.getPortName();
        this.out = out;
        String message = "Creating " +  this.portName;
        out.add(message);
        System.out.println(message);
    }

    public void run() {
        String message = "Running " + portName;
        out.add(message);
        System.out.println(message);
        byte[] response;
        while(serialPort.isOpened()) {
            try {
                if(serialPort.getInputBufferBytesCount() != 0) {
                    response = ViscaResponceReader.readResponse(serialPort);
                    StringBuilder sb = new StringBuilder();
                    for (byte b : response) {
                        sb.append(String.format("%02X ", b));
                    }

                    if (sb.charAt(3) == '4') {
                        out.add("ACK");
                        System.out.println("ACK");
                    } else if (sb.charAt(3) == '5') {
                        out.add("Command completion");
                        System.out.println("Command completion");
                    } else if (sb.charAt(3) == '6') {
                        out.add("Error");
                        System.out.println("Error");
                    }
                }
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        String message = ("Starting " +  portName);
        out.add(message);
        System.out.println(message);
        if (t == null) {
            t = new Thread (this, portName);
            t.start ();
        }
    }
}