package com.hendrick.carshop.repository;

import com.hendrick.carshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandInterface extends JpaRepository<Brand, Long> {

    public Brand findBrandById(Long id);
}
