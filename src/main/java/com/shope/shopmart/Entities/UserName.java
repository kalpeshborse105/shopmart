package com.shope.shopmart.Entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userName", types = { User.class })
public interface UserName {
    @Value("#{target.firstName}" + " " + "#{target.lastName}")
    String getName();

    String getEmail();

    String getMobile();
}
