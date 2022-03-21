package commons;

import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;

public class Result<T> implements Serializable{

    private static final long serialVersionUID = -105755889014186227L;
    /**
     * 状态码
     */
    private String code;

    /**
     * 获取状态。
     * @return 状态
     */
    public String getCode() {
        return code;
    }

    /**
     * 状态信息,错误描述.
     */
    private String message;

    /**
     * 获取消息内容。
     * @return 消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 数据.
     */
    private T data;

    /**
     * 前端请求的requestFlag 参数
     */
    private String requestFlag;

    /**
     * 获取数据内容。
     * @return 数据
     */
    public T getData() {
        return data;
    }

    public Result() {
    }

    private Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(String message) {
        this.message = message;
    }

    public Result createResultInfo(T data,String message,String status) {
        this.data = data;
        this.message = message;
        this.code = status;
        return this;
    }

    /**
     * 创建一个带有<b>状态</b>、<b>消息</b>、<b>数据总条数</b>和<b>数据</b>的结果对象.
     * @param status 状态
     * @param message 消息内容
     * @param data 数据
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Status status, String message, T data) {
        return new Result<T>(status.getCode(), message, data);
    }

    /**
     * 创建一个带有<b>状态</b>、<b>消息</b>和<b>数据</b>的结果对象.
     * @param status 状态
     * @param message 消息内容
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Status status, String message) {
        return new Result<T>(status.getCode(), message);
    }

    /**
     * 创建一个带有<b>状态</b>和<b>数据</b>的结果对象.
     * @param status 状态
     * @param data 数据
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Status status, T data) {
        return new Result<T>(status.getCode(), status.getReason(), data);
    }

    /**
     * 创建一个带有<b>状态</b>的结果对象.
     * @param status 状态
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Status status) {
        return new Result<T>(status.getCode(), status.getReason());
    }

    public static <T> Result<T> buildResultSuccess(){
        return new Result<T>(Status.OK.getCode(), Status.OK.reason);
    }

    public static <T> Result<T> buildResultSuccess(T data){
        return new Result<T>(Status.OK.getCode(), Status.OK.reason,data);
    }

    public static <T> Result<T> buildResultSuccess(String message){
        return new Result<T>(Status.OK.getCode(),message);
    }

    public static <T> Result<T> buildResultSuccess(String message,T data){
        return new Result<T>(Status.OK.getCode(),message,data);
    }

    public static <T> Result<T> buildResultError(){
        return new Result<T>(Status.INTERNAL_SERVER_ERROR.getCode(), Status.INTERNAL_SERVER_ERROR.reason);
    }

    public static <T> Result<T> buildResultError(T data){
        return new Result<T>(Status.INTERNAL_SERVER_ERROR.getCode(), Status.INTERNAL_SERVER_ERROR.reason,data);
    }

    public static <T> Result<T> buildResultError(String message){
        return new Result<T>(Status.INTERNAL_SERVER_ERROR.getCode(),message);
    }

    public static <T> Result<T> buildResultError(String message,T data){
        return new Result<T>(Status.INTERNAL_SERVER_ERROR.getCode(),message,data);
    }

    public static <T> Result<T> buildResultOther(){
        return new Result<T>(Status.NO.getCode(), Status.NO.reason);
    }

    public static <T> Result<T> buildResultOther(String message){
        return new Result<T>(Status.NO.getCode(),message);
    }

    public static <T> Result<T> buildResultOther(T data){
        return new Result<T>(Status.NO.getCode(), Status.NO.reason,data);
    }

    public static <T> Result<T> buildResultOther(String message,T data){
        return new Result<T>(Status.NO.getCode(),message,data);
    }
    public static <T> Result<T> buildBusinessResultError(String message){
        return new Result<T>(Status.NO.getCode(), message);
    }
    /**
     *
     * <p> Title: 状态枚举</p>
     *
     * <p> Description: 用于Result构建时，规范状态值范围</p>
     *
     * <p> Copyright: Copyright (c) 2017 by unknown </p>
     *
     * <p> Company: yu feng </p>
     *
     * @author: unknown
     * @version: 1.0
     * @date: 2017年11月28日 下午14:05:27
     *
     */
    public enum Status {

        /**
         * 状态
         */
        OK("200", "正确"),
        NO("318", "业务错误"),
        BUSINESS_TIPS("325","返回业务判断提示"),
        REPEAT_REQUEST("319","重复请求"),
        BAD_REQUEST("400", "错误的请求"),
        UNAUTHORIZED("401", "禁止访问"),
        NO_PERMISSION("403","没有权限"),
        NOT_FOUND("404", "没有可用的数据"),
        INTERNAL_SERVER_ERROR("500", "服务器遇到了一个未曾预料的状况"),
        SERVICE_UNAVAILABLE("503", "服务器当前无法处理请求");

        /**
         * 状态码,长度固定为6位的字符串.
         */
        private String code;

        /**
         * 错误信息.
         */
        private String reason;

        Status(final String code, final String reason) {
            this.code = code;
            this.reason = reason;
        }

        public String getCode() {
            return code;
        }

        public String getReason() {
            return reason;
        }

        @Override
        public String toString() {
            return code + ": " + reason;
        }

        public static Status getStatusByCode(String code) {
            for (Status status : values()) {
                if (StringUtils.equals(status.code, code)) {
                    return status;
                }
            }
            return Status.OK;
        }
    }

}
