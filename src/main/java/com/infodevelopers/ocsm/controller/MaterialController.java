package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.materialDto.MaterialDto;
import com.infodevelopers.ocsm.service.FileStorageService;
import com.infodevelopers.ocsm.service.material.MaterialService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController extends BaseController{

    private final MaterialService materialService;
    private final FileStorageService fileStorageService;

    public MaterialController(MaterialService materialService, FileStorageService fileStorageService) {
        this.materialService = materialService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createMaterial(@ModelAttribute MaterialDto materialDto) throws Exception {
        MaterialDto dto = materialService.create(materialDto);

        if(dto != null){
            return new ResponseEntity<>(successResponse("Material Created Successfully",dto), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(failureResponse("Material Creation failed",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Resource resource = fileStorageService.loadFileAsResource(fileName);

            // Determine the file's content type
            String contentType = "application/octet-stream"; // Default if type cannot be determined

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/materialList")
    public ResponseEntity<GlobalApiResponse> allMaterial(){
       List<MaterialDto> materialDtos = materialService.findAll();

        if(materialDtos != null){
            return new ResponseEntity<>(successResponse("Material Created Successfully",materialDtos), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(failureResponse("Material Creation failed",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/materialList/{courseId}")
    public ResponseEntity<GlobalApiResponse> materialByCourseId(@PathVariable("courseId")Integer id){
        List<MaterialDto> materialDtos = materialService.materialListByCourseId(id);

        if(materialDtos != null){
            return new ResponseEntity<>(successResponse("Material Created Successfully",materialDtos), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(failureResponse("Material Creation failed",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMaterial/{materialId}")
    public ResponseEntity<GlobalApiResponse> deleteMaterial(@PathVariable("materialId") Integer id){
        materialService.deleteById(id);
        boolean flag = materialService.findByMaterialId(id);
        if (flag == true){
            return new ResponseEntity<>(successResponse("Material deleted successfully",id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Material still exists",id),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/updateMaterial")
    public ResponseEntity<GlobalApiResponse> updateMaterial(@ModelAttribute MaterialDto materialDto){
        MaterialDto dto = materialService.update(materialDto);
        if (dto != null){
            return new ResponseEntity<>(successResponse("Material updated successfully",dto),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Material update failed",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
