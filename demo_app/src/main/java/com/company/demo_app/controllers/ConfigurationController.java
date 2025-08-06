package com.company.demo_app.controllers;

import com.company.demo_app.annotations.LogMethod;
import com.company.demo_app.model.entities.Specifier;
import com.company.demo_app.model.entities.Sport;
import com.company.demo_app.model.errors.ApiError;
import com.company.demo_app.services.ConfigurationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/matches/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @GetMapping(value = "/getAllSports")
    @Operation(description = "Get available sports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sport.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<List<Sport>> getAllSports() {
        return ResponseEntity.ok(configurationService.getAllSports());
    }

    @PutMapping(value = "/createSport")
    @Operation(description = "Create new sport entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> createSport(@RequestBody Sport sport) {
        configurationService.createSport(sport);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/deleteSport")
    @Operation(description = "Delete sport entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> deleteSport(@RequestBody Sport sport) {
        configurationService.deleteSport(sport);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAllSpecifiers")
    @Operation(description = "Get available specifiers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Specifier.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<List<Specifier>> getAllSpecifiers() {
        return ResponseEntity.ok(configurationService.getAllSpecifiers());
    }

    @PutMapping(value = "/createSpecifier")
    @Operation(description = "Create new specifier entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> createSpecifier(@RequestBody Specifier specifier) {
        configurationService.createSpecifier(specifier);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/deleteSpecifier")
    @Operation(description = "Delete sport entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> deleteSpecifier(@RequestBody Specifier specifier) {
        configurationService.deleteSpecifier(specifier);
        return ResponseEntity.ok().build();
    }

}
