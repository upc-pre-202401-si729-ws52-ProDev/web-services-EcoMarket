// SignUpResource
package com.prodev.ecomarket.iam.interfaces.rest.resources;

import java.util.List;
import java.util.Optional;

public record SignUpResource(String username, String password, List<String> roles, Optional<String> ruc
        , Optional<String> aboutDescription, Optional<String> address, Optional<Integer> loyaltyPoi
        , Optional<String> email, Optional<String> name, Optional<String> lastName, Optional<String> phone,
                             Optional<Integer> age) {
}