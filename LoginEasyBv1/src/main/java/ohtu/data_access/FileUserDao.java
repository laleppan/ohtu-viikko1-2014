/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.data_access;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author laleppan
 */
@Component
public class FileUserDao implements UserDao {
    
    //private List<User> users;
    private final File pwfile;
    private BufferedWriter writer;
    private Scanner scanner;
    @Autowired
    public FileUserDao(String filename) {
        pwfile = new File(filename);
       try {
        if(!pwfile.exists()){
            pwfile.createNewFile();
        }   
        writer = new BufferedWriter(new FileWriter(pwfile,true));
       }
       catch (IOException e) {
           System.err.println("File error0");
       }
    }
    

    @Override
    public List<User> listAll() {
        try {
        scanner = new Scanner(pwfile);
        }
        catch (FileNotFoundException e) {
            System.err.println("File error1");
        }
        List<User> users = new ArrayList<User>();
        User line;
        while (scanner.hasNext()) {
            line = new User(scanner.next(), scanner.next());
            users.add(line);
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        try {
        scanner = new Scanner(pwfile);
        }
        catch (FileNotFoundException e) {
            System.err.println("File error2");
        }
        while(scanner.hasNext()) {
            String username = scanner.next();
            String password = scanner.next();
            if (username.equalsIgnoreCase(name)) {
                User user = new User(username,password);
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
        //writer = new BufferedWriter(new FileWriter(pwfile));
        writer.write(user.getUsername()+ " " + user.getPassword());
        writer.newLine();
        writer.flush();
        }
        catch (IOException e) {
            System.err.println("File error3");
        }
    }
}
