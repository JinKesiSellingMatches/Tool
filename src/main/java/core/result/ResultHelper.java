package core.result;

/**
 * @author bin
 *
 */
public class ResultHelper {

    /**
     * 成功
     */
    public static Integer SUCCESS = 1;

    /**
     * 失败
     */
    public static Integer ERROR = 5;

    /**
     * 系统异常（CSRF、UnCatchException）
     */
    public static String EXCEPTION = "100";

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 结果集
     */
    private Object data;

    // 增加以下几个构造函数，以便更好地调用
    public ResultHelper() {

        this.code = this.SUCCESS;
    }

    public ResultHelper(Integer code) {

        this.code = code;
    }

    public ResultHelper(Integer code, Object data) {

        this.code = code;
        this.data = data;
    }


    public Object getData() {

        return data;
    }

    public void setData(Object data) {

        this.data = data;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
