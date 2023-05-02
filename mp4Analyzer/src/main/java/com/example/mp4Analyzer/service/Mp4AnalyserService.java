package com.example.mp4Analyzer.service;

import com.example.mp4Analyzer.model.BoxInfo;
import com.example.mp4Analyzer.util.Mp4AnalyserUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Mp4AnalyserService {

    public static List<BoxInfo> analyseMp4(String url) throws IOException {
        URL mediaUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) mediaUrl.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();

        // Reading the byte information from the connection and storing it in a byte array
        List<BoxInfo> boxList = new ArrayList<>();
        byte[] boxHeader = new byte[8];
        int bytesRead = inputStream.read(boxHeader);
        while (bytesRead > 0) {
            long boxSize = Mp4AnalyserUtil.readBoxSize(boxHeader);
            String boxType = Mp4AnalyserUtil.readBoxType(boxHeader);
            boxList.add(new BoxInfo(boxType, boxSize));
            if (boxSize == 0) {
                break;
            }
            //This skips the payload data
            inputStream.skip(boxSize - 8);
            //Updates the loop
            bytesRead = inputStream.read(boxHeader);
        }

        connection.disconnect();
        inputStream.close();
        return boxList;
    }
}
