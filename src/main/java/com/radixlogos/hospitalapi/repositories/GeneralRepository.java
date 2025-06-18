package com.radixlogos.hospitalapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GeneralRepository<T, ID> extends JpaRepository<T, ID> {

}
