package com.ecommerce.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class LocalStorageImageApplication  implements CommandLineRunner {

	 @Resource
	  StorageService storageService;
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(LocalStorageImageApplication.class, args);
	}

	 @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }
}
