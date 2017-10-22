## 原始类型流
Java 8引入了三个原始类型特化流接口来装箱成本问题: IntStream 、 DoubleStream 和LongStream ,分别将流中的元素特化为 int 、 long 和 double ,从而避免了暗含的装箱成本  
#### 对象流转数值流(特化流)
将流转换为特化版本的常用方法是 mapToInt 、 mapToDouble 和 mapToLong 。这些方法和前面说的 map 方法的工作方式一样,只是它们返回的是一个特化流(数值流),而不是 Stream<T>  

#### 转换会对象流
要 把 原 始 流 转 换 成 一 般 流 ( 每 个 int 都 会 装 箱 成 一 个Integer ),可以使用 boxed 方法  

代码Stream5_6_1演示了这两种操作
#### 创建流
通过值,数组,文件,函数创建流,代码为Stream5_7
>Stream API提供了两个静态方法来从函数生成流: Stream.iterate 和 Stream.generate 。
这两个操作可以创建所谓的无限流:不像从固定集合创建的流那样有固定大小的流。由 iterate
和 generate 产生的流会用给定的函数按需创建值,因此可以无穷无尽地计算下去!一般来说,
应该使用 limit(n) 来对这种流加以限制,以避免打印无穷多个值。  

iterate产生的流是顺序的,无界的,因为值是按需计算的,可以永远计算下去,这是流和集合之间的一个关键区别
