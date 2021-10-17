package com.sobev.nginxauthserver.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 邓力宾
 * @date 2021/3/23 10:48
 * @desc
 */
@Data
//@ApiModel("分页参数")
public class PageParam implements Serializable {
	
	private static final long serialVersionUID = -3014376969164237681L;
	
//	@ApiModelProperty?(value = "模糊匹配关键字")
	private String keyword;
	
//    @ApiModelProperty("页码")
    private Integer pageNum = 1;
    
//    @ApiModelProperty("每页大小")
    private Integer pageSize = 10;
    
//    @ApiModelProperty("查询开始日期")
    private Date beginDate;
    
//    @ApiModelProperty("查询结束日期")
    private Date endDate;
}
