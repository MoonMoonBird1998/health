import com.yyn.utils.QiniuUtils;
import org.junit.Test;

public class TestQiniu {

    @Test
    public void testUpload(){

        QiniuUtils.upload2Qiniu("D:\\唧唧down\\Download\\视频文件\\12项目\\传智健康SSM项目\\day04 预约管理-套餐管理\\资源\\图片资源\\03a36073-a140-4942-9b9b-712cecb144901.jpg","test");
    }

    @Test
    public void testDelete(){

        QiniuUtils.deleteFileFromQiniu("test");
    }
}
