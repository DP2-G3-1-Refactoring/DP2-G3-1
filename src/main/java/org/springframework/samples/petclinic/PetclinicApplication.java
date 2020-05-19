
package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "/DP2-G3-1/src/main/java/org/springframework/samples/petclinic/model")
@SpringBootApplication
public class PetclinicApplication {

	public static void main(final String[] args) {
		SpringApplication.run(PetclinicApplication.class, args);
	}

}
