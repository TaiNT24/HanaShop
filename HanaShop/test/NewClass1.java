
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class NewClass1 {
    
    public static void main(String[] args) throws IOException {
        File f = new File("D:/1.txt");

        FileWriter fw = new FileWriter(f, true);
        
        fw.write("123");
        fw.write("\n");
        fw.close();
    }
}
