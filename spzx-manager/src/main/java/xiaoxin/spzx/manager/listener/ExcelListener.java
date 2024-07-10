package xiaoxin.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import xiaoxin.spzx.manager.mapper.CategoryMapper;
import xiaoxin.spzx.model.entity.product.Category;
import xiaoxin.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ExcelListener
 * Package: xiaoxin.spzx.manager.listener
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 10:01
 * @Version 1.0
 */
//监听器
public class ExcelListener<T> extends AnalysisEventListener<T> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list，方便回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    //获取mapper对象
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    //每解析一行数据就会调用一次该方法
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        CategoryExcelVo data = (CategoryExcelVo) t;
        cachedDataList.add(data);
        //到达BATCH_COUNT了，需要去存储一次数据库,防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            //存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    private void saveData() {
        categoryMapper.batchInser(cachedDataList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //excel解析完毕以后需要执行的代码
        //这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }
}
