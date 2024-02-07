package com.shope.shopmart.Repository;

import com.shope.shopmart.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Integer> {
}
