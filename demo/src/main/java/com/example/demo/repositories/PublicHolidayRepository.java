package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;
import com.example.demo.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday,String>{

}
