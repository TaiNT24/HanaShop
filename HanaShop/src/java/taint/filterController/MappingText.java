/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.filterController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;


/**
 *
 * @author nguye
 */
public class MappingText {
    public HashMap<String, String> mapping(File fileName)
            throws FileNotFoundException, IOException {

        HashMap<String, String> hashMap = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, "=");
                String key = stk.nextToken();
                String value = stk.nextToken();
                hashMap.put(key, value);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return hashMap;
    }

}
