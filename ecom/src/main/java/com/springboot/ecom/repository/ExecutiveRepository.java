package com.springboot.ecom.repository;

import com.springboot.ecom.enums.JobTitle;
import com.springboot.ecom.model.Executive;
import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;
import java.util.Optional;

public interface ExecutiveRepository extends JpaRepository<Executive, Long> {

    List<Executive> findByJobTitle(JobTitle jobTitle);
    // select e from Executive e where e.jobTitle = ?1

    Executive findByUserUsername(String executiveUsername);

}
