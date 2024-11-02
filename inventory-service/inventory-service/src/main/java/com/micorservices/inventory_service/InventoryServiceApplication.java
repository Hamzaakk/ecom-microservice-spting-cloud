package com.micorservices.inventory_service;

import com.micorservices.inventory_service.model.Inventory;
import com.micorservices.inventory_service.repository.InventoryRepository;
import com.micorservices.inventory_service.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository) {

		return args -> {
			Inventory inventory1 = Inventory.builder()
					.skuCode("TEST")
					.quantity(100)
					.build();
			Inventory inventory2 = Inventory.builder()
					.skuCode("QWERTY")
					.quantity(100)
					.build();
			inventoryRepository.saveAll(List.of(inventory1,inventory2));
		};

	}

}
