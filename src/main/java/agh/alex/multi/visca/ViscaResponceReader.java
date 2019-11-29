package agh.alex.multi.visca;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sieci on 01.01.02.
 */
public class ViscaResponceReader {
    public ViscaResponceReader() {
    }

    public static byte[] readResponse(SerialPort serialPort) throws SerialPortException {
        ArrayList<Byte> data = new ArrayList();

        while (serialPort.getInputBufferBytesCount() != 0) {
            byte[] responseData = serialPort.readBytes(1);
            Byte b = responseData[0];
            data.add(b);
            if (b == -1) {
                responseData = new byte[data.size()];
                int idx = 0;

//              Byte b;
                for (Iterator var7 = data.iterator(); var7.hasNext(); responseData[idx++] = b.byteValue()) {
                    b = (Byte) var7.next();
                }
                return responseData;
            }
        }
        throw new SerialPortException("", "", "");
    }
}
