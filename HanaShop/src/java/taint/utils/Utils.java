/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import taint.model.foodAndDrink.FoodAndDrinkDAO;

/**
 *
 * @author nguye
 */
public class Utils {

    public static String formatDateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateFormat = df.format(date);

        return dateFormat;
    }

    public static String formatStringDate(String strDate) {
        java.sql.Date date = java.sql.Date.valueOf(strDate);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");

        String dateFormat = df.format(date);

        return dateFormat;
    }

    public static String encodePassword(String password)
            throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        String sha256hex = Base64.getEncoder().encodeToString(hash);
        return sha256hex;
    }

    public static String getNewName()
            throws SQLException, NamingException {

        FoodAndDrinkDAO dao = new FoodAndDrinkDAO();

        int size = dao.loadLastProduct();

        String newNameImg = (++size) + "";

        return newNameImg;
    }

    public static void writeIntoMappingFile(File file, String newMapping)
            throws IOException {

        FileWriter fw = new FileWriter(file, true);
        String newLine = newMapping + "=" + newMapping;

        fw.write(newLine);
        fw.write("\n");

        fw.close();
    }

    public static FileOutputStream loadFileOutputStream(File f)
            throws FileNotFoundException, IOException {
        return (new FileOutputStream(f));
    }

    public static void copyFileToStatic(String path) throws FileNotFoundException, IOException {
        File f = new File(path);
        File[] sub = f.listFiles();

        if (path.contains("\\build")) {
            path = path.replace("\\build", "");
        }

        for (int i = 0; i < sub.length; i++) {
            String name = sub[i].getName();

            File fileCopy = new File(path + "/" + name);

            InputStream filecontent = new FileInputStream(sub[i]);
            OutputStream out = new FileOutputStream(fileCopy);

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.close();
            filecontent.close();

        }
    }

    public static void copyFileMappingToStatic(String path, String fileName)
            throws FileNotFoundException, IOException {
        File f = new File(path);
        File[] sub = f.listFiles();

        if (path.contains("\\build")) {
            path = path.replace("\\build", "");
        }

        for (int i = 0; i < sub.length; i++) {
            String name = sub[i].getName();
            if (name.equals(fileName)) {
                File fileCopy = new File(path + "/" + name);

                PrintWriter pw = new PrintWriter(fileCopy);

                BufferedReader bf = new BufferedReader(new FileReader(sub[i]));
                String line;

                while ((line = bf.readLine()) != null) {
                    pw.println(line);
                }

                bf.close();
                pw.close();

            }
        }
    }

    public static void copyImg(Part filePart, File fileCopy, String realPath)
            throws IOException {
        OutputStream out = loadFileOutputStream(fileCopy);

        InputStream filecontent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
//
        copyFileToStatic(realPath + "food");
    }


}
