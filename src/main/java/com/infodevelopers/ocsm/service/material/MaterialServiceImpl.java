package com.infodevelopers.ocsm.service.material;

import com.infodevelopers.ocsm.dto.materialDto.MaterialDto;
import com.infodevelopers.ocsm.entity.Course;
import com.infodevelopers.ocsm.entity.Material;
import com.infodevelopers.ocsm.repository.CourseRepository;
import com.infodevelopers.ocsm.repository.MaterialRepository;
import com.infodevelopers.ocsm.service.FileStorageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    }

    @Override
    public List<MaterialDto> findAll() {
        return null;
    }

    @Override
    public MaterialDto update(MaterialDto materialDto) {
        return null;
    }
}
