package wsjk;

/**
 * @program: selfplay
 * @description: 卫生监督查询结果
 * @author: zx
 * @create: 2018-07-30 14:53
 **/
//@Data(staticConstructor = "PersonFactory")
//@Accessors(chain=true) //采用链式  相当于直接返回
public class WsjdSearchRes {
    private String code;
    private String message;
    protected Object data;

    @Override
    public String toString() {
        return "WsjdSearchRes{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
