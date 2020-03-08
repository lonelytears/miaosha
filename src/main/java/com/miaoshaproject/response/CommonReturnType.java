package com.miaoshaproject.response;

public class CommonReturnType {
    private String status;
    private Object data;
    private Integer code;

    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success", 200);
    }
    public static CommonReturnType create(Object result, String status, Integer code) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        type.setCode(code);
        return type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
