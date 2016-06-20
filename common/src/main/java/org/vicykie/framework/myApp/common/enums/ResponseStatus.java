package org.vicykie.framework.myApp.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.vicykie.framework.myApp.common.serializer.ResponseStatusDeserializer;
import org.vicykie.framework.myApp.common.serializer.ResponseStatusSerializer;
import org.vicykie.framework.myApp.common.serializer.helper.EnumFinder;
import org.vicykie.framework.myApp.common.serializer.helper.EnumKeyGetter;

/**
 * Created by vicykie on 2016/6/7.
 */
@JsonSerialize(using = ResponseStatusSerializer.class)
@JsonDeserialize(using = ResponseStatusDeserializer.class)
public enum ResponseStatus {
    /**
     *
     */
    SUC_GET_DATA("GT001"),
    /**
     *
     */
    SUC_UPDATE("UP001"),
    /**
     *
     */
    SUC_DELETE("DEL001"),
    /**
     *
     */
    SUC_LOGIN("IN001"),
    /**
     * 登出成功
     */
    SUC_LOGOUT("OUT001"),
    /**
     * 获取数据促我
     */
    ERR_GET_DATA("GT000"),
    /**
     * 更新错误
     */
    ERR_UPDATE("UP000"),
    /**
     * 删除错误
     */
    ERR_DELETE("DEL000"),
    /**
     * 参数类型错误
     */
    ERR_PARA_TYPE_ERR("PARA000"),
    /**
     * 缺少参数
     */
    ERR_PARA_LACK("PARA-1"),
    /**
     * 参数验证失败
     */
    ERR_PARA_INVALID("PARA-2"),
    /**
     *
     */
    UNKOWN("UN-1"),;
    private String code;

    ResponseStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    static final EnumFinder<ResponseStatus, String> codeFinder = new
            EnumFinder<>(ResponseStatus.class, new EnumKeyGetter<ResponseStatus, String>() {
        @Override
        public String getKey(ResponseStatus enumValue) {
            return enumValue.code;
        }
    });


    public static ResponseStatus getResponseStatusByCode(String code, ResponseStatus defaultValue) {
        return codeFinder.find(code, defaultValue);
    }

}
