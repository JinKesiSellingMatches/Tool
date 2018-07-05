package data.common.query;

/*
 * Created by ChenShuai on 2017/5/4.
 */
public class HqlQueryParams extends QueryParams {
    public HqlQueryParams() {

    }

    public HqlQueryParams(Class<?> clazz) {
        this.clazz = clazz;
    }

    public HqlQueryParams(String hql) {
        this.query.append(hql);
    }
}
