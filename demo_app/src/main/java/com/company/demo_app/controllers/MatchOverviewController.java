package com.company.demo_app.controllers;

import com.company.demo_app.annotations.LogMethod;
import com.company.demo_app.model.dtos.MatchDto;
import com.company.demo_app.model.dtos.MatchOddsDto;
import com.company.demo_app.model.errors.ApiError;
import com.company.demo_app.services.MatchOverviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/matches/overview", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchOverviewController {

    private final MatchOverviewService matchOverviewService;

    public MatchOverviewController(MatchOverviewService matchOverviewService) {
        this.matchOverviewService = matchOverviewService;
    }

    @GetMapping(value = "/getAllMatches")
    @Operation(description = "Get pageable list of matches", parameters = {
            @Parameter(name = "page", in = ParameterIn.QUERY, schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "size", in = ParameterIn.QUERY, schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "sort", in = ParameterIn.QUERY, schema = @Schema(implementation = String.class)),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MatchDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<PagedModel<MatchDto>> getAllMatches(@Parameter(hidden = true) @SortDefault("id") @PageableDefault(size = 20) final Pageable pageable) {
        return ResponseEntity.ok(matchOverviewService.getAllMatchesWithPagination(pageable));
    }

    @GetMapping(value = "/getMatchById/{id}")
    @Operation(description = "Get match by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<MatchDto> getMatchById(@PathVariable("id") final String id) {
        return ResponseEntity.ok(matchOverviewService.getMatchById(id));
    }

    @GetMapping(value = "/getMatchByIdWithOdds/{id}")
    @Operation(description = "Get match by id with odds")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<MatchDto> getMatchByIdWithOdds(@PathVariable("id") final String id) {
        return ResponseEntity.ok(matchOverviewService.getMatchByIdWithOdds(id));
    }

    @GetMapping(value = "/getAllOdds")
    @Operation(description = "Get pageable list of odds", parameters = {
            @Parameter(name = "page", in = ParameterIn.QUERY, schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "size", in = ParameterIn.QUERY, schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "sort", in = ParameterIn.QUERY, schema = @Schema(implementation = String.class)),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MatchOddsDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<PagedModel<MatchOddsDto>> getAllOdds(@Parameter(hidden = true) @SortDefault("id") @PageableDefault(size = 20) final Pageable pageable) {
        return ResponseEntity.ok(matchOverviewService.getAllOddsWithPagination(pageable));
    }

    @GetMapping(value = "/getOddsByMatchId/{id}")
    @Operation(description = "Get odds by match id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchOddsDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @LogMethod
    public ResponseEntity<List<MatchOddsDto>> getMatchOddsById(@PathVariable("id") final String id) {
        return ResponseEntity.ok(matchOverviewService.getMatchOddsById(id));
    }

}
