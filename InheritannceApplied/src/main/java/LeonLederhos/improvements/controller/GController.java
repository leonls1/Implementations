package LeonLederhos.improvements.controller;



import LeonLederhos.improvements.model.entity.BasicEntity;
import LeonLederhos.improvements.service.imp.GService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Validated
public abstract class GController<T extends BasicEntity, ID, Rq, Rs, CollRs> {
    private final GService<T, ID, Rq, Rs, CollRs> service;

    public GController(GService<T, ID, Rq, Rs, CollRs> service) {
        this.service = service;
    }

    @Operation(
            summary = "create entity",
            description = "create the entity passed by parameter as a requestDto",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "entity created successfully"
            )
    )
    @PostMapping
    public ResponseEntity<?> create(@NonNull @RequestBody @Valid Rq requestDto) {
        service.create(requestDto);
        return new ResponseEntity<>("entity created successfully", HttpStatus.CREATED);
    }
    @Operation(
            summary = "find all entities",
            description = "find all entities without any filter",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with entity responses"
                    ), @ApiResponse(
                    responseCode = "204",
                    description = "no content available"
            )}
    )
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Rs> responses = service.findAllResponse();
        return validateListNotEmpty(responses);
    }

    @Operation(
            summary = "find not deleted",
            description = "find all entities mark as not deleted",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "a list with entity responses"
            ), @ApiResponse(
                    responseCode = "204",
                    description = "no content available"
            )}
    )
    @GetMapping("/not-deleted")
    public ResponseEntity<?> findAllNotDeleted() {
        List<Rs> responses = service.findAllResponseNotDeleted();
        return validateListNotEmpty(responses);
    }

    @Operation(
            summary = "find by id",
            description = "find the entity for the given id",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "an entity for that id in responseDto format"
            ), @ApiResponse(
                    responseCode = "404",
                    description = "a custom message for the entity indicating that cannot be found"
            )},
            parameters = @Parameter(name = "id", description = "entity id", required = true)

    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@NonNull @PathVariable ID id) {
        return new ResponseEntity<>(service.findResponseDtoById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "update entity",
            description = "find the entity for the given id and update it with the requestDto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "entity updated successfully"
                    ), @ApiResponse(
                    responseCode = "404",
                    description = "a custom message for the entity indicating that cannot be found"
            )},
            parameters = {
                    @Parameter(name = "id", description = "entity id", required = true),
                    @Parameter(name = "requestDto", description = "entity request", required = true)
            }

    )
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEntity(@NonNull @PathVariable ID id, @RequestBody @Valid Rq requestDto) {
        service.update(requestDto, id);
        return new ResponseEntity<>("entity updated successfully", HttpStatus.OK);
    }


    @Operation(
            summary = "delete entity",
            description = "delete the entity for the given id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "entity deleted successfully"
                    ), @ApiResponse(
                    responseCode = "404",
                    description = "a custom message for the entity indicating that cannot be found"
            )},
            parameters = @Parameter(name = "id", description = "entity id", required = true))


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntityById(@NonNull @PathVariable ID id) {
        service.deleteById(id);
        return new ResponseEntity<>("entity deleted successfully", HttpStatus.OK);
    }

    @Operation(
            summary = "soft delete",
            description = "mark the entity as deleted for logical delete",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "entity marked as deleted"
                    ), @ApiResponse(
                    responseCode = "404",
                    description = "a custom message for the entity indicating that cannot be found"
            )},
            parameters = @Parameter(name = "id", description = "entity id", required = true))
    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<?> softDeleteEntityById(@NonNull @PathVariable ID id) {
        service.softDeleteById(id);
        return new ResponseEntity<>("entity marked as deleted", HttpStatus.OK);
    }


    //----------------------------aid methods------------------------------------
    protected ResponseEntity<?> validateListNotEmpty(List<?> responseList) {
        if (!responseList.isEmpty()) {
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
