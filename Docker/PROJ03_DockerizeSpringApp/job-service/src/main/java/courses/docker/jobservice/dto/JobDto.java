package courses.docker.jobservice.dto;

import courses.docker.jobservice.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(staticName = "create")
public class JobDto {
    private String id;
    private String description;
    private String company;
    private Set<String> skills;
    private Integer salary;
    private Boolean isRemote;

    public static Job toEntity(JobDto dto){
        Job entity = new Job();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
