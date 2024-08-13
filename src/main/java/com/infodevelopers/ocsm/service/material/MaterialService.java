package com.infodevelopers.ocsm.service.material;

import com.infodevelopers.ocsm.dto.materialDto.MaterialDto;
import com.infodevelopers.ocsm.service.GenericService;

import java.util.List;

public interface MaterialService extends GenericService<MaterialDto> {

    List<MaterialDto> materialListByCourseId(Integer courseId);

    boolean findByMaterialId(Integer id);
}
