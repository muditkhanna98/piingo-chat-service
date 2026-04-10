package com.mudit.piingochatservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "piingo-user-service")
public interface UserServiceClient {
    @PostMapping("/users/validate")
    void validateUsers(@RequestBody List<UUID> userIds);
}
