package agh.alex.multi.rest;

import agh.alex.multi.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        Main.init();
        SpringApplication.run(App.class, args);
    }

}

