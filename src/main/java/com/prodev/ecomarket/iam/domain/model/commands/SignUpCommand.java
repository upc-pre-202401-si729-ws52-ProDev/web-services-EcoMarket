package com.prodev.ecomarket.iam.domain.model.commands;

import com.prodev.ecomarket.iam.domain.model.entities.Role;

import java.util.List;
import java.util.Optional;

public record SignUpCommand(String username, String password, List<Role> roles, Optional<String> ruc,
                            Optional<String> aboutDescription, Optional<String> address,
                            Optional<Integer> loyaltyPoi, Optional<String> email, Optional<String> name,
                            Optional<String> phone) {
}