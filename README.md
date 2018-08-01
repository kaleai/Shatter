# Shatter

[![](https://jitpack.io/v/tianzhijiexian/Shatter.svg)](https://jitpack.io/#tianzhijiexian/Shatter)

[Shatter](https://github.com/tianzhijiexian/Shatter)是一个代替fragment来划分ui模块的库。它主要完成的工作是管理ui区块，并且能和activity保持完全相同的生命周期，没有任何学习成本。它对于单页面多ui模块的结构有着很好的支持，非常适合用来降低复杂activity的复杂度。但因为设计的关系，它的生命周期仅仅被activity触发的，所以不会有完整的生命周期的概念。

所有的监听工作都是通过shatterManager来实现的，这个类将会把activity的方法对应给shatter：

![](http://static.zybuluo.com/shark0017/yui6evs3qghmofoevdxripzo/image_1btm78fhn1inj2mbn8gnunm3a9.png)

*上图的方法均是一一对应的关系*

## 引入方式

1.添加JitPack仓库

```
repositories {
	maven {
		url "https://jitpack.io"
	}
}
```

2.添加依赖

> implementation 'com.github.tianzhijiexian:Shatter:[Latest release](https://github.com/tianzhijiexian/Shatter/releases)（<-click）'


## 配置方式

配置的方式有两种可选，第一种比较复杂，第二种较为简单。

### 1. 让shatter有监听activity全部生命周期的能力

在app的build.gradle中配置aspectj：

```gradle
apply plugin: 'com.android.application'

apply plugin: 'me.leolin.gradle-android-aspectj'
```

接着在baseActivity实现`IShatterActivity`，并复写你需要被shatter感知的生命周期（无需做任何处理，只需复写即可）,如：

```
private ShatterManager mShatterManager;

public ShatterManager getShatterManager() {
     if (mShatterManager == null) {
    	mShatterManager = new ShatterManager(this);
     }
     return mShatterManager;
}

@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}

@Override
public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
}

@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
}

// ...

```

### 2. 仅仅需要监听部分生命周期

在baseActivity中的onCreate()中写上如下语句：

```
private ShatterManager mShatterManager;

public ShatterManager getShatterManager() {
     if (mShatterManager == null) {
    	mShatterManager = new ShatterManager(this);
     }
     return mShatterManager;
}

@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventDispatchFragment.injectIfNeededIn(this);
}
```

这种方式下shatter可拥有如下生命周期：

- onRestoreInstanceState
- onStart
- onResume
- onPause
- onStop
- onDestroy
- onActivityResult
- onSaveInstanceState

## 使用

定义一个shatter：

```
public static class MyShatter extends Shatter {

    private TextView mTopTv;
    
    @Override
    protected int getLayoutResId() {
        return android.R.layout.my_shatter;
    }

    @Override
    public void bindViews(View rootView) {
        mTopTv = findViewById(R.id.top_tv);
    }

    @Override
    public void setViews() {
        View root = getRootView();
        root.setBackgroundResource(R.drawable.shatter_green_bg);
        
        TextView textView = mTopTv;
        textView.setGravity(Gravity.CENTER);
        textView.setText(R.string.test_text);
    }

}
```

在activity中添加这个shatter：

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    getShatterManager().add(R.id.root_container, new MyShatter());
}
```

在shatter中添加一个shatter（支持多重嵌套）：

```
public class MiddleShatter extends Shatter {

    @Override
    protected int getLayoutResId() {
        return R.layout.middle_shatter;
    }

    @Override
    public void bindViews(View rootView) {
    
        getShatterManager().add(R.id.inner_fl, new InnerShatter());
    }

}
```

## 额外说明

1. Shatter自身会产生事件，如果要和activity进行交互，那么可以通过activity给shatter设置listener的方式来做。

2. ShatterManager提供了`findShatterByTag()`和`findShatterByContainViewId()`，可以通过二者来查找shatter，方便解耦。

3. 如果你需要在viewPager中使用shatter，那么可以“选用”`shatterPagerAdapter`来做。

### 开发者
![](https://avatars3.githubusercontent.com/u/9552155?v=3&s=460)

Jack Tony: <developer_kale@foxmail.com>

### License

    Copyright 2016-2019 Jack Tony

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
