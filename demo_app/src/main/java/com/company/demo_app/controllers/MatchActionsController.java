package com.company.demo_app.controllers;

import com.company.demo_app.annotations.LogMethod;
import com.company.demo_app.model.dtos.MatchDto;
import com.company.demo_app.model.dtos.MatchOddsDto;
import com.company.demo_app.model.errors.ApiError;
import com.company.demo_app.services.MatchActionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/matches/actions", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchActionsController {

    private final MatchActionsService matchActionsService;

    public MatchActionsController(MatchActionsService matchActionsService) {
        this.matchActionsService = matchActionsService;
    }

    @PutMapping(value = "/createMatch")
    @Operation(description = "Create new Match entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<String> createMatch(@RequestBody MatchDto matchDto) {
        return new ResponseEntity<>(matchActionsService.createMatch(matchDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/updateMatch")
    @Operation(description = "Update Match entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> updateMatch(@RequestBody MatchDto matchDto) {
        matchActionsService.updateMatch(matchDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deleteMatchById/{id}")
    @Operation(description = "Delete Match entry by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> deleteMatch(@PathVariable String id) {
        matchActionsService.deleteMatchById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/createMatchOdds")
    @Operation(description = "Create new MatchOdds entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<String> createMatchOdds(@RequestBody MatchOddsDto matchOddsDto) {
        return new ResponseEntity<>(matchActionsService.createMatchOdds(matchOddsDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/updateMatchOdds")
    @Operation(description = "Update MatchOdds entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> updateMatchOdds(@RequestBody MatchOddsDto matchOddsDto) {
        matchActionsService.updateMatchOdds(matchOddsDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deleteMatchOddsById/{id}")
    @Operation(description = "Delete MatchOdds entry by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<Void> deleteMatchOdds(@PathVariable String id) {
        matchActionsService.deleteMatchOddsById(id);
        return ResponseEntity.ok().build();
    }

}
