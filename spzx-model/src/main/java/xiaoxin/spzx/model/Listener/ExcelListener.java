package xiaoxin.spzx.model.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ExcelListener
 * Package: xiaoxin.spzx.model.Listener
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 9:31
 * @Version 1.0
 */

public class ExcelListener<T> extends AnalysisEventListener<T> {
    //可以通过实例获取该值
    private List<T> datas = new ArrayList<T>();

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        //每解析一行数据就会调用一次该方法
        //数据存储到list，供批量处理,或后续自己业务逻辑处理
        datas.add(t);
    }
    public List<T> getDatas() {
        return datas;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //excel解析完毕以后需要执行的代码
    }
}
