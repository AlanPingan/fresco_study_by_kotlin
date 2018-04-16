package com.project.alan.frescolearningbykotlin;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FrescoUseActivity extends AppCompatActivity {

    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        String imgUrl = "http://www.16sucai.com/uploadfile/2013/0619/20130619104606252.jpg";
        simpleDraweeView = findViewById(R.id.main_simple_draweediew);
        simpleDraweeView.setController(getController(simpleDraweeView.getController(),getRequest(imgUrl)));
        simpleDraweeView.setHierarchy(getHierarchy());
    }

    /**
     * ImageRequest 主要负责图片相关的获取
     *
     * @param imgUrl
     * @return
     */
    private ImageRequest getRequest(String imgUrl) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgUrl))
                //图片加载后的处理 IterativeBoxBlurPostProcessor(高斯模糊)
                .setPostprocessor(new IterativeBoxBlurPostProcessor(5))
                .build();
        return request;
    }

    private DraweeController getController(DraweeController oldController, ImageRequest request) {
        return Fresco.newDraweeControllerBuilder()
                //设置图片加载的request
                .setImageRequest(request)
                .setOldController(oldController)
                .build();
    }

    /**
     * hierarchy是控制层次结构的的
     * @return
     */
    private GenericDraweeHierarchy getHierarchy() {
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                //设置view的圆角属性
                .setRoundingParams(new RoundingParams().setRoundAsCircle(true)
                        //设置图片的外面的border，颜色和宽度
                        .setBorder(Color.GREEN, 7))
                //设置图片还没有加载出来的占位图片
                .setPlaceholderImage(getResources().getDrawable(R.mipmap.ic_launcher))
                .setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)))
                .build();
        return hierarchy;
    }
}
