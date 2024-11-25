# 实验二 Android界面布局实验
包含以下实验：
* 线性布局
* 表格布局
* 约束布局1——计算器
* 约束布局2——规定布局界面
## 一 线性布局 
线性布局是按照水平或者垂直方向线性分布的一个布局，设置orientation即可修改水平或垂直。
### 1、创建一个LinearLayoutActivity，同时创建一个布局activity_linear_layout.xml
### 2、在LinearLayoutActivity的onCreate方法中，通过setContentView，将activity_linear_layout.xml绑定到LinearLayoutActivity中
```
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
    }
```
### 3、编写activity_linear_layout.xml
#### （1）最外层垂直线性布局
对于题目要求的布局，可以采用多层LinearLayout进行编写，最外层是垂直布局的线性布局，对每一行进行垂直布局，而每一行是一个水平布局的线性布局。
在activity_linear_layout.xml中，最外层是一个`android:orientation="vertical"`表明是一个垂直布局的线性布局，`android:layout_width="match_parent"`和`android:layout_height="match_parent"`表明了最外层的线性布局宽高都与父布局相同。
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:fitsSystemWindows="true"
    tools:context=".LinearLayoutActivity">
```
#### （2）内部水平线性布局
而在里面，共有4个线性布局，`android:orientation="horizontal"`表明是一个水平布局的线性布局，不同的是`android:layout_height="wrap_content"`，使得其高度根据内容而改变其高度。
```
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
```
线性布局内，设置了TextView，`android:layout_width="0dp"`和`android:layout_weight="1"`表示他们的宽度自适应平均分配，并且设置了一系列margin进行美观设计，`android:text=" “`设置了TextView中的文本，`android:textColor="@color/white"`设置了TextView中文本颜色，`android:background="@color/black"`设置了TextView的背景颜色，`android:textSize="12sp"`设置了文本大小，`android:gravity="center"`设置了文本在TextView的居中位置。
```
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:layout_marginTop="1dp"
            android:layout_marginLeft="3dp"
            android:text="0ne,One"
            android:textColor="@color/white"
            android:background="@color/black"
            android:textSize="12sp"
            android:gravity="center"/>
```
### 结果
![image](images\1.png)

## 二 表格布局
表格布局是按照表格来进行布局，通过TableRow来放入不同行、列，通过stretchColumns等参数，进行布局。
### 1、创建一个TableLayoutActivity，同时创建一个布局activity_table_layout.xml
### 2、在TableLayoutActivity的onCreate方法中，通过setContentView，将activity_table_layout.xml绑定到TableLayoutActivity中
```
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
    }
```
### 3、编写activity_table_layout.xml
#### （1）表格布局
在TableLayout中设置了黑色的背景颜色，`android:stretchColumns="1"`表示在TableRow中拉伸第二列（列index从0开始）
```
<TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black"
    android:stretchColumns="1"
    tools:context=".TableLayoutActivity">
```
### 4、TableRow设计
在TableRow中有2个TextView，其中第二个TextView由于前面设置的StretchColumns会进行拉伸，效果如图。并且设置了`android:gravity="right"`使得文本靠右
![image](images\2.png)
```
    <TableRow>
        <TextView
            android:text="    Open..."
            android:textColor="@color/white"/>

        <TextView
            android:gravity="right"
            android:text="Ctrl+O"
            android:textColor="@color/white" />
    </TableRow>
```
### 5、分割线
通过View并且设置高度为1dp，宽度为"match_parent"，背景颜色为白色，来设计一条白色的分割线
```
    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="@color/white"/>
```

### 结果
![image](images\3.png)

