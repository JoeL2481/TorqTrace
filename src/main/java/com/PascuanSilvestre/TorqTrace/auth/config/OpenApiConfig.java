package com.PascuanSilvestre.TorqTrace.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "TorqTrace API",
                version = "1.0",
                description = "Dynamic OpenAPI documentation for TorqTrace endpoints.",
                contact = @Contact(name = "TorqTrace")
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI torqTraceOpenApi() {
        return new OpenAPI().tags(preferredTags());
    }

    @Bean
    public OpenApiCustomizer securityCustomizer() {
        return openApi -> {
            if (openApi.getPaths() == null) {
                return;
            }

            openApi.getPaths().forEach((path, pathItem) -> {
                if (isPublicPath(path)) {
                    return;
                }

                addSecurityRequirement(pathItem);
            });
        };
    }

    @Bean
    public OpenApiCustomizer examplesCustomizer() {
        return openApi -> {
            if (openApi.getComponents() == null || openApi.getComponents().getSchemas() == null) {
                return;
            }

            openApi.getComponents().getSchemas().values().forEach(this::applyExamplesToSchema);
        };
    }

    @Bean
    public OpenApiCustomizer tagOrderingCustomizer() {
        return openApi -> {
            if (openApi.getTags() == null || openApi.getTags().isEmpty()) {
                return;
            }

            Map<String, Tag> preferredByName = new LinkedHashMap<>();
            for (Tag tag : preferredTags()) {
                preferredByName.put(tag.getName(), tag);
            }

            Map<String, Tag> discoveredByName = new LinkedHashMap<>();
            for (Tag tag : openApi.getTags()) {
                discoveredByName.putIfAbsent(tag.getName(), tag);
            }

            List<Tag> orderedTags = new ArrayList<>();

            for (Map.Entry<String, Tag> entry : preferredByName.entrySet()) {
                Tag discovered = discoveredByName.remove(entry.getKey());
                if (discovered != null && (entry.getValue().getDescription() == null || entry.getValue().getDescription().isBlank())) {
                    orderedTags.add(discovered);
                } else {
                    orderedTags.add(entry.getValue());
                }
            }

            orderedTags.addAll(discoveredByName.values());
            openApi.setTags(orderedTags);
        };
    }

    @Bean
    public OperationCustomizer operationAccessCustomizer() {
        return (operation, handlerMethod) -> {
            String accessDescription = buildAccessDescription(handlerMethod);
            String currentDescription = operation.getDescription();

            if (currentDescription == null || currentDescription.isBlank()) {
                operation.setDescription(accessDescription);
            } else if (!currentDescription.contains("Access:")) {
                operation.setDescription(currentDescription + "\n\n" + accessDescription);
            }

            addSuccessResponse(operation, handlerMethod);
            addSecurityResponses(operation, handlerMethod);
            return operation;
        };
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/api/auth")
                || path.startsWith("/oauth2")
                || path.startsWith("/login")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui");
    }

    private void addSecurityRequirement(PathItem pathItem) {
        pathItem.readOperations().forEach(operation ->
                operation.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        );
    }

    private Tag buildTag(String name, String description) {
        return new Tag().name(name).description(description);
    }

    private List<Tag> preferredTags() {
        return List.of(
                buildTag("Vehicle", "Primary vehicle catalog and configuration endpoints"),
                buildTag("User", "User profile and administration endpoints"),
                buildTag("User Vehicle", "Vehicles owned by authenticated users"),
                buildTag("Maintenance", "Vehicle maintenance history endpoints"),
                buildTag("Work Order", "Workshop work order endpoints"),
                buildTag("Work Order Item", "Auxiliary work order item endpoints"),
                buildTag("Work Order Type", "Auxiliary work order type endpoints"),
                buildTag("Workshop", "Workshop management endpoints"),
                buildTag("Workshop Client", "Workshop client endpoints"),
                buildTag("Workshop Staff", "Workshop staff endpoints"),
                buildTag("Workshop Stock", "Workshop inventory stock endpoints"),
                buildTag("Authentication", "Authentication and account registration endpoints"),
                buildTag("Authentication - Provider", "Auxiliary authentication provider endpoints"),
                buildTag("Authentication - User Provider", "Auxiliary user-provider relationship endpoints"),
                buildTag("Spare Part", "Inventory spare part management endpoints"),
                buildTag("Spare Part Category", "Inventory spare part category endpoints"),
                buildTag("Spare Part Compatibility", "Compatibility rules between spare parts and vehicles"),
                buildTag("Vehicle Catalog - Brand", "Auxiliary vehicle brand catalog endpoints"),
                buildTag("Vehicle Catalog - Model", "Auxiliary vehicle model catalog endpoints"),
                buildTag("Vehicle Catalog - Generation", "Auxiliary vehicle generation catalog endpoints"),
                buildTag("Vehicle Catalog - Variant", "Auxiliary vehicle variant catalog endpoints"),
                buildTag("Vehicle Catalog - Equipment Level", "Auxiliary vehicle equipment level catalog endpoints"),
                buildTag("Vehicle Powertrain - Engine", "Auxiliary engine catalog endpoints"),
                buildTag("Vehicle Powertrain - Transmission", "Auxiliary transmission catalog endpoints"),
                buildTag("Vehicle Powertrain - Engine Transmission", "Auxiliary engine and transmission relationship endpoints")
        );
    }

    private String buildAccessDescription(HandlerMethod handlerMethod) {
        PreAuthorize methodAuthorization = handlerMethod.getMethodAnnotation(PreAuthorize.class);
        if (methodAuthorization != null) {
            return "Access: " + formatAuthorization(methodAuthorization.value());
        }

        PreAuthorize classAuthorization = handlerMethod.getBeanType().getAnnotation(PreAuthorize.class);
        if (classAuthorization != null) {
            return "Access: " + formatAuthorization(classAuthorization.value());
        }

        return "Access: Requires authentication.";
    }

    private String formatAuthorization(String expression) {
        if (expression.contains("hasAnyRole")) {
            return "Available to " + extractRoles(expression) + ".";
        }

        if (expression.contains("hasRole")) {
            return "Available to " + extractRoles(expression) + ".";
        }

        return "Requires authorization.";
    }

    private String extractRoles(String expression) {
        int start = expression.indexOf('(');
        int end = expression.lastIndexOf(')');

        if (start < 0 || end <= start) {
            return "authorized roles";
        }

        String rolesSection = expression.substring(start + 1, end).replace("'", "");
        String[] roles = rolesSection.split(",");
        StringBuilder builder = new StringBuilder();

        for (String role : roles) {
            String cleanRole = role.trim();
            if (cleanRole.isEmpty()) {
                continue;
            }

            if (!builder.isEmpty()) {
                builder.append(", ");
            }

            builder.append("ROLE_").append(cleanRole);
        }

        if (builder.isEmpty()) {
            return "authorized roles";
        }

        return builder.toString();
    }

    private void addSuccessResponse(io.swagger.v3.oas.models.Operation operation, HandlerMethod handlerMethod) {
        if (handlerMethod.getMethodAnnotation(PostMapping.class) != null) {
            addResponseIfMissing(operation, "201", "Created successfully.");
            return;
        }

        if (handlerMethod.getMethodAnnotation(DeleteMapping.class) != null) {
            addResponseIfMissing(operation, "204", "Deleted successfully.");
            return;
        }

        addResponseIfMissing(operation, "200", "Request processed successfully.");
    }

    private void addSecurityResponses(io.swagger.v3.oas.models.Operation operation, HandlerMethod handlerMethod) {
        addResponseIfMissing(operation, "401", "Unauthorized. Valid authentication is required.");

        PreAuthorize methodAuthorization = handlerMethod.getMethodAnnotation(PreAuthorize.class);
        PreAuthorize classAuthorization = handlerMethod.getBeanType().getAnnotation(PreAuthorize.class);

        if (methodAuthorization != null || classAuthorization != null) {
            addResponseIfMissing(operation, "403", "Forbidden. You do not have permission to access this endpoint.");
        }
    }

    private void addResponseIfMissing(io.swagger.v3.oas.models.Operation operation, String code, String description) {
        if (operation.getResponses() == null) {
            operation.setResponses(new io.swagger.v3.oas.models.responses.ApiResponses());
        }

        if (operation.getResponses().containsKey(code)) {
            return;
        }

        operation.getResponses().addApiResponse(code, new ApiResponse().description(description));
    }

    @SuppressWarnings("unchecked")
    private void applyExamplesToSchema(Schema<?> schema) {
        if (schema.getProperties() == null) {
            return;
        }

        Map<String, Schema> properties = schema.getProperties();

        properties.forEach((propertyName, propertySchema) -> {
            if (propertySchema.getExample() != null) {
                return;
            }

            Object example = resolveExample(propertyName, propertySchema);
            if (example != null) {
                propertySchema.setExample(example);
            }
        });
    }

    private Object resolveExample(String propertyName, Schema<?> propertySchema) {
        String normalizedName = propertyName.toLowerCase(Locale.ROOT);

        if (propertySchema instanceof ArraySchema) {
            return List.of();
        }

        if (propertySchema.getEnum() != null && !propertySchema.getEnum().isEmpty()) {
            return propertySchema.getEnum().getFirst();
        }

        if ("publicid".equals(normalizedName)) {
            return "A1B2C3D4E5F6G7H8";
        }

        if ("particularvehicleid".equals(normalizedName)) {
            return "A1B2C3D4E5F6G7H8";
        }

        if ("uservehicleid".equals(normalizedName)) {
            return "U1V2W3X4Y5Z6A7B8";
        }

        if (normalizedName.endsWith("email") || "email".equals(normalizedName)) {
            return "joel248@gmail.com";
        }

        if ("username".equals(normalizedName)) {
            return "joel248";
        }

        if ("password".equals(normalizedName)) {
            return "clave1234";
        }

        if ("token".equals(normalizedName)) {
            return "eyJhbGciOiJIUzM4NCJ9.example.token";
        }

        if (normalizedName.contains("firstname")) {
            return "Joel";
        }

        if (normalizedName.contains("lastname")) {
            return "Pascuan";
        }

        if (normalizedName.contains("vehiclebrandname")) {
            return "Toyota";
        }

        if (normalizedName.contains("vehiclemodelname")) {
            return "Corolla";
        }

        if (normalizedName.contains("vehiclegenerationname")) {
            return "E210";
        }

        if (normalizedName.contains("vehiclegenerationalias")) {
            return "12th Gen";
        }

        if (normalizedName.contains("vehiclevariantname")) {
            return "XEi";
        }

        if (normalizedName.contains("vehicleequipmentlevelname")) {
            return "Full";
        }

        if ("name".equals(normalizedName)) {
            return "Oil Filter";
        }

        if (normalizedName.contains("workshopname")) {
            return "TorqTrace Garage";
        }

        if (normalizedName.contains("workordertypename")) {
            return "Oil Change";
        }

        if (normalizedName.contains("categoryname")) {
            return "Filters";
        }

        if (normalizedName.contains("sparepartname")) {
            return "Bosch Oil Filter";
        }

        if (normalizedName.contains("name")) {
            return "Example Name";
        }

        if (normalizedName.contains("description")) {
            return "Example description";
        }

        if (normalizedName.contains("note")) {
            return "Compatible with selected configuration";
        }

        if (normalizedName.contains("street")) {
            return "Calle 123";
        }

        if (normalizedName.contains("city")) {
            return "Cordoba";
        }

        if (normalizedName.contains("state")) {
            return "Cordoba";
        }

        if (normalizedName.contains("country")) {
            return "Argentina";
        }

        if (normalizedName.contains("zipcode")) {
            return "5000";
        }

        if (normalizedName.contains("phone")) {
            return "3511234567";
        }

        if (normalizedName.contains("avatarurl")) {
            return "https://example.com/avatar.jpg";
        }

        if (normalizedName.contains("licenceplate") || normalizedName.contains("licenseplate") || normalizedName.contains("plate")) {
            return "AB123CD";
        }

        if (normalizedName.equals("vin")) {
            return "JTDBR32E720123456";
        }

        if ("year".equals(normalizedName)) {
            return 2020;
        }

        if (normalizedName.contains("currentkm")) {
            return 84500;
        }

        if (normalizedName.contains("servicekm")) {
            return 85000;
        }

        if (normalizedName.contains("nextservicekm")) {
            return 95000;
        }

        if (normalizedName.contains("categoryid")) {
            return 1L;
        }

        if (normalizedName.contains("workorderid")) {
            return 1L;
        }

        if (normalizedName.contains("enginecode")) {
            return "M20A-FKS";
        }

        if (normalizedName.contains("enginename")) {
            return "2.0 Dynamic Force";
        }

        if (normalizedName.contains("transmissionname")) {
            return "CVT";
        }

        if (normalizedName.contains("alias")) {
            return "12th Gen";
        }

        if (normalizedName.endsWith("id")) {
            if ("vehicleid".equals(normalizedName)) {
                return "A1B2C3D4E5F6G7H8";
            }
            if ("publicid".equals(normalizedName)) {
                return "A1B2C3D4E5F6G7H8";
            }
            if ("vehiclebrandid".equals(normalizedName)) {
                return "550e8400-e29b-41d4-a716-446655440000";
            }
            return 1L;
        }

        String type = propertySchema.getType();
        String format = propertySchema.getFormat();

        if ("string".equals(type) && "date-time".equals(format)) {
            return "2026-06-16T10:30:00Z";
        }

        if ("string".equals(type) && "date".equals(format)) {
            return "2026-06-16";
        }

        if ("string".equals(type) && "uuid".equals(format)) {
            return "550e8400-e29b-41d4-a716-446655440000";
        }

        if ("integer".equals(type)) {
            return 1;
        }

        if ("number".equals(type)) {
            return 84500;
        }

        if ("boolean".equals(type)) {
            return true;
        }

        if ("string".equals(type)) {
            return "example";
        }

        return null;
    }
}
