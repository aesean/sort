package com.aesean.sort;

import android.util.Log;

import java.util.Random;

/**
 * 索引排序
 * <p/>
 * 作者：XL.
 * 创建时间： 2015-07-15.
 * 问世间Bug为何物，直教阿猿生死相许。
 */
public class IndexSort {

    /**
     * 生成的随机数组大小
     */
    private int arraySize;
    /**
     * 生成的随机数组最大值
     */
    private int arrayMaxValue;
    private long mTime;

    public String getResult() {
        return mResult.toString();
    }

    private StringBuilder mResult;
    private int[] mRandomArray;

    public int[] getRandomArray() {
        return mRandomArray;
    }


    /**
     * 获取算法执行花费时间
     *
     * @return
     */
    public long getTime() {
        return mTime;
    }

    /**
     * 初始化IndexSort，默认数组大小：2000000，数据最大值：1600000
     */
    public IndexSort() {
        //int最大值2147483648
        this(2000000, 1600000);
    }

    /**
     * 初始化IndexSort
     *
     * @param arraySize     数组大小
     * @param arrayMaxValue 数组最大值
     */
    public IndexSort(int arraySize, int arrayMaxValue) {
        this.arraySize = arraySize;
        this.arrayMaxValue = arrayMaxValue;
        mResult = new StringBuilder();
    }

    /**
     * 获取数组大小
     *
     * @return
     */
    public int getArraySize() {
        return arraySize;
    }

    /**
     * 设置数组大小
     *
     * @param arraySize 数组大小
     */
    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    /**
     * 获取数组最大值
     *
     * @return 数组最大值
     */
    public int getArrayMaxValue() {
        return arrayMaxValue;
    }

    /**
     * 设置数组最大值
     *
     * @param arrayMaxValue 数组最大值
     */
    public void setArrayMaxValue(int arrayMaxValue) {
        this.arrayMaxValue = arrayMaxValue;
    }

    /**
     * 生成随机数组
     *
     * @return
     */
    public int[] getDatas() {
        mRandomArray = new int[arraySize];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < mRandomArray.length; i++) {
            mRandomArray[i] = random.nextInt(arrayMaxValue);
        }
        return mRandomArray;
    }

    /**
     * 排序
     */
    public void run() {
        run(getDatas(), arrayMaxValue);
    }

    /**
     * 排序（核心实现，如果只是为了看实现过程，只需要看本方法就可以）
     * 由于算法特殊，如果不知道排序数组中的最大值则需要动态改变数据大小，这里为了演示所以直接人为指定了最大值。
     *
     * @param datas    需要排序的数组
     * @param maxValue 需要排序的数组最大值
     * @return 排序结果
     */
    public int[] run(int[] datas, int maxValue) {
        //获取计算开始时间，非算法必须
        long t1 = System.currentTimeMillis();
        //创建临时有序数组，数组的下标表示当前数值大小，数组的值表示重复次数，0表示不存在
        int[] temp = new int[maxValue + 1];
        //遍历待排序数组
        for (int data : datas) {
            //将待排序数组中数据data插入到临时数组下标为data的数组中
            temp[data]++;
        }

        //创建结果数组，用于保存最终剔除无效数据的数组
        int[] result = new int[datas.length];
        int j = 0;
        //遍历临时有序数组，将有数据的保存到结果数组中
        for (int i = 0; i < temp.length; i++) {
            //将重复数据写入结果数组
            for (int k = 0; k < temp[i]; k++) {
                result[j] = i;
                j++;
            }
        }
        //获取计算结束时间，得出算法执行时间（complex的复制一定程度上增加了这个时间）
        mTime = System.currentTimeMillis() - t1;
        //后面的仅仅是为了输出数据，与算法无关，可删除。
        //打印数据量小于20的数据结果
        mResult = new StringBuilder();
        if (result.length < 101 && temp.length < 102) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("原始数据：");
            for (int i : datas)
                sb1.append(i).append(", ");
            Log.e("DEBUG", "原始数据：" + sb1);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("中间数据：");
            for (int i : temp)
                sb2.append(i).append(", ");
            Log.e("DEBUG", "中间数据：" + sb2);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("排序结果：");
            for (int i : result)
                sb3.append(i).append(", ");
            Log.e("DEBUG", "排序结果：" + sb3);
            mResult.append(sb1.toString()).append("\n").append(sb2.toString()).append("\n").append(sb3.toString()).append("\n");
        }
        mResult.append("待排序数组大小：").append(datas.length).append("\n");
        mResult.append("待排序数组中数据的范围：0~").append(maxValue).append("\n");
        mResult.append("计算用时：").append(mTime).append("毫秒").append("\n");
        Log.e("DEBUG", "待排序数组大小：" + datas.length);
        Log.e("DEBUG", "待排序数组中数据的范围：0~" + maxValue);
        Log.e("DEBUG", "计算用时：" + mTime + "毫秒");
        return result;
    }
}
