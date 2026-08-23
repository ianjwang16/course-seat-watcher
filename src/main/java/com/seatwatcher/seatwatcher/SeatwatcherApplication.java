package com.seatwatcher.seatwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeatwatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeatwatcherApplication.class, args);
	}
}
