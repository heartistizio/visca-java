package agh.alex.multi.visca;

import java.util.*;

/**
 * Created by sieci on 24.10.19.
 */
public class Commands {
    public static Map<String, byte[]> list;

    Set<String> speedable = new HashSet(Arrays.asList("DOWN", "UP", "RIGHT", "LEFT", "TOPLEFT" , "TOPRIGHT"));

    public Commands(){
        list = new HashMap<>();
        list.put("SET", new byte[]{48, 1});
        list.put("ADDRESS", new byte[]{48,1});
        list.put("HOME", new byte[]{1,6,4});
        list.put("DOWN", new byte[]{1,6,1,5,5,3,2});
        list.put("UP", new byte[]{1,6,1,5,5,3,1});
        list.put("RIGHT", new byte[]{1,6,1,5,5,2,3});
        list.put("LEFT", new byte[]{1,6,1,5,5,1,3});
        list.put("TOPRIGHT", new byte[]{1,6,1,5,5,2,1});
        list.put("TOPLEFT", new byte[]{1,6,1,5,5,1,1});
        list.put("TELEZOOM", new byte[]{1,4,7,2});
        list.put("WIDEZOOM", new byte[]{1,4,7,3});
    }

    public byte[] getCommand(String commandName,int speedTilt, int speedPan){
        byte[] command = list.get(commandName);
        if(commandName.equals("SET")){
            command[1] = (byte)speedTilt;
        }
        if(speedable.contains(commandName)){
            command[3] = (byte)speedTilt;
            command[4] = (byte)speedPan;
        }
        return list.get(commandName);
    }

    public byte[] getCommand(String commandName, int newAddress) {
        byte[] command = list.get(commandName);
        if(commandName.equals("SET")){
            command[1] = (byte)newAddress;
            return command;
        }
        return command;
    }

    public byte[] getCommand(String commandName){
        return list.get(commandName);
    }
}
