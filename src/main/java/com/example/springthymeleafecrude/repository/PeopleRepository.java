/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 24/6/2023
 * Time: 00:48
 */

package com.example.springthymeleafecrude.repository;

import com.example.springthymeleafecrude.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
}
