package com.awangelo.msnucleo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MsNucleoApplication {

    static void main(String[] args) {
        SpringApplication.run(MsNucleoApplication, args)
    }

}