## 三 约束布局1——计算器
约束布局，通过对每个部件设计约束，"layout_constraint..."如"app:layout_constraintEnd_toEndOf"、"app:layout_constraintStart_toStartOf"等，进行布局。
### 1、创建一个CalculatorActivity，同时创建一个布局activity_calculator.xml
### 2、在CalculatorActivity的onCreate方法中，通过setContentView，将activity_calculator.xml绑定到CalculatorActivity中
这里我们先只在onCreate中绑定对应的布局，稍后再对计算器的逻辑进行编写。
```
setContentView(R.layout.activity_calculator);
```
### 3、编写activity_calculator.xml
#### (1)先给出一个约束布局
```
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".CalculatorActivity">
```
#### (2)设计包括 Input标签，计算器输入输出显示栏，数字及运算按键
其中Input标签通过TextView实现，定义了与左边与父布局的左边和顶部与父布局的顶部的约束，还有底部与输入输出显示栏tv_result顶部的约束
```
    <TextView
        android:id="@+id/inputLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="70dp"
        android:text="Input"
        android:textColor="#b8babc"
        android:textSize="40sp"
        app:layout_constraintBottom_toTopOf="@id/tv_result"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
输入输出栏通过TextView实现，定义了左侧和右侧对父布局的左侧和右侧的约束，还有顶部与Input标签的底部的约束
```
    <TextView
        android:id="@+id/tv_result"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:background="#CDBE70"
        android:gravity="right"
        android:hint="0.0"
        android:textSize="45sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/inputLabel" />
```
而数字及运算按键通过Button实现，他们之间需要定义各自之间的约束，靠上面的7,8,9,÷需要与输入输出栏设计顶部与底部的约束，靠左侧和右侧的要分别设置与父布局左侧和父布局右侧的约束。下面是7的举例，定义了左侧与父布局左侧的约束、右侧与8的左侧的约束、顶部与输入输出栏的底部的约束
```
    <Button
        android:id="@+id/btn_7"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="12dp"
        android:layout_marginTop="76dp"
        android:background="#cfcfcf"
        android:text="7"
        android:textColor="@color/black"
        android:textSize="20sp"
        android:textStyle="bold"
        app:layout_constraintHorizontal_weight="1"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@id/btn_8"
        app:layout_constraintTop_toBottomOf="@id/tv_result" />
```
#### (3)完善计算器功能
对每个按键都设置一个监听器setOnClickListener，
```
findViewById(R.id.btn_0).setOnClickListener(this);
```
然后实现onClick方法，在其中对点击的按钮进行比对，若是
btn_equals（=按钮）：
调用 calculate() 执行运算
```
private double calculate(){
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "x":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }
```
更新运算结果，调用 refreshText() 更新显示。并且调用 refreshOperate() 对操作符、firstNum、secondNum、result进行刷新
```
private void refreshOperate(String new_result){
        result = new_result;
        firstNum = new_result;
        secondNum = "";
        operator = "";
    }
```
```
private void refreshText(String text){
        showText = text;
        tv_result.setText(showText);
    }
```

运算符按钮（如+、-、x、/）：
将运算符存储到变量 operator 中
更新显示文本 showText，以包含当前运算符

其他按钮（数字或小数点）：
判断当前是否输入了运算符，用于区分输入的数字是第一个操作数还是第二个操作数
如果结果已存在且没有输入新运算符，调用 clear() 重置状态
```
private void clear(){
        refreshOperate("");
        refreshText("");
    }
```
拼接输入内容更新到 firstNum 或 secondNum
更新显示文本，避免前导 "0.0" 的影响

```
@Override
    public void onClick(View view) {
        String inputText;
        inputText = ((TextView)view).getText().toString();
        if(view.getId() == R.id.btn_equals){
            double cal_result = calculate();
            refreshOperate(String.valueOf(cal_result));
            refreshText(showText + "=" + result);
        }else if(view.getId() == R.id.btn_add){
            operator = inputText;
            refreshText(showText + operator);
        }else if(view.getId() == R.id.btn_sub){
            operator = inputText;
            refreshText(showText + operator);
        }else if(view.getId() == R.id.btn_mul){
            operator = inputText;
            refreshText(showText + operator);
        }else if(view.getId() == R.id.btn_div){
            operator = inputText;
            refreshText(showText + operator);
        }else{
            if(result.length() > 0 && operator.equals("")){
                clear();
            }
            if(operator.equals("")){
                firstNum = firstNum + inputText;
            }else{
                secondNum = secondNum + inputText;
            }
            if(showText.equals("0.0") && !inputText.equals(".")){
                refreshText(inputText);
            }else{
                refreshText(showText + inputText);
            }

        }
    }
```

### 结果
![image](images\4.png)

## 四 约束布局2——规定布局界面
### 1、创建一个ConstraintLayoutActivity，同时创建一个布局activity_constraint_layout.xml
### 2、在ConstraintLayoutActivity的onCreate方法中，通过setContentView，将activity_constraint_layout.xml绑定到ConstraintLayoutActivity中
```
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
    }
```
