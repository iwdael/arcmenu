# arcmenu[![](https://jitpack.io/v/aliletter/arcmenu.svg)](https://jitpack.io/#aliletter/arcmenu)
ArcMenue is a menu control that expands the options by animating the effect.[中文文档](https://github.com/aliletter/arcmenu/blob/master/README_CHINESE.md)
## Instrcution
The first child control is the central control, and the other controls appear by clicking on it.The value of position corresponds to the order in which subspaces are added in the layout.
### Code Sample
```Java
        arcMenu= (ArcMenu) findViewById(R.id.arc_menu);
        arcMenu.setOnArcMenuItemClickListener(new ArcMenu.OnArcMenuItemClickListener() {
            @Override
            public void onClick(View item, int position) {

            }
        });
```
```Java
<com.aliletter.arcmenu.ArcMenu
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
    </com.aliletter.arcmenu.ArcMenu>
```
## How to
To get a Git project into your build:
### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories.   [click here for details](https://github.com/aliletter/CarouselBanner/blob/master/root_build.gradle.png)
 ```Java
 	allprojects {
 		repositories {
 			...
 			maven { url 'https://jitpack.io' }
 		}
 	}
 ```
### Step 2. Add the dependency
Add it in your application module build.gradle at the end of dependencies where you want to use.[click here for details](https://github.com/aliletter/CarouselBanner/blob/master/application_build.gradle.png)
 ```Java
 	dependencies {
                 ...
 	        compile 'com.github.aliletter:arcmenu:v1.0.1'
 	}
 ```
<br><br>
![Image text](https://github.com/aliletter/arcmenu/blob/master/arcmenu.gif)
<br><br><br>
## Thank you for your browsing
If you have any questions, please join the QQ group. I will do my best to answer it for you. Welcome to star and fork this repository, alse follow me.
<br>
![Image Text](https://github.com/aliletter/CarouselBanner/blob/master/qq_group.png)