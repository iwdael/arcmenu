# arcmenu  [![](https://jitpack.io/v/hacknife/arcmenu.svg)](https://jitpack.io/#hacknife/arcmenu)
ArcMenue是一个菜单控件，通过动画的效果展开更多的选项。[English](https://github.com/hacknife/arcmenu/blob/master/README_ENGLISH.md)
## 使用说明
第一个子控件是中央控件，其他控件通过点击来显示。 position的值与子控件在布局中添加的顺序相对应。
### 代码示例
```Java
        arcMenu= (ArcMenu) findViewById(R.id.arc_menu);
        arcMenu.setOnArcMenuItemClickListener(new ArcMenu.OnArcMenuItemClickListener() {
            @Override
            public void onClick(View item, int position) {

            }
        });
```
```Java
<com.hacknife.arcmenu.ArcMenu
        android:layout_marginRight="40dp"
        android:layout_marginBottom="40dp"
        android:id="@+id/arc_menu"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:position="right_bottom"
        app:raduis="200dp">

        <RelativeLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@mipmap/composer_button">

            <ImageView
                android:id="@+id/id_buttom"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerInParent="true"
                android:src="@mipmap/composer_icn_plus" />
        </RelativeLayout>

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@mipmap/composer_camera"
            android:tag="Camera" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@mipmap/composer_music"
            android:tag="Music" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@mipmap/composer_place"
            android:tag="Place" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@mipmap/composer_thought"
            android:tag="Thought" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@mipmap/composer_sleep"
            android:tag="Sleep" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@mipmap/composer_with"
            android:tag="With" />
    </com.hacknife.arcmenu.ArcMenu>
```
## 如何配置
将本仓库引入你的项目:
### Step 1. 添加JitPack仓库到Build文件
合并以下代码到项目根目录下的build.gradle文件的repositories尾。[点击查看详情](https://github.com/hacknife/CarouselBanner/blob/master/root_build.gradle.png)
```Java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### Step 2. 添加依赖   
合并以下代码到需要使用的application Module的dependencies尾。[点击查看详情](https://github.com/hacknife/CarouselBanner/blob/master/application_build.gradle.png)
```Java
	dependencies {
                ...
	        compile 'com.github.hacknife:arcmenu:v1.0.2'
	}
```
<br><br>
![Image text](https://github.com/hacknife/arcmenu/blob/master/arcmenu.gif)
<br><br><br>
## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/hacknife/CarouselBanner/blob/master/qq_group.png)