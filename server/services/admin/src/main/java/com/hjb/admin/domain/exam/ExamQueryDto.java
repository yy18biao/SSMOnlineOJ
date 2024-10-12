package com.hjb.admin.domain.exam;

import com.hjb.core.domain.PageQueryDto;
import lombok.Data;

@Data
public class ExamQueryDto extends PageQueryDto {
    private String title;

    private String startTime;

    private String endTime;
}
