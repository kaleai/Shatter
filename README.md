# Shatter

## 引入方式

1.添加JitPack仓库

repositories {
	maven {
		url "https://jitpack.io"
	}
}

2.添加依赖

compile 'com.github.tianzhijiexian:Shatter:Latest release（<-click）'


## 配置方式

1.让shatter有监听activity全部生命周期的能力

需要在app的build.gradle中配置aspectj：

```
apply plugin: 'com.android.application'

apply plugin: 'me.leolin.gradle-android-aspectj'
```

并且在baseActivity实现`IShatterActivity`，并复写你需要被监听的生命周期方法（无需做任何处理，只需复写即可）,如：

```
@Override
public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
}

@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
}

```

2.仅仅需要监听部分重要的生命周期

在baseActivity中的onCreate()中写上如下语句：

```
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventDispatchFragment.injectIfNeededIn(this);
}
```

## 使用

在activity中添加：

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    getShatterManager().add(R.id.root_container, new MyShatter());
}
```

在shatter中添加（嵌套）：

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

Shatter会自身产生事件，如果要和activity进行交互，那么可以通过activity给shatter设置listener的方式来做。

ShatterManager提供了`findShatterByTag()`和`findShatterByContainViewId()`，可以通过二者来查找，方便解耦。



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
