package com.zunshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "zun")
public class ZunProperties {

	private String greeting;
}
