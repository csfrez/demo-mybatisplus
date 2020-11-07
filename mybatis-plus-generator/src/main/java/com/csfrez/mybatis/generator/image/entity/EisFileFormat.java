package com.csfrez.mybatis.generator.image.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Frez
 * @since 2020-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EisFileFormat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件格式编码
     */
    private String formatType;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 文件类型：image图片 document文档 video视频 audio音频
     */
    private String fileType;

    /**
     * 最后修改时间
     */
    private LocalDateTime lastModifyTime;


}
