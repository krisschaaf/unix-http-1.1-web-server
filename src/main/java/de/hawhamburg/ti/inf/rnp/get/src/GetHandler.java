package de.hawhamburg.ti.inf.rnp.get.src;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetHandler {
    private GetClient getClient;

    GetHandler(GetClient getClient){
        this.getClient = getClient;
    }

    public void get() {
        String remoteHost = getClient.getHost();
        int port = getClient.getPort();
        String file = getClient.getFile();

        try {
            Socket socket = new Socket(remoteHost, port);

            StringBuilder request = new StringBuilder();
            request.append("GET " + file +  " HTTP/1.1\r\n");
            request.append("Host: " + remoteHost + "\r\n");
            request.append("Connection: close\r\n\r");

            PrintWriter wtr = new PrintWriter(socket.getOutputStream());

            if(getClient.getSlowMoBytes() != -1 && getClient.getSlowMoTime() != -1) {
                byte[] requestAsBytes = request.toString().getBytes();

                for (int i = 0; i < requestAsBytes.length; i += getClient.getSlowMoBytes()) {
                    int endIndex = Math.min(i + getClient.getSlowMoBytes(), requestAsBytes.length);
                    byte[] slice = Arrays.copyOfRange(requestAsBytes, i, endIndex);

                    String sliceText = new String(slice, StandardCharsets.UTF_8);

                    wtr.println(sliceText);

                    Thread.sleep(getClient.getSlowMoTime());
                }
            } else {
                wtr.println(request);
            }

            //TODO move so that -s will work
            wtr.flush();
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            getBytesFromReader(bufRead);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void getBytesFromReader(BufferedReader bufRead) throws IOException {
        int byteValue;
        int passedDataInBytes = 0;
        List<Byte> bytesToStream = new ArrayList<>();

        if(getClient.getContentRangeStart() == -1) {
            //The whole Data will be passed
            while((byteValue = bufRead.read()) != -1) {
                byte streamedByte = (byte) byteValue;
                    bytesToStream.add(streamedByte);
            }
        } else if (getClient.getContentRangeStart() != -1 && getClient.getContentRangeEnd() == -1) {
            // Data from given Content-Range start will be passed
            while((byteValue = bufRead.read()) != -1) {
                byte streamedByte = (byte) byteValue;
                passedDataInBytes ++;
                if(passedDataInBytes > getClient.getContentRangeStart()) {
                    bytesToStream.add(streamedByte);
                }
            }
        } else if (getClient.getContentRangeStart() != -1 && getClient.getContentRangeEnd() != -1) {
            // Data in between given Content-Range start and Content-Range end will be passed
            while((byteValue = bufRead.read()) != -1) {
                byte streamedByte = (byte) byteValue;
                passedDataInBytes ++;
                if(passedDataInBytes > getClient.getContentRangeStart()) {
                    bytesToStream.add(streamedByte);
                }
                if(passedDataInBytes > getClient.getContentRangeEnd()) {
                    break;
                }
            }
        } else {
            System.err.println("No valid Content-Range.");
            System.exit(-1);
        }

        send(convertListToByteArray(bytesToStream));
    }

    private byte[] convertListToByteArray(List<Byte> bytesToStream) {
        byte[] byteArray = new byte[bytesToStream.size()];
        for (int i = 0; i < bytesToStream.size(); i++) {
            byteArray[i] = bytesToStream.get(i);
        }
        return byteArray;
    }

    private void send(byte[] bytes) {
        saveText(new String(bytes, StandardCharsets.UTF_8));
    }

    private void saveText(String text) {
        System.out.println(text);
    }

//    private void sendInSlowMo(byte[] bytes) {
//        try {
//            for (int i = 0; i < bytes.length; i += getClient.getSlowMoBytes()) {
//                int endIndex = Math.min(i + getClient.getSlowMoBytes(), bytes.length);
//                byte[] slice = Arrays.copyOfRange(bytes, i, endIndex);
//
//                String sliceText = new String(slice, StandardCharsets.UTF_8);
//
//                Thread thread = new Thread(() -> {
//                    try {
//                        Thread.sleep(getClient.getSlowMoTime());
//                        saveText(sliceText);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });
//                thread.start();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
