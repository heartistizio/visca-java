package agh.alex.multi;

import agh.alex.multi.visca.Commands;
import agh.alex.multi.visca.ResponseThread;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sieci on 31.12.01.
 */
public class Main {
    static SerialPort serialPort;
    static Commands commands;
    static boolean connected = false;
    static ResponseThread thread;
    public static ArrayList<String> out;

    public static void main(String[] args) {
        init();
        String response = "";
        Scanner scanner = new Scanner(System.in);
        while (!response.equals("QUIT")) {
            System.out.println("CMD|quit|OUT ADDRESS=1 (new_address)|(speed tilt)?");
            String inputString = scanner.nextLine();
            response = translateCommand(inputString);
            System.out.println("Response: " + response);
        }
    }

    public static String translateCommand(String inputString) {
        String[] input = inputString.split(" ");
        switch (input.length) {
            case 1:
                if (input[0].equals("quit")) {
                    return "QUIT";
                }
                if (input[0].equals("OUT")) {
                    System.out.println(out);
                    return "";
                }
                sendCommand(commands.getCommand(input[0]), (byte) 1);
                return "";
            case 2:
                System.out.println("case 2");
                sendCommand(commands.getCommand(input[0]), (byte) Integer.parseInt(input[1]));
                return "";
            case 3:
                System.out.println("case 3");
                sendCommand(commands.getCommand(input[0], Integer.parseInt(input[2])), (byte) Integer.parseInt(input[1]));
                return "";
            case 4:
                sendCommand(commands.getCommand(input[0], Integer.parseInt(input[2]), Integer.parseInt(input[3])), (byte) Integer.parseInt(input[1]));
                return "";
            default:
                System.out.println("Bad input");
                return "";
        }
    }


    static void sendCommand(byte[] command, byte destAdr) {
        try {
            System.out.println("writing....");
            serialPort.writeBytes(createData(command, (byte) 0, destAdr));
            System.out.println("sending....");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        if (!connected) {
            serialPort = new SerialPort(Port.name);
            commands = new Commands();
            try {
                serialPort.openPort();
                serialPort.setParams(9600, 8, 1, 0);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            out = new ArrayList<>();
            out.add("Initialized");
            connected = true;
            thread = new ResponseThread(serialPort, out);
            thread.start();
        }
    }

    public static byte[] createData(byte[] commandData, byte sourceAdr, byte destinationAdr) {
        byte[] cmdData = new byte[commandData.length + 1 + 1];
        byte head = (byte) (128 | (sourceAdr & 7) << 4 | destinationAdr & 15);
        byte tail = -1;
        System.arraycopy(commandData, 0, cmdData, 1, commandData.length);
        cmdData[0] = head;
        cmdData[commandData.length + 1] = tail;
        StringBuilder sb = new StringBuilder();
        for (byte b : cmdData) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString());
        return cmdData;
    }
}



