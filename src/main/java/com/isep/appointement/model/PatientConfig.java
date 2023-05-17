package com.isep.appointement.model;/*
package com.isep.appointement.model;

import com.isep.appointement.Repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository repository){
        return args -> {
            Patient p1 = new Patient(1L,
                    "p1",
                    "1111",
                    "p1",
                    11, 0,
                    111111,
                    "p1@gmail.com",
                    "130102198002030211");
            Patient p2 =  new Patient(2L,
                    "p2",
                    "2222",
                    "p2",
                    22, 0,
                    111111,
                    "p2@gmail.com",
                    "130102198002030211");
            Patient p3 = new Patient(3L,
                    "p3",
                    "3333",
                    "p3",
                    33, 0,
                    111111,
                    "p3@gmail.com",
                    "130102198002030211");
            repository.saveAll(List.of(p1, p2, p3));
        };
    }
}
*/
