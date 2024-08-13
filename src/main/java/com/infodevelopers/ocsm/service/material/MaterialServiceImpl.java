package com.infodevelopers.ocsm.service.material;

import com.infodevelopers.ocsm.dto.materialDto.MaterialDto;
import com.infodevelopers.ocsm.entity.Course;
import com.infodevelopers.ocsm.entity.Material;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.repository.MaterialRepository;
import com.infodevelopers.ocsm.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {
    private final CourseRepository courseRepository;
    private final FileStorageService fileStorageService;
    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(CourseRepository courseRepository, FileStorageService fileStorageService, MaterialRepository materialRepository) {
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
        this.materialRepository = materialRepository;
    }

    @Override
    public MaterialDto create(MaterialDto materialDto) {
        Optional<Course> optionalCourse = courseRepository.findById(materialDto.getCourseId());
        String filepath = fileStorageService.storeFile(materialDto.getFile());
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            Material material = Material.builder()
                    .title(materialDto.getTitle())
                    .content(materialDto.getContent())
                    .materialPath(filepath)
                    .course(course)
                    .build();
            materialRepository.save(material);

            materialDto.setId(material.getId());
            materialDto.setMaterialPath(material.getMaterialPath());
            return materialDto;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        materialRepository.deleteById(id);
    }

    @Override
    public List<MaterialDto> findAll() {
        List<MaterialDto> materialDtoList = new ArrayList<>();

        materialRepository.findAll().stream().forEach(material -> {
            materialDtoList.add(MaterialDto.builder()
                    .id(material.getId())
                    .title(material.getTitle())
                    .content(material.getContent())
                    .materialPath(material.getMaterialPath())
                    .courseId(material.getCourse().getId())
                    .build());
        });
        return materialDtoList;
    }

    @Override
    public MaterialDto update(MaterialDto materialDto) {
        Optional<Material> existingMaterial = materialRepository.findById(materialDto.getId());
        Material material = existingMaterial.get();
        String filePath = fileStorageService.storeFile(materialDto.getFile());
        if(material!= null){
            material.setTitle(materialDto.getTitle());
            material.setContent(materialDto.getContent());
            material.setMaterialPath(filePath);
            Optional<Course> course = courseRepository.findById(materialDto.getCourseId());
            Course c = course.get();
            if (c != null){
                material.setCourse(c);
            }else {
                log.error("Course with id: {} not found ",materialDto.getCourseId());
            }
        }
        materialRepository.save(material);
        return materialDto;
    }

    @Override
    public List<MaterialDto> materialListByCourseId(Integer courseId) {
        List<MaterialDto> materialDtoList = new ArrayList<>();
        materialRepository.findByCourse_Id(courseId).stream().forEach(material -> {
            materialDtoList.add(MaterialDto.builder()
                    .id(material.getId())
                    .title(material.getTitle())
                    .content(material.getContent())
                    .materialPath(material.getMaterialPath())
                    .courseId(material.getCourse().getId())
                    .build());
        });
        return materialDtoList;
    }

    @Override
    public boolean findByMaterialId(Integer id) {
        Optional<Material> material = materialRepository.findById(id);

        if (material.isPresent()){
            return false;
        } else{
            return true;
        }

    }
}
