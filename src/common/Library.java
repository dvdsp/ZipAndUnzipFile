/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author DELL
 */
public class Library {
    Scanner sc = new Scanner(System.in);
    public int getIntForMenu(String msg, int min, int max){
        int a = -1;
        while(true){
            System.out.print(msg);
            try {
                a = Integer.parseInt(sc.nextLine());
                if(a >= min && a <= max){
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }
        return a;
    }
    
    public int getInteger(String msg){
        int a = -1;
        while (true) {            
            System.out.println(msg);
            try {
                a = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Only input number");
                continue;
            }
            break;
        }
        return a;
    }
    
    

    public  String checkString() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String str = sc.nextLine().trim();
                if (str.length() == 0) {
                    throw new NumberFormatException();
                }
                return str;
            } catch (NumberFormatException e) {
                System.out.println("Not empty!");
            }
        }
    }

    public  void zipFile() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Source Folder: ");
        String pathSrc = checkString();
        System.out.print("Enter Destination Folder: ");
        String pathCompress = checkString();
        System.out.print("Enter Name: ");
        String fileZipName = in.nextLine();

        boolean check = compressTo(pathSrc, fileZipName, pathCompress);
        if (check == true) {
            System.out.println("Successfully");
        } else {
            System.out.println("Fail");
        }
    }

    public  boolean compressTo(String pathSrc, String fileZipName,String pathCompress)  {
        try {
            String sourceFile = pathSrc;
            String nameFos = pathCompress + "/" + fileZipName + ".zip";
            FileOutputStream fos = new FileOutputStream(nameFos);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
            final byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            zipOut.close();
            fis.close();
            fos.close(); 
        } catch (FileNotFoundException e) {
            //
        } catch(IOException e) {
            //
        }
        return true;
    }

    public  void unzipFile() {
        System.out.print("Enter Source file: ");
        String pathZipFile = checkString();
        System.out.print("Enter Destination Folder: ");
        String pathExtract = checkString();
        boolean check = extractTo(pathZipFile, pathExtract);
        if (check == true) {
            System.out.println("Successfully");
        } else {
            System.out.println("error");
        }
        
    }

    public  boolean extractTo(String pathZipFile, String pathExtract) {
        try {
            String fileZip = pathZipFile;
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(pathExtract + fileName);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            
        } catch (FileNotFoundException e) {
            //
        } catch(IOException e) {
            //
        }
        return true;
    }
    
    
}
