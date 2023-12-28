package com.example.tumbaburros.java9;

import javax.imageio.IIOException;
import java.io.*;
import java.util.stream.Stream;

public class Main {

    public static void line(){
        System.out.println("----------------------------------------");
    }

    public static void java7tryWithResources(){
        //java 7
        try( FileReader fr = new FileReader("C:\\Users\\FArellano\\Desktop\\fer.txt");
             BufferedReader br = new BufferedReader(fr)){
            System.out.println(br.readLine());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void java9TryWithResources() throws FileNotFoundException {
        //java9
        FileReader fr = new FileReader("C:\\Users\\FArellano\\Desktop\\fer.txt");
        BufferedReader br = new BufferedReader(fr);
        try(fr; br){
            System.out.println(br.readLine());
        } catch(IOException e){
            System.out.println(e);
        }
    }

    //iterate n times
    //start, until when predicate, what to execute function
    public static void iterate(){
        Stream.iterate(1, n->n<20, n -> n*2).forEach(System.out::println);
    }

    public static void main(String[] args) throws FileNotFoundException {
        java7tryWithResources();line();
        java9TryWithResources();line();
        iterate();
    }
}
