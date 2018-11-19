import com.morrle.model.entity.Attach;
import com.morrle.units.BeanUtils;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

public class TestEntity {


    @Test
    public void test(){

        Attach attach = new Attach();
        attach.setName("张三");
        attach.setPath("/aaa");
        attach.setCreated(123123L);
        attach.setId("1111");




    }

    @Test
    public void test2(){
        Attach attach = new Attach();
        attach.setName("张三");
        attach.setId("21");
        attach.setPath("/aaa");
        attach.setCreated(123123L);
        attach.setId("1111");
        attach.setType("ggg");
        attach.setStatus("Y");
        attach.setAuthorId("444");
        JsonObject jsonObject = BeanUtils.beanToJson(attach);
        System.out.println(jsonObject);

        Attach bean = BeanUtils.jsonToBean(jsonObject, Attach.class);
        System.out.println(bean);

    }
}
