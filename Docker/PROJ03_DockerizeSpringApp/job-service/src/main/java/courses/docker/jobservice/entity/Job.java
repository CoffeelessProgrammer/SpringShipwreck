package courses.docker.jobservice.entity;

import courses.docker.jobservice.dto.JobDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Document
public class Job {

    @Id
    private String id;
    private String description;
    private String company;
    private Set<String> skills;
    private Integer salary;
    private Boolean isRemote;

    public static JobDto toDto(Job entity) {
        JobDto dto = new JobDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
