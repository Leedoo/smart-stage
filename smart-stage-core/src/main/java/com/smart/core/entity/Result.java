package com.smart.core.entity;

import com.smart.core.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.beans.Transient;

@ApiModel(description = "响应结果")
@Data
@Accessors(chain = true)
public class Result<T> {

	@ApiModelProperty("结果体")
	protected T data;

	@ApiModelProperty("状态码")
	protected String code;

	@ApiModelProperty("消息")
	protected String message;

	private Result() {
		super();
	}

	public static <T> Result<T> create() {
		return new Result<>();
	}

	public static <T> Result<T> create(String code) {
		Result<T> r = create();
		return r.setCode(code);
	}

	public static <T> Result<T> create(String code, String message) {
		Result<T> r = create(code);
		return r.setMessage(message);
	}

	public static <T> Result<T> success() {
		return create(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
	}

	public static <T> Result<T> success(T data) {
		Result<T> r = success();
		r.setData(data);
		return r;
	}

	public static <T> Result<T> success(T data, String message) {
		Result<T> r = success(data);
		r.setMessage(message);
		return r;
	}

	@Transient
	public boolean isSuccess() {
		return ResultEnum.SUCCESS.getCode().equals(code);
	}

	@Override
	public String toString() {
		return "Result [data=" + data + ", code=" + code + ", message=" + message + "]";
	}
}