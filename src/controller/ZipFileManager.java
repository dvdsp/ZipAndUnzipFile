/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Library;
import java.io.IOException;
import view.Menu;

/**
 *
 * @author DELL
 */

public class ZipFileManager extends Menu<String>{
    static String[] mc = {"Zip file", "Unzip file", "exit"};
    private Library library = new Library();
    
    public ZipFileManager(){
        super("\tZIP AND UNZIP FILE", mc);
    }
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                try {
                    library.zipFile();
                } catch (IOException ex) {
                    System.out.println("Error");
                }
                break;
            case 2:
                try {
                    library.unzipFile();
                } catch (IOException ex) {
                    System.out.println("Error");
                }
                break;
            case 3: System.exit(0); break;
        }
    }
}
