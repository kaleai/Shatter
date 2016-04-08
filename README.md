# UiBlock  
[![](https://jitpack.io/v/tianzhijiexian/UIBlock.svg)](https://jitpack.io/#tianzhijiexian/UIBlock)

代替fragment的轻量级解耦Activity的类  

之前用fragment来降低activity的复杂度，但因此带来的各种奇葩问题让我们头疼。因此，我利用UIBlock实现了类似的功能，但复杂度远远降低。其本质上就是自定义view的实现。   

注意：UiBlock的所有生命周期和activity完全保持一致  

## 添加依赖  
在项目外层的build.gradle中添加JitPack仓库：

```  
repositories {
	maven {
		url "https://jitpack.io"
	}
}
```    
在用到的项目中添加依赖：  
>	compile 'com.github.tianzhijiexian:UiBlock:[Latest release](https://github.com/tianzhijiexian/UIBlock/releases)'

在用到的项目中添加：
```
android.libraryVariants.all { variant ->
    LibraryPlugin plugin = project.plugins.getPlugin(LibraryPlugin)
    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.5",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", plugin.project.android.bootClasspath.join(
                File.pathSeparator)]

        MessageHandler handler = new MessageHandler(true);
        new Main().run(args, handler)
    }
}
```

## 准备工作  
在项目中的BaseActivity(如果没有请自行建立)，让它实现`UIBlockActivity`接口：  

```JAVA
public class BaseActivity extends AppCompatActivity implements UIBlockActivity {

    private UiBlockManager mUiBlockManager;

    @Override
    public UiBlockManager getUiBlockManager() {
        if (mUiBlockManager == null) {
            mUiBlockManager = new UiBlockManager(this);
        }
        return mUiBlockManager;
    }

		// 下方均为activity的默认实现

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
```     
在这里写出activity默认的生命周期和`getUiBlockManager()`方法。这里写出默认的生命周期方法的目的是给UiBlock的生命周期做钩子。

## 使用情形  
**1. 简单划分UI逻辑**  

![](./images/demo01.png)  

这里的ui界面可以明显看出是由上下两个部分组成的，如果我认为下方的操作很复杂，整个界面的activity很臃肿。利用UiBlock就可以把下半部分的逻辑独立出来，也不用写一个xml布局文件。

简单例子：
```JAVA
public class TextUIBlock extends UiBlock{

    @Override
    public int getLayoutResId() {
        return R.layout.demo_uiblock;
    }

    TextView text;

    @Override
    protected void bindViews() {
        text = getView(R.id.tv);
    }

    @Override
    protected void setViews() {
        mTopTv.setText("Share");
    }
}
```   
接着，在activity通过`getUiBlockManager().add(R.id.share_linearlayout, new TextUIBlock())`把UiBlock绑定到activity上。  

**2. 复用UI区块**  

![](./images/demo02.png)

复用UI是很常见的需求，我的建议是：多复用UI组件，而不是复用activity。因为如果activity被多次复用，可能会因为后面设计师的界面分化，造成维护的难度。   

UiBlock可以用来做ui区块的复用：  
1. 建立一个要复用layout文件   
2. 建立对应的UiBlock  
3. 通过`include`将layout文件放入activity的xml中  
4. 调用UiBlockManager的add方法进行挂载(id就是include标签的id)  

**3. 嵌套使用UiBlock**  

![](./images/demo03.png)

之前`豪哥`有过这样的需求，activity中套fragment，这个fragment中又套了一个fragment，这种嵌套的问题令人头疼，UiBlock可以简单地解决这个问题。  

主要方法：  
1. 建立两个UiBlock  
2. 在activity中调用UiBlockManager的add方法挂载外层的UiBlock  
3. 在外层的UiBlock中调用UiBlockManager的add方法挂载内层的UiBlock  

### 开发者
![](https://avatars3.githubusercontent.com/u/9552155?v=3&s=460)

Jack Tony: <developer_kale@foxmail.com>  


### License

    Copyright 2016 Jack Tony

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
